package dev.wotnak.rby.shop

import me.elsiff.egui.GuiOpener
import dev.wotnak.rby.configuration.Config
import dev.wotnak.rby.configuration.ConfigurationSectionAccessor
import dev.wotnak.rby.fishing.Fish
import dev.wotnak.rby.hooker.VaultHooker
import dev.wotnak.rby.item.FishItemStackConverter
import dev.wotnak.rby.util.OneTickScheduler
import net.milkbowl.vault.economy.Economy
import org.bukkit.entity.Player
import kotlin.math.floor

class FishShop(
    private val guiOpener: GuiOpener,
    private val oneTickScheduler: OneTickScheduler,
    private val converter: FishItemStackConverter,
    private val vault: VaultHooker
) {

    private val economy: Economy
        get() {
            check(vault.hasHooked) { "Vault must be hooked for fish shop feature" }
            check(vault.hasEconomy()) { "Vault doesn't have economy plugin" }
            check(vault.economy.isEnabled) { "Economy must be enabled" }
            return vault.economy
        }
    private val shopConfig: ConfigurationSectionAccessor
        get() = Config.standard["fish-shop"]

    val enabled: Boolean
        get() = shopConfig.boolean("enable")

    private val priceMultiplier: Double
        get() = shopConfig.double("multiplier")

    private val roundDecimalPoints: Boolean
        get () = shopConfig.boolean("round-decimal-points")

    fun sell(player: Player, fish: Fish) {
        val price = priceOf(fish)
        economy.depositPlayer(player, price)
    }

    fun sell(player: Player, fish: Collection<Fish>) {
        val price = fish.map(this::priceOf).sum()
        economy.depositPlayer(player, price)
    }

    fun priceOf(fish: Fish): Double {
        val rarityPrice = fish.type.rarity.additionalPrice
        val price = (priceMultiplier * fish.length) + rarityPrice

        return if (roundDecimalPoints) {
            floor(price)
        } else {
            price
        }
    }

    fun openGuiTo(player: Player) {
        val gui = FishShopGui(this, converter, oneTickScheduler, player)
        guiOpener.open(player, gui)
    }

}
