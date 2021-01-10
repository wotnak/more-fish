package dev.wotnak.rby.configuration.loader

import dev.wotnak.rby.configuration.ConfigurationValueAccessor
import dev.wotnak.rby.configuration.translated
import dev.wotnak.rby.hooker.PluginHooker
import dev.wotnak.rby.hooker.ProtocolLibHooker
import dev.wotnak.rby.item.edit
import dev.wotnak.rby.item.editIfHas
import dev.wotnak.rby.item.editIfIs
import dev.wotnak.rby.util.NamespacedKeyUtils
import org.bukkit.Bukkit
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.Damageable
import org.bukkit.inventory.meta.ItemMeta
import org.bukkit.inventory.meta.SkullMeta
import java.util.*

class CustomItemStackLoader(
    private val enchantmentMapLoader: EnchantmentMapLoader
) : CustomLoader<ItemStack> {

    lateinit var protocolLib: ProtocolLibHooker

    override fun loadFrom(section: ConfigurationValueAccessor, path: String): ItemStack {
        section[path].let {
            val material = NamespacedKeyUtils.material(it.string("id"))
            val amount = it.int("amount", 1)
            var itemStack = ItemStack(material, amount)

            itemStack.edit<ItemMeta> {
                lore = it.strings("lore", emptyList()).translated()
                for ((enchantment, level) in enchantmentMapLoader.loadFrom(it, "enchantments")) {
                    addEnchant(enchantment, level, true)
                }
                isUnbreakable = it.boolean("unbreakable", false)
            }

            itemStack.editIfIs<Damageable> {
                damage = it.int("durability", 0)
            }

            if (it.contains("skull-uuid")) {
                itemStack.editIfHas<SkullMeta> {
                    val uuid = UUID.fromString(it.string("skull-uuid"))
                    owningPlayer = Bukkit.getOfflinePlayer(uuid)
                }
            }

            if (it.contains("skull-texture")) {
                PluginHooker.checkHooked(protocolLib)
                itemStack = protocolLib.skullNbtHandler.writeTexture(itemStack, it.string("skull-texture"))
            }
            return itemStack
        }
    }

}
