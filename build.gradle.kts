plugins {
    id("fpgradle-minecraft") version("0.6.0")
}

group = "com.example"

minecraft_fp {
    // optional
    // If you want to use modern java
//    java {
//        compatibility = legacy //Convention
//        version       = JavaVersion.VERSION_XYZ //Convention, determined by compatibility
//    }

    // REQUIRED
    // Basic information about the mod
    mod {
        modid   = "examplemod"   //REQUIRED
        name    = "Example Mod"  //REQUIRED
//        version = "autogenerated from git" //Convention
        rootPkg = "$group.mymod" //REQUIRED
    }

    // optional
    // for runClient/runServer
//    run {
//        username = "Developer" //Convention, client only
//        userUUID = "00000000-0000-4000-0000-000000000000" //optional, client only
//    }

    // optional
    // For generating an -api jar
//    api {
//        packages          = listOf() //Convention
//        packagesNoRecurse = listOf() //Convention
//    }

    // optional
//    mixin {
//        pkg          = "mixins"      //optional
//        pluginClass  = "MixinPlugin" //optional, requires pkg set
//        debug        = false         //Convention
//        hasMixinDeps = false         //Convention
//    }

    // optional
//    core {
//        coreModClass                   = "Core"      //optional
//        accessTransformerFile          = "le_at.cfg" //optional
//        containsMixinsAndOrCoreModOnly = false       //Convention
//    }

    // optional
    // Extra settings for shadowed dependencies
//    shadow {
//        minimize = false //Convention
//        relocate = false //Convention
//    }

    // optional
    // Generates string constants you can use in code
    tokens {
        tokenClass = "Tags"
//        modid      = "MOD_ID"      //Convention
//        name       = "MOD_NAME"    //Convention
//        version    = "MOD_VERSION" //Convention
//        rootPkg    = "ROOT_PKG"    //Convention
    }

    // optional
    // Changes the log level of the console output
//    logging {
//        level = INFO //Convention
//    }

    // optional
    // Use this if you want to publish on maven/curse/modrinth
//    publish {
//        // optional
//        // If not set, tries to read CHANGELOG.md (or the file specified in the CHANGELOG_FILE env var, if set)
//        changelog = "https://github.com/myname/mymod/releases/tag/{version}"
//
//        // optional
//        maven {
//            repoUrl  = "https://example.com/"
//            repoName = "example"
//            sources  = true                    //Convention
//            group    = "${project.group}"      //Convention
//            artifact = "${mod.modid}-mc1.7.10" //Convention
//            version  = "${mod.version}"        //Convention
//            userEnv  = "MAVEN_DEPLOY_USER"     //Convention
//            passEnv  = "MAVEN_DEPLOY_PASSWORD" //Convention
//        }
//
//        // optional
//        curseforge {
//            projectId = "123456"
//            tokenEnv = "CURSEFORGE_TOKEN" //Convention
//
//            // optional
//            dependencies {
//                required("foo")
//                required("bar")
//                incompatible("baz")
//            }
//        }
//
//        // optional
//        modrinth {
//            projectId = "blah123"
//            tokenEnv = "MODRINTH_TOKEN" //Convention
//
//            // optional
//            dependencies {
//                required("bar")
//                incompatible("qux")
//            }
//        }
//    }

    //optional
    //For configuring the fpgradle update checker
//    updates {
//        check = true // Convention
//    }
}

repositories {
    //Add repositories here
    //You can use maven("reponame", "https://example.com/") as a shortcut for maven repositories.

    /*
     * Built-in maven repository convenience shortcuts:
     *
     * cursemavenEX() -> adds cursemaven, and forces all curse.maven packages to route to only it
     * modrinthEX() -> same thing but with modrinth maven
     * mavenpattern() -> FalsePattern's maven
     * jitpack() -> jitpack
     * mega() -> The MEGA team's public maven
     *
     * The exclusive(...) method can be used as a simplified wrapper on top of exclusiveContent. See the FPGradle sources for details.
     */
}

dependencies {
    /*
     * Add your dependencies here. Supported configurations:
     *  - api("group:name:version:classifier"): if you use the types from this dependency in the public API of this mod
     *       Available at runtime and compiletime for mods depending on this mod
     *  - implementation("g:n:v:c"): if you need this for internal implementation details of the mod, but none of it is visible via the public API
     *       Available at runtime but not compiletime for mods depending on this mod
     *  - compileOnly("g:n:v:c"): if the mod you're building doesn't need this dependency during runtime at all, e.g. for optional mods
     *       Not available at all for mods depending on this mod, only visible at compiletime for this mod
     *  - compileOnlyApi("g:n:v:c"): like compileOnly, but also visible at compiletime for mods depending on this mod
     *       Available at compiletime but not runtime for mods depending on this mod
     *  - runtimeOnlyNonPublishable("g:n:v:c"): if you want to include a mod in this mod's runClient/runServer runs, but not publish it as a dependency
     *       Not available at all for mods depending on this mod, only visible at runtime for this mod
     *  - devOnlyNonPublishable("g:n:v:c"): a combination of runtimeOnlyNonPublishable and compileOnly for dependencies present at both compiletime and runtime,
     *       but not published as Maven dependencies - useful for RFG-deobfuscated dependencies or local testing
     *  - runtimeOnly("g:n:v:c"): if you don't need this at compile time, but want it to be present at runtime
     *       Available at runtime for mods depending on this mod
     *  - annotationProcessor("g:n:v:c"): mostly for java compiler plugins, if you know you need this, use it, otherwise don't worry
     *
     *  - implementationSplit("g:n:v"): Useful for depending on mods with explicit public API. This is a shortcut for adding
     *       an `implementation` dependency on the :api variant, and a `runtimeOnly` dependency on the :dev variant
     *
     *  - apiSplit("g:n:v"): Same as above, but uses `api` instead of `implementation`
     *
     *  - devOnlySplit("g:n:v"): Equivalent of implementationSplit, but with `runtimeOnlyNonPublishable` instead of `runtimeOnly`
     *
     *  - apiOnlySplit("g:n:v"): Equivalent of apiSplit, but with `runtimeOnlyNonPublishable` instead of `runtimeOnly`.
     *
     *  - testCONFIG("g:n:v:c") - replace CONFIG by one of the above (except api and the split ones), same as above but for the test sources instead of main
     *
     *  - shadowImplementation("g:n:v:c"): effectively the same as API, but the dependency is included in your jar under a renamed package name
     *       Requires you to enable usesShadowedDependencies in gradle.properties
     *
     *  - compile("g:n:v:c"): deprecated, replace with "api" (works like the old "compile") or "implementation" (can be more efficient)
     *
     * You can exclude transitive dependencies (dependencies of the chosen dependency) by appending { transitive = false } if needed,
     * but use this sparingly as it can break using your mod as another mod's dependency if you're not careful.
     *
     * To depend on obfuscated jars you can use `devOnlyNonPublishable(rfg.deobf("dep:spec:1.2.3"))` to fetch an obfuscated jar from maven,
     * or `devOnlyNonPublishable(rfg.deobf(project.files("libs/my-mod-jar.jar")))` to use a file. Also works with compileOnly
     * if you only want to compile against a mod, but not load it in dev.
     *
     * For CurseMaven dependencies, you can use `compileOnly(deobfCurse(modname-projectid:fileID))`. Note that you still
     * need to have the cursemaven maven in your repositories! Also works with configurations other than compileOnly.
     *
     * For Modrinth dependencies, you can use deobfModrinth.
     *
     * Gradle names for some of the configuration can be misleading, compileOnlyApi and runtimeOnly both get published as dependencies in Maven, but compileOnly does not.
     * The buildscript adds runtimeOnlyNonPublishable to also have a runtime dependency that's not published.
     *
     * For more details, see https://docs.gradle.org/8.8/userguide/java_library_plugin.html#sec:java_library_configurations_graph
     */
}