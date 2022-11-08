plugins {
    application
    id("com.android.application") version "7.3.1" apply false
    id("com.android.library") version "7.3.1" apply false
    id("org.jetbrains.kotlin.android") version "1.7.10" apply false
    kotlin("jvm") version "1.7.10" // or kotlin("multiplatform") or any other kotlin plugin
    kotlin("plugin.serialization") version "1.7.10"
    id("com.google.dagger.hilt.android") version "2.44" apply false
}

buildscript {
    repositories { mavenCentral() }
    dependencies {
        classpath ("com.google.gms:google-services:4.3.14")
        val kotlinVersion = "1.7.10"
        classpath(kotlin("gradle-plugin", version = kotlinVersion))
        classpath(kotlin("serialization", version = kotlinVersion))
    }
}