import jetbrains.buildServer.configs.kotlin.v10.toExtId
import jetbrains.buildServer.configs.kotlin.v2018_2.*
import jetbrains.buildServer.configs.kotlin.v2018_2.buildFeatures.Swabra
import jetbrains.buildServer.configs.kotlin.v2018_2.buildFeatures.swabra
import jetbrains.buildServer.configs.kotlin.v2018_2.buildSteps.dotnetBuild
import jetbrains.buildServer.configs.kotlin.v2018_2.buildSteps.maven
import jetbrains.buildServer.configs.kotlin.v2018_2.buildSteps.nuGetInstaller
import jetbrains.buildServer.configs.kotlin.v2018_2.triggers.finishBuildTrigger
import jetbrains.buildServer.configs.kotlin.v2018_2.triggers.vcs
import jetbrains.buildServer.configs.kotlin.v2018_2.vcs.GitVcsRoot

/*
The settings script is an entry point for defining a TeamCity
project hierarchy. The script should contain a single call to the
project() function with a Project instance or an init function as
an argument.

VcsRoots, BuildTypes, Templates, and subprojects can be
registered inside the project using the vcsRoot(), buildType(),
template(), and subProject() methods respectively.

To debug settings scripts in command-line, run the

    mvnDebug org.jetbrains.teamcity:teamcity-configs-maven-plugin:generate

command and attach your debugger to the port 8000.

To debug in IntelliJ Idea, open the 'Maven Projects' tool window (View
-> Tool Windows -> Maven Projects), find the generate task node
(Plugins -> teamcity-configs -> teamcity-configs:generate), the
'Debug' option is available in the context menu for the task.
*/
version = "2019.1"

class Repository constructor(val name: String, val url: String, val sln: String, val branch: String)

class Build(val repo: Repository, val vcsRoot: GitVcsRoot) : BuildType({
    id(repo.name.toExtId())
    name = "Build ${repo.name}"

    val parentId = RelativeId("123").value.replace("_Examples123", "")
    val nugetBuildId = "${parentId}_Install_NugetXamarinLicense"

    vcs {
        root(vcsRoot)
    }

    triggers {
        vcs {
        }
        finishBuildTrigger {
            buildType = nugetBuildId
            successfulOnly = true
        }
    }

    steps {
        nuGetInstaller {
            noCache = true
            sources = "../../packages"
            toolPath = "%teamcity.tool.NuGet.CommandLine.5.5.0%"
            projects = repo.sln
        }

        dotnetBuild {
            configuration = "debug"
            workingDir = "CS"
        }
    }

    dependencies {
        dependency(AbsoluteId(nugetBuildId)) {
            snapshot {
                onDependencyFailure = FailureAction.FAIL_TO_START
            }

            artifacts {
                cleanDestination = true
                artifactRules = "*.nupkg=>./packages"
                lastSuccessful()
            }
        }
    }
})

val repositories = listOf(
        Repository("xamarin_forms_demo_app","https://github.com/DevExpress-Examples/xamarin-forms-demo-app.git", "CS/DemoCenter.sln", "20.1.3+"),
        Repository("xamarin_forms_stocks_app","https://github.com/DevExpress-Examples/xamarin-forms-stocks-app.git", "CS/Stocks.sln","19.2.1+")
)

project {
    for(repo in repositories) {
        val vcs = GitVcsRoot{
            id(repo.name.toExtId())
            name = repo.name
            url = repo.url
            branch = repo.branch
        }
        vcsRoot(vcs)
        buildType(Build(repo, vcs))
    }
}
