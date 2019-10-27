package fr.k0bus.betterhalloween;

import java.io.File;

import org.bukkit.configuration.Configuration;

public class ConfigManager {
    
    private Main plugin;
    private Configuration config;

    public ConfigManager(Main plugin)
    {
        this.plugin = plugin;
        this.setDefaultConfig();
        this.loadConfig();
    }
	private void setDefaultConfig()
	{
		File mainconfigfile = new File(plugin.getDataFolder(), "config.yml");
		if (!mainconfigfile.exists())
		{            
			plugin.saveResource("config.yml", true);
		}
	}
    private void loadConfig()
    {
        this.config = plugin.getConfig().getRoot();
    }
    public Configuration getConfig()
    {
        return this.config;
    }
}