package com.pool25m.logtime

import org.bukkit.Bukkit
import org.bukkit.Statistic
import java.util.concurrent.TimeUnit

data class PlayerRankData(
    val playerName: String,
    val playTicks: Long,
    val formattedPlayTime: String,
)

class PlayerStatsService {
    /**
     * サーバーにログインしたことのある全プレイヤーの総プレイ時間ランキングを取得する
     */
    fun getPlayerRankings(): List<PlayerRankData> {
        return Bukkit
            .getOfflinePlayers()
            .map { player ->
                val playTicks = player.getStatistic(Statistic.PLAY_ONE_MINUTE).toLong()

                PlayerRankData(
                    // OfflinePlayer の場合は name が null になる可能性がある
                    playerName = player.name ?: "Unknown Player",
                    playTicks = playTicks,
                    formattedPlayTime = formatPlayTimeFromTicks(playTicks),
                )
            }.sortedByDescending { it.playTicks } // 正確な時間でソート
    }

    private fun formatPlayTimeFromTicks(ticks: Long): String {
        val millis = ticks * 50 // 1 tick = 50ms
        val hours = TimeUnit.MILLISECONDS.toHours(millis)
        val minutes = TimeUnit.MILLISECONDS.toMinutes(millis) % 60
        val seconds = TimeUnit.MILLISECONDS.toSeconds(millis) % 60

        return buildString {
            if (hours > 0) append("${hours}h ")
            if (minutes > 0) append("${minutes}m ")
            append("${seconds}s")
        }.ifEmpty { "0s" }
    }
}
