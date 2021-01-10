package dev.wotnak.rby.announcement

import org.bukkit.entity.Player

class ServerAnnouncement : PlayerAnnouncement {

    override fun receiversOf(catcher: Player): Collection<Player> =
        catcher.server.onlinePlayers

}
