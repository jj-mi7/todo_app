plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.www.todofinal"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.www.todofinal"
        minSdk = 30
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }


    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
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
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.navigation.runtime.ktx)
    implementation(libs.androidx.navigation.compose)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    val room_version = "2.6.1"

    implementation("androidx.room:room-runtime:$room_version")
    annotationProcessor("androidx.room:room-compiler:$room_version")

    // To use Kotlin annotation processing tool (kapt)
//    kapt("androidx.room:room-compiler:$room_version")
    // To use Kotlin Symbol Processing (KSP)
    ksp("androidx.room:room-compiler:$room_version")

    // optional - Kotlin Extensions and Coroutines support for Room
    implementation("androidx.room:room-ktx:$room_version")

    implementation ("androidx.compose.runtime:runtime-livedata:1.0.5")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:2.4.0")
    implementation("com.google.dagger:hilt-android:2.48")
    ksp("com.google.dagger:hilt-android-compiler:2.48")
    // For instrumented tests.
    androidTestImplementation("com.google.dagger:hilt-android-testing:2.48")
    // ...with Kotlin.
    kspAndroidTest("com.google.dagger:hilt-android-compiler:2.48")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.0")
    val activity_version = "1.8.2"

    // Java language implementation
    implementation("androidx.activity:activity:$activity_version")
    // Kotlin
    implementation("androidx.activity:activity-ktx:$activity_version")
    implementation ("org.jetbrains.kotlinx:kotlinx-datetime:0.3.0")
    implementation("androidx.compose.material:material:1.6.4")

    implementation ("androidx.compose.ui:ui:1.0.0")
    implementation ("androidx.compose.material:material:1.0.0")
    implementation ("androidx.navigation:navigation-compose:2.4.0-alpha01")
    implementation( "androidx.activity:activity-compose:1.4.0")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:1.0.0-alpha07")
    implementation ("com.google.dagger:hilt-android:2.48")
    implementation ("androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha03")
    ksp ("androidx.hilt:hilt-compiler:1.0.0")
    implementation ("androidx.hilt:hilt-navigation-compose:1.0.0")
//    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")



}