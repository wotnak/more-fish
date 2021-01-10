package dev.wotnak.rby.fishing.condition

import dev.wotnak.rby.fishing.competition.FishingCompetition
import org.bukkit.entity.Item
import org.bukkit.entity.Player

class CompetitionCondition(
    private val state: FishingCompetition.State
) : FishCondition {

    override fun check(
        caught: Item,
        fisher: Player,
        competition: FishingCompetition
    ): Boolean {
        return competition.state == state
    }

}
