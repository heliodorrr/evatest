plugins {
    `java-gradle-plugin`
    `kotlin-dsl`
    //id("org.jetbrains.kotlin.android")
}

gradlePlugin {
    plugins {
        register("subprojects-plugin") {
            id = "subprojects-plugin"
            implementationClass = "SubprojectsPlugin"
        }
    }
}

repositories {
    google()
    mavenCentral()
    jcenter()
}

dependencies {
    compileOnly(gradleApi())



    //classpath("org.gradle.kotlin:gradle-kotlin-dsl-plugins:4.0.2")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.0")
    implementation("com.android.tools.build:gradle:7.3.1")
    implementation(kotlin("gradle-plugin", "1.8.0"))

}