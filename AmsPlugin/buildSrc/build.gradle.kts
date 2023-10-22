plugins {
//    `kotlin-dsl`
//    kotlin
    `maven-publish`
//    groovy

    `kotlin-dsl`
    kotlin("jvm") version "1.7.20"
}


dependencies {
    implementation(gradleApi())
//    implementation(localGroovy())
//    compileOnly("commons-io:commons-io:2.6")
//    compileOnly("commons-codec:commons-codec:1.15")
//    compileOnly("org.ow2.asm:asm-commons:9.2")
//    compileOnly("org.ow2.asm:asm-tree:9.2")

//    implementation('org.ow2.asm:asm:9.2')
//    implementation('org.ow2.asm:asm-util:9.2')
//    implementation('org.ow2.asm:asm-commons:9.2')
//    implementation('org.ow2.asm:asm-analysis:9.2')
//    implementation('org.ow2.asm:asm-tree:9.2')

    implementation("com.android.tools.build:gradle:7.4.2")
//    compileOnly('com.android.tools.build:gradle:7.4.2', {
//        exclude group : 'org.ow2.asm'
//    })

}

repositories {
    google()
    mavenCentral()
//    maven {
//        url('https://jitpack.io')
//    }
//    maven {
//        url("https://plugins.gradle.org/m2/")
//    }
}