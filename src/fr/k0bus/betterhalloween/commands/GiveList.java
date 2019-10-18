package fr.k0bus.betterhalloween.commands;

import org.bukkit.inventory.ItemStack;

import fr.k0bus.betterhalloween.items.ChestItem;
import fr.k0bus.betterhalloween.items.KeyItem;
import fr.k0bus.betterhalloween.items.KeyType;

public enum GiveList {
	IronKey ("ironkey", new KeyItem(KeyType.IRON, 1)),
	GoldKey("goldkey", new KeyItem(KeyType.GOLD, 1)),
	HeroChest("chest", new ChestItem(1));
	
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
