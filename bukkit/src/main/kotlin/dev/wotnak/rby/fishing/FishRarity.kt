package dev.wotnak.rby.fishing

import dev.wotnak.rby.announcement.PlayerAnnouncement
import dev.wotnak.rby.fishing.catchhandler.CatchHandler
import org.bukkit.ChatColor

data class FishRarity(
    val name: String,
    val displayName: String,
    val default: Boolean,
    val probability: Double,
    val color: ChatColor,
    val catchHandlers: List<CatchHandler>,
    val catchAnnouncement: PlayerAnnouncement,
    val hasNotFishItemFormat: Boolean = false,
    val noDisplay: Boolean = false,
    val hasCatchFirework: Boolean = false,
    val additionalPrice: Double = 0.0
)