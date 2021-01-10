package dev.wotnak.rby.hooks

import org.bukkit.plugin.PluginManager

interface PluginHook {

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
        fun checkEnabled(hook: PluginHook, pluginManager: PluginManager) {
            check(hook.canHook(pluginManager)) { "${hook.pluginName} must be enabled" }
        }

        fun checkHooked(hook: PluginHook) {
            check(hook.hasHooked) { "${hook.pluginName} must be hooked" }
        }
    }

}
