package com.company;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.List;

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
        if (label.equalsIgnoreCase("sugma")){
            if (!(sender instanceof Player)) {
                sender.sendMessage("players only BOSS");
            }
            player.getInventory().addItem(pissPants());
            player.sendMessage("ligma");
            return true;

        }
        if (label.equalsIgnoreCase("gm0")) {
            if (!(sender instanceof Player)){
                sender.sendMessage("players only BOSS");
            }
            if (player.hasPermission("needs.gm")) {
                player.setGameMode(GameMode.SURVIVAL);
                return true;
            }
        }
        if (label.equalsIgnoreCase("gm1")) {
            if (!(sender instanceof Player)){
                sender.sendMessage("players only BOSS");
            }
            if (player.hasPermission("needs.gm")) {
                player.setGameMode(GameMode.CREATIVE);
                return true;
            }
        }
        if (label.equalsIgnoreCase("gm2")) {
            if (!(sender instanceof Player)){
                sender.sendMessage("players only BOSS");
            }
            if (player.hasPermission("needs.gm")) {
                player.setGameMode(GameMode.SPECTATOR);
                return true;
            }
        }
        if (cmd.getName().equalsIgnoreCase("heal")) {
            if (player.hasPermission("needs.heal")) {
                double maxHealth = player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getDefaultValue();
                player.setHealth(maxHealth);
                player.sendMessage(ChatColor.RED + "I gotchu");
                return true;
            }
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
        if (player.getPlayer().getGameMode() != GameMode.ADVENTURE){
            player.setGameMode(GameMode.ADVENTURE);
        }
    }
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        e.setQuitMessage(ChatColor.GOLD + player.getName() + ChatColor.AQUA + " out lul");
    }

    @EventHandler
    public void pissPant(PlayerInteractEvent event) {
        Player player = (Player) event.getPlayer();
        if (player.getInventory().getLeggings() != null) {
            if (player.getInventory().getLeggings().getType() == Material.LEATHER_LEGGINGS)
                if (player.getInventory().getLeggings().getItemMeta().getDisplayName().contains("PissPants"))
                    if (player.getInventory().getLeggings().getItemMeta().hasLore()) {
                        player.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 600, 2));
                        player.sendMessage(ChatColor.AQUA + player.getName()
                                + ChatColor.GOLD + " " + ChatColor.BOLD + "PISSED PANTS");
                    }
        }
    }
    @EventHandler
    public void motd(ServerListPingEvent event) {
        event.setMotd(ChatColor.DARK_RED + "" + ChatColor.BOLD + "RAGE " + ChatColor.RESET + ChatColor.BLUE
                + "Kit PvP");
    }

    public ItemStack pissPants() {
        ItemStack pant = new ItemStack(Material.LEATHER_LEGGINGS);
        ItemMeta meta = pant.getItemMeta();
        meta.setDisplayName(ChatColor.GOLD + "PissPants");
        List<String> lore = new ArrayList<String>();
        lore.add("");
        lore.add(ChatColor.ITALIC + "" + ChatColor.GREEN + "These pants have been PISSED");
        meta.setLore(lore);
        meta.addEnchant(Enchantment.LURE, 1, true);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        meta.setUnbreakable(true);
        pant.setItemMeta(meta);

        return pant;
    }
}




