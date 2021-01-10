package dev.wotnak.rby.fishing.competition

import dev.wotnak.rby.util.NumberUtils
import org.bukkit.OfflinePlayer
import org.bukkit.plugin.Plugin

class Prize(
    private val commands: List<String>
) {

    fun giveTo(player: OfflinePlayer, rankNumber: Int, plugin: Plugin) {
        if (!player.isOnline) {
            val ordinal = NumberUtils.ordinalOf(rankNumber)
            plugin.logger.warning("$ordinal fisher ${player.name} isn't online! Contest prizes may not be sent.")
        }

        val server = plugin.server
        for (command in commands) {
            server.dispatchCommand(server.consoleSender, command.replace("@p", player.name!!))
        }
    }

}
