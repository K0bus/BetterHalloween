package fr.k0bus.betterhalloween;

import java.util.logging.Level;

import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.Configuration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import fr.k0bus.betterhalloween.commands.MainCommand;
import fr.k0bus.betterhalloween.event.PlayerBreak;
import fr.k0bus.betterhalloween.event.PlayerBuild;
import fr.k0bus.betterhalloween.event.PlayerCraft;
import fr.k0bus.betterhalloween.event.PlayerUse;
import fr.k0bus.betterhalloween.event.PlayerUseEntity;
import fr.k0bus.betterhalloween.event.ZombieKill;
import fr.k0bus.betterhalloween.event.ZombieSpawn;
import fr.k0bus.betterhalloween.items.BookItem;
import fr.k0bus.betterhalloween.items.KeyItem;
import fr.k0bus.betterhalloween.items.KeyType;
import fr.k0bus.betterhalloween.items.PaperItem;;

public class Main extends JavaPlugin{
	
	public static String tag;
	public static ConfigManager configManager;
	public static Configuration config;
	public static Plugin plugin;
	
	@Override
	public void onEnable()
	{
		//Config
		this.getLogger().log(Level.INFO, "Loading config.");
		Main.configManager = new ConfigManager(this);
		Main.config = configManager.getConfig();
		Main.tag = ChatColor.translateAlternateColorCodes('&', Main.config.getString("tag") + "&r ");
		Main.plugin = this;
		// Commands
		this.getLogger().log(Level.INFO, "Loading commands.");
		this.getCommand("bh").setExecutor(new MainCommand());
		// Events
		this.getLogger().log(Level.INFO, "Loading events.");
		this.registerEvent(this.getServer().getPluginManager());
		//Adding recipe
		this.registerRecipe();
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
		pm.registerEvents(new PlayerUseEntity(), this);
		pm.registerEvents(new PlayerCraft(), this);
	}
	private void registerRecipe()
	{
		ItemStack goldKey = new KeyItem(KeyType.GOLD, 1);
		ItemStack ironKey = new KeyItem(KeyType.IRON, 1);
		ItemStack paper = new PaperItem(0, 1);
		ItemStack book = new BookItem(1);
	    ShapedRecipe goldKeyRecipe = new ShapedRecipe(new NamespacedKey(this, "goldKey_recipe"), goldKey);
	    goldKeyRecipe.shape("kkk","kkk","kkk");
	    goldKeyRecipe.setIngredient('k', ironKey.getData());
		this.getServer().addRecipe(goldKeyRecipe);
		ShapedRecipe bookRecipe = new ShapedRecipe(new NamespacedKey(this, "book_recipe"), book);
		bookRecipe.shape("xxx", "xxx", "xxx");
		bookRecipe.setIngredient('x', paper.getData());
		this.getServer().addRecipe(bookRecipe);
	}
}
