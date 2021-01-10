package dev.wotnak.rby.item

import dev.wotnak.rby.fishing.Fish
import org.bukkit.NamespacedKey
import org.bukkit.inventory.meta.ItemMeta
import org.bukkit.persistence.PersistentDataType

class FishItemTagWriter(
    private val fishTypeKey: NamespacedKey,
    private val fishLengthKey: NamespacedKey
) {

    fun write(itemMeta: ItemMeta, fish: Fish) {
        itemMeta.persistentDataContainer.run {
            set(fishTypeKey, PersistentDataType.STRING, fish.type.name)
            set(fishLengthKey, PersistentDataType.DOUBLE, fish.length)
        }
    }

}
