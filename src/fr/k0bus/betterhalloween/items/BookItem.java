package fr.k0bus.betterhalloween.items;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.k0bus.betterhalloween.Main;

public class BookItem extends ItemStack
{
    public BookItem(int count)
    {
        this.setType(Material.BOOK);
        this.setAmount(1);
        ItemMeta im = this.getItemMeta();
        im.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        im.setDisplayName(Main.config.getString("book.name"));
        List<String> lore = Main.config.getStringList("book.lore");
        im.setLore(lore);
        this.setItemMeta(im);
        this.addUnsafeEnchantment(Enchantment.MENDING, 1);
    }
}