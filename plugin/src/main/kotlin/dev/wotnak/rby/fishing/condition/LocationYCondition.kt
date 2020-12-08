package dev.wotnak.rby.fishing.condition

import dev.wotnak.rby.fishing.competition.FishingCompetition
import org.apache.commons.lang.math.DoubleRange
import org.bukkit.entity.Item
import org.bukkit.entity.Player

class LocationYCondition(
    private val range: DoubleRange
) : FishCondition {
    override fun check(caught: Item, fisher: Player, competition: FishingCompetition): Boolean {
        return range.containsDouble(fisher.location.y)
    }
}