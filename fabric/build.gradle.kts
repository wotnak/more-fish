plugins {
    kotlin("jvm")
    id("fabric-loom") version "0.5-SNAPSHOT"
}

group = "dev.wotnak.rby.fabric"

repositories {
    maven("https://maven.fabricmc.net/") { name = "Fabric" }
}

dependencies {
    implementation(project(":common"))
    minecraft("com.mojang:minecraft:${property("minecraft_version")}")
    mappings("net.fabricmc:yarn:${property("yarn_mappings")}:v2")
    modImplementation("net.fabricmc:fabric-loader:${property("loader_version")}")
    modImplementation("net.fabricmc:fabric-language-kotlin:${property("fabric_kotlin_version")}")
    modImplementation("net.fabricmc.fabric-api:fabric-api:${property("fabric_version")}")
}

tasks.processResources {
    filesMatching("fabric.mod.json") {
        expand(
            mutableMapOf(
                "version" to version
            )
        )
    }
}
