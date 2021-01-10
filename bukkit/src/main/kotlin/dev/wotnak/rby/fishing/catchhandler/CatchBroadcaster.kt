package dev.wotnak.rby.fishing.catchhandler

import dev.wotnak.rby.announcement.NoAnnouncement
import dev.wotnak.rby.announcement.PlayerAnnouncement
import dev.wotnak.rby.configuration.Lang
import dev.wotnak.rby.configuration.format.TextFormat
import dev.wotnak.rby.fishing.Fish
import org.bukkit.entity.Player

class CatchBroadcaster : AbstractBroadcaster() {

    override val catchMessageFormat: TextFormat
        get() = Lang.format("catch-fish")

    override fun meetBroadcastCondition(catcher: Player, fish: Fish): Boolean {
        return fish.type.catchAnnouncement !is NoAnnouncement
    }

    override fun announcement(fish: Fish): PlayerAnnouncement {
        return fish.type.catchAnnouncement
    }

}
