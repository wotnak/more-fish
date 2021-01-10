package dev.wotnak.rby.hooks

class ProtocolLibHook : PluginHook {

    override val pluginName = "ProtocolLib"
    override var hasHooked = false
    lateinit var skullNbtHandler: SkullNbtHandler

    override fun hook(plugin: dev.wotnak.rby.Rby) {
        PluginHook.checkEnabled(this, plugin.server.pluginManager)

        skullNbtHandler = SkullNbtHandler()
        hasHooked = true
    }

}
