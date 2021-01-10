package dev.wotnak.rby.fishing

import dev.wotnak.rby.fishing.competition.FishingCompetition
import org.bukkit.entity.Item
import org.bukkit.entity.Player

interface FishTypeTable : Map<FishRarity, Set<FishType>> {

    val defaultRarity: FishRarity?
    val rarities: Set<FishRarity>
    val types: Set<FishType>

    fun pickRandomRarity(): FishRarity

    fun pickRandomType(rarity: FishRarity = pickRandomRarity()): FishType

    fun pickRandomType(
        caught: Item,
        fisher: Player,
        competition: FishingCompetition,
        rarity: FishRarity = pickRandomRarity()
    ): FishType

}
