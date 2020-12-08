package dev.wotnak.rby.fishing.catchhandler

import dev.wotnak.rby.announcement.PlayerAnnouncement
import dev.wotnak.rby.configuration.Config
import dev.wotnak.rby.configuration.Lang
import dev.wotnak.rby.configuration.format.TextFormat
import dev.wotnak.rby.fishing.Fish
import dev.wotnak.rby.fishing.competition.FishingCompetition
import org.bukkit.entity.Player

/**
 * Created by elsiff on 2019-01-13.
 */
class NewFirstBroadcaster(
    private val competition: FishingCompetition
) : AbstractBroadcaster() {
    override val catchMessageFormat: TextFormat
        get() = Lang.format("get-1st")

    override fun meetBroadcastCondition(catcher: Player, fish: Fish): Boolean {
        return competition.isEnabled() && competition.willBeNewFirst(catcher, fish)
    }

    override fun announcement(fish: Fish): PlayerAnnouncement {
        return Config.newFirstAnnouncement
    }
}