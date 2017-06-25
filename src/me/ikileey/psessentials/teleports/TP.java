package me.ikileey.psessentials.teleports;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.ikileey.psessentials.messages.Mensagens;

public class TP implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("tp")){
			if(sender instanceof Player){
				Player p = (Player)sender;
				if(p.hasPermission("psessentials.tp")){
					if(args.length == 1){
						Player other = Bukkit.getPlayer(args[0]);
						if(other == null){
							p.sendMessage(Mensagens.getErro("pne"));
						}else{
							p.teleport(other);
							p.sendMessage(Mensagens.getMensagem("tp").replace("@Player", other.getName()));
						}
					}
					if(args.length == 2){
						if(p.hasPermission("psessentials.tp.other")){
							Player p1 = Bukkit.getPlayer(args[0]);
							Player p2 = Bukkit.getPlayer(args[1]);
							if(p1 == null){
								p.sendMessage(Mensagens.getErro("tp_erro").replace("@Player", args[0]));
								return false;
							}
							if(p2 == null){
								p.sendMessage(Mensagens.getErro("tp_erro").replace("@Player", args[1]));
								return false;
							}
							p1.teleport(p2);
							p.sendMessage(Mensagens.getMensagem("tp_other").replace("@Player1", p1.getName()).replace("@Player2", p2.getName()));
						}
					}
					if(args.length == 0){
						p.sendMessage("§cUse: /tp <jogador>");
					}
				}else{
					p.sendMessage(Mensagens.getErro("sem_perm"));
				}
			}else{
				if(args.length == 2){
					Player p1 = Bukkit.getPlayer(args[0]);
					Player p2 = Bukkit.getPlayer(args[1]);
					if(p1 == null){
						sender.sendMessage(Mensagens.getErro("tp_erro").replace("@Player", args[0]));
						return false;
					}
					if(p2 == null){
						sender.sendMessage(Mensagens.getErro("tp_erro").replace("@Player", args[1]));
						return false;
					}
					p1.teleport(p2);
					sender.sendMessage(Mensagens.getMensagem("tp_other").replace("@Player1", p1.getName()).replace("@Player2", p2.getName()));
				}else{
					sender.sendMessage("§cUse: /tp <jogador> <jogador>");
				}
			}
		}
		return false;
	}
}
