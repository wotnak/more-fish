rootProject.name = "rby"
include("bukkit")
include("fabric")
include("common")

pluginManagement {
    repositories {
        jcenter()
        gradlePluginPortal()
        maven("https://maven.fabricmc.net/") { name = "Fabric" }
    }
}