package dev.wotnak.rby.hooker

import org.bukkit.plugin.PluginManager

interface PluginHooker {

    val pluginName: String
    var hasHooked: Boolean

    fun canHook(pluginManager: PluginManager) = pluginManager.isPluginEnabled(pluginName)

    fun hook(plugin: dev.wotnak.rby.Rby)

    fun hookIfEnabled(plugin: dev.wotnak.rby.Rby) {
        if (canHook(plugin.server.pluginManager)) {
            hook(plugin)
        }
    }

    companion object {
        fun checkEnabled(hooker: PluginHooker, pluginManager: PluginManager) {
            check(hooker.canHook(pluginManager)) { "${hooker.pluginName} must be enabled" }
        }

        fun checkHooked(hooker: PluginHooker) {
            check(hooker.hasHooked) { "${hooker.pluginName} must be hooked" }
        }
    }

}
