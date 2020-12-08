package dev.wotnak.rby.fishing.condition

import dev.wotnak.rby.fishing.competition.FishingCompetition
import dev.wotnak.rby.hooker.McmmoHooker
import dev.wotnak.rby.hooker.PluginHooker
import org.bukkit.entity.Item
import org.bukkit.entity.Player

/**
 * Created by elsiff on 2019-01-12.
 */
class McmmoSkillCondition(
    private val mcmmoHooker: McmmoHooker,
    private val skillType: String,
    private val minLevel: Int
) : FishCondition {
    override fun check(caught: Item, fisher: Player, competition: FishingCompetition): Boolean {
        PluginHooker.checkEnabled(mcmmoHooker, fisher.server.pluginManager)
        return mcmmoHooker.skillLevelOf(fisher, skillType) >= minLevel
    }
}