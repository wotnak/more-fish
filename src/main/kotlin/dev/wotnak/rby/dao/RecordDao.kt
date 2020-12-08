package dev.wotnak.rby.dao

import dev.wotnak.rby.fishing.competition.Record

/**
 * Created by elsiff on 2019-01-18.
 */
interface RecordDao {
    fun insert(record: Record)

    fun update(record: Record)

    fun clear()

    fun top(size: Int): List<Record>

    fun all(): List<Record>
}