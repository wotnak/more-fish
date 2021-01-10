import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
    id("com.github.johnrengelman.shadow") version "6.1.0"
    id("net.minecrell.plugin-yml.bukkit") version "0.3.0"
}

group = "dev.wotnak.rby"

repositories {
    maven("https://papermc.io/repo/repository/maven-public/")
    maven("https://repo.citizensnpcs.co/")
    maven("https://repo.extendedclip.com/content/repositories/placeholderapi/")
    maven("https://repo.dmulloy2.net/nexus/repository/public/")
    maven("https://maven.enginehub.org/repo/")
    maven("https://repo.aikar.co/content/groups/aikar/")
    maven("https://jitpack.io")
    maven {
        setUrl("https://papermc.io/ci/view/%20%20Plugins/job/mcMMO/com.gmail.nossr50.mcMMO\$mcMMO/1107/artifact/")
        artifactUrls("https://papermc.io/ci/view/%20%20Plugins/job/mcMMO/com.gmail.nossr50.mcMMO\$mcMMO/1107/artifact/com.gmail.nossr50.mcMMO/")
        metadataSources {
            mavenPom()
            artifact()
        }
    }
}

dependencies {
    implementation(project(":common"))
    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("reflect"))
    implementation("co.aikar:acf-paper:0.5.0-SNAPSHOT")
    implementation("com.github.elsiff:egui:1.0.2-SNAPSHOT")

    compileOnly("com.destroystokyo.paper:paper-api:1.16.4-R0.1-SNAPSHOT")
    compileOnly("com.comphenix.protocol:ProtocolLib-API:4.4.0")
    compileOnly("com.sk89q.worldguard:worldguard-bukkit:7.0.4")
    compileOnly("com.github.MilkBowl:VaultAPI:1.7")
    compileOnly("net.citizensnpcs:citizensapi:2.0.27-SNAPSHOT")
    compileOnly("me.clip:placeholderapi:2.10.9")
    compileOnly(".:mcMMO:2.1.159")
}

tasks.withType<JavaCompile> {
    options.compilerArgs.add("-parameters")
    options.isFork = true
    options.forkOptions.executable = "javac"
}

tasks.withType<KotlinCompile> {
    kotlinOptions.javaParameters = true
}

tasks.withType<ShadowJar> {
    relocate("co.aikar.commands", "${group}.acf")
}

tasks { build { dependsOn(shadowJar) } }

bukkit {
    name = "Rby"
    authors = listOf("elsiff", "wotnak")
    main = "$group.Rby"
    apiVersion = "1.16"
    softDepend = listOf("Vault", "Citizens", "PlaceholderAPI", "ProtocolLib", "mcMMO", "WorldGuard")
}
