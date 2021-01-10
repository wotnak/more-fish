package dev.wotnak.rby.configuration.format

import dev.wotnak.rby.hooks.PlaceholderApiHook
import org.bukkit.entity.Player

interface Format<T, R> {

    fun replace(vararg pairs: Pair<String, Any>): T

    fun replace(pairs: Map<String, Any>): T =
        replace(*pairs.toList().toTypedArray())

    fun output(player: Player? = null): R

    companion object {
        private lateinit var placeholderApiHook: PlaceholderApiHook

        fun init(placeholderApiHook: PlaceholderApiHook) {
            this.placeholderApiHook = placeholderApiHook
        }

        fun tryReplacing(string: String, player: Player?): String {
            return if (::placeholderApiHook.isInitialized && placeholderApiHook.hasHooked)
                placeholderApiHook.tryReplacing(string, player)
            else
                string
        }
    }

}
