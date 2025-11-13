plugins {
    id("com.falsepattern.fpgradle-mc") version "3.3.0"
}

group = "com.example"

minecraft_fp {
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
    add("deploader", "com.falsepattern:falsepatternlib-mc1.7.10:1.10.1:deploader")
}

// endregion

// region DepLoader Stub

dependencies {
    // again, GTNH buildscript has this same thing available
    // 1.10.1-hotfix1 because i made a typo. Once fplib 1.10.2 and later is out, you can just use the regular version and drop the hotfix suffix
    shadowImplementation("com.falsepattern:falsepatternlib-mc1.7.10:1.10.1-hotfix1:deploader_stub")
}

// endregion
