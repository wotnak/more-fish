package dev.wotnak.rby.hooker

/**
 * Created by elsiff on 2018-12-31.
 */
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