package dev.wotnak.rby.announcement

import org.bukkit.entity.Player

class RangedAnnouncement(
    private val radius: Double
) : PlayerAnnouncement {

    override fun receiversOf(catcher: Player): Collection<Player> =
        catcher.world.players.filter { it.location.distance(catcher.location) <= radius }

}
