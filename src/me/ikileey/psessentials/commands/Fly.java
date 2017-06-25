package me.ikileey.psessentials.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.ikileey.psessentials.messages.Mensagens;

public class Fly implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("fly")){
			if(sender instanceof Player){
				Player p = (Player)sender;
				if(args.length == 0){
					if(p.hasPermission("psessentials.fly")){
						if(p.getAllowFlight()){
							p.setAllowFlight(false);
							p.setFlying(false);
							p.sendMessage(Mensagens.getMensagem("fly_desativado"));
						}else{
							p.setAllowFlight(true);
							p.setFlying(true);
							p.sendMessage(Mensagens.getMensagem("fly_ativo"));
						}
					}else{
						p.sendMessage(Mensagens.getErro("sem_perm"));
					}
				}
				if(args.length == 1){
					if(p.hasPermission("psessentials.fly.other")){
						Player p1 = Bukkit.getPlayer(args[0]);
						if(p1 == null){
							p.sendMessage(Mensagens.getErro("pne"));
						}else{
							if(p1.getAllowFlight()){
								p1.setAllowFlight(false);
								p1.setFlying(false);
								p.sendMessage(Mensagens.getMensagem("fly_desativado_by_other").replace("@Player", p1.getName()));
								p1.sendMessage(Mensagens.getMensagem("fly_desativado"));
							}else{
								p1.setAllowFlight(true);
								p1.setFlying(true);
								p.sendMessage(Mensagens.getMensagem("fly_ativo_by_other").replace("@Player", p1.getName()));
								p1.sendMessage(Mensagens.getMensagem("fly_ativo"));
							}	
						}
					}
				}
			}
			
		}
		return false;
	}
}
