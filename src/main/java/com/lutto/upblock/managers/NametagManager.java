package com.lutto.upblock.managers;

import com.lutto.upblock.Rank;
import com.lutto.upblock.UpBlock;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;

public class NametagManager {

    private UpBlock mainClass;

    public NametagManager(UpBlock mainClass) {
        this.mainClass = mainClass;
    }

    public void setNametags(Player player) {

        player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());

        for (Rank rank : Rank.values()) {
            Team rankTeam = player.getScoreboard().registerNewTeam(rank.getOrderSymbol() + rank.name());
            rankTeam.setPrefix(rank.getDisplay() + " ");
        }

        for (Player target : Bukkit.getOnlinePlayers()) {
            if (player.getUniqueId() == target.getUniqueId()) return;
            player.getScoreboard().getTeam(mainClass.getRankManager().getRank(target.getUniqueId()).name()).addEntry(target.getName());
        }

    }

    public void newTag(Player player) {

        Rank playerRank = mainClass.getRankManager().getRank(player.getUniqueId());
        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            onlinePlayer.getScoreboard().getTeam(playerRank.getOrderSymbol() + playerRank.name()).addEntry(player.getName());
        }

    }

    public void removeTag(Player player) {

        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            onlinePlayer.getScoreboard().getEntryTeam(player.getName()).removeEntry(player.getName());
        }

    }

}
