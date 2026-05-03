plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.sunnyweather.android"
    compileSdk {
        version = release(36) {
            minorApiLevel = 1
        }
    }

    defaultConfig {
        applicationId = "com.sunnyweather.android"
        minSdk = 24
        targetSdk = 36
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
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}
androidComponents {
    onVariants { variant ->
        variant.outputs.forEach { output ->
            if (variant.buildType == "release") {
                val apkName =
                    "${
                        android.defaultConfig.applicationId?.split(".")?.last()
                    }_v${android.defaultConfig.versionName}.apk"
                @Suppress("UnstableApiUsage")
                output.outputFileName = apkName
            }
        }
    }
}

dependencies {
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.converter.gson)
    implementation(libs.material)
    implementation(libs.swiperefreshlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
}