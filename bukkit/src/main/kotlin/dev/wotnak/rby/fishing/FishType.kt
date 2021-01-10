package dev.wotnak.rby.fishing

import dev.wotnak.rby.announcement.PlayerAnnouncement
import dev.wotnak.rby.fishing.catchhandler.CatchHandler
import dev.wotnak.rby.fishing.condition.FishCondition
import org.bukkit.inventory.ItemStack
import kotlin.math.floor
import kotlin.math.max
import kotlin.math.min
import kotlin.random.Random

data class FishType(
    val name: String,
    val rarity: FishRarity,
    val displayName: String,
    val lengthMin: Double,
    val lengthMax: Double,
    val icon: ItemStack,
    val catchHandlers: List<CatchHandler>,
    val catchAnnouncement: PlayerAnnouncement,
    val conditions: Set<FishCondition> = emptySet(),
    val hasNotFishItemFormat: Boolean = false,
    val noDisplay: Boolean = false,
    val hasCatchFirework: Boolean = false,
    val additionalPrice: Double = 0.0
) {

    fun generateFish(): Fish {
        check(lengthMin <= lengthMax) { "Max-length must not be smaller than min-length" }

        val rawLength = lengthMin + Random.nextDouble() * (lengthMax - lengthMin)
        val length = clamp(floorToTwoDecimalPlaces(rawLength), lengthMin, lengthMax)
        return Fish(this, length)
    }

    private fun clamp(value: Double, min: Double, max: Double): Double =
        max(min(value, max), min)

    private fun floorToTwoDecimalPlaces(value: Double): Double =
        floor(value * 10) / 10

}
