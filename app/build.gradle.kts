import com.android.build.gradle.AppPlugin

plugins {
    id("com.android.application")
    id("subprojects-plugin")
    id("retrofit-dependencies")
}

android {
    defaultConfig {
        namespace = "com.eva.app"
    }
    buildFeatures {
        viewBinding = true
    }
}

val nav_version = "2.5.3"

dependencies {

    implementation(project(":domain"))
    implementation(project(":data"))
    implementation(project(":presentation"))

    /*
    //REST
    implementation("com.google.code.gson:gson:2.10")
    implementation("com.squareup.retrofit2:retrofit:2.6.0")
    implementation("com.squareup.retrofit2:converter-gson:2.6.0")

    //Shimmer
    implementation("com.facebook.shimmer:shimmer:0.5.0")

    //Navigation

    api("androidx.navigation:navigation-fragment-ktx:$nav_version")
    implementation("androidx.navigation:navigation-fragment-ktx:$nav_version")
    implementation("androidx.navigation:navigation-ui-ktx:$nav_version")

    //PowerSpinner
    implementation("com.github.skydoves:powerspinner:1.2.4")

    //GLIDE
    implementation("com.github.bumptech.glide:glide:4.14.2")

    //ADAPTER DELEGATES
    implementation ("com.hannesdorfmann:adapterdelegates4-kotlin-dsl-viewbinding:4.3.2")
    implementation ("com.hannesdorfmann:adapterdelegates4-kotlin-dsl:4.3.2")

    //ARCH
    implementation("androidx.activity:activity-ktx:1.6.1")
    implementation("androidx.fragment:fragment-ktx:1.5.5")
    implementation("androidx.navigation:navigation-ui-ktx:2.5.3")
    implementation("androidx.core:core-ktx:1.9.0")
    implementation("com.google.android.material:material:1.7.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.5.1")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.5.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.1")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.5.1")
     */

}