package fr.k0bus.betterhalloween.items;

public class Loots {
	int min;
	int max;
	String key;
	public Loots(int min, int max, String key)
	{
		this.min = min;
		this.max = max;
		this.key = key;
	}
	public int getMin()
	{
		return this.min;
	}
	public int getMax()
	{
		return this.max;
	}
	public String getKey()
	{
		return this.key;
	}
}
