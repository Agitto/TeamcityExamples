package patches.buildTypes

import jetbrains.buildServer.configs.kotlin.v2018_2.*
import jetbrains.buildServer.configs.kotlin.v2018_2.buildSteps.NuGetInstallerStep
import jetbrains.buildServer.configs.kotlin.v2018_2.buildSteps.dotnetBuild
import jetbrains.buildServer.configs.kotlin.v2018_2.buildSteps.nuGetInstaller
import jetbrains.buildServer.configs.kotlin.v2018_2.ui.*

/*
This patch script was generated by TeamCity on settings change in UI.
To apply the patch, change the buildType with id = 'XamarinFormsDemoApp'
accordingly, and delete the patch script.
*/
changeBuildType(RelativeId("XamarinFormsDemoApp")) {
    expectSteps {
        nuGetInstaller {
            toolPath = "/Library/Frameworks/Mono.framework/Versions/Current/Commands/nuget"
            projects = "CS/DemoCenter.sln"
            noCache = true
            sources = "../../packages"
        }
        dotnetBuild {
            workingDir = "CS"
            configuration = "debug"
        }
    }
    steps {
    }
}
