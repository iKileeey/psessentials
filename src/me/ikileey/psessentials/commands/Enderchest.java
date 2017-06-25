package me.ikileey.psessentials.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.ikileey.psessentials.messages.Mensagens;

public class Enderchest implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("echest")){
			if(sender instanceof Player){
				Player p = (Player)sender;
				if(args.length == 0){
					if(p.hasPermission("psessentials.echest")){
						p.openInventory(p.getEnderChest());
						p.sendMessage(Mensagens.getMensagem("echest_aberto"));
					}else{
						p.sendMessage(Mensagens.getErro("sem_perm"));
					}
				}
				if(args.length == 1){
					if(p.hasPermission("psessentials.echest.other")){
						Player p1 = Bukkit.getPlayer(args[0]);
						if(p1 == null){
							p.sendMessage(Mensagens.getErro("pne"));
						}else{
							p.openInventory(p1.getInventory());
							p.sendMessage(Mensagens.getMensagem("echest_aberto_by_other").replace("@Player", p1.getName()));
						}
					}
				}
			}else{
				sender.sendMessage("§eExecutável apenas in-game.");
			}
		}
		return false;
	}
}
