package fr.k0bus.betterhalloween.items;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.k0bus.betterhalloween.Main;

public class ChestItem extends ItemStack{

	public ChestItem(int count)
	{
		this.setType(Material.JACK_O_LANTERN);
		this.setAmount(count);
    	ItemMeta keyMeta = this.getItemMeta();
    	keyMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
    	keyMeta.setDisplayName(Main.config.getString("chest.name"));
    	List<String> lore = Main.config.getStringList("chest.lore");
    	keyMeta.setLore(lore);
    	keyMeta.setUnbreakable(true);
    	this.setItemMeta(keyMeta);
    	this.addUnsafeEnchantment(Enchantment.MENDING, 1);
	}
}
