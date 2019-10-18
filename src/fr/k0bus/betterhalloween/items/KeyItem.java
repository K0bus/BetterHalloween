package fr.k0bus.betterhalloween.items;

import java.util.List;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.k0bus.betterhalloween.Main;

public class KeyItem extends ItemStack {

	public KeyItem(KeyType type, int count)
	{
		this.setType(type.getMaterial());
		this.setAmount(count);
    	ItemMeta keyMeta = this.getItemMeta();
    	keyMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
    	keyMeta.setDisplayName(Main.config.getString("key." + type.toString() + ".name"));
    	List<String> lore = Main.config.getStringList("key." + type.toString() + ".lore");
    	keyMeta.setLore(lore);
    	keyMeta.setUnbreakable(true);
    	this.setItemMeta(keyMeta);
    	this.addUnsafeEnchantment(Enchantment.MENDING, 1);
	}
	
}
