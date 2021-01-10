package dev.wotnak.rby.configuration.loader

import dev.wotnak.rby.configuration.ConfigurationValueAccessor
import dev.wotnak.rby.configuration.translated
import dev.wotnak.rby.fishing.competition.Prize

class PrizeMapLoader : CustomLoader<Map<IntRange, Prize>> {

    override fun loadFrom(section: ConfigurationValueAccessor, path: String): Map<IntRange, Prize> {
        return section[path].children.map {
            val range = intRangeFrom(it.name)
            val commands = it.strings("commands").translated()

            range to Prize(commands)
        }.toMap()
    }

    private fun intRangeFrom(string: String): IntRange {
        val tokens = string.split("~")

        val start = tokens[0].toInt()
        val end = if (tokens.size > 1) {
            if (tokens[1].isEmpty())
                Int.MAX_VALUE
            else
                tokens[1].toInt()
        } else {
            start
        }
        return IntRange(start, end)
    }

}
