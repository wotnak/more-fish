package dev.wotnak.rby.hooker

class ProtocolLibHooker : PluginHooker {

    override val pluginName = "ProtocolLib"
    override var hasHooked = false
    lateinit var skullNbtHandler: SkullNbtHandler

    override fun hook(plugin: dev.wotnak.rby.Rby) {
        PluginHooker.checkEnabled(this, plugin.server.pluginManager)

        skullNbtHandler = SkullNbtHandler()
        hasHooked = true
    }

}
