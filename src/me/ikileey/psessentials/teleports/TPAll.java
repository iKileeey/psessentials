package me.ikileey.psessentials.teleports;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.ikileey.psessentials.messages.Mensagens;

public class TPAll implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("tpall")){
			if(sender instanceof Player){
				Player p = (Player)sender;
				if(p.hasPermission("psessentials.tpall")){
					for(Player onlines : Bukkit.getOnlinePlayers()){
						if(onlines != p){
							onlines.teleport(p);
							onlines.sendMessage(Mensagens.getMensagem("tp_all_other").replace("@Player", p.getName()));
						}
					}
					p.sendMessage(Mensagens.getMensagem("tp_all"));
				}else{
					p.sendMessage(Mensagens.getErro("sem_perm"));
				}
			}else{
				sender.sendMessage("§cExecutável apenas in-game.");
			}
		}
		return false;
	}
}
