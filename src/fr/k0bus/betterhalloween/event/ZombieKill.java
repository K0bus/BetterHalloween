package fr.k0bus.betterhalloween.event;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.k0bus.betterhalloween.Main;

public class ZombieKill implements Listener{
    @SuppressWarnings({ "deprecation" })
	@EventHandler(ignoreCancelled = true)
    public void onKill(EntityDeathEvent e)
    {
    	if(e.getEntity().getKiller() instanceof Player && e.getEntity().hasMetadata("herobrine"))
    	{
    		Player p = (Player) e.getEntity().getKiller();
    		Main.plugin.getServer().broadcastMessage(Main.tag + p.getDisplayName() + " a détruit un fragment d'âme d'Herobrine !");
    		ItemStack key = new ItemStack(Material.IRON_NUGGET, 1, (byte) 3);
    		ItemMeta keyMeta = key.getItemMeta();
    		keyMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
    		keyMeta.setDisplayName(Main.config.getString("key.iron.name"));
    		List<String> lore = Main.config.getStringList("key.iron.lore");
    		keyMeta.setLore(lore);
    		keyMeta.setUnbreakable(true);
    		key.setItemMeta(keyMeta);
    		key.addUnsafeEnchantment(Enchantment.MENDING, 1);
    		e.getDrops().clear();
    		e.getEntity().getWorld().dropItemNaturally(e.getEntity().getLocation(), key);
    	}
    }
}
