package me.ikileey.psessentials.teleports;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.ikileey.psessentials.messages.Mensagens;

public class Tphere implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("puxar")){
			if(sender instanceof Player){
				Player p = (Player)sender;
				if(p.hasPermission("psessentials.puxar")){
					if(args.length == 1){
						Player p1 = Bukkit.getPlayer(args[0]);
						if(p1 == null){
							p.sendMessage(Mensagens.getErro("pne"));
						}else{
							p1.teleport(p);
							p.sendMessage(Mensagens.getMensagem("tphere").replace("@Player", p1.getName()));
						}
					}else{					
						p.sendMessage("§cUse: /puxar <jogador>");
					}
				}else{
					p.sendMessage(Mensagens.getErro("sem_perm"));
				}
			}
		}
		return false;
	}
}
