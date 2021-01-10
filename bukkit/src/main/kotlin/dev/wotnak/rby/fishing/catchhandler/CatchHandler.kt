package dev.wotnak.rby.fishing.catchhandler

import dev.wotnak.rby.fishing.Fish
import org.bukkit.entity.Player

interface CatchHandler {

    fun handle(catcher: Player, fish: Fish)

}
