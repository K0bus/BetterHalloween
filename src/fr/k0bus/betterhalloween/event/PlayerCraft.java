package fr.k0bus.betterhalloween.event;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;

import fr.k0bus.betterhalloween.items.KeyItem;
import fr.k0bus.betterhalloween.items.KeyType;

public class PlayerCraft implements Listener {
	@EventHandler(ignoreCancelled = true)
    public void onCraft(PrepareItemCraftEvent e)
    {
		ItemStack air = new ItemStack(Material.AIR);
		if(e.getRecipe() != null)
		{
			if(e.getRecipe().getResult().equals(new KeyItem(KeyType.GOLD, 1)))
			{
				for (int a = 1; a < 9; a++)
				{
					if (!e.getInventory().getItem(a).containsEnchantment(Enchantment.MENDING))
					{
						e.getInventory().setResult(air);
					}
				}
			}	
		}
    }
}
