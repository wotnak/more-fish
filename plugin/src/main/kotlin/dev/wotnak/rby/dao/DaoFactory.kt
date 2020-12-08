package dev.wotnak.rby.dao

import dev.wotnak.rby.Rby
import dev.wotnak.rby.dao.yaml.YamlRecordDao

/**
 * Created by elsiff on 2019-01-18.
 */
object DaoFactory {
    private lateinit var rby: Rby

    val records: RecordDao
        get() = YamlRecordDao(rby, rby.fishTypeTable)

    fun init(rby: Rby) {
        this.rby = rby
    }
}