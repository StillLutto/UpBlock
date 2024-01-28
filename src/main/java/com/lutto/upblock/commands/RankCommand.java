package com.lutto.upblock.commands;

import com.lutto.upblock.Rank;
import com.lutto.upblock.UpBlock;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.scheduler.BukkitTask;

import java.lang.reflect.Field;
import java.util.UUID;

public class RankCommand implements CommandExecutor {

    private UpBlock mainClass;

    public RankCommand(UpBlock mainClass) {
        this.mainClass = mainClass;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (!player.isOp()) {
                player.sendMessage(ChatColor.RED + "You must be op to use this command!");
                return false;
            }

            if (!(args.length == 3) || !args[0].equals("set")) {
                player.sendMessage(ChatColor.RED + "Invalid Usage!");
                player.sendMessage(ChatColor.RED + "Please use /rank set <player> <rank>");
                return false;
            }

            if (!Bukkit.getOfflinePlayer(args[1]).hasPlayedBefore()) {
                player.sendMessage(ChatColor.RED + "Player not found!");
                return false;
            }


            OfflinePlayer targetPlayer = Bukkit.getOfflinePlayer(args[1]);
            for (Rank rank : Rank.values()) {

                if (rank.name().equalsIgnoreCase(args[2])) {

                    // commented for testing purposes
//                    if (targetPlayer.getUniqueId() == player.getUniqueId()) {
//                        player.sendMessage(ChatColor.RED + "You can't change your own rank!");
//                        return false;
//                    }

                    mainClass.getRankManager().setRank(targetPlayer.getUniqueId(), rank);

                    player.sendMessage(ChatColor.GREEN + "You have changed " + targetPlayer.getName() + "'s rank to " + ChatColor.translateAlternateColorCodes('&', rank.getDisplay()) + ChatColor.GREEN + ".");
                    if (targetPlayer.isOnline()) {
                        targetPlayer.getPlayer().sendMessage(ChatColor.GREEN + player.getName() + " has changed you rank to " + ChatColor.translateAlternateColorCodes('&', rank.getDisplay()) + ChatColor.GREEN + ".");
                    }

                    return false;
                }

            }

            // if it doesn't find the rank, it doesn't return and sends this:
            player.sendMessage(ChatColor.RED + "Rank not found!");

        }

        return false;
    }

}


// TODO: ADD GUI IMPLEMENTATION

//                            Inventory rankPickerInventory = Bukkit.createInventory(player, 54, ChatColor.BLUE.toString() + ChatColor.BOLD + "Rank Picker");
//
//                            // Close item
//                            ItemStack closeItem = new ItemStack(Material.BARRIER);
//                            ItemMeta closeItemMeta = closeItem.getItemMeta();
//                            closeItemMeta.setDisplayName(ChatColor.RED + "Close");
//                            closeItem.setItemMeta(closeItemMeta);
//                            rankPickerInventory.setItem(0, closeItem);
//
//                            // Frame Items
//                            ItemStack frame = new ItemStack(Material.LIGHT_BLUE_STAINED_GLASS_PANE);
//                            ItemMeta frameMeta = frame.getItemMeta();
//                            frameMeta.setDisplayName("");
//                            frame.setItemMeta(frameMeta);
//                            for (int i : new int[]{1,2,3,4,5,6,7,8,9,18,27,36,45,17,26,35,44,53,46,47,48,50,51,52}) {
//                                rankPickerInventory.setItem(i, frame);
//                            }
//
//                            // Target Player Head
//
//                            ItemStack targetPlayerHead = new ItemStack(Material.PLAYER_HEAD);
//                            SkullMeta targetPlayerHeadMeta = (SkullMeta) targetPlayerHead.getItemMeta();
//                            targetPlayerHeadMeta.setDisplayName(ChatColor.GREEN + "Targeted Player: " + Bukkit.getPlayerExact(args[1]).getName());
//                            targetPlayerHeadMeta.setOwningPlayer(Bukkit.getPlayerExact(args[1]));
//                            targetPlayerHead.setItemMeta(targetPlayerHeadMeta);
//                            rankPickerInventory.setItem(49, targetPlayerHead);
//
//                            // Owner Player head
//                            ItemStack ownerPlayerHead = new ItemStack(Material.PLAYER_HEAD);
//                            ItemMeta ownerPlayerHeadMeta = ownerPlayerHead.getItemMeta();
//                            ownerPlayerHeadMeta.setDisplayName(ChatColor.RED.toString() + ChatColor.BOLD + "Owner");
//
//                            GameProfile ownerProfile = new GameProfile(UUID.randomUUID(), null);
//                            ownerProfile.getProperties().put("textures", new Property("textures", "e3RleHR1cmVzOntTS0lOOnt1cmw6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTEyOTJkYTNkMjFhYTQxNDMyMDZiMjNjODZlNDE1YmQ3NTNlYzVlOTVjYjNkOWMyMjViMDJmOWM0MGExMjVjNCJ9fX0"));
//                            Field field;
//                            try {
//                                field = ownerPlayerHeadMeta.getClass().getDeclaredField("profile");
//                                field.setAccessible(true);
//                                field.set(ownerPlayerHeadMeta, ownerProfile);
//                            } catch (NoSuchFieldException | IllegalAccessException e) {
//                                throw new RuntimeException(e);
//                            }
//                            ownerPlayerHead.setItemMeta(ownerPlayerHeadMeta);
//                            rankPickerInventory.setItem(10, ownerPlayerHead);
//
//
//                            player.openInventory(rankPickerInventory);