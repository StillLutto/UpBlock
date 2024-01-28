package com.lutto.upblock;

import org.bukkit.ChatColor;

public enum Rank {

    OWNER(ChatColor.GRAY + "[" + ChatColor.RED + "Owner" + ChatColor.GRAY + "]", 'a'),
    HELPER(ChatColor.GRAY + "[" + ChatColor.BLUE + "Helper" + ChatColor.GRAY + "]", 'b'),
    MODERATOR(ChatColor.GRAY + "[" + ChatColor.DARK_GREEN + "Moderator" + ChatColor.GRAY + "]", 'c'),
    TESTER(ChatColor.GRAY + "[" + ChatColor.AQUA + "Tester" + ChatColor.GRAY + "]", 'd');

    private String display;
    private char orderSymbol;

    Rank(String display, char orderSymbol) {
        this.display = display;
        this.orderSymbol = orderSymbol;
    }

    public String getDisplay() { return display; }
    public char getOrderSymbol() { return orderSymbol; }

}
