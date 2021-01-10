package dev.wotnak.rby.configuration

import org.bukkit.ChatColor

internal fun String.translated(): String =
    ChatColor.translateAlternateColorCodes(Lang.ALTERNATE_COLOR_CODE, this)

internal fun List<String>.translated(): List<String> =
    this.map(String::translated)