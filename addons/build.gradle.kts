plugins {
    kotlin("jvm")
    id("net.minecrell.plugin-yml.bukkit")
}

group = "dev.wotnak.rby.addons"
version = "0.1-SNAPSHOT"

repositories {
    maven("https://repo.citizensnpcs.co/")
    maven("https://repo.extendedclip.com/content/repositories/placeholderapi/")
    maven("https://repo.dmulloy2.net/nexus/repository/public/")
    maven("https://maven.enginehub.org/repo/")
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
    implementation(project(":RbyApi"))

    compileOnly("com.destroystokyo.paper:paper-api:1.16.4-R0.1-SNAPSHOT")
    compileOnly("com.comphenix.protocol:ProtocolLib-API:4.4.0")
    compileOnly("com.sk89q.worldguard:worldguard-bukkit:7.0.4")
    compileOnly("com.github.MilkBowl:VaultAPI:1.7")
    compileOnly("net.citizensnpcs:citizensapi:2.0.27-SNAPSHOT")
    compileOnly("me.clip:placeholderapi:2.10.9")
    compileOnly(".:mcMMO:2.1.159")
}

bukkit {
    name = "Rby Addons"
    authors = listOf("wotnak")
    main = "$group.RbyAddons"
    apiVersion = "1.16"
    softDepend = listOf("Vault", "Citizens", "PlaceholderAPI", "ProtocolLib", "mcMMO", "WorldGuard")
}