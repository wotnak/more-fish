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
    maven("https://repo.citizensnpcs.co/")
    maven("https://repo.extendedclip.com/content/repositories/placeholderapi/")
    maven("https://repo.dmulloy2.net/nexus/repository/public/")
    maven("https://maven.enginehub.org/repo/")
    maven("https://repo.aikar.co/content/groups/aikar/")
    maven("https://papermc.io/repo/repository/maven-public/")
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
    //compileOnly("com.github.mcMMO-Dev:mcMMO:dacd846fe7")
    compileOnly(".:mcMMO:2.1.159")
}

tasks.withType<JavaCompile> {
    options.compilerArgs.add("-parameters")
    options.isFork = true
    options.forkOptions.executable = "javac"
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
    kotlinOptions.javaParameters = true
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
