package me.ikileey.psessentials.motds;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.ikileey.psessentials.utils.UMotd;

public class Motd implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("motd")){
			if(sender instanceof Player){
				Player p = (Player)sender;
				if(p.hasPermission("psessentials.motd")){
					if(args.length > 1 && args[0].equalsIgnoreCase("setar")){
						StringBuilder sb = new StringBuilder();
						for (int i = 1; i < args.length; i++){
							sb.append(args[i] + " ");							
						}
						String msg = sb.toString();
						UMotd.setMotd(msg.substring(0, msg.length() - 1));
						p.sendMessage("§6Motd setada para: " + msg.substring(0, msg.length() - 1).replace("&", "§"));
					}
					if(args.length == 1 && args[0].equalsIgnoreCase("atual")){
						if(UMotd.getMotd().equalsIgnoreCase("noset")){
							p.sendMessage("§cNenhuma motd setada.");
						}else{
							p.sendMessage("§6Motd atual: "+UMotd.getMotd().replace("&", "§"));						
						}
					}			
					if(args.length == 1 && args[0].equalsIgnoreCase("setar")){
						p.sendMessage("§cUse: /motd setar <mensagem>.");
					}
					if(args.length == 0){
						p.sendMessage("§cUse: /motd <setar/atual>.");
					}
				}else{
					p.sendMessage("§cVocê não tem acesso a este comando!");
				}
			}
		}
		return false;
	}
}
