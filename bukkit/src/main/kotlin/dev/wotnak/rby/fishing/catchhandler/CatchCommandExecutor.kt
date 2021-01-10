package dev.wotnak.rby.fishing.catchhandler

import dev.wotnak.rby.fishing.Fish
import org.bukkit.entity.Player

class CatchCommandExecutor(
    private val commands: List<String>
) : CatchHandler {

    override fun handle(catcher: Player, fish: Fish) {
        val server = catcher.server
        for (command in commands) {
            server.dispatchCommand(server.consoleSender, command.replace("@p", catcher.name))
        }
    }

}
