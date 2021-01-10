package dev.wotnak.rby.hooks

import dev.wotnak.rby.shop.FishShopKeeperTrait
import net.citizensnpcs.api.CitizensAPI
import net.citizensnpcs.api.trait.TraitInfo

class CitizensHook : PluginHook {

    override val pluginName = "Citizens"
    override var hasHooked = false
    private lateinit var traitInfo: TraitInfo

    override fun hook(plugin: dev.wotnak.rby.Rby) {
        traitInfo = TraitInfo.create(FishShopKeeperTrait::class.java)
        CitizensAPI.getTraitFactory().registerTrait(traitInfo)
        FishShopKeeperTrait.init(plugin.fishShop)
        hasHooked = true
    }

    fun dispose() {
        CitizensAPI.getTraitFactory().deregisterTrait(traitInfo)
    }

}
