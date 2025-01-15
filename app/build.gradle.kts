import org.gradle.kotlin.dsl.android

configurations.all {
    resolutionStrategy {
        exclude(group="org.jetbrains.kotlinx", module= "kotlinx-coroutines-debug")
    }
}

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
//    id("com.google.devtools.ksp")
    alias(libs.plugins.compose.compiler)
    id("com.google.dagger.hilt.android")
    id("org.jetbrains.kotlin.kapt") version "2.1.20-Beta1"
}


android {
    namespace = "com.mf.homeassignment4"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.mf.homeassignment4"
        minSdk = 34
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("debug")
        }
        debug {
            packaging{
                resources{
                    excludes += "win32-x86/attach_hotspot_windows.dll"
                }
            }

        }
        create("benchmark") {
            initWith(buildTypes.getByName("release"))
            matchingFallbacks += listOf("release")
            isDebuggable = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.kotlin.toString()
    }


}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.retrofit)
//    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.activity.ktx)
    implementation(libs.converter.gson)
//    implementation(libs.androidx.navigation.fragment.ktx)
//    implementation(libs.androidx.navigation.ui.ktx)
//    implementation(libs.lifecycle.runtime.ktx)
//    implementation(libs.androidx.swiperefreshlayout)
    implementation(libs.androidx.activity.compose)
//    implementation(libs.androidx.foundation)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.hilt.android)
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.coil.compose)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.compose.bom)
    implementation(libs.androidx.runtime)
    implementation(libs.androidx.ui)
    implementation(libs.androidx.animation)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.runtime.livedata)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.material.icons.extended)
//    debugImplementation(libs.jetbrains.kotlinx.coroutines.debug)
    debugImplementation(libs.androidx.ui.tooling)
    kapt(libs.hilt.android.compiler)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
kapt {
    correctErrorTypes = true
}