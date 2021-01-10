package dev.wotnak.rby.configuration.loader

import dev.wotnak.rby.announcement.PlayerAnnouncement
import dev.wotnak.rby.configuration.ConfigurationValueAccessor

class PlayerAnnouncementLoader : CustomLoader<PlayerAnnouncement> {

    override fun loadFrom(section: ConfigurationValueAccessor, path: String): PlayerAnnouncement {
        val configuredValue = section.double(path)
        return when (configuredValue.toInt()) {
            -2 -> PlayerAnnouncement.ofEmpty()
            -1 -> PlayerAnnouncement.ofServerBroadcast()
            0 -> PlayerAnnouncement.ofBaseOnly()
            else -> PlayerAnnouncement.ofRanged(configuredValue)
        }
    }

}
