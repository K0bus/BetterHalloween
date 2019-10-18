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
import fr.k0bus.betterhalloween.commands.MainCommand;
import fr.k0bus.betterhalloween.event.PlayerBreak;
import fr.k0bus.betterhalloween.event.PlayerBuild;
import fr.k0bus.betterhalloween.event.PlayerUse;
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
		this.getCommand("bh").setExecutor(new MainCommand());
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
		pm.registerEvents(new PlayerBuild(), this);
		pm.registerEvents(new PlayerBreak(), this);
		pm.registerEvents(new PlayerUse(), this);
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

		List <String> defaultKeyLore= new ArrayList<String>();
		defaultKeyLore.add("Une clé d'Herobrine pour un coffre légendaire !");
		defaultKeyLore.add("Rendez vous au spawn pour ouvrir un coffre !");
		List <String> defaultChestLore= new ArrayList<String>();
		defaultKeyLore.add("Un coffre hanté fermé par un verrou magique !");
		defaultKeyLore.add("Il semble attirer les fragments d'âme d'HeroBrine !");
		
		Main.config.addDefault("key.iron.name", "Clé en fer");
		Main.config.addDefault("key.iron.lore", defaultKeyLore);
		Main.config.addDefault("key.iron.chance_ironkey", 5);
		Main.config.addDefault("key.iron.chance_goldkey", 1);
		Main.config.addDefault("key.iron.chance_paper", 1);
		Main.config.addDefault("key.gold.name", "Clé en or");
		Main.config.addDefault("key.gold.lore", defaultKeyLore);
		Main.config.addDefault("key.gold.chance_ironkey", 2);
		Main.config.addDefault("key.gold.chance_goldkey", 0);
		Main.config.addDefault("key.gold.chance_paper", 3);
		Main.config.addDefault("chest.name", "Coffre hanté");
		Main.config.addDefault("chest.lore", defaultChestLore);
		
		Main.config.options().copyDefaults(true);
		this.saveConfig();
	}
}
