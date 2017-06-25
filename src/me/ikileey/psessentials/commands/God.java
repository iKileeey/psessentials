package me.ikileey.psessentials.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.ikileey.psessentials.messages.Mensagens;
import me.ikileey.psessentials.utils.Utils;

public class God implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("deus")){
			if(sender instanceof Player){
				Player p = (Player)sender;
				if(args.length == 0){
					if(p.hasPermission("psessentials.god")){
						if(!Utils.hasGod(p)){
							Utils.setGod(p, true);
							p.sendMessage(Mensagens.getMensagem("god_ativo"));
						}else{
							Utils.setGod(p, false);
							p.sendMessage(Mensagens.getMensagem("god_desativado"));
						}
					}
				}
				if(args.length == 1){
					if(p.hasPermission("psessentials.god.other")){
						Player p1 = Bukkit.getPlayer(args[0]);
						if(p1 == null){
							p.sendMessage(Mensagens.getErro("pne"));
						}else{
							if(!Utils.hasGod(p1)){
								Utils.setGod(p1, true);
								p1.sendMessage(Mensagens.getMensagem("god_ativo"));
								p.sendMessage(Mensagens.getMensagem("god_ativo_by_other").replace("@Player", p1.getName()));
							}else{
								Utils.setGod(p1, false);
								p1.sendMessage(Mensagens.getMensagem("god_desativado"));
								p.sendMessage(Mensagens.getMensagem("god_desativado_by_other").replace("@Player", p1.getName()));
							}
						}
					}
				}
			}else{
				if(args.length == 0){
					sender.sendMessage("§cUse: /deus <jogador>");
				}else{
					Player p1 = Bukkit.getPlayer(args[0]);
					if(p1 == null){
						sender.sendMessage(Mensagens.getErro("pne"));
					}else{
						if(!Utils.hasGod(p1)){
							Utils.setGod(p1, true);
							p1.sendMessage(Mensagens.getMensagem("god_ativo"));
							sender.sendMessage(Mensagens.getMensagem("god_ativo_by_other").replace("@Player", p1.getName()));
						}else{
							Utils.setGod(p1, false);
							p1.sendMessage(Mensagens.getMensagem("god_desativado"));
							sender.sendMessage(Mensagens.getMensagem("god_desativado_by_other").replace("@Player", p1.getName()));
						}
					}			
				}
			}
		}
		return false;
	}
}
