
import com.android.build.gradle.BaseExtension
import org.gradle.api.Plugin
import org.gradle.api.*

class SubprojectsPlugin : Plugin<Project>  {
    override fun apply(project: Project) {



        val androidExtension = project.extensions.getByName("android")
        if (androidExtension is BaseExtension) {
            androidExtension.apply {
                android {
                    namespace = "com.eva.test"
                    compileSdk = 33

                    defaultConfig {
                        applicationId = "com.eva.test"
                        minSdk = 21
                        targetSdk = 33
                        versionCode = 1
                        versionName = "1.0"

                        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                    }

                    buildTypes {
                        release {
                            isMinifyEnabled = false
                            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
                        }
                    }
                    compileOptions {
                        sourceCompatibility(JavaVersion.VERSION_1_8)
                        targetCompatibility(JavaVersion.VERSION_1_8)
                    }
                    kotlinOptions {
                        jvmTarget = "1.8"
                    }
                }
            }
        }
    }
}