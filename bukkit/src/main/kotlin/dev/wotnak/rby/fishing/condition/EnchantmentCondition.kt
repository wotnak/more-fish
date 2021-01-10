package dev.wotnak.rby.fishing.condition

import dev.wotnak.rby.fishing.competition.FishingCompetition
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.Item
import org.bukkit.entity.Player

class EnchantmentCondition(
    private val enchantment: Enchantment,
    private val minLevel: Int
) : FishCondition {

    override fun check(
        caught: Item,
        fisher: Player,
        competition: FishingCompetition
    ): Boolean {
        val fishingRod = fisher.inventory.itemInMainHand
        return fishingRod.containsEnchantment(enchantment) && fishingRod.getEnchantmentLevel(enchantment) >= minLevel
    }

}
