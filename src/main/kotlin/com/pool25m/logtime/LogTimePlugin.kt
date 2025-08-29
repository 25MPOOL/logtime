package com.pool25m.logtime

import org.bukkit.plugin.java.JavaPlugin

class LogTimePlugin : JavaPlugin() {
    override fun onEnable() {
        val playerStatsService = PlayerStatsService()
        getCommand("ranking")?.setExecutor(RankingCommand(playerStatsService))

        logger.info("LogTime Plugin has been enabled!")
    }

    override fun onDisable() {
        logger.info("LogTime Plugin has been disabled.")
    }
}
