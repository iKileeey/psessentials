package me.ikileey.psessentials.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.ikileey.psessentials.messages.Mensagens;

public class Heal implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("vida")) {
			if (sender instanceof Player) {
				Player p = (Player) sender;
				if (args.length == 0) {
					if (p.hasPermission("psessentials.heal")) {
						p.setFoodLevel(20);
						p.sendMessage(Mensagens.getMensagem("vida_recuperada"));
					} else {
						p.sendMessage(Mensagens.getErro("sem_perm"));
					}
				}
				if (args.length == 1) {
					if (p.hasPermission("psessentials.heal.other")) {
						Player p1 = Bukkit.getPlayer(args[0]);
						if (p1 == null) {
							p.sendMessage(Mensagens.getErro("pne"));
						} else {
							p1.setHealth(20);
							p1.sendMessage(Mensagens.getMensagem("vida_recuperada"));
							p.sendMessage(Mensagens.getMensagem("vida_recuperada_by_other").replace("@Player", p1.getName()));
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
						p1.setHealth(20);
						p1.sendMessage(Mensagens.getMensagem("vida_recuperada"));
						sender.sendMessage(Mensagens.getMensagem("vida_recuperada_by_other").replace("@Player", p1.getName()));
					}
				}else{
					sender.sendMessage("§cUse: /heal <jogador>");
				}
			}
		}

		return false;
	}

}
