package com.lutto.upblock.listeners;

import com.lutto.upblock.UpBlock;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;

public class DiscordConsoleListener extends ListenerAdapter {

    private UpBlock mainClass;

    public DiscordConsoleListener(UpBlock mainClass) {
        this.mainClass = mainClass;
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {

        if (event.getChannel().getId().equals("1202785720490786956")) {

            System.out.println(Bukkit.getServer().getConsoleSender());
            String command = event.getMessage().getContentRaw();
            System.out.println(event.getMessage().getContentRaw());

            Bukkit.getScheduler().runTask(mainClass, () -> {
                ConsoleCommandSender consoleCommandSender = Bukkit.getServer().getConsoleSender();
                Bukkit.dispatchCommand(consoleCommandSender, command);
            });

        }

    }

}
