package fr.k0bus.betterhalloween.items;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PaperItem extends ItemStack
{
    public PaperItem(int pageNumber, int count)
    {
        this.setType(Material.PAPER);
        this.setAmount(count);
        ItemMeta im = this.getItemMeta();
        im.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        im.setDisplayName("Page arraché n°" + Integer.toString(pageNumber));
        List<String> lore = new ArrayList<String>();
        lore.add("Une page maculé de sange arraché");
        lore.add("Vous voyez en bas de cette page " + Integer.toString(pageNumber) + "/9");
        lore.add("En trouvant les autres pages vous pourrez peut être les déchiffrer !");
        im.setLore(lore);
        this.setItemMeta(im);
        this.addUnsafeEnchantment(Enchantment.MENDING, 1);
    }
}