package com.lutto.upblock;

import com.lutto.upblock.commands.PlaytimeCommand;
import com.lutto.upblock.commands.RankCommand;
import com.lutto.upblock.commands.tabcompleters.PlaytimeTabCompleter;
import com.lutto.upblock.commands.tabcompleters.RankTabCompleter;
import com.lutto.upblock.listeners.*;
import com.lutto.upblock.manager.DatabaseManager;
import com.lutto.upblock.manager.PlayerManager;
import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;

public final class UpBlock extends JavaPlugin {

    private static Dotenv env;

    private JDA jda;

    private DatabaseManager databaseManager;
    private PlayerManager playerManager;

    @Override
    public void onEnable() {

        env = Dotenv.configure().load();
        String discordBotToken = env.get("TOKEN");

        JDABuilder jdaBuilder = JDABuilder.createDefault(discordBotToken);
        jdaBuilder.setActivity(Activity.playing("UpBlock.minehut.gg"));
        jdaBuilder.addEventListeners(new DiscordConsoleListener(this));
        jdaBuilder.enableIntents(GatewayIntent.GUILD_MESSAGES);
        try {
            jda = jdaBuilder.build();
            System.out.println("Successfully connected to discord bot!");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(), this);
        Bukkit.getPluginManager().registerEvents(new RankListener(this), this);
        Bukkit.getPluginManager().registerEvents(new CustomTabListListener(), this);
        Bukkit.getPluginManager().registerEvents(new ConnectionListener(this), this);
        Bukkit.getPluginManager().registerEvents(new PlaytimeListener(this), this);
        getCommand("rank").setExecutor(new RankCommand(this));
        getCommand("rank").setTabCompleter(new RankTabCompleter());
        getCommand("playtime").setExecutor(new PlaytimeCommand(this));
        getCommand("playtime").setTabCompleter(new PlaytimeTabCompleter());


        databaseManager = new DatabaseManager();
        playerManager = new PlayerManager();

        try {
            databaseManager.connect();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void onDisable() {
        databaseManager.disconnect();
    }

    public DatabaseManager getDatabaseManager() { return databaseManager; }
    public PlayerManager getPlayerManager() { return playerManager; }

}
