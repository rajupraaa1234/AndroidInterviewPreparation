plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.interview.ui"

    compileSdk = 36
}

dependencies {
    implementation(project(":sdk-compose"))
}
