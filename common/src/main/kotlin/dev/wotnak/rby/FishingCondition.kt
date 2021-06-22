package dev.wotnak.rby

import org.spongepowered.configurate.ConfigurationNode
import org.spongepowered.configurate.ConfigurationNodeFactory;

abstract class FishingCondition(
    attributes: ConfigurationNode
) {
    abstract fun check(angler: Angler, location: Location)
}

