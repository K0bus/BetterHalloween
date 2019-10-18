package fr.k0bus.betterhalloween.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import fr.k0bus.betterhalloween.ExceptionClass;
import fr.k0bus.betterhalloween.Main;

public class MainCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player)
		{
			Player p = (Player) sender;
			if(args.length>0)
			{
				switch(args[0]){
					case "items":
						//Listing all give commands
						if(p.hasPermission("bh.items"))
						{
							p.sendMessage("List des give disponible : ");
							for(GiveList gl : GiveList.values())
							{
								p.sendMessage("   - " + gl.getCommand() + " : " + gl.getItem().getItemMeta().getDisplayName());
							}
							return true;
						}
						break;
					case "give":
						//Give commands
						if(args.length>1)
						{
							if(p.hasPermission("bh.give"))
							{
								GiveList gl = GiveList.fromString(args[1]);
								if(gl != null)
								{
									if(args.length > 2 && ExceptionClass.isNumeric(args[2]))
									{
										int count = Integer.parseInt(args[2]);
										ItemStack item = gl.getItem();
										item.setAmount(count);
										p.getInventory().addItem(item);
										p.sendMessage(Main.tag + count + " " + item.getItemMeta().getDisplayName() + " ajouté à votre inventaire !");
										return true;
									}
									else
									{
										p.getInventory().addItem(gl.getItem());
										p.sendMessage(Main.tag + 1 + " " + gl.getItem().getItemMeta().getDisplayName() + " ajouté à votre inventaire !");
										return true;
									}
								}
								else
								{
									p.sendMessage(Main.tag + ChatColor.RED + "Item inconnus. (/bh items)");
								}
							}
							else
							{
								p.sendMessage(Main.tag + ChatColor.RED + "Vous n'êtes pas autorisé à faire cette commande.");
							}
						}
						else
						{
							p.sendMessage(Main.tag + ChatColor.RED + "Merci de préciser un items. (/bh items)");
						}
						break;
					default:
						p.sendMessage(Main.tag + ChatColor.RED + "Commande introuvable !");
						break;
				}
			}
			else
			{
				sender.sendMessage(Main.tag + ChatColor.RED + "Commande introuvable !");
			}
		}
		else
		{
			sender.sendMessage(Main.tag + ChatColor.RED + "Seul les joueurs peuvent executer cette commandes.");
		}
		return true;
	}
}
