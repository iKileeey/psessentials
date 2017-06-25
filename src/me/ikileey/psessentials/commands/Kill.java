package me.ikileey.psessentials.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.ikileey.psessentials.messages.Mensagens;

public class Kill implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("kill")){
			if(sender instanceof Player){
				Player p = (Player)sender;
				if(p.hasPermission("psessentials.kill")){
					if(args.length == 1){
						Player p1 = Bukkit.getPlayer(args[0]);
						if(p1 == null){
							p.sendMessage(Mensagens.getErro("pne"));
						}else{
							p1.setHealth(0);
							p1.sendMessage(Mensagens.getMensagem("kill").replace("@Player", p.getName()));
							p.sendMessage(Mensagens.getMensagem("kill_by_other").replace("@Player", p1.getName()));
						}
					}else{
						p.sendMessage("§cUse: /kill <jogador>");
					}
				}else{
					p.sendMessage(Mensagens.getErro("sem_perm"));
				}
			}else{
				if(sender.hasPermission("psessentials.kill")){
					if(args.length == 1){
						Player p1 = Bukkit.getPlayer(args[0]);
						if(p1 == null){
							sender.sendMessage(Mensagens.getErro("pne"));
						}else{
							p1.setHealth(0);
							p1.sendMessage(Mensagens.getMensagem("kill").replace("@Player", sender.getName()));
							sender.sendMessage(Mensagens.getMensagem("kill_by_other").replace("@Player", p1.getName()));
						}
					}else{
						sender.sendMessage("§cUse: /kill <jogador>");
					}
				}
			}
		}
		return false;
	}
}
