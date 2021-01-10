package dev.wotnak.rby.fishing.competition

import dev.wotnak.rby.dao.DaoFactory
import dev.wotnak.rby.dao.RecordDao
import dev.wotnak.rby.fishing.Fish
import org.bukkit.OfflinePlayer

class FishingCompetition {

    var state: State = State.DISABLED

    val ranking: List<Record>
        get() = records.all()

    private val records: RecordDao
        get() = DaoFactory.records

    fun enable() {
        checkStateDisabled()

        state = State.ENABLED
    }

    fun disable() {
        checkStateEnabled()

        state = State.DISABLED
    }

    fun isEnabled(): Boolean =
        state == State.ENABLED

    fun isDisabled(): Boolean =
        state == State.DISABLED

    fun willBeNewFirst(catcher: OfflinePlayer, fish: Fish): Boolean {
        return ranking.isEmpty() || ranking.first().let { fish.length > it.fish.length && it.fisher != catcher }
    }

    fun putRecord(record: Record) {
        checkStateEnabled()

        if (containsContestant(record.fisher)) {
            val oldRecord = recordOf(record.fisher)
            if (record.fish.length > oldRecord.fish.length) {
                records.update(record)
            }
        } else {
            records.insert(record)
        }
    }

    fun containsContestant(contestant: OfflinePlayer): Boolean =
        ranking.any { it.fisher == contestant }

    fun rankNumberOf(record: Record): Int =
        ranking.indexOf(record) + 1

    fun recordOf(contestant: OfflinePlayer): Record {
        for (record in ranking) {
            if (record.fisher == contestant) {
                return record
            }
        }
        throw IllegalStateException("Record not found")
    }

    fun rankedRecordOf(contestant: OfflinePlayer): Pair<Int, Record> {
        for ((index, record) in ranking.withIndex()) {
            if (record.fisher == contestant) {
                return Pair(index + 1, record)
            }
        }
        throw IllegalStateException("Record not found")
    }

    fun recordOf(rankNumber: Int): Record {
        require(rankNumber >= 1 && rankNumber <= ranking.size) { "Rank number is out of records size" }
        return ranking.elementAt(rankNumber - 1)
    }

    fun top(size: Int): List<Record> =
        records.top(size)

    fun clearRecords() =
        records.clear()

    private fun checkStateEnabled() =
        check(state == State.ENABLED) { "Fishing competition hasn't enabled" }

    private fun checkStateDisabled() =
        check(state == State.DISABLED) { "Fishing competition hasn't disabled" }

    enum class State { ENABLED, DISABLED }

}
