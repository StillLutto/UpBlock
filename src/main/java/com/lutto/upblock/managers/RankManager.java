package com.lutto.upblock.managers;

import com.lutto.upblock.Rank;
import com.lutto.upblock.UpBlock;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.util.io.BukkitObjectInputStream;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

public class RankManager {

    private UpBlock mainClass;

    private File ranksFile;
    private YamlConfiguration ranksFileConfig;

    private HashMap<UUID, PermissionAttachment> permissions = new HashMap<>();

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

    public void setRank(UUID uuid, Rank rank, boolean firstJoin) {

        if (Bukkit.getOfflinePlayer(uuid).isOnline() && !firstJoin) {

            Player player = Bukkit.getPlayer(uuid);
            PermissionAttachment permissionAttachment;

            if (permissions.containsKey(uuid)) {
                permissionAttachment = permissions.get(uuid);
            } else {
                permissionAttachment = player.addAttachment(mainClass);
                permissions.put(uuid, permissionAttachment);
            }

            for (String permission : getRank(uuid).getPermissions()) {
                if (!player.hasPermission(permission)) return; // as a little failsafe

                permissionAttachment.unsetPermission(permission);
            }

            for (String permission : rank.getPermissions()) {
                permissionAttachment.setPermission(permission, true);
            }

        }

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

    public HashMap<UUID, PermissionAttachment> getPermissions() { return permissions; }

}
