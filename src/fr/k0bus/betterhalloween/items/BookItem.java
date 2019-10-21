package fr.k0bus.betterhalloween.items;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class BookItem extends ItemStack
{
    public BookItem(int count)
    {
        this.setType(Material.BOOK);
        this.setAmount(1);
        ItemMeta im = this.getItemMeta();
        im.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        im.setDisplayName("Livre maculé de sang");
        List<String> lore = new ArrayList<String>();
        lore.add("Un livre maculé de sang !");
        lore.add("Il reste indéchiffrable ...");
        lore.add("Peut être qu'un bibliothécaire pourrais nous aider.");
        im.setLore(lore);
        this.setItemMeta(im);
        this.addUnsafeEnchantment(Enchantment.MENDING, 1);
    }
}