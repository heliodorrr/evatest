plugins {
    id("com.android.library")
    id("subprojects-plugin")
    id("retrofit-dependencies")
}
android {
    defaultConfig {
        namespace = "com.eva.data"
    }

}
dependencies {
    implementation(project(":domain"))

    val room_version = "2.5.0"

    implementation("androidx.room:room-runtime:$room_version")
    annotationProcessor("androidx.room:room-compiler:$room_version")

    kapt("androidx.room:room-compiler:$room_version")

    implementation("androidx.room:room-ktx:$room_version")
}