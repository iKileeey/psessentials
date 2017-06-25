package me.ikileey.psessentials.commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.ikileey.psessentials.messages.Mensagens;
import me.ikileey.psessentials.utils.UEnchant;
import me.ikileey.psessentials.utils.Utils;

public class Enchant implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("enchant")){
			if(sender instanceof Player){
				Player p = (Player)sender;
				if(p.hasPermission("psessentials.enchant")){
					if(args.length == 2){
						if(p.getItemInHand().getType() != Material.AIR){
							if(Utils.isInt(args[1])){
								UEnchant.enchant(p, args[0], Integer.parseInt(args[1]));							
							}else{
								p.sendMessage(Mensagens.getErro("n_i"));
							}
						}else{
							p.sendMessage(Mensagens.getErro("erro1"));
						}
					}else{
						p.sendMessage("§cUse: /enchant <encantamento> <nível>");
					}					
				}else{
					p.sendMessage(Mensagens.getErro("sem_perm"));
				}
			}else{
				sender.sendMessage("§eExecutável apenas in-game.");
			}
		}
		if(cmd.getName().equalsIgnoreCase("enchants")){
			if(sender instanceof Player){
				Player p = (Player)sender;
				if(p.hasPermission("psessentials.enchant")){
					p.sendMessage("§eEncantamentos: protection, fireprotection, featherfalling, blastprotection, projectileprotection, respiration, aquaaffinity, thorns, sharpness, smite, baneofarthropods, knockback, fireaspect, looting, efficiency, silktouch, unbreaking, fortune, power, punch, flame, infinity");
				}else{
					p.sendMessage(Mensagens.getMensagem("sem_perm"));
				}
			}else{
				sender.sendMessage("§eExecutável apenas in-game.");
			}
		}
		return false;
	}
}
