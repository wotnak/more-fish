package dev.wotnak.rby.fishing.catchhandler

import dev.wotnak.rby.fishing.Fish
import org.bukkit.entity.Player

/**
 * Created by elsiff on 2018-12-25.
 */
interface CatchHandler {
    fun handle(catcher: Player, fish: Fish)
}