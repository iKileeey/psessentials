package me.ikileey.psessentials.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.ikileey.psessentials.messages.Mensagens;

public class Clear implements CommandExecutor {

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("clear")){
			if(sender instanceof Player){
				Player p = (Player)sender;
				if(args.length == 0){
					if(p.hasPermission("psessentials.clear")){
						p.getInventory().clear();
						p.getInventory().setArmorContents(null);
						p.updateInventory();
						p.sendMessage(Mensagens.getMensagem("inv_limpo"));
						
					}else{
						p.sendMessage(Mensagens.getErro("sem_perm"));
					}
				}
				if(args.length == 1){
					if(p.hasPermission("psessentials.clear.other")){
						Player p1 = Bukkit.getPlayer(args[0]);
						if(p1 == null){
							p.sendMessage(Mensagens.getErro("pne"));
						}else{
							p1.getInventory().clear();
							p1.getInventory().setArmorContents(null);
							p1.updateInventory();
							p1.sendMessage(Mensagens.getMensagem("inv_limpo_by_other").replace("@Player", p.getName()));
							p.sendMessage(Mensagens.getMensagem("inv_limpo_sucesso").replace("@Player", p1.getName()));
						}
					}else{
						p.sendMessage(Mensagens.getErro("pne"));
					} 
				}
			}else{
				if(args.length == 1){
					if(sender.hasPermission("psessentials.clear.other")){
						Player p1 = Bukkit.getPlayer(args[0]);
						if(p1 == null){
							sender.sendMessage(Mensagens.getErro("pne"));
						}else{
							p1.getInventory().clear();
							p1.getInventory().setArmorContents(null);
							p1.updateInventory();
							p1.sendMessage("§e*CONSOLE* limpou seu inventário.");
							sender.sendMessage(Mensagens.getMensagem("inv_limpo_sucesso").replace("@Player", p1.getName()));
						}
					}
				}
				if(args.length == 0){
					sender.sendMessage("§cUse: /clear <jogador>");
				}
			}
		}
		return false;
	}
}
