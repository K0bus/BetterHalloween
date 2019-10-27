package fr.k0bus.betterhalloween.event;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.entity.Villager.Profession;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;

import fr.k0bus.betterhalloween.Main;
import fr.k0bus.betterhalloween.items.TranslatedBookItem;

public class PlayerUseEntity implements Listener{
    @EventHandler(ignoreCancelled = true)
    public void onUse(PlayerInteractEntityEvent e)
    {
        if(e.getRightClicked() instanceof Villager)
        {
            Villager v = (Villager) e.getRightClicked();
            Player p = e.getPlayer();
            ItemStack hand = p.getInventory().getItemInMainHand();
            if (v.getProfession() == Profession.LIBRARIAN && hand.getType() == Material.BOOK && hand.getItemMeta().hasEnchant(Enchantment.MENDING)){
                p.sendMessage(Main.tag + "Le libraire vous fournis un exemplaire traduit du livre maculé de sang.");
                p.getInventory().setItemInMainHand(new TranslatedBookItem());;
                e.setCancelled(true);
            }
        }
    }
}