
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.hilt)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.example.androidinterviewprepration"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.androidinterviewprepration"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    flavorDimensions += "environment"

    productFlavors {

        create("dev") {
            dimension = "environment"
            applicationIdSuffix = ".dev"
            versionNameSuffix = "-DEV"

            buildConfigField(
                "String",
                "BASE_URL",
                "\"https://fake-json-api.mock.beeceptor.com/users/\""
            )
        }

        create("qe") {
            dimension = "environment"

            applicationIdSuffix = ".qe"
            versionNameSuffix = "-QE"

            buildConfigField(
                "String",
                "BASE_URL",
                "\"https://jsonplaceholder.typicode.com/todos/1/\""
            )
        }

        create("prod") {
            dimension = "environment"

            buildConfigField(
                "String",
                "BASE_URL",
                "\"https://fake-json-api.mock.beeceptor.com/users/\""
            )
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            isDebuggable = false
            signingConfig = signingConfigs.getByName("debug")
            buildConfigField(
                "boolean",
                "ENABLE_LOGS",
                "false"
            )
        }

        debug {
            isDebuggable = true
            buildConfigField(
                "boolean",
                "ENABLE_LOGS",
                "true"
            )
        }

        create("staging") {
            initWith(getByName("debug"))
            isDebuggable = true
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    buildFeatures {
        compose = true
    }
    android.buildFeatures.buildConfig = true
}

dependencies {
    implementation(platform(libs.compose.bom))
    androidTestImplementation(platform(libs.compose.bom))

    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
    debugImplementation(libs.androidx.compose.ui.tooling)
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)

    implementation(project(":features:interview:ui"))
    implementation(project(":features:interview:data"))
}