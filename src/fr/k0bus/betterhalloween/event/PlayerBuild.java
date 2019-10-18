package fr.k0bus.betterhalloween.event;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

import fr.k0bus.betterhalloween.Main;

public class PlayerBuild  implements Listener{
	@EventHandler(ignoreCancelled = true)
    public void onPlace(BlockPlaceEvent e)
    {
		Player p = e.getPlayer();
		ItemStack item = e.getItemInHand();
		Block block = e.getBlockPlaced();
		Block bL = block.getWorld().getBlockAt(new Location(block.getWorld(), block.getX(), block.getY() -1, block.getZ()));
		Block bU = block.getWorld().getBlockAt(new Location(block.getWorld(), block.getX(), block.getY() +1, block.getZ()));
		
		if(item.getItemMeta().hasLore() && item.getType() == Material.JACK_O_LANTERN && p.hasPermission("bh.chest.build"))
		{
			Main.plugin.getServer().broadcastMessage(Main.tag + p.getDisplayName() + " a posé un coffre hanté !");
			bU.setType(Material.SKELETON_SKULL);
			bL.setType(Material.DIAMOND_BLOCK);
			block.setMetadata("herobrine", new FixedMetadataValue(Main.plugin, true));
		}
		
    }
}
