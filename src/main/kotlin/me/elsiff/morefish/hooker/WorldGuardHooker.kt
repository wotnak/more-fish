package me.elsiff.morefish.hooker

import com.sk89q.worldedit.bukkit.BukkitAdapter
import com.sk89q.worldguard.WorldGuard
import me.elsiff.morefish.MoreFish
import org.bukkit.Location


/**
 * Created by elsiff on 2019-01-20.
 */
class WorldGuardHooker : PluginHooker {
    override val pluginName = "WorldGuard"
    override var hasHooked: Boolean = false

    override fun hook(plugin: MoreFish) {
        hasHooked = true
    }

    fun containsLocation(regionId: String, location: Location): Boolean {
        val x = location.blockX
        val y = location.blockY
        val z = location.blockZ
        val regionManager = WorldGuard.getInstance().platform.regionContainer.get(BukkitAdapter.adapt(location.world))
        val region = regionManager?.getRegion(regionId)
            ?: throw IllegalStateException("Region '$regionId' doesn't exist")

        return region.contains(x, y, z)
    }
}