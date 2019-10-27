package fr.k0bus.betterhalloween.event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import fr.k0bus.betterhalloween.Main;

public class PlayerBreak implements Listener{
	@EventHandler(ignoreCancelled = true)
    public void onBreak(BlockBreakEvent e)
    {
		if(e.getBlock().hasMetadata("herobrine"))
		{
			Main.plugin.getServer().broadcastMessage(Main.tag + e.getPlayer().getDisplayName() + " a détruit un coffre hanté !");
		}
    }
}
