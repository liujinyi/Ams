package com.ichatmaster.playlet

import DeleteClassTransform
import com.android.build.gradle.AppExtension
import org.gradle.api.Plugin
import org.gradle.api.Project

class PlayLetPlugin : Plugin<Project?> {
//    override fun apply(project: Project?) {
//        println("PlayLetPlugin apply success ---> $project")
//        if (project != null) {
//            val androidComponents =
//                project.extensions.getByType(AndroidComponentsExtension::class.java)
//            androidComponents.onVariants { variant ->
//                variant.instrumentation.transformClassesWith(
//                    RenameClassTransform::class.java,
//                    InstrumentationScope.ALL
//                ) {
//                }
//                variant.instrumentation.setAsmFramesComputationMode(
//                    FramesComputationMode.COMPUTE_FRAMES_FOR_ALL_CLASSES
//                )
//            }
//
//        }
//    }

    override fun apply(project: Project?) {
        println("PlayLetPlugin apply success ---> $project")
        if (project != null) {
            val appExtension =
                project.extensions.getByType(AppExtension::class.java)
            appExtension.registerTransform(DeleteClassTransform())

        }
    }

}