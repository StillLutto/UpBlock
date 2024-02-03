package com.lutto.upblock.listeners;

import com.lutto.upblock.UpBlock;
import com.lutto.upblock.utils.CustomPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class ConnectionListener implements Listener {

    private UpBlock mainClass;

    public ConnectionListener(UpBlock mainClass) {
        this.mainClass = mainClass;
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerJoin(PlayerJoinEvent event) {

        Player player = event.getPlayer();

        try {
            CustomPlayer playerData = new CustomPlayer(mainClass, player.getUniqueId());
            mainClass.getPlayerManager().addCustomPlayer(player.getUniqueId(), playerData);
        } catch (Exception e) {
            player.kickPlayer("Your data could not be loaded!");
            throw new RuntimeException(e);
        }

    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        mainClass.getPlayerManager().removeCustomPlayer(event.getPlayer().getUniqueId());
    }

}
