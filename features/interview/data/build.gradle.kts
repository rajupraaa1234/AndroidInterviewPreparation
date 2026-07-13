plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
    alias(libs.plugins.kotlin.serialization)

}

android {
    namespace = "com.interview.data"

    compileSdk = 36
}

dependencies {
    implementation(project(":network"))
    implementation(project(":core-cache"))
    implementation(project(":features:interview:domain"))
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
    testImplementation(libs.androidx.junit)
    testImplementation(libs.mockk)
    implementation(libs.kotlinx.serialization.json)
    testImplementation(libs.coroutines.test)
}