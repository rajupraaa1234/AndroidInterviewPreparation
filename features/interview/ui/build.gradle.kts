plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
}

android {
    namespace = "com.mobile.interview.ui"

    compileSdk = 36
}

dependencies {
    implementation(project(":sdk-compose"))
    implementation(libs.javax.inject)
    implementation(project(":features:interview:domain"))
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
}
