plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.nokhbativi"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.nokhbativi"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "0.0.1"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
//        vectorDrawables {
//            useSupportLibrary = true
//        }

        multiDexEnabled = true
    }

    buildTypes {
        getting {
            applicationIdSuffix = ".debug"
        }
        getting {
            isMinifyEnabled = false
            isShrinkResources = false
            proguardFiles(
                "proguard-rules.txt",
                getDefaultProguardFile("proguard-android.txt")
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.0"
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    packagingOptions {
        resources {
            excludes.add("/META-INF/{AL2.0,LGPL2.1}")
        }
    }
    kapt {
        correctErrorTypes = true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.5.1")
    implementation("androidx.activity:activity-compose:1.6.0")
    implementation("androidx.compose.ui:ui:1.3.0-beta03")
    implementation("androidx.compose.ui:ui-tooling-preview:1.3.0-beta03")
    implementation("androidx.compose.material3:material3:1.0.0-beta03")
    implementation("androidx.compose.material:material:1.2.1")
    implementation("androidx.appcompat:appcompat:1.5.1")
    implementation("com.google.android.material:material:1.6.1")
    implementation("androidx.paging:paging-compose:1.0.0-alpha16")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.3.0-beta03")
    debugImplementation("androidx.compose.ui:ui-tooling:1.3.0-beta03")
    debugImplementation("androidx.compose.ui:ui-test-manifest:1.3.0-beta03")

    //compose
    //runtime
    implementation("androidx.compose.runtime:runtime:1.2.1")

    //accompanist
    implementation("androidx.compose.material3:material3-window-size-class:1.0.0-beta03")
    implementation("com.google.accompanist:accompanist-systemuicontroller:0.17.0")
    implementation("com.google.accompanist:accompanist-swiperefresh:0.26.4-beta")

    //view model
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.5.1")
    implementation("androidx.navigation:navigation-compose:2.5.2")

    //gson
    implementation("com.google.code.gson:gson:2.9.1")

    //work manager
    implementation("androidx.work:work-runtime-ktx:2.7.1")

    //datastore
//    implementation("androidx.datastore:datastore:1.0.0")
    implementation("androidx.datastore:datastore-preferences:1.0.0")
//    implementation("androidx.datastore:datastore-preferences-core:1.0.0")

    //room
    implementation("androidx.room:room-ktx:2.4.3")
    implementation("androidx.room:room-paging:2.4.3")
    implementation("androidx.room:room-runtime:2.4.3")
    kapt("androidx.room:room-compiler:2.4.3")

    //exoplayer
    implementation("com.google.android.exoplayer:exoplayer:2.18.1")
//    implementation("com.google.android.exoplayer:extension-cronet:2.13.2")

    //mediation
//    implementation("com.google.android.gms:play-services-ads:19.7.0")
//    implementation("com.google.ads.mediation:ironsource:7.1.0.2.0")
//    implementation("com.google.ads.mediation:facebook:6.3.0.0")

    //coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")
    runtimeOnly("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")

    //dependencies injection
    implementation("androidx.hilt:hilt-work:1.0.0")
    implementation("com.google.dagger:hilt-android:2.44")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")
    implementation("com.google.dagger:dagger-android-support:2.44")
    kapt("com.google.dagger:hilt-android-compiler:2.44")
    kapt("androidx.hilt:hilt-compiler:1.0.0")

    //lifecycle
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.0-alpha02")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.0-alpha02")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.0-alpha02")

    //image loader
    implementation("io.coil-kt:coil-compose:2.1.0")
    implementation("io.coil-kt:coil-svg:2.1.0")

    //landscapist
//    implementation("com.github.skydoves:landscapist-coil:1.6.0")

    //shimmer
//    implementation("com.facebook.shimmer:shimmer:0.5.0")
//    implementation("com.github.kazemihabib:compose-shimmer:1.0.1")

    //palette generate color swatch from bitmap
//    implementation("androidx.palette:palette:1.0.0")
//    implementation("androidx.palette:palette-ktx:1.0.0")

    //constrain layout
    implementation("androidx.constraintlayout:constraintlayout-compose:1.0.1")

    // Retrofit for networking
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.2")

    implementation("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:0.8.0")

//    implementation("joda-time:joda-time:2.11.0")

//    implementation("androidx.multidex:multidex:2.0.1")

    //firebase
    implementation("com.google.firebase:firebase-analytics-ktx:21.1.1")
    implementation("com.google.firebase:firebase-firestore-ktx:24.3.1")
    implementation("com.google.firebase:firebase-database-ktx:20.0.6")
    implementation("com.google.firebase:firebase-messaging-ktx:23.0.8")
    implementation("com.google.firebase:firebase-config-ktx:21.1.2")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.0")

}