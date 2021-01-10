package dev.wotnak.rby.configuration.loader

import dev.wotnak.rby.configuration.ConfigurationValueAccessor

/**
 * Created by elsiff on 2019-01-09.
 */
interface CustomLoader<T> {
    fun loadFrom(section: ConfigurationValueAccessor, path: String = ROOT_PATH): T

    fun loadIfExists(section: ConfigurationValueAccessor, path: String = ROOT_PATH): T? {
        return if (section.contains(path))
            loadFrom(section, path)
        else
            null
    }

    companion object {
        private const val ROOT_PATH = ""
    }
}