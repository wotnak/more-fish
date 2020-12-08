package dev.wotnak.rby.hooker

import me.clip.placeholderapi.PlaceholderAPI
import me.clip.placeholderapi.expansion.PlaceholderExpansion
import dev.wotnak.rby.configuration.format.Format
import dev.wotnak.rby.fishing.competition.FishingCompetition
import org.bukkit.entity.Player

/**
 * Created by elsiff on 2019-01-24.
 */
class PlaceholderApiHooker : PluginHooker {
    override val pluginName = "PlaceholderAPI"
    override var hasHooked = false

    override fun hook(plugin: dev.wotnak.rby.Rby) {
        MoreFishPlaceholder(plugin).register()
        Format.init(this)
        hasHooked = true
    }

    fun tryReplacing(string: String, player: Player? = null): String {
        return PlaceholderAPI.setPlaceholders(player, string)
    }

    class MoreFishPlaceholder(
        val rby: dev.wotnak.rby.Rby
    ) : PlaceholderExpansion() {

        private val competition: FishingCompetition = this.rby.competition

        override fun canRegister(): Boolean = true
        override fun getAuthor(): String = this.rby.description.authors.toString()
        override fun getIdentifier(): String = this.rby.description.name

        override fun getVersion(): String = this.rby.description.version

        override fun onPlaceholderRequest(player: Player?, params: String): String? {
            return when {
                params.startsWith("top_player_") -> {
                    val number = params.replace("top_player_", "").toInt()
                    if (this.competition.ranking.size >= number)
                        this.competition.recordOf(number).fisher.name
                    else
                        "no one"
                }
                params.startsWith("top_fish_length_") -> {
                    val number = params.replace("top_fish_length_", "").toInt()
                    if (this.competition.ranking.size >= number)
                        this.competition.recordOf(number).fish.length.toString()
                    else
                        "0.0"
                }
                params.startsWith("top_fish_") -> {
                    val number = params.replace("top_fish_", "").toInt()
                    if (this.competition.ranking.size >= number)
                        this.competition.recordOf(number).fish.type.name
                    else
                        "none"
                }
                params == "rank" -> {
                    require(player != null) { "'rank' placeholder requires a player" }
                    if (this.competition.containsContestant(player)) {
                        val record = this.competition.recordOf(player)
                        this.competition.rankNumberOf(record).toString()
                    } else {
                        "0"
                    }
                }
                params == "fish_length" -> {
                    require(player != null) { "'fish_length' placeholder requires a player" }
                    if (this.competition.containsContestant(player))
                        this.competition.recordOf(player).fish.length.toString()
                    else
                        "0.0"
                }
                params == "fish" -> {
                    require(player != null) { "'fish' placeholder requires a player" }
                    if (this.competition.containsContestant(player))
                        this.competition.recordOf(player).fish.type.name
                    else
                        "none"
                }
                else -> null
            }
        }
    }
}