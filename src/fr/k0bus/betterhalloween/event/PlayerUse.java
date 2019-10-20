package fr.k0bus.betterhalloween.event;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.k0bus.betterhalloween.Main;
import fr.k0bus.betterhalloween.items.KeyItem;
import fr.k0bus.betterhalloween.items.KeyType;
import fr.k0bus.betterhalloween.items.Loots;

public class PlayerUse implements Listener{
	@EventHandler(ignoreCancelled = true)
    public void onUse(PlayerInteractEvent e)
    {
		if(e.getAction() == Action.RIGHT_CLICK_BLOCK)
		{
			Player p = e.getPlayer();
			Block b = e.getClickedBlock();
			Block bL = b.getWorld().getBlockAt(new Location(b.getWorld(), b.getX(), b.getY() -1, b.getZ()));
			Block bU = b.getWorld().getBlockAt(new Location(b.getWorld(), b.getX(), b.getY() +1, b.getZ()));
			ItemStack i = e.getItem();
			if(b.getType() == Material.JACK_O_LANTERN && bU.getType() == Material.SKELETON_SKULL && bL.getType() == Material.DIAMOND_BLOCK && i.getItemMeta() != null)
			{
				if(i.getItemMeta().hasEnchant(Enchantment.MENDING))
				{
					String v = null;
					switch (i.getType()){
					case IRON_NUGGET:
						v = "iron";
						break;
					case GOLD_NUGGET:
						v = "gold";
						break;
					default:
						break;
					
					}
					if(v != null)
					{
						p.sendMessage(Main.tag + " Vous avez utilisé une " + Main.config.getString("key."+v+".name") + " sur un " + Main.config.getString("chest.name") + " !");
						ItemStack loot = this.getRandomItem(v);
						if(loot != null)
						{
							p.getInventory().addItem(loot);
							String name = loot.getItemMeta().getLocalizedName();
							if(loot.getItemMeta().getDisplayName()!= "")
							{
								name = loot.getItemMeta().getDisplayName();
							}
							
							Main.plugin.getServer().broadcastMessage(Main.tag + p.getDisplayName() + " a gagné " + loot.getAmount() + " " + name + " en ouvrant un " + Main.config.getString("chest.name"));
						}
						else
						{
							p.sendMessage(Main.tag + "Le coffre est vide !");
						}
						
						i.setAmount(i.getAmount()-1);
					}
				}
			}
		}
    }
	private ItemStack getRandomItem(String type)
	{
		int max_rand = 0;
		ConfigurationSection sub = Main.config.getConfigurationSection("key." + type + ".loot");
		Set<String> subKeys = sub.getKeys(false);
		List<Loots> loots = new ArrayList<Loots>();
		ConfigurationSection c = null;
		boolean isItem = true;
		String win = null;
		//Start randomizer
		int min = max_rand;
		max_rand = max_rand+ Main.config.getInt("key." + type + ".chance_ironkey");
		loots.add(new Loots(min, max_rand, "ironkey"));
		
		min = max_rand;
		max_rand = max_rand+ Main.config.getInt("key." + type + ".chance_goldkey");
		loots.add(new Loots(min, max_rand, "goldkey"));
		
		min = max_rand;
		max_rand = max_rand+ Main.config.getInt("key." + type + ".chance_paper");
		loots.add(new Loots(min, max_rand, "paper"));
		for (String s : subKeys)
		{
			min = max_rand;
			max_rand = max_rand+ Main.config.getInt("key." + type + ".loot." + s + ".chance");
			loots.add(new Loots(min, max_rand, s));
			Main.plugin.getServer().broadcastMessage("+ " + s + " " + min + "-" + max_rand);
		}
		long random = Math.round(Math.random() * max_rand);
		for(Loots loot : loots)
		{
			if(random > loot.getMin() && random <= loot.getMax())
			{
				if(loot.getKey() == "ironkey" || loot.getKey() == "goldkey" || loot.getKey() == "paper")
				{
					isItem = false;
					win = loot.getKey();
				}
				else
				{
					loots = null;
					c = Main.config.getConfigurationSection("key." + type + ".loot." + loot.getKey());
				}
				break;
			}
		}
		ItemStack i = null;
		if(isItem)
		{
			//Create Item
			i = new ItemStack(Material.getMaterial(c.getString("item").toUpperCase()),c.getInt("count"));
			ItemMeta meta = i.getItemMeta();
			meta.setDisplayName(c.getString("name"));
			meta.setLore(c.getStringList("lore"));
			meta.setUnbreakable(c.getBoolean("unbreakable"));
			ConfigurationSection enchantConf = c.getConfigurationSection("enchant");
			Set<String> enchantList = enchantConf.getKeys(false);
			i.setItemMeta(meta);
			for (String s : enchantList)
			{
				i.addUnsafeEnchantment(Enchantment.getByKey(NamespacedKey.minecraft(s)), enchantConf.getInt(s));
			}
		}
		else
		{
			switch(win){
			case "ironkey":
				i = new KeyItem(KeyType.IRON, 1);
				break;
			case "goldkey":
				i = new KeyItem(KeyType.GOLD, 1);
				break;
			case "paper":
				//TODO Make Paper items
				break;
			}
		}
		return i;
	}
}
