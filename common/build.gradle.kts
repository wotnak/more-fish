plugins {
    kotlin("jvm")
}

group = "dev.wotnak.rby"

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("org.spongepowered:configurate-jackson:4.0.0")
    implementation("org.spongepowered:configurate-extra-kotlin:4.0.0")
}
