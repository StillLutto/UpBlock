package com.lutto.upblock;

import com.lutto.upblock.commands.RankCommand;
import com.lutto.upblock.listeners.RankListener;
import com.lutto.upblock.listeners.PlayerJoinListener;
import com.lutto.upblock.managers.NametagManager;
import com.lutto.upblock.managers.RankManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class UpBlock extends JavaPlugin {

    private RankManager rankManager;
    private NametagManager nametagManager;

    @Override
    public void onEnable() {

        Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(), this);
        Bukkit.getPluginManager().registerEvents(new RankListener(this), this);
        getCommand("rank").setExecutor(new RankCommand(this));

        rankManager = new RankManager(this);
        nametagManager = new NametagManager(this);

    }

    public RankManager getRankManager() { return rankManager; }
    public NametagManager getNametagManager() { return nametagManager; }

}
