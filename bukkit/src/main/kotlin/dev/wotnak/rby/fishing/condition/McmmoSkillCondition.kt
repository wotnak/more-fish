package dev.wotnak.rby.fishing.condition

import dev.wotnak.rby.fishing.competition.FishingCompetition
import dev.wotnak.rby.hooks.McmmoHook
import dev.wotnak.rby.hooks.PluginHook
import org.bukkit.entity.Item
import org.bukkit.entity.Player

class McmmoSkillCondition(
    private val mcmmoHook: McmmoHook,
    private val skillType: String,
    private val minLevel: Int
) : FishCondition {

    override fun check(caught: Item, fisher: Player, competition: FishingCompetition): Boolean {
        PluginHook.checkEnabled(mcmmoHook, fisher.server.pluginManager)
        return mcmmoHook.skillLevelOf(fisher, skillType) >= minLevel
    }

}
