package fr.k0bus.betterhalloween;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import fr.k0bus.betterhalloween.event.ZombieSpawn;
import fr.k0bus.betterhalloween.event.ZombieKill;

public class Main extends JavaPlugin{
	
	public static String tag;
	public static FileConfiguration config;
	public static Plugin plugin;
	
	@Override
	public void onEnable()
	{
		//Config
		this.getLogger().log(Level.INFO, "Loading config.");
		this.saveDefaultConfig();
		Main.config = this.getConfig();
		this.setDefaultConfig();
		Main.tag = ChatColor.translateAlternateColorCodes('&', Main.config.getString("tag") + "&r ");
		Main.plugin = this;
		// Commands
		this.getLogger().log(Level.INFO, "Loading commands.");
		
		// Events
		this.getLogger().log(Level.INFO, "Loading events.");
		this.registerEvent(this.getServer().getPluginManager());
		this.getLogger().log(Level.INFO, "Loaded successfully.");
	}
	@Override
	public void onDisable()
	{
		this.saveConfig();
	}
	private void registerEvent(PluginManager pm)
	{
		pm.registerEvents(new ZombieSpawn(), this);
		pm.registerEvents(new ZombieKill(), this);
	}
	
	private void setDefaultConfig()
	{
		Main.config.addDefault("tag", "&r[&cRandomTP&r]");
		Main.config.addDefault("zombie.head-name", "Herobrine");
		Main.config.addDefault("zombie.health", 60);
		Main.config.addDefault("zombie.effect.invisibility.status", true);
		Main.config.addDefault("zombie.effect.invisibility.value", 1);
		Main.config.addDefault("zombie.effect.speed.status", true);
		Main.config.addDefault("zombie.effect.speed.value", 4);
		Main.config.addDefault("zombie.effect.fire_resistance.status", true);
		Main.config.addDefault("zombie.effect.fire_resistance.value", 1);
		Main.config.addDefault("zombie.effect.damage_resistance.status", false);
		Main.config.addDefault("zombie.effect.damage_resistance.value", 1);
		Main.config.addDefault("zombie.spawn-chance", 10);

		List <String> defaultLore= new ArrayList<String>();
		defaultLore.add("Une clé d'Herobrine pour un coffre légendaire !");
		defaultLore.add("Rendez vous au spawn pour ouvrir un coffre !");
		
		Main.config.addDefault("key.iron.name", "Clé en fer");
		Main.config.addDefault("key.iron.lore", defaultLore);
		Main.config.addDefault("key.gold.name", "Clé en or");
		Main.config.addDefault("key.gold.lore", defaultLore);
		
		Main.config.options().copyDefaults(true);
		this.saveConfig();
	}
}
