package dev.wotnak.rby.hooks

import com.gmail.nossr50.api.ExperienceAPI
import com.gmail.nossr50.datatypes.skills.PrimarySkillType
import org.bukkit.entity.Player

class McmmoHook : PluginHook {

    override val pluginName = "mcMMO"
    override var hasHooked: Boolean = false

    override fun hook(plugin: dev.wotnak.rby.Rby) {
        hasHooked = true
    }

    fun skillLevelOf(player: Player, skillType: String): Int =
        ExperienceAPI.getLevel(player, PrimarySkillType.getSkill(skillType))

}
