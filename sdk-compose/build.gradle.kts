plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.mobile.sdk.compose"

    compileSdk = 36

    defaultConfig {
        minSdk = 24
    }

    buildFeatures {
        compose = true
    }
}

dependencies {
    api(platform(libs.compose.bom))
    api(libs.compose.ui)
    api(libs.compose.material3)
    api(libs.compose.ui.tooling.preview)
    api(libs.androidx.compose.runtime)
    api(libs.androidx.activity.compose)
    api(libs.navigation.compose)
    api(libs.lifecycle.viewmodel.compose)
    debugApi(libs.compose.ui.tooling)
}