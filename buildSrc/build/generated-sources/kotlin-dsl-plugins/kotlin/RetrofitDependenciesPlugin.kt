/**
 * Precompiled [retrofit-dependencies.gradle.kts][Retrofit_dependencies_gradle] script plugin.
 *
 * @see Retrofit_dependencies_gradle
 */
public
class RetrofitDependenciesPlugin : org.gradle.api.Plugin<org.gradle.api.Project> {
    override fun apply(target: org.gradle.api.Project) {
        try {
            Class
                .forName("Retrofit_dependencies_gradle")
                .getDeclaredConstructor(org.gradle.api.Project::class.java, org.gradle.api.Project::class.java)
                .newInstance(target, target)
        } catch (e: java.lang.reflect.InvocationTargetException) {
            throw e.targetException
        }
    }
}
