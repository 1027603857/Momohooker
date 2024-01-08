plugins {
    id("com.android.application")
}

android {
    namespace = "com.momohooker"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.momohooker"
        minSdk = 21
        targetSdk = 33
        versionCode = 2
        versionName = "1.1"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    compileOnly ("de.robv.android.xposed:api:82")
}