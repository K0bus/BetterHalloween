package fr.k0bus.betterhalloween;

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
		Main.config.options().copyDefaults(true);
		this.saveConfig();
	}
}
