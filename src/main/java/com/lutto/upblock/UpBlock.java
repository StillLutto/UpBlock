package com.lutto.upblock;

import com.lutto.upblock.commands.RankCommand;
import com.lutto.upblock.commands.tabcompleters.RankTabCompleter;
import com.lutto.upblock.listeners.CustomTabListListener;
import com.lutto.upblock.listeners.DiscordConsoleListener;
import com.lutto.upblock.listeners.RankListener;
import com.lutto.upblock.listeners.PlayerJoinListener;
import com.lutto.upblock.manager.RankManager;
import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class UpBlock extends JavaPlugin {

    private static Dotenv env;

    private JDA jda;

    private RankManager rankManager;

    @Override
    public void onEnable() {

        env = Dotenv.configure().load();
        String discordBotToken = env.get("token");

        JDABuilder jdaBuilder = JDABuilder.createDefault(discordBotToken);
        jdaBuilder.setActivity(Activity.playing("UpBlock.minehut.gg"));
        jdaBuilder.addEventListeners(new DiscordConsoleListener(this));
        jdaBuilder.enableIntents(GatewayIntent.MESSAGE_CONTENT);
        try {
            jda = jdaBuilder.build();
            System.out.println("Successfully connected to discord bot!");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(), this);
        Bukkit.getPluginManager().registerEvents(new RankListener(this), this);
        Bukkit.getPluginManager().registerEvents(new CustomTabListListener(), this);
        getCommand("rank").setExecutor(new RankCommand(this));
        getCommand("rank").setTabCompleter(new RankTabCompleter());

        rankManager = new RankManager(this);

    }

    public RankManager getRankManager() { return rankManager; }

}
