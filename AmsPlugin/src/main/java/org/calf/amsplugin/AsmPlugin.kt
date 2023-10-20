package org.calf.amsplugin

import org.gradle.api.Project

class AsmPlugin : org.gradle.api.Plugin<Project> {
    override fun apply(target: Project) {
        println("this is AsmPlugin")
    }
}