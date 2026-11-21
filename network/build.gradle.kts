plugins {
    alias(libs.plugins.android.library)
}

android {
    namespace = "com.mobile.network"

    compileSdk = 36
}

dependencies {
    api(libs.retrofit)
    api(libs.retrofit.gson)
    api(libs.okhttp)
    api(libs.okhttp.logging)
}