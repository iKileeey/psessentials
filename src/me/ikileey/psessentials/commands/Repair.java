package me.ikileey.psessentials.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.ikileey.psessentials.messages.Mensagens;
import me.ikileey.psessentials.utils.URepair;
import me.ikileey.psessentials.utils.Utils;

public class Repair implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("fix")){
			if(sender instanceof Player){
				Player p = (Player)sender;
				if(args.length == 1 && args[0].equalsIgnoreCase("all")){
					if(p.hasPermission("psessentials.fix.all")){
						if(Utils.isInventoryEmpty(p)){
							p.sendMessage(Mensagens.getErro("erro3"));
							return false;
						}
						final List<String> repaired = new ArrayList<String>();
						URepair.repairAll(p, repaired);
						if (repaired.isEmpty()) {
							p.sendMessage(Mensagens.getErro("erro3"));
						} else {
							p.sendMessage(Mensagens.getMensagem("fix_all").replace("@Itens", repaired.toString().toLowerCase().replace("[", "").replace("]", "").replace("_", "")));
						}						
					}else{
						p.sendMessage(Mensagens.getErro("sem_perm"));
					}
				}else{
					p.sendMessage("§cUse: /fix all");
				}
			}else{
				sender.sendMessage("§cExecutável apenas in-game.");
			}
		}
		return false;
	}
}
