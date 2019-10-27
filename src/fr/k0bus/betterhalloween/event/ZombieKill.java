package fr.k0bus.betterhalloween.event;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import fr.k0bus.betterhalloween.Main;
import fr.k0bus.betterhalloween.items.KeyItem;
import fr.k0bus.betterhalloween.items.KeyType;

public class ZombieKill implements Listener{
	@EventHandler(ignoreCancelled = true)
    public void onKill(EntityDeathEvent e)
    {
    	if(e.getEntity().getKiller() instanceof Player && e.getEntity().hasMetadata("herobrine"))
    	{
    		Player p = (Player) e.getEntity().getKiller();
    		Main.plugin.getServer().broadcastMessage(Main.tag + p.getDisplayName() + " a détruit un fragment d'âme d'Herobrine !");
    		ItemStack key = new KeyItem(KeyType.IRON, 1);
    		e.getDrops().clear();
    		e.getEntity().getWorld().dropItemNaturally(e.getEntity().getLocation(), key);
    	}
    }
}
