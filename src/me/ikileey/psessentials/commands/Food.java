package me.ikileey.psessentials.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.ikileey.psessentials.messages.Mensagens;

public class Food implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("food")) {
			if (sender instanceof Player) {
				Player p = (Player) sender;
				if (args.length == 0) {
					if (p.hasPermission("psessentials.food")) {
						p.setFoodLevel(20);
						p.sendMessage(Mensagens.getMensagem("fome_recuperada"));
					} else {
						p.sendMessage(Mensagens.getErro("sem_perm"));
					}
				}
				if (args.length == 1) {
					if (p.hasPermission("psessentials.food.other")) {
						Player p1 = Bukkit.getPlayer(args[0]);
						if (p1 == null) {
							p.sendMessage(Mensagens.getErro("pne"));
						} else {
							p1.setFoodLevel(20);
							p1.sendMessage(Mensagens.getMensagem("fome_recuperada"));
							p.sendMessage(Mensagens.getMensagem("fome_recuperada_by_other").replace("@Player", p1.getName()));
						}
					} else {
						p.sendMessage(Mensagens.getErro("sem_perm"));
					}
				}
			} else {
				if (args.length == 1) {
					Player p1 = Bukkit.getPlayer(args[0]);
					if (p1 == null) {
						sender.sendMessage(Mensagens.getErro("pne"));
					} else {
						p1.setFoodLevel(20);
						p1.sendMessage(Mensagens.getMensagem("fome_recuperada"));
						sender.sendMessage(Mensagens.getMensagem("fome_recuperada_by_other").replace("@Player", p1.getName()));
					}
				}else{
					sender.sendMessage("§cUse: /food <jogador>");
				}
			}
		}

		return false;
	}
}
