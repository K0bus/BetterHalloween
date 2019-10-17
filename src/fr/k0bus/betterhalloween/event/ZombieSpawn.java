package fr.k0bus.betterhalloween.event;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import fr.k0bus.betterhalloween.Main;

public class ZombieSpawn implements Listener{
	@SuppressWarnings("deprecation")
	@EventHandler(ignoreCancelled = true)
	public void onMobSpawn(CreatureSpawnEvent e)
	{
		Random rand = new Random();
		double de = rand.nextInt((int) (Math.floor(100) - Math.floor(0))) + Math.floor(0) + 0.5;
		if(e.getEntity() instanceof Zombie && de > 50)
		{
			Main.plugin.getServer().broadcastMessage(Main.tag + "Un fragment d'âme d'Herobrine vient d'apparaitre !");
			this.addHead(e.getEntity(), "Herobrine");
			Zombie z = (Zombie) e.getEntity();
			z.setMaxHealth(60);
			z.setHealth(60);
			z.setCustomName("Herobrine");
			z.setCustomNameVisible(true);
			z.setCanPickupItems(false);
			z.isSilent();
			z.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, -1, 1));
			z.setMetadata("herobrine", new FixedMetadataValue(Main.plugin, true));
		}
	}

	@SuppressWarnings("deprecation")
	private void addHead(LivingEntity e, String name)
	{
		ItemStack skull = new ItemStack(Material.PLAYER_HEAD, 1, (byte) 3);
		SkullMeta sm = (SkullMeta) skull.getItemMeta();
		sm.setOwner(name);
		e.getEquipment().setHelmet(skull);
	}
}
