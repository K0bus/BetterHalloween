package fr.k0bus.betterhalloween.items;

import org.bukkit.Material;

public enum KeyType {
	IRON ("iron", Material.IRON_NUGGET),
	GOLD ("gold", Material.GOLD_NUGGET);
	
	private String value = "";
	private Material material = null;
	
	KeyType(String value, Material material)
	{
		this.value = value;
		this.material = material;
	}
	public String toString()
	{
		return value;
	}
	public Material getMaterial()
	{
		return material;
	}
}
