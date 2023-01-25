
import com.android.build.gradle.*
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

class SubprojectsPlugin : Plugin<Project>  {

    override fun apply(project: Project) {
        with(project) {
            plugins()
            android()
            dependencies()
        }
    }

    private fun Project.plugins() {
        project.plugins.apply("org.jetbrains.kotlin.kapt")
        project.plugins.apply("org.jetbrains.kotlin.android")
    }

    private fun Project.android() {
        val androidExtension = project.extensions.getByName("android")
        if (androidExtension is BaseExtension) {
            androidExtension.apply {

                //println("${project.name}")
                //namespace = "com.eva.test"
                compileSdkVersion(33)

                defaultConfig {
                    //applicationId = "com.eva.test"
                    minSdk = 21
                    targetSdk = 33
                    versionCode = 1
                    versionName = "1.0"

                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

                    multiDexEnabled = true
                }

                //buildFeatures.viewBinding = true

                buildTypes {
                    getByName("release") {
                        isMinifyEnabled = false
                        proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
                    }
                }
                compileOptions {
                    isCoreLibraryDesugaringEnabled = true
                    sourceCompatibility(JavaVersion.VERSION_1_8)
                    targetCompatibility(JavaVersion.VERSION_1_8)
                }
                project.tasks.withType(KotlinCompile::class.java) {
                    kotlinOptions {
                        jvmTarget = "1.8"
                    }
                }

            }
        }
    }

    private fun Project.dependencies() = with(project.dependencies) {
        val daggerVersion = "2.44"

        //Desugaring
        add("coreLibraryDesugaring", "com.android.tools:desugar_jdk_libs:1.2.2")

        add("implementation", "com.google.dagger:dagger:$daggerVersion")
        add("kapt", "com.google.dagger:dagger-compiler:$daggerVersion")

        add("implementation", "com.google.dagger:dagger-android-support:$daggerVersion")
        add("kapt", "com.google.dagger:dagger-android-processor:$daggerVersion")

        add("implementation", "androidx.core:core-ktx:1.9.0")
        add("implementation", "androidx.appcompat:appcompat:1.6.0")

        add("testImplementation", "junit:junit:4.13.2")
        add("androidTestImplementation", "androidx.test.ext:junit:1.1.5")
        add("androidTestImplementation", "androidx.test.espresso:espresso-core:3.5.1")
    }

}