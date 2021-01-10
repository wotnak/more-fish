package dev.wotnak.rby.announcement

import org.bukkit.entity.Player

class BaseOnlyAnnouncement : PlayerAnnouncement {

    override fun receiversOf(catcher: Player): Collection<Player> =
        listOf(catcher)

}
