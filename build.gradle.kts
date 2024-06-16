plugins {
    id("fpgradle-minecraft") version("0.1.13")
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
}

repositories {
    //Add repositories here
}

dependencies {
    //Add dependencies here
}