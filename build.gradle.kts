import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.21" apply false
}

subprojects {
    version = "0.1-SNAPSHOT"

    repositories {
        jcenter()
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "1.8"
    }

    tasks.withType<AbstractArchiveTask> {
        archiveBaseName.set("${rootProject.name}-${this@subprojects.name}")
        from("${rootDir.path}/LICENSE.md")
    }

}
