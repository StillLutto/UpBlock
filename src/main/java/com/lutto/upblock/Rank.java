package com.lutto.upblock;

import org.bukkit.ChatColor;

public enum Rank {

    OWNER(ChatColor.GRAY + "[" + ChatColor.RED + "Owner" + ChatColor.GRAY + "]", 'a', new String[]{"upblock.rank"}),
    MODERATOR(ChatColor.GRAY + "[" + ChatColor.DARK_GREEN + "Moderator" + ChatColor.GRAY + "]", 'b', new String[]{}),
    HELPER(ChatColor.GRAY + "[" + ChatColor.BLUE + "Helper" + ChatColor.GRAY + "]", 'c', new String[]{}),
    TESTER(ChatColor.GRAY + "[" + ChatColor.AQUA + "Tester" + ChatColor.GRAY + "]", 'd', new String[]{});

    private String display;
    private char orderSymbol;
    private String[] permissions;

    Rank(String display, char orderSymbol, String[] permissions) {
        this.display = display;
        this.orderSymbol = orderSymbol;
        this.permissions = permissions;
    }

    public String getDisplay() { return display; }
    public char getOrderSymbol() { return orderSymbol; }
    public String[] getPermissions() { return permissions; }

}
