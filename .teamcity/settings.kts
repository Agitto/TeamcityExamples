import jetbrains.buildServer.configs.kotlin.v10.toExtId
import jetbrains.buildServer.configs.kotlin.v2018_2.*
import jetbrains.buildServer.configs.kotlin.v2018_2.buildSteps.*
import jetbrains.buildServer.configs.kotlin.v2018_2.triggers.finishBuildTrigger
import jetbrains.buildServer.configs.kotlin.v2018_2.triggers.vcs
import jetbrains.buildServer.configs.kotlin.v2018_2.vcs.GitVcsRoot
import org.json.*
import java.io.File
import java.lang.Exception
import java.lang.NumberFormatException

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

var json = File("repos.json").readText()

var repositories = mutableListOf<GitRepository>()
var reposArray = JSONArray(json)

for(i in 0 until reposArray.length()) {
    val obj = reposArray.getJSONObject(i)
    var branches = mutableListOf<GitBranch>()
    val branchesArray = obj.getJSONArray("Branches")
    for(j in 0 until branchesArray.length()) {
        val branchObj = branchesArray.getJSONObject(j)
        val branch = GitBranch(
                branchObj.getString("name"),
                branchObj.getString("sln")
        )
        branches.add(branch)
        branches.sortByDescending { branch -> branch.major }
    }
    val repo = GitRepository(
            obj.getInt("id"),
            obj.getString("name"),
            obj.getString("full_name"),
            obj.getString("git_url"),
            obj.getString("clone_url"),
            branches
    )
    repositories.add(repo)
}

class GitRepository constructor(val id: Int,
                                val name: String,
                                val fullName: String,
                                val gitUrl: String,
                                val cloneUrl: String,
                                val branches: MutableList<GitBranch>) {

    init {
        branches.sortByDescending { branch -> branch.major }
    }

    fun getHighestBranch() : GitBranch? {
        for (branch in branches) {
            if(branch.isHighest) {
                return branch
            }
        }

        return null;
    }

    fun getBranch(version: String) : GitBranch? {
        val highest: GitBranch = getHighestBranch()!!
        if(version == "dev") {
            return highest;
        }

        var numericVersion = version.toDouble()

        for(branch in branches) {
            if(branch.major == numericVersion) {
                return branch
            }
        }

        if(highest.major <= numericVersion) {
            return highest
        }

        return null
    }
}

class GitBranch constructor(val name: String, val sln: String) {
    val isHighest = name.endsWith("+")
    var major: Double = 0.0
    var minor: Int = 0

    init {
        val match = Regex("^(?<major>\\d{1,2}\\.\\d)\\.(?<minor>\\d{1,2})\\+?\$").matchEntire(name)
                ?: throw Exception("couldn't parse $name")
        major = match!!.groupValues[1]!!.toDouble()
        minor = match!!.groupValues[2]!!.toInt()
    }
}

class Build(private val repo: GitRepository,
            private val vcsRoot: GitVcsRoot,
            private val branch: GitBranch,
            private val version: String) : BuildType({
    id("${repo.name}_${version}".toExtId())
    name = "Build ${repo.name}"
    description = repo.cloneUrl.replace(".git", "")

//    val parentId = RelativeId("123").value.replace("_Examples_123", "")
    val parentBuildId = if(version == "dev") "DevBuild" else version.replace(".", "")
    val nugetBuildId = "NativeMobile_${parentBuildId}_Install_NugetXamarinLicense"

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
            projects = branch.sln
        }

//       script {
//           name = "Restore"
//           scriptContent = "nuget restore -NoCache"
//           workingDir = "CS"
//       }

//        dotnetBuild {
//            configuration = "Debug"
//            workingDir = "CS"
//        }
//        msBuild {
//            targets = "clean,build"
//            version = MSBuildStep.MSBuildVersion.MONO_v4_5
//            toolsVersion = MSBuildStep.MSBuildToolsVersion.V4_0
//            path = repo.branches[0].sln
//        }

        script {
            name = "MSBuild"
            scriptContent = "msbuild ${branch.sln} /t:clean,build /p:Configuration=Debug /clp:errorsonly"
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

val versions = listOf<String>("19.2", "20.1", "20.2", "dev", "fetcher")

project {
    for (version in versions) {
        subProject {
            if(version == "fetcher") {
                id("fetcher")
                name = "Fetch Examples"
                val vcs = GitVcsRoot {
                    id("fetcher")
                    name = "fetcher"
                    url = "https://github.com/Agitto/GithubSamples.git"
                    branch = "master"
                }
                vcsRoot(vcs)
                buildType {
                    id("fetcherBuild")
                    name = "Fetch Examples"

                    vcs {
                        root(vcs)
                    }

                    steps {
                        script {
                            name = "run"
                            workingDir = "GithubExamples"
                            scriptContent = "dotnet run"
                        }
                    }
                }
            }

            else {
                id(version.toExtId())
                name = version
                for (repo in repositories) {
                    val matchingBranch = repo.getBranch(version) ?: continue
                    val vcs = GitVcsRoot {
                        id("${repo.name}_${version}".toExtId())
                        name = repo.name
                        url = repo.cloneUrl
                        branch = matchingBranch.name
                    }

                    vcsRoot(vcs)
                    buildType(Build(repo, vcs, matchingBranch, version))
                }
            }
        }
    }
}

