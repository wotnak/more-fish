package dev.wotnak.rby.configuration

import org.bukkit.configuration.ConfigurationSection

class ConfigurationSectionAccessor(
    override val currentSection: ConfigurationSection
) : ConfigurationValueAccessor() {

    val name: String
        get() = currentSection.name

}
