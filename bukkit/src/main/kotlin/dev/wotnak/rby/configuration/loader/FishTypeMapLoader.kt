package dev.wotnak.rby.configuration.loader

import dev.wotnak.rby.configuration.ConfigurationValueAccessor
import dev.wotnak.rby.configuration.translated
import dev.wotnak.rby.fishing.FishRarity
import dev.wotnak.rby.fishing.FishType
import dev.wotnak.rby.fishing.catchhandler.CatchCommandExecutor
import dev.wotnak.rby.fishing.catchhandler.CatchHandler

class FishTypeMapLoader(
    private val fishRaritySetLoader: FishRaritySetLoader,
    private val customItemStackLoader: CustomItemStackLoader,
    private val fishConditionSetLoader: FishConditionSetLoader,
    private val playerAnnouncementLoader: PlayerAnnouncementLoader
) : CustomLoader<Map<FishRarity, Set<FishType>>> {

    override fun loadFrom(section: ConfigurationValueAccessor, path: String): Map<FishRarity, Set<FishType>> {
        section[path].let { root ->
            val rarities = fishRaritySetLoader.loadFrom(root, "rarity-list")
            return root["fish-list"].children.map { groupByRarity ->
                val rarity = findRarity(rarities, groupByRarity.name)
                val fishTypes = groupByRarity.children.map {
                    val catchHandlers = mutableListOf<CatchHandler>()

                    catchHandlers.addAll(rarity.catchHandlers)
                    if (it.contains("commands")) {
                        val handler = CatchCommandExecutor(it.strings("commands").translated())
                        catchHandlers.add(handler)
                    }
                    FishType(
                        name = it.name,
                        displayName = it.string("display-name").translated(),
                        rarity = rarity,
                        lengthMin = it.double("length-min"),
                        lengthMax = it.double("length-max"),
                        icon = customItemStackLoader.loadFrom(it, "icon"),
                        catchHandlers = catchHandlers,
                        catchAnnouncement = playerAnnouncementLoader.loadIfExists(it, "catch-announce")
                            ?: rarity.catchAnnouncement,
                        conditions = fishConditionSetLoader.loadFrom(it, "conditions"),
                        hasNotFishItemFormat = it.boolean("skip-item-format", rarity.hasNotFishItemFormat),
                        noDisplay = it.boolean("no-display", rarity.noDisplay),
                        hasCatchFirework = it.boolean("firework", rarity.hasCatchFirework),
                        additionalPrice = rarity.additionalPrice + it.double("additional-price", 0.0)
                    )
                }.toSet()
                Pair(rarity, fishTypes)
            }.toMap()
        }
    }

    private fun findRarity(rarities: Set<FishRarity>, name: String): FishRarity {
        return rarities.find { it.name == name } ?: throw IllegalStateException("Rarity '$name' doesn't exist")
    }

}
