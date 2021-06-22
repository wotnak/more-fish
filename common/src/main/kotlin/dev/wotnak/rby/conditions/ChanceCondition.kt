package dev.wotnak.rby.conditions

import dev.wotnak.rby.Angler
import dev.wotnak.rby.FishingCondition
import dev.wotnak.rby.Location
import dev.wotnak.rby.Rby
import org.spongepowered.configurate.ConfigurationNode
import kotlin.random.Random

class ChanceCondition(attributes: ConfigurationNode) : FishingCondition(attributes) {

    val chance : Float

    init {
        this.chance = attributes.node("chance").getFloat(100f).coerceIn(0f, 100f)
        Rby.fishTypeRegistry.getAll().values.map { it ->
            val chanceConditions = it.conditions.filterIsInstance<ChanceCondition>()

            it.conditions.filterIsInstance<ChanceCondition>().map {  }
        }
        var chanceTotal = Rby.fishTypeRegistry.getAll().values.reduce { total, type ->
            return total + (type is ChanceCondition ? type.chance : 100)
        }
        Rby.fishTypeRegistry.getAll().forEach { id, type ->
            type.conditions.filterIsInstance<ChanceCondition>().forEach {
                chanceTotal += it.chance
            }
        }
    }

    companion object {
        var fishChanceTotalCache = 0
    }

    override fun check(angler: Angler, location: Location) {

        Rby.fishTypeRegistry.getAll().forEach { id, type ->

        }
        return Random.nextDouble()
    }
}
