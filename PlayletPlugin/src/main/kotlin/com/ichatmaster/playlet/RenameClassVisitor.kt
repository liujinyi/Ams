package com.ichatmaster.playlet

import org.objectweb.asm.ClassReader
import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.ClassWriter
import org.objectweb.asm.ClassWriter.COMPUTE_FRAMES
import org.objectweb.asm.FieldVisitor
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes

open class RenameClassVisitor(nextVisitor: ClassVisitor) :
    ClassVisitor(Opcodes.ASM9, nextVisitor) {
    lateinit var name: String

    override fun visit(
        version: Int,
        access: Int,
        name: String?,
        signature: String?,
        superName: String?,
        interfaces: Array<out String>?,
    ) {
        super.visit(version, access, name, signature, superName, interfaces)
        this.name = name ?: ""

        println("------------------> RenameClassVisitor [visit] name is $name, access is $access")
    }

    override fun visitMethod(
        access: Int,
        name: String?,
        descriptor: String?,
        signature: String?,
        exceptions: Array<out String>?
    ): MethodVisitor {
        return super.visitMethod(access, name, descriptor, signature, exceptions)
    }

    override fun visitField(
        access: Int,
        name: String?,
        descriptor: String?,
        signature: String?,
        value: Any?
    ): FieldVisitor {
        return super.visitField(access, name, descriptor, signature, value)
    }
    override fun visitEnd() {
        super.visitEnd()
        if (name.isNotEmpty()) {
            val classReader = ClassReader(name)
            val classWriter = ClassWriter(classReader, COMPUTE_FRAMES)
            classReader.accept(object : ClassVisitor(Opcodes.ASM9, classWriter) {
                override fun visit(
                    version: Int,
                    access: Int,
                    name: String?,
                    signature: String?,
                    superName: String?,
                    interfaces: Array<out String>?,
                ) {
                    super.visit(
                        version,
                        access,
                        name?.replace("DPDrawSeekLayout", "DPDrawSeekLayoutBak"),
                        signature,
                        superName,
                        interfaces
                    )
                }
            }, ClassReader.EXPAND_FRAMES)
        }
    }
}