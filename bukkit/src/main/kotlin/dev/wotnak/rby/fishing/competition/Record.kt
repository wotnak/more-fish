package dev.wotnak.rby.fishing.competition

import dev.wotnak.rby.fishing.Fish
import org.bukkit.OfflinePlayer

/**
 * Created by elsiff on 2018-12-25.
 */
data class Record(val fisher: OfflinePlayer, val fish: Fish) : Comparable<Record> {
    override fun compareTo(other: Record): Int {
        return fish.length.compareTo(other.fish.length)
    }
}