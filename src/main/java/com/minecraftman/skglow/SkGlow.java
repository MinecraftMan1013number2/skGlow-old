package com.minecraftman.skglow;

import ch.njol.skript.Skript;
import ch.njol.skript.SkriptAddon;
import me.MrGraycat.eGlow.API.EGlowAPI;
import me.MrGraycat.eGlow.EGlow;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SkGlow extends JavaPlugin {

    public static SkGlow instance;
	private SkriptAddon addon;
	
	public EGlowAPI api = EGlow.getAPI();

	public void onEnable() {
	    
	    if(Bukkit.getPluginManager().getPlugin("Skript") == null) {
	        Bukkit.getPluginManager().disablePlugin(this);
            System.out.println();
            System.out.println(ChatColor.DARK_RED + "Error! Skript was not found!");
            System.out.println(ChatColor.DARK_RED + "Please install it from https://github.com/SkriptLang/Skript/releases/latest and restart the server!");
            System.out.println();
        }
        if(Bukkit.getPluginManager().getPlugin("eGlow") == null) {
            Bukkit.getPluginManager().disablePlugin(this);
            System.out.println();
            System.out.println(ChatColor.DARK_RED + "Error! eGlow was not found!");
            System.out.println(ChatColor.DARK_RED + "Please install it from https://www.spigotmc.org/resources/eglow-1-9-4-1-17-1-glow-cosmetic-respects-eula-mysql.63295/ and restart the server!");
            System.out.println();
        }
        if(!(Bukkit.getPluginManager().getPlugin("Skript") == null) && !(Bukkit.getPluginManager().getPlugin("eGlow") == null)) {
            System.out.println("[skGlow] " + ChatColor.GREEN + "skGlow has been enabled!");
        }
        
        instance = this;
        addon = Skript.registerAddon(this);
        try {
            addon.loadClasses("com.minecraftman.skglow.skript.effects");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Register Metrics
        
        int pluginId = 12725;
        
        Metrics metrics = new Metrics(this, pluginId);
        metrics.addCustomChart(new Metrics.SimplePie("used_language", () ->
                getConfig().getString("language", "en")));
        metrics.addCustomChart(new Metrics.SimplePie("skript_version", () ->
                Bukkit.getServer().getPluginManager().getPlugin("Skript").getDescription().getVersion()));
        metrics.addCustomChart(new Metrics.SimplePie("Skord_version", () ->
                this.getDescription().getVersion()));
        metrics.addCustomChart(new Metrics.DrilldownPie("java_version", () -> {
            Map<String, Map<String, Integer>> map = new HashMap<>();
            String javaVersion = System.getProperty("java.version");
            Map<String, Integer> entry = new HashMap<>();
            entry.put(javaVersion, 1);
            if (javaVersion.startsWith("1.7")) {
                map.put("Java 1.7", entry);
            } else if (javaVersion.startsWith("1.8")) {
                map.put("Java 1.8", entry);
            } else if (javaVersion.startsWith("1.9")) {
                map.put("Java 1.9", entry);
            } else {
                map.put("Other", entry);
            }
            return map;
        }));

    }

}
