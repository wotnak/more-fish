package dev.wotnak.rby.configuration.loader

import dev.wotnak.rby.configuration.ConfigurationValueAccessor
import org.bukkit.ChatColor

class ChatColorLoader : CustomLoader<ChatColor> {

    override fun loadFrom(section: ConfigurationValueAccessor, path: String): ChatColor {
        val colorName = section.string(path).toUpperCase()
        return ChatColor.valueOf(colorName)
    }

}
