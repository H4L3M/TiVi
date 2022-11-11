plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "ma.nokhbativi"
    compileSdk = 33

    defaultConfig {
        applicationId = "ma.nokhbativi"
        minSdk = 24
        targetSdk = 33
        versionCode = 1_00_00
        versionName = "1.0.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
//        vectorDrawables {
//            useSupportLibrary = true
//        }
        multiDexEnabled = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.0"
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    kapt {
        correctErrorTypes = true
    }
    buildTypes {
        getting {
            applicationIdSuffix = ".debug"
        }
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                "proguard-rules.txt",
                getDefaultProguardFile("proguard-android-optimize.txt")
            )
        }
        debug {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                "proguard-rules.txt",
                getDefaultProguardFile("proguard-android-optimize.txt")
            )
        }
    }
    buildFeatures {
        compose = true
    }
    packagingOptions {
        resources {
            excludes.add("/META-INF/{AL2.0,LGPL2.1}")
        }
    }

}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.core:core-splashscreen:1.0.0")
    implementation("androidx.activity:activity-compose:1.6.1")

    implementation("androidx.compose.material3:material3:1.1.0-alpha02")
    implementation("androidx.compose.material:material:1.3.1")

//    implementation("androidx.paging:paging-compose:1.0.0-alpha17")

    //compose
    //runtime
    implementation("androidx.compose.runtime:runtime:1.3.1")
    //ui
    implementation("androidx.compose.ui:ui:1.4.0-alpha02")
    implementation("androidx.compose.ui:ui-tooling-preview:1.4.0-alpha02")

    //accompanist
    implementation("com.google.accompanist:accompanist-pager:0.26.5-rc")
//    implementation("com.google.accompanist:accompanist-flowlayout:0.26.5-rc")
    implementation("com.google.accompanist:accompanist-swiperefresh:0.26.5-rc")
    implementation("com.google.accompanist:accompanist-systemuicontroller:0.17.0")
    implementation("com.google.accompanist:accompanist-pager-indicators:0.26.5-rc")
    implementation("com.google.accompanist:accompanist-placeholder-material:0.26.5-rc")
//    implementation("androidx.compose.material3:material3-window-size-class:1.1.0-alpha02")

    //view model
    implementation("androidx.navigation:navigation-compose:2.5.3")

    //gson
    implementation("com.google.code.gson:gson:2.9.1")

    //work manager
    implementation("androidx.work:work-runtime-ktx:2.8.0-beta02")

    //datastore
    implementation("androidx.datastore:datastore:1.0.0")
    implementation("androidx.datastore:datastore-preferences:1.0.0")

    //room
    implementation("androidx.room:room-ktx:2.5.0-beta02")
    implementation("androidx.room:room-paging:2.5.0-beta02")
    implementation("androidx.room:room-runtime:2.5.0-beta02")
    kapt("androidx.room:room-compiler:2.5.0-beta02")

    //coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")
    runtimeOnly("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")

    //dependencies injection
    implementation("androidx.hilt:hilt-work:1.0.0")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")
    implementation("com.google.dagger:hilt-android:2.44")
    kapt("com.google.dagger:hilt-android-compiler:2.44")
    kapt("androidx.hilt:hilt-compiler:1.0.0")
    implementation("com.google.dagger:dagger-android-support:2.44")

    //lifecycle
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.0-alpha03")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.0-alpha03")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.0-alpha03")

    //image loader
    implementation("io.coil-kt:coil-compose:2.2.2")
    implementation("io.coil-kt:coil-svg:2.2.2")

    //constrain layout
    implementation("androidx.constraintlayout:constraintlayout-compose:1.0.1")

    // Retrofit for networking
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.2")

    implementation("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:0.8.0")

    //firebase
    implementation("com.google.firebase:firebase-analytics-ktx:21.2.0")
    implementation("com.google.firebase:firebase-firestore-ktx:24.4.0")
//    implementation("com.google.firebase:firebase-database-ktx:20.1.0")
//    implementation("com.google.firebase:firebase-messaging-ktx:23.1.0")
//    implementation("com.google.firebase:firebase-config-ktx:21.2.0")

    implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.4.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.0")

//    implementation("com.google.ads.mediation:ironsource:7.2.3.0")
//    implementation("com.ironsource.sdk:mediationsdk:7.2.5")
    implementation("com.unity3d.ads:unity-ads:4.4.1")

//    implementation("com.google.android.gms:play-services-appset:16.0.2")
//    implementation("com.google.android.gms:play-services-ads-identifier:18.0.1")
//    implementation("com.google.android.gms:play-services-basement:18.1.0")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.4.0-alpha01")
    debugImplementation("androidx.compose.ui:ui-tooling:1.4.0-alpha01")
    debugImplementation("androidx.compose.ui:ui-test-manifest:1.4.0-alpha01")


    implementation("com.google.android.exoplayer:exoplayer:2.18.1")
//    implementation("com.google.android.exoplayer:extension-ima:2.18.1")
//    implementation("com.google.android.exoplayer:extension-cronet:2.18.1")

    implementation("org.checkerframework:checker-qual:3.25.0")


    implementation("org.orbit-mvi:orbit-core:4.3.2")
    implementation("org.orbit-mvi:orbit-viewmodel:4.3.2")

}