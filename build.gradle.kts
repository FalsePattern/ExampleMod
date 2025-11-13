plugins {
    id("com.falsepattern.fpgradle-mc") version "3.3.0"
}

group = "com.example"

minecraft_fp {
    java {
        compatibility = jvmDowngrader
    }
    mod {
        modid   = "examplemod"
        name    = "Example Mod"
        rootPkg = "$group.mymod"
    }
    tokens {
        tokenClass = "Tags"
    }
    //Make sure you have shadow relocation enabled
    //GTNH buildscript has a way to enable this too
    shadow {
        relocate = true
    }
    core {
        coreModClass = "core.CoreLoadingPlugin"
    }
}

val dgd = jarInJar_fp("downgraded") {
    this.artifactVersion = "1.0.0"
    this.artifactName = "dgd"
    this.javaCompatibility = jvmDowngrader
    this.jvmDowngraderShade = projectIsLgpl21PlusCompatible
    this.jvmDowngraderShadePackage = minecraft_fp.mod.rootPkg.map { "$it.dgd" }
    this.dependsOnMain = false
    this.dependsOnMinecraft = false
}

val udgd = jarInJar_fp("undowngraded") {
    this.artifactVersion = "1.0.0"
    this.artifactName = "udgd"
    this.javaCompatibility = modern
    this.dependsOnMain = false
    this.dependsOnMinecraft = false
}


// FalsePattern maven
repositories {
    maven {
        name = "mavenpattern"
        setUrl("https://mvn.falsepattern.com/releases")
    }
}

// region DepLoader JarInJar

//Evil black magic to embed a jar from a maven repo, and rename it to fplib_deploader.jar

val deploaderConfig = configurations.register("deploader")

tasks.processResources {
    from(deploaderConfig) {
        rename { "fplib_deploader.jar" }
    }
}

dependencies {
    add("deploader", "com.falsepattern:falsepatternlib-mc1.7.10:1.10.5:deploader")
}

// endregion

// region DepLoader Stub

dependencies {
    // again, GTNH buildscript has this same thing available
    shadowImplementation("com.falsepattern:falsepatternlib-mc1.7.10:1.10.5:deploader_stub")

    //Depend on the downgraded code for compilation
    //If the JarInJar'd library is jvmdowngraded, the main codebase also needs to use jvmdowngrader or modern
    //If the JarInJar library is legacy, it's safe to have the main codebase also as legacy
    compileOnly(dgd.output)
}

// endregion
