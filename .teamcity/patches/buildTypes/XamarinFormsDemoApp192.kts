package patches.buildTypes

import jetbrains.buildServer.configs.kotlin.v2018_2.*
import jetbrains.buildServer.configs.kotlin.v2018_2.ui.*

/*
This patch script was generated by TeamCity on settings change in UI.
To apply the patch, change the buildType with id = 'XamarinFormsDemoApp192'
accordingly, and delete the patch script.
*/
changeBuildType(RelativeId("XamarinFormsDemoApp192")) {
    check(paused == false) {
        "Unexpected paused: '$paused'"
    }
    paused = true
}
