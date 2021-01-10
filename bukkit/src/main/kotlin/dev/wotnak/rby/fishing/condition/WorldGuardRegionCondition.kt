package dev.wotnak.rby.fishing.condition

import dev.wotnak.rby.fishing.competition.FishingCompetition
import dev.wotnak.rby.hooks.WorldGuardHook
import org.bukkit.entity.Item
import org.bukkit.entity.Player

class WorldGuardRegionCondition(
    private val worldGuardHooker: WorldGuardHook,
    private val regionId: String
) : FishCondition {

    override fun check(caught: Item, fisher: Player, competition: FishingCompetition): Boolean {
        return worldGuardHooker.containsLocation(regionId, caught.location)
    }

}
