package dev.wotnak.rby.configuration.loader

import dev.wotnak.rby.configuration.Config
import dev.wotnak.rby.configuration.ConfigurationValueAccessor
import dev.wotnak.rby.configuration.translated
import dev.wotnak.rby.fishing.FishRarity
import dev.wotnak.rby.fishing.catchhandler.CatchCommandExecutor
import dev.wotnak.rby.fishing.catchhandler.CatchHandler

class FishRaritySetLoader(
    private val chatColorLoader: ChatColorLoader,
    private val playerAnnouncementLoader: PlayerAnnouncementLoader
) : CustomLoader<Set<FishRarity>> {

    override fun loadFrom(section: ConfigurationValueAccessor, path: String): Set<FishRarity> {
        return section[path].children.map {
            val catchHandlers = mutableListOf<CatchHandler>()

            if (it.contains("commands")) {
                val handler = CatchCommandExecutor(it.strings("commands").translated())
                catchHandlers.add(handler)
            }
            FishRarity(
                name = it.name,
                displayName = it.string("display-name").translated(),
                default = it.boolean("default", false),
                probability = it.double("chance", 0.0) / 100.0,
                color = chatColorLoader.loadFrom(it, "color"),
                catchHandlers = catchHandlers,
                catchAnnouncement = playerAnnouncementLoader.loadIfExists(it, "catch-announce")
                    ?: Config.defaultCatchAnnouncement,
                hasNotFishItemFormat = it.boolean("skip-item-format", false),
                noDisplay = it.boolean("no-display", false),
                hasCatchFirework = it.boolean("firework", false),
                additionalPrice = it.double("additional-price", 0.0)
            )
        }.toSet()
    }

}
