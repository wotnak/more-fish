package dev.wotnak.rby

class FishTypesRegistry {

    private val types = mutableMapOf<String, FishType>()

    fun registerType(id: String, type: FishType) {
        this.types[id] = type
    }

    fun get(id: String) : FishType? = this.types[id]

    fun getAll() = this.types.toMap()
}
