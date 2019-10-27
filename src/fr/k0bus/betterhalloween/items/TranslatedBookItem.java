package fr.k0bus.betterhalloween.items;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import fr.k0bus.betterhalloween.Main;
import net.md_5.bungee.api.ChatColor;

public class TranslatedBookItem extends ItemStack {
    public TranslatedBookItem()
    {
        this.setType(Material.WRITTEN_BOOK);
        this.setAmount(1);
        BookMeta bm = (BookMeta)this.getItemMeta();
        bm.setAuthor(Main.config.getString("zombie.head-name"));
        bm.setTitle(Main.config.getString("translated_book.name"));
        List<String> pages = Main.config.getStringList("translated_book.pages");
        List<String> coloredPages = new ArrayList<String>();
        for (String page : pages) {
            coloredPages.add(ChatColor.translateAlternateColorCodes('&', page));
        }
        bm.setPages(coloredPages);
        List<String> lore = Main.config.getStringList("translated_book.lore");
        bm.setLore(lore);
        this.setItemMeta(bm);
    }
}