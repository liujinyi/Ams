package com.ichatmaster.playlet

import com.android.build.api.instrumentation.AsmClassVisitorFactory
import com.android.build.api.instrumentation.ClassContext
import com.android.build.api.instrumentation.ClassData
import com.android.build.api.instrumentation.InstrumentationParameters
import org.objectweb.asm.ClassVisitor

abstract class RenameClassTransform : AsmClassVisitorFactory<InstrumentationParameters.None> {
    override fun createClassVisitor(
        classContext: ClassContext,
        nextClassVisitor: ClassVisitor,
    ): ClassVisitor = RenameClassVisitor(nextClassVisitor)


    override fun isInstrumentable(classData: ClassData): Boolean {
        println("RenameClassTransform [isInstrumentable] classData is ${classData.className}")
        return classData.className.contains("com.bytedance.sdk.dp.core.business.view.DPDrawSeekLayout")
    }
}