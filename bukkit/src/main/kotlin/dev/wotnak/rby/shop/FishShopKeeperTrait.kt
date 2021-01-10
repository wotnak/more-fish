package dev.wotnak.rby.shop

import dev.wotnak.rby.configuration.Config
import dev.wotnak.rby.configuration.Lang
import net.citizensnpcs.api.event.NPCRightClickEvent
import net.citizensnpcs.api.trait.Trait
import net.citizensnpcs.api.trait.TraitName
import org.bukkit.event.EventHandler

@TraitName("fishshop")
class FishShopKeeperTrait : Trait("fishshop") {

    @EventHandler
    fun onClickNpc(event: NPCRightClickEvent) {
        if (event.npc == this.npc && dev.wotnak.rby.Rby.instance.isEnabled) {
            if (Config.standard.boolean("fish-shop.enable")) {
                fishShop.openGuiTo(event.clicker)
            } else {
                event.clicker.sendMessage(Lang.text("shop-disabled"))
            }
        }
    }

    companion object {
        private lateinit var fishShop: FishShop

        fun init(fishShop: FishShop) {
            this.fishShop = fishShop
        }
    }

}
