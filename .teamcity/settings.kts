import jetbrains.buildServer.configs.kotlin.v10.toExtId
import jetbrains.buildServer.configs.kotlin.v2018_2.*
import jetbrains.buildServer.configs.kotlin.v2018_2.buildSteps.*
import jetbrains.buildServer.configs.kotlin.v2018_2.triggers.finishBuildTrigger
import jetbrains.buildServer.configs.kotlin.v2018_2.triggers.vcs
import jetbrains.buildServer.configs.kotlin.v2018_2.vcs.GitVcsRoot
import org.json.*

val realJson = "[\n" +
        "  {\n" +
        "    \"id\": 259696816,\n" +
        "    \"name\": \"xamarin-data-form\",\n" +
        "    \"full_name\": \"DevExpress-Examples/xamarin-data-form\",\n" +
        "    \"description\": null,\n" +
        "    \"branches_url\": \"https://api.github.com/repos/DevExpress-Examples/xamarin-data-form/branches{/branch}\",\n" +
        "    \"git_url\": \"git://github.com/DevExpress-Examples/xamarin-data-form.git\",\n" +
        "    \"clone_url\": \"https://github.com/DevExpress-Examples/xamarin-data-form.git\",\n" +
        "    \"Branches\": [\n" +
        "      {\n" +
        "        \"name\": \"20.1.1+\",\n" +
        "        \"commit\": {\n" +
        "          \"sha\": \"2eb5901e54b29b44fcca9b7a0f3e6a8878cffa7f\"\n" +
        "        },\n" +
        "        \"sln\": \"CS/DataForm.sln\"\n" +
        "      }\n" +
        "    ]\n" +
        "  },\n" +
        "  {\n" +
        "    \"id\": 228646956,\n" +
        "    \"name\": \"xamarin-forms-demo-app\",\n" +
        "    \"full_name\": \"DevExpress-Examples/xamarin-forms-demo-app\",\n" +
        "    \"description\": \"Xamarin, Xamarin.Forms\",\n" +
        "    \"branches_url\": \"https://api.github.com/repos/DevExpress-Examples/xamarin-forms-demo-app/branches{/branch}\",\n" +
        "    \"git_url\": \"git://github.com/DevExpress-Examples/xamarin-forms-demo-app.git\",\n" +
        "    \"clone_url\": \"https://github.com/DevExpress-Examples/xamarin-forms-demo-app.git\",\n" +
        "    \"Branches\": [\n" +
        "      {\n" +
        "        \"name\": \"19.2.4+\",\n" +
        "        \"commit\": {\n" +
        "          \"sha\": \"c955b09d2794454e9b2ee436be3ad99d5d2a7b5d\"\n" +
        "        },\n" +
        "        \"sln\": \"CS/DemoCenter.sln\"\n" +
        "      },\n" +
        "      {\n" +
        "        \"name\": \"20.1.3+\",\n" +
        "        \"commit\": {\n" +
        "          \"sha\": \"752b936dbf363f8af02e546d0a314d839ca034d2\"\n" +
        "        },\n" +
        "        \"sln\": \"CS/DemoCenter.sln\"\n" +
        "      }\n" +
        "    ]\n" +
        "  },\n" +
        "  {\n" +
        "    \"id\": 148444621,\n" +
        "    \"name\": \"xamarin-forms-financial-chart-example\",\n" +
        "    \"full_name\": \"DevExpress-Examples/xamarin-forms-financial-chart-example\",\n" +
        "    \"description\": null,\n" +
        "    \"branches_url\": \"https://api.github.com/repos/DevExpress-Examples/xamarin-forms-financial-chart-example/branches{/branch}\",\n" +
        "    \"git_url\": \"git://github.com/DevExpress-Examples/xamarin-forms-financial-chart-example.git\",\n" +
        "    \"clone_url\": \"https://github.com/DevExpress-Examples/xamarin-forms-financial-chart-example.git\",\n" +
        "    \"Branches\": [\n" +
        "      {\n" +
        "        \"name\": \"1.1.1+\",\n" +
        "        \"commit\": {\n" +
        "          \"sha\": \"c9ec1ff157f45e19571f1e3a91954bf5169daef0\"\n" +
        "        },\n" +
        "        \"sln\": \"CS/FinancialChartExample.sln\"\n" +
        "      }\n" +
        "    ]\n" +
        "  },\n" +
        "  {\n" +
        "    \"id\": 279337454,\n" +
        "    \"name\": \"xamarin-forms-logify-client-app\",\n" +
        "    \"full_name\": \"DevExpress-Examples/xamarin-forms-logify-client-app\",\n" +
        "    \"description\": null,\n" +
        "    \"branches_url\": \"https://api.github.com/repos/DevExpress-Examples/xamarin-forms-logify-client-app/branches{/branch}\",\n" +
        "    \"git_url\": \"git://github.com/DevExpress-Examples/xamarin-forms-logify-client-app.git\",\n" +
        "    \"clone_url\": \"https://github.com/DevExpress-Examples/xamarin-forms-logify-client-app.git\",\n" +
        "    \"Branches\": [\n" +
        "      {\n" +
        "        \"name\": \"20.1.4+\",\n" +
        "        \"commit\": {\n" +
        "          \"sha\": \"c3a7ec4659f9807a53d759f1e044a5bcccd9b42a\"\n" +
        "        },\n" +
        "        \"sln\": \"CS/LogifyMobile.sln\"\n" +
        "      }\n" +
        "    ]\n" +
        "  },\n" +
        "  {\n" +
        "    \"id\": 148539860,\n" +
        "    \"name\": \"xamarin-forms-pie-chart-center-label-example\",\n" +
        "    \"full_name\": \"DevExpress-Examples/xamarin-forms-pie-chart-center-label-example\",\n" +
        "    \"description\": null,\n" +
        "    \"branches_url\": \"https://api.github.com/repos/DevExpress-Examples/xamarin-forms-pie-chart-center-label-example/branches{/branch}\",\n" +
        "    \"git_url\": \"git://github.com/DevExpress-Examples/xamarin-forms-pie-chart-center-label-example.git\",\n" +
        "    \"clone_url\": \"https://github.com/DevExpress-Examples/xamarin-forms-pie-chart-center-label-example.git\",\n" +
        "    \"Branches\": [\n" +
        "      {\n" +
        "        \"name\": \"1.1.1+\",\n" +
        "        \"commit\": {\n" +
        "          \"sha\": \"657457d22c39b0e11aed383f4010e2cfbbf92f61\"\n" +
        "        },\n" +
        "        \"sln\": \"CS/PieChartExample.sln\"\n" +
        "      }\n" +
        "    ]\n" +
        "  },\n" +
        "  {\n" +
        "    \"id\": 243744496,\n" +
        "    \"name\": \"xamarin-forms-stocks-app\",\n" +
        "    \"full_name\": \"DevExpress-Examples/xamarin-forms-stocks-app\",\n" +
        "    \"description\": \"Xamarin Forms Stocks App\",\n" +
        "    \"branches_url\": \"https://api.github.com/repos/DevExpress-Examples/xamarin-forms-stocks-app/branches{/branch}\",\n" +
        "    \"git_url\": \"git://github.com/DevExpress-Examples/xamarin-forms-stocks-app.git\",\n" +
        "    \"clone_url\": \"https://github.com/DevExpress-Examples/xamarin-forms-stocks-app.git\",\n" +
        "    \"Branches\": [\n" +
        "      {\n" +
        "        \"name\": \"19.2.1+\",\n" +
        "        \"commit\": {\n" +
        "          \"sha\": \"c4d954366f2c2f79cabbedcc7a91cf9e2fc6882c\"\n" +
        "        },\n" +
        "        \"sln\": \"CS/Stocks.sln\"\n" +
        "      }\n" +
        "    ]\n" +
        "  },\n" +
        "  {\n" +
        "    \"id\": 191357991,\n" +
        "    \"name\": \"Xamarin.Forms-Navigation-Views-Getting-Started-Lesson-1\",\n" +
        "    \"full_name\": \"DevExpress-Examples/Xamarin.Forms-Navigation-Views-Getting-Started-Lesson-1\",\n" +
        "    \"description\": null,\n" +
        "    \"branches_url\": \"https://api.github.com/repos/DevExpress-Examples/Xamarin.Forms-Navigation-Views-Getting-Started-Lesson-1/branches{/branch}\",\n" +
        "    \"git_url\": \"git://github.com/DevExpress-Examples/Xamarin.Forms-Navigation-Views-Getting-Started-Lesson-1.git\",\n" +
        "    \"clone_url\": \"https://github.com/DevExpress-Examples/Xamarin.Forms-Navigation-Views-Getting-Started-Lesson-1.git\",\n" +
        "    \"Branches\": [\n" +
        "      {\n" +
        "        \"name\": \"19.1.2+\",\n" +
        "        \"commit\": {\n" +
        "          \"sha\": \"5fe7c772ebf3a1318a0336982b0fe3d7543a3415\"\n" +
        "        },\n" +
        "        \"sln\": \"CS/GettingStarted1.sln\"\n" +
        "      },\n" +
        "      {\n" +
        "        \"name\": \"19.2.3+\",\n" +
        "        \"commit\": {\n" +
        "          \"sha\": \"70dca987d51a9186a30d4cd57f242457a7138169\"\n" +
        "        },\n" +
        "        \"sln\": \"CS/GettingStarted1.sln\"\n" +
        "      },\n" +
        "      {\n" +
        "        \"name\": \"19.2.7+\",\n" +
        "        \"commit\": {\n" +
        "          \"sha\": \"3304a52212c6ac08f33989a7c322e69eb4e38bae\"\n" +
        "        },\n" +
        "        \"sln\": \"CS/GettingStarted1.sln\"\n" +
        "      }\n" +
        "    ]\n" +
        "  },\n" +
        "  {\n" +
        "    \"id\": 191350804,\n" +
        "    \"name\": \"Xamarin.Forms-Navigation-Views-Getting-Started-Lesson-2\",\n" +
        "    \"full_name\": \"DevExpress-Examples/Xamarin.Forms-Navigation-Views-Getting-Started-Lesson-2\",\n" +
        "    \"description\": null,\n" +
        "    \"branches_url\": \"https://api.github.com/repos/DevExpress-Examples/Xamarin.Forms-Navigation-Views-Getting-Started-Lesson-2/branches{/branch}\",\n" +
        "    \"git_url\": \"git://github.com/DevExpress-Examples/Xamarin.Forms-Navigation-Views-Getting-Started-Lesson-2.git\",\n" +
        "    \"clone_url\": \"https://github.com/DevExpress-Examples/Xamarin.Forms-Navigation-Views-Getting-Started-Lesson-2.git\",\n" +
        "    \"Branches\": [\n" +
        "      {\n" +
        "        \"name\": \"19.1.2+\",\n" +
        "        \"commit\": {\n" +
        "          \"sha\": \"f39793924132e8a027bf0e4eaf00370123afe4ea\"\n" +
        "        },\n" +
        "        \"sln\": \"CS/GettingStarted2.sln\"\n" +
        "      },\n" +
        "      {\n" +
        "        \"name\": \"19.2.7+\",\n" +
        "        \"commit\": {\n" +
        "          \"sha\": \"4d6618f6621bfc9d7a35c758d5772aebc653aa9b\"\n" +
        "        },\n" +
        "        \"sln\": \"CS/GettingStarted2.sln\"\n" +
        "      }\n" +
        "    ]\n" +
        "  },\n" +
        "  {\n" +
        "    \"id\": 191364822,\n" +
        "    \"name\": \"Xamarin.Forms-Navigation-Views-Getting-Started-Lesson-3\",\n" +
        "    \"full_name\": \"DevExpress-Examples/Xamarin.Forms-Navigation-Views-Getting-Started-Lesson-3\",\n" +
        "    \"description\": null,\n" +
        "    \"branches_url\": \"https://api.github.com/repos/DevExpress-Examples/Xamarin.Forms-Navigation-Views-Getting-Started-Lesson-3/branches{/branch}\",\n" +
        "    \"git_url\": \"git://github.com/DevExpress-Examples/Xamarin.Forms-Navigation-Views-Getting-Started-Lesson-3.git\",\n" +
        "    \"clone_url\": \"https://github.com/DevExpress-Examples/Xamarin.Forms-Navigation-Views-Getting-Started-Lesson-3.git\",\n" +
        "    \"Branches\": [\n" +
        "      {\n" +
        "        \"name\": \"19.1.2+\",\n" +
        "        \"commit\": {\n" +
        "          \"sha\": \"28ee35fb7f58e19baeee9095eaaa7bf311a30e64\"\n" +
        "        },\n" +
        "        \"sln\": \"CS/DrawerSample.sln\"\n" +
        "      },\n" +
        "      {\n" +
        "        \"name\": \"19.2.7+\",\n" +
        "        \"commit\": {\n" +
        "          \"sha\": \"b239c0205f9a6a5d4823c257e2f2bdc6439d6ee4\"\n" +
        "        },\n" +
        "        \"sln\": \"CS/DrawerSample.sln\"\n" +
        "      }\n" +
        "    ]\n" +
        "  }\n" +
        "]"

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

var repositories = mutableListOf<GitRepository>()
var reposArray = JSONArray(realJson)

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
                                val branches: List<GitBranch>)

class GitBranch constructor(val name: String, val sln: String)

class Build(private val repo: GitRepository, private val vcsRoot: GitVcsRoot) : BuildType({
    id(repo.name.toExtId())
    name = "Build ${repo.name}"

    val parentId = RelativeId("123").value.replace("_Examples_123", "")
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
            projects = repo.branches[0].sln
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
        msBuild {
            targets = "clean,build"
            path = repo.branches[0].sln
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


project {
    for(repo in repositories) {
        val vcs = GitVcsRoot{
            id(repo.name.toExtId())
            name = repo.name
            url = repo.cloneUrl
            branch = repo.branches[0].name
        }

        subProject {
            id(repo.name.toExtId())
            name = "Build ${repo.name}"
            vcsRoot(vcs)
            buildType(Build(repo, vcs))
        }
    }
}

