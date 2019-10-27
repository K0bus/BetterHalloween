package fr.k0bus.betterhalloween.event;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMove implements Listener
{
    BossBar bb = Bukkit.createBossBar("Un fragment d'Herobrine est prêt de vous !", BarColor.RED, BarStyle.SOLID,BarFlag.PLAY_BOSS_MUSIC);

    @EventHandler(ignoreCancelled = true)
    public void onMove(PlayerMoveEvent e)
    {
        Player p = e.getPlayer();
        List<Entity> nearby = p.getNearbyEntities(30, 30, 30);
        boolean hasHerobrine = false;
        for (Entity entity: nearby)
        {
            if(entity instanceof Zombie)
            {
                Zombie z = (Zombie) entity;
                if(z.hasMetadata("herobrine"))
                {
                    hasHerobrine = true;
                    break;
                }
            }
        }
        if(hasHerobrine)
        {
            bb.addPlayer(p);
        }
        else
        {
            bb.removePlayer(p);
        }
    }
}