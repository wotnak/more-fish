package dev.wotnak.rby.fishing.catchhandler

import dev.wotnak.rby.fishing.Fish
import dev.wotnak.rby.fishing.competition.FishingCompetition
import dev.wotnak.rby.fishing.competition.Record
import org.bukkit.entity.Player

/**
 * Created by elsiff on 2018-12-25.
 */
class CompetitionRecordAdder(
    private val competition: FishingCompetition
) : CatchHandler {
    override fun handle(catcher: Player, fish: Fish) {
        if (competition.isEnabled()) {
            competition.putRecord(Record(catcher, fish))
        }
    }
}