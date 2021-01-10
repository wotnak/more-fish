package dev.wotnak.rby.announcement

import org.bukkit.entity.Player

class NoAnnouncement : PlayerAnnouncement {

    override fun receiversOf(catcher: Player): Collection<Player> =
        emptyList()

}
