plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
}

android {
    namespace = "com.interview.data"

    compileSdk = 36
}

dependencies {
    implementation(project(":network"))
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
}
