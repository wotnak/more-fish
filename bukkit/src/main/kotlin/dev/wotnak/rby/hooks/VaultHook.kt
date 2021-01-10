package dev.wotnak.rby.hooks

import net.milkbowl.vault.economy.Economy

class VaultHook : PluginHook {

    override val pluginName = "Vault"
    override var hasHooked = false
    lateinit var economy: Economy

    override fun hook(plugin: dev.wotnak.rby.Rby) {
        PluginHook.checkEnabled(this, plugin.server.pluginManager)

        val registration = plugin.server.servicesManager.getRegistration(Economy::class.java)
        if (registration != null) {
            economy = registration.provider
        }
        hasHooked = true
    }

    fun hasEconomy(): Boolean {
        return ::economy.isInitialized
    }

}
