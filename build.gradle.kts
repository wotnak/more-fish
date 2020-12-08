import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.21" apply false
    id("net.minecrell.plugin-yml.bukkit") version "0.3.0" apply false
}

subprojects {
    version = "0.1-SNAPSHOT"

    repositories {
        jcenter()
        maven("https://papermc.io/repo/repository/maven-public/")
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "1.8"
    }
}
