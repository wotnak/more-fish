package dev.wotnak.rby

import co.aikar.commands.PaperCommandManager
import me.elsiff.egui.GuiOpener
import me.elsiff.egui.GuiRegistry
import dev.wotnak.rby.command.MainCommand
import dev.wotnak.rby.configuration.Config
import dev.wotnak.rby.dao.DaoFactory
import dev.wotnak.rby.fishing.FishingListener
import dev.wotnak.rby.fishing.MutableFishTypeTable
import dev.wotnak.rby.fishing.catchhandler.CatchBroadcaster
import dev.wotnak.rby.fishing.catchhandler.CatchHandler
import dev.wotnak.rby.fishing.catchhandler.CompetitionRecordAdder
import dev.wotnak.rby.fishing.catchhandler.NewFirstBroadcaster
import dev.wotnak.rby.fishing.competition.FishingCompetition
import dev.wotnak.rby.fishing.competition.FishingCompetitionAutoRunner
import dev.wotnak.rby.fishing.competition.FishingCompetitionHost
import dev.wotnak.rby.hooker.*
import dev.wotnak.rby.item.FishItemStackConverter
import dev.wotnak.rby.shop.FishShop
import dev.wotnak.rby.shop.FishShopSignListener
import dev.wotnak.rby.update.UpdateChecker
import dev.wotnak.rby.update.UpdateNotifierListener
import dev.wotnak.rby.util.OneTickScheduler
import org.bukkit.plugin.java.JavaPlugin

/**
 * Created by elsiff on 2018-12-20.
 */
class Rby : JavaPlugin() {
    val protocolLib = ProtocolLibHooker()
    val vault = VaultHooker()
    val mcmmoHooker = McmmoHooker()
    val worldGuardHooker = WorldGuardHooker()
    val citizensHooker = CitizensHooker()
    val placeholderApiHooker = PlaceholderApiHooker()

    val guiRegistry = GuiRegistry(this)
    val guiOpener = GuiOpener(guiRegistry)
    val oneTickScheduler = OneTickScheduler(this)
    val fishTypeTable = MutableFishTypeTable()
    val competition = FishingCompetition()
    val competitionHost = FishingCompetitionHost(this, competition)
    val autoRunner = FishingCompetitionAutoRunner(this, competitionHost)
    val converter = FishItemStackConverter(this, fishTypeTable)
    val fishShop = FishShop(guiOpener, oneTickScheduler, converter, vault)
    val globalCatchHandlers: List<CatchHandler> = listOf(
        CatchBroadcaster(),
        NewFirstBroadcaster(competition),
        CompetitionRecordAdder(competition)
    )
    val updateChecker = UpdateChecker(22926, this.description.version)

    override fun onEnable() {
        INSTANCE = this
        DaoFactory.init(this)

        protocolLib.hookIfEnabled(this)
        vault.hookIfEnabled(this)
        mcmmoHooker.hookIfEnabled(this)
        worldGuardHooker.hookIfEnabled(this)
        citizensHooker.hookIfEnabled(this)
        placeholderApiHooker.hookIfEnabled(this)

        applyConfig()

        server.pluginManager.run {
            val fishingListener =
                FishingListener(fishTypeTable, converter, competition, globalCatchHandlers)
            registerEvents(fishingListener, this@Rby)
            registerEvents(FishShopSignListener(fishShop), this@Rby)
        }

        val commands = PaperCommandManager(this)
        val mainCommand = MainCommand(this, competitionHost, fishShop)
        commands.registerCommand(mainCommand)

        if (!isSnapshotVersion()) {
            updateChecker.check()
            if (updateChecker.hasNewVersion()) {
                val notifier = UpdateNotifierListener(updateChecker.newVersion)
                server.pluginManager.registerEvents(notifier, this)
            }
        }

        logger.info("Plugin has been enabled.")

        if (Config.standard.boolean("general.auto-start")) {
            competitionHost.openCompetition()
        }
    }

    override fun onDisable() {
        guiRegistry.clear(true)
        if (autoRunner.isEnabled) {
            autoRunner.disable()
        }
        if (citizensHooker.hasHooked) {
            citizensHooker.dispose()
        }
        logger.info("Plugin has been disabled.")
    }

    private fun isSnapshotVersion(): Boolean {
        return this.description.version.contains("SNAPSHOT", true)
    }

    fun applyConfig() {
        Config.load(this)
        Config.customItemStackLoader.protocolLib = protocolLib
        Config.fishConditionSetLoader.init(mcmmoHooker, worldGuardHooker)

        fishTypeTable.clear()
        fishTypeTable.putAll(Config.fishTypeMapLoader.loadFrom(Config.fish))
        logger.info("Loaded ${fishTypeTable.rarities.size} rarities and ${fishTypeTable.types.size} fish types")

        if (Config.standard.boolean("auto-running.enable") && !autoRunner.isEnabled) {
            val scheduledTimes = Config.localTimeListLoader.loadFrom(Config.standard, "auto-running.start-time")
            autoRunner.setScheduledTimes(scheduledTimes)
            autoRunner.enable()
        }
    }

    companion object {
        private lateinit var INSTANCE: Rby
        val instance: Rby
            get() = INSTANCE
    }
}