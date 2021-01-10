package dev.wotnak.rby.dao

import dev.wotnak.rby.fishing.competition.Record

interface RecordDao {

    fun insert(record: Record)

    fun update(record: Record)

    fun clear()

    fun top(size: Int): List<Record>

    fun all(): List<Record>

}
