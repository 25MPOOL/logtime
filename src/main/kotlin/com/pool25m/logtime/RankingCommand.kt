package com.pool25m.logtime

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

class RankingCommand(
    private val playerStatsService: PlayerStatsService,
) : CommandExecutor {
    override fun onCommand(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>,
    ): Boolean {
        val rankedList = playerStatsService.getPlayerRankings()

        if (rankedList.isEmpty()) {
            sender.sendMessage("No players found.")
            return true
        }

        sender.sendMessage("--- 総プレイ時間ランキング ---")
        rankedList.forEachIndexed { index, rankData ->
            val rank = index + 1
            sender.sendMessage("$rank. ${rankData.playerName} - ${rankData.formattedPlayTime}")
        }

        return true
    }
}
