package com.lutto.upblock.managers;

import com.lutto.upblock.Rank;
import com.lutto.upblock.UpBlock;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class RankManager {

    private UpBlock mainClass;

    private File ranksFile;
    private YamlConfiguration ranksFileConfig;

    public RankManager(UpBlock mainClass) {

        this.mainClass = mainClass;

        if (!mainClass.getDataFolder().exists()) {
            mainClass.getDataFolder().mkdir();
        }

        ranksFile = new File(mainClass.getDataFolder(), "ranks.yml");
        if (!ranksFile.exists()) {
            try {
                ranksFile.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        ranksFileConfig = YamlConfiguration.loadConfiguration(ranksFile);

    }

    public void setRank(UUID uuid, Rank rank) {

        ranksFileConfig.set(uuid.toString(), rank.name());
        try {
            ranksFileConfig.save(ranksFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        if (Bukkit.getOfflinePlayer(uuid).isOnline()) {
            Player player = Bukkit.getPlayer(uuid);
            mainClass.getNametagManager().removeTag(player);
            mainClass.getNametagManager().newTag(player);
        }

    }

    public Rank getRank(UUID uuid) {
        return Rank.valueOf(ranksFileConfig.getString(uuid.toString()));
    }

}
