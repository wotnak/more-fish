import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    kotlin("jvm") version "1.4.21"
    id("com.github.johnrengelman.shadow") version "6.1.0"
}

group = "me.elsiff"
version = "3.0.3-SNAPSHOT-fixes.2"

val pluginName = "MoreFish"
val author = "elsiff"
val website = "https://elsiff.me"
val mainPackage = "me.elsiff.morefish"
val mainClass = "$mainPackage.MoreFish"

repositories {
    jcenter()
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    maven("http://nexus.hc.to/content/repositories/pub_releases")
    maven("http://repo.citizensnpcs.co/")
    maven("http://repo.extendedclip.com/content/repositories/placeholderapi/")
    maven("http://repo.dmulloy2.net/nexus/repository/public/")
    maven("http://maven.sk89q.com/repo/")
    maven("https://repo.aikar.co/content/groups/aikar/")
    maven("https://hub.spigotmc.org/nexus/content/groups/public/")
    maven("https://jitpack.io")
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("reflect"))
    testImplementation(kotlin("test"))
    testImplementation(kotlin("test-junit"))
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.1.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.1.0")
    testImplementation("com.nhaarman.mockitokotlin2:mockito-kotlin:2.0.0")

    implementation("co.aikar:acf-core:0.5.0-SNAPSHOT")
    implementation("co.aikar:acf-paper:0.5.0-SNAPSHOT")
    implementation("com.github.elsiff:egui:1.0.2-SNAPSHOT")
    compileOnly("com.comphenix.protocol:ProtocolLib-API:4.2.1")
    compileOnly("com.sk89q:worldguard:6.1")
    compileOnly("org.spigotmc:spigot-api:1.16.4-R0.1-SNAPSHOT")
    compileOnly("net.milkbowl.vault:VaultAPI:1.6")
    compileOnly("net.citizensnpcs:citizensapi:2.0.18-SNAPSHOT")
    compileOnly("me.clip:placeholderapi:2.10.9")
    compileOnly("com.github.mcMMO-Dev:mcMMO:fa1bbd2")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

tasks.withType<ShadowJar> {
    relocate("co.aikar.commands", "${mainPackage}.acf")
}

tasks { build { dependsOn(shadowJar) } }

tasks.processResources {
    expand(
        "pluginName"      to pluginName,
        "project.version" to project.property("version"),
        "author"          to author,
        "website"         to website,
        "mainClass"       to mainClass
    )
}
