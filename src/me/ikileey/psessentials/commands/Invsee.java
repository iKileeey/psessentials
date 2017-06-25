package me.ikileey.psessentials.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

import me.ikileey.psessentials.messages.Mensagens;

public class Invsee implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("invsee")){
			if(sender instanceof Player){
				Player p = (Player)sender;
				if(p.hasPermission("psessentials.invsee") && !p.isOp()){
					if(args.length == 1){
						Player p1 = Bukkit.getPlayer(args[0]);
						if(p1 == null){
							p.sendMessage(Mensagens.getErro("pne"));
						}else{
							Inventory inv = Bukkit.createInventory((InventoryHolder)p1, p1.getInventory().getSize(), "Inventário");
							inv.setContents(p1.getInventory().getContents());
							p.openInventory(inv);
							p.sendMessage(Mensagens.getMensagem("invsee").replace("@Player", p1.getName()));
						}
					}
				}
				if(p.hasPermission("psessentials.invsee.modify")){
					if(args.length == 1){
						Player p1 = Bukkit.getPlayer(args[0]);
						if(p1 == null){
							p.sendMessage(Mensagens.getErro("pne"));
						}else{
							p.openInventory(p1.getInventory());
							p.sendMessage(Mensagens.getMensagem("invsee_op").replace("@Player", p1.getName()));
						}
					}
				}
			}
		}
		return false;
	}
}
