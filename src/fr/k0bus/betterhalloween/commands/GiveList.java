package fr.k0bus.betterhalloween.commands;

import org.bukkit.inventory.ItemStack;

import fr.k0bus.betterhalloween.items.ChestItem;
import fr.k0bus.betterhalloween.items.KeyItem;
import fr.k0bus.betterhalloween.items.KeyType;
import fr.k0bus.betterhalloween.items.PaperItem;
import fr.k0bus.betterhalloween.items.BookItem;

public enum GiveList {
	IronKey ("ironkey", new KeyItem(KeyType.IRON, 1)),
	GoldKey("goldkey", new KeyItem(KeyType.GOLD, 1)),
	HeroChest("chest", new ChestItem(1)),
	Pages1("page_1", new PaperItem(1,1)),
	Pages2("page_2", new PaperItem(2,1)),
	Pages3("page_3", new PaperItem(3,1)),
	Pages4("page_4", new PaperItem(4,1)),
	Pages5("page_5", new PaperItem(5,1)),
	Pages6("page_6", new PaperItem(6,1)),
	Pages7("page_7", new PaperItem(7,1)),
	Pages8("page_8", new PaperItem(8,1)),
	Pages9("page_9", new PaperItem(9,1)),
	Book("book", new BookItem(1));
	
	String cmd = "";
	ItemStack item = null;
	
	GiveList(String cmd, ItemStack item)
	{
		this.cmd = cmd;
		this.item = item;
	}
	
	public ItemStack getItem()
	{
		return item;
	}
	public String getCommand()
	{
		return cmd;
	}
	public static GiveList fromString(String cmd)
	{
		for (GiveList gl : GiveList.values())
		{
			if(gl.cmd.equalsIgnoreCase(cmd))
			{
				return gl;
			}
		}
		return null;
	}
}
