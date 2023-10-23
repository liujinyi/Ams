package com.ichatmaster.playlet

import com.android.build.api.instrumentation.AsmClassVisitorFactory
import com.android.build.api.instrumentation.ClassContext
import com.android.build.api.instrumentation.ClassData
import com.android.build.api.instrumentation.InstrumentationParameters
import org.objectweb.asm.ClassVisitor
import java.io.File

abstract class RenameClassTransform : AsmClassVisitorFactory<InstrumentationParameters.None> {
    override fun createClassVisitor(
        classContext: ClassContext,
        nextClassVisitor: ClassVisitor,
    ): ClassVisitor {
//        println("RenameClassTransform [createClassVisitor] classData is ${classContext.currentClassData.className} , nextClassVisitor is $nextClassVisitor")

        return RenameClassVisitor(nextClassVisitor)
    }


    override fun isInstrumentable(classData: ClassData): Boolean {
//        println("RenameClassTransform [isInstrumentable] classData is ${classData.className}")
//        return classData.className.contains("com.bytedance.sdk.dp.core.business.view.DPDrawSeekLayout")
        val result =
            (classData.className == "com.bytedance.sdk.dp.core.business.view.DPDrawSeekLayout"
                    || classData.className == "com.bytedance.sdk.dp.core.business.view.DPDrawSeekLayout$1"
                    || classData.className == "com.bytedance.sdk.dp.core.business.view.DPDrawSeekLayout$2")
        if (result) {
            println("RenameClassTransform [isInstrumentable] classData is ${classData.className}")
        }
        return result
    }
}