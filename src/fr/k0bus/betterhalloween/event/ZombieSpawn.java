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
		if(e.getEntity() instanceof Zombie && de < Main.config.getInt("zombie.chance") && e.getEntity() != null)
		{
			Main.plugin.getServer().broadcastMessage(Main.tag + "Un fragment d'âme d'Herobrine vient d'apparaitre !");
			this.addHead(e.getEntity(), Main.config.getString("zombie.head-name"));
			Zombie z = (Zombie) e.getEntity();
			z.setMaxHealth(Main.config.getDouble("zombie.health"));
			z.setHealth(Main.config.getDouble("zombie.health"));
			z.setCustomName(Main.config.getString("zombie.head-name"));
			z.setCustomNameVisible(true);
			z.setCanPickupItems(false);
			z.isSilent();
			if(Main.config.getBoolean("zombie.effect.invisibility.status"))
			{
				z.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, Main.config.getInt("zombie.effect.invisibility.value"), true, false));
			}
			if(Main.config.getBoolean("zombie.effect.speed.status"))
			{
				z.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, Main.config.getInt("zombie.effect.speed.value"), true, false));
			}
			if(Main.config.getBoolean("zombie.effect.fire_resistance.status"))
			{
				z.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, Integer.MAX_VALUE, Main.config.getInt("zombie.effect.fire_resistance.value"), true, false));
			}
			if(Main.config.getBoolean("zombie.effect.damage_resistance.status"))
			{
				z.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, Main.config.getInt("zombie.effect.damage_resistance.value"), true, false));
			}
			z.setMetadata("herobrine", new FixedMetadataValue(Main.plugin, true));
		}
	}

	@SuppressWarnings("deprecation")
	private void addHead(LivingEntity e, String name)
	{
		ItemStack skull = new ItemStack(Material.PLAYER_HEAD, 1, (byte) 3);
		SkullMeta sm = (SkullMeta) skull.getItemMeta();
		sm.setOwner(name);
		skull.setItemMeta(sm);
		e.getEquipment().setHelmet(skull);
	}
}
