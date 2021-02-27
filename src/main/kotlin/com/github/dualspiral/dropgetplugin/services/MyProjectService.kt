package com.github.dualspiral.dropgetplugin.services

import com.github.dualspiral.dropgetplugin.MyBundle
import com.intellij.openapi.project.Project

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}
