package com.ichatmaster.playlet

import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.Opcodes

open class RenameClassVisitor(nextVisitor: ClassVisitor) :
    ClassVisitor(Opcodes.ASM9, nextVisitor) {
    override fun visit(
        version: Int,
        access: Int,
        name: String?,
        signature: String?,
        superName: String?,
        interfaces: Array<out String>?,
    ) {
        super.visit(version, access, name, signature, superName, interfaces)
        println("------------------> RenameClassVisitor [visit] name is $name")
    }

}