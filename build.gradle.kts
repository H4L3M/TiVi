plugins {
    id("com.android.application") version "8.0.0-alpha02" apply false
    id("com.android.library") version "8.0.0-alpha02" apply false
    id("org.jetbrains.kotlin.android") version "1.7.10" apply false
    kotlin("jvm") version "1.7.10" // or kotlin("multiplatform") or any other kotlin plugin
    kotlin("plugin.serialization") version "1.7.10"
    id("com.google.dagger.hilt.android") version "2.44" apply false
}

buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
//        classpath("com.google.dagger:hilt-android-gradle-plugin:2.42")
        classpath ("com.google.gms:google-services:4.3.14")
//        val kotlinVersion = "1.7.10"
//        classpath(kotlin("gradle-plugin", version = kotlinVersion))
//        classpath(kotlin("serialization", version = kotlinVersion))
    }
}