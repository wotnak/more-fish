package dev.wotnak.rby

import org.spongepowered.configurate.ConfigurationNode;

abstract class FishEffect(
    val attributes: ConfigurationNode
) {
    abstract fun apply(caughtFish: CaughtFish, angler: Angler)
}
