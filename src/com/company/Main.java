package com.company;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {

    public void onEnable() {
        System.out.println("NEEDS in");
        getServer().getPluginManager().registerEvents(this, this);
    }

    public void onDisable(){
        System.out.println("NEEDS out");
    }
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;
        if (label.equalsIgnoreCase("gm0")) {
            player.setGameMode(GameMode.SURVIVAL);
        }
        if (label.equalsIgnoreCase("gm1")) {
            player.setGameMode(GameMode.CREATIVE);
        }
        if (label.equalsIgnoreCase("gm2")) {
            player.setGameMode(GameMode.SPECTATOR);
        }


        return true;
    }
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        e.setJoinMessage(ChatColor.GOLD + player.getName() + ChatColor.AQUA + " in");
        player.teleport(player.getWorld().getSpawnLocation());
        player.setHealth(20);
        player.setFoodLevel(20);
        player.getInventory().clear();
        if (player.getPlayer().getGameMode() != GameMode.ADVENTURE){
            player.setGameMode(GameMode.ADVENTURE);
        }
    }
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        e.setQuitMessage(ChatColor.GOLD + player.getName() + ChatColor.AQUA + " out lul");
    }
}




