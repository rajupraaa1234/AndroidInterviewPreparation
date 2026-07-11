plugins {
    alias(libs.plugins.jetbrains.kotlin.jvm)
}

dependencies {
    implementation(libs.javax.inject)
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.2")
}