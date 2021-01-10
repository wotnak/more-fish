package dev.wotnak.rby.item

import dev.wotnak.rby.fishing.Fish
import dev.wotnak.rby.fishing.FishTypeTable
import org.bukkit.NamespacedKey
import org.bukkit.inventory.meta.ItemMeta
import org.bukkit.persistence.PersistentDataType

class FishItemTagReader(
    private val fishTypeTable: FishTypeTable,
    private val fishTypeKey: NamespacedKey,
    private val fishLengthKey: NamespacedKey
) {

    fun canRead(itemMeta: ItemMeta): Boolean {
        return itemMeta.persistentDataContainer.let { tags ->
            tags.has(fishTypeKey, PersistentDataType.STRING) && tags.has(fishLengthKey, PersistentDataType.DOUBLE)
        }
    }

    fun read(itemMeta: ItemMeta): Fish {
        return itemMeta.persistentDataContainer.let { tags ->
            require(tags.has(fishTypeKey, PersistentDataType.STRING)) { "Item meta must have fish type tag" }
            require(tags.has(fishLengthKey, PersistentDataType.DOUBLE)) { "Item meta must have fish length tag" }

            val typeName = tags.get(fishTypeKey, PersistentDataType.STRING)
            val type = fishTypeTable.types.find { it.name == typeName }
                ?: throw IllegalStateException("Fish type doesn't exist")
            val length = tags.get(fishLengthKey, PersistentDataType.DOUBLE)
            Fish(type, length!!)
        }
    }

}
