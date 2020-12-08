package dev.wotnak.rby.fishing.condition

import dev.wotnak.rby.fishing.competition.FishingCompetition
import org.bukkit.entity.Item
import org.bukkit.entity.Player

/**
 * Created by elsiff on 2018-12-28.
 */
interface FishCondition {
    fun check(
        caught: Item,
        fisher: Player,
        competition: FishingCompetition
    ): Boolean
}