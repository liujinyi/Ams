package com.ichatmaster.playlet

import com.android.build.api.instrumentation.FramesComputationMode
import com.android.build.api.instrumentation.InstrumentationScope
import com.android.build.api.variant.AndroidComponentsExtension
import org.gradle.api.Plugin
import org.gradle.api.Project

class PlayLetPlugin : Plugin<Project?> {
    override fun apply(project: Project?) {
        println("PlayLetPlugin apply success ---> $project")
        if (project != null) {
            val androidComponents =
                project.extensions.getByType(AndroidComponentsExtension::class.java)
            androidComponents.onVariants { variant ->
                variant.instrumentation.transformClassesWith(
                    RenameClassTransform::class.java,
                    InstrumentationScope.PROJECT
                ) {}
                variant.instrumentation.setAsmFramesComputationMode(
                    FramesComputationMode.COMPUTE_FRAMES_FOR_INSTRUMENTED_METHODS
                )
            }
        }

    }
}