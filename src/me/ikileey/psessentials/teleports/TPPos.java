package me.ikileey.psessentials.teleports;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.ikileey.psessentials.messages.Mensagens;
import me.ikileey.psessentials.utils.Utils;

public class TPPos implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("tppos")) {
			if (sender instanceof Player) {
				Player p = (Player) sender;
					if (args.length == 3) {
						if (p.hasPermission("psessentials.tppos")) {
							if (Utils.isInt(args[0])) {
								if (Utils.isInt(args[1])) {
									if (Utils.isInt(args[2])) {
										Location location = new Location(p.getWorld(), Integer.parseInt(args[0]),
												Integer.parseInt(args[1]), Integer.parseInt(args[2]));
										p.teleport(location);
										p.sendMessage(Mensagens.getMensagem("tppos").replace("@W", p.getWorld().getName()).replace("@X", args[0]).replace("@Y", args[1]).replace("@Z", args[2]));
									} else {
										p.sendMessage(Mensagens.getErro("erro17").replace("@Valor", "Z"));
									}
								} else {
									p.sendMessage(Mensagens.getErro("erro17").replace("@Valor", "Y"));
								}
							} else {
								p.sendMessage(Mensagens.getErro("erro17").replace("@Valor", "X"));
							}
						}
					}
					if (args.length == 4) {
						if (p.hasPermission("psessentials.tppos.other")) {
							Player p1 = Bukkit.getPlayer(args[3]);
							if (p1 == null) {
								sender.sendMessage(Mensagens.getErro("pne"));
							} else {
								if (Utils.isInt(args[0])) {
									if (Utils.isInt(args[1])) {
										if (Utils.isInt(args[2])) {
											Location location = new Location(p1.getWorld(), Integer.parseInt(args[0]),
													Integer.parseInt(args[1]), Integer.parseInt(args[2]));
											p1.teleport(location);
											p1.sendMessage(Mensagens.getMensagem("tppos_other").replace("@Player", sender.getName()).replace("@W", p.getWorld().getName()).replace("@X", args[0]).replace("@Y", args[1]).replace("@Z", args[2]));
											p.sendMessage(Mensagens.getMensagem("tppos_other_sucesso").replace("@Player", p1.getName()).replace("@W", p.getWorld().getName()).replace("@X", args[0]).replace("@Y", args[1]).replace("@Z", args[2]));
										} else {
											p.sendMessage(Mensagens.getErro("erro17").replace("@Valor", "Z"));
										}
									} else {
										p.sendMessage(Mensagens.getErro("erro17").replace("@Valor", "Y"));
									}
								} else {
									p.sendMessage(Mensagens.getErro("erro17").replace("@Valor", "X"));
								}
							}
						}else{
							p.sendMessage(Mensagens.getErro("sem_perm"));
						}
					}
					if (args.length < 3) {
						sender.sendMessage("§cUse: /tppos <x> <y> <z> [jogador]");
					}

				} else {
					if (args.length == 4) {
						if (sender.hasPermission("psessentials.tppos.other")) {
							Player p1 = Bukkit.getPlayer(args[3]);
							if (p1 == null) {
								sender.sendMessage(Mensagens.getErro("pne"));
							} else {
								if (Utils.isInt(args[0])) {
									if (Utils.isInt(args[1])) {
										if (Utils.isInt(args[2])) {
											Location location = new Location(p1.getWorld(), Integer.parseInt(args[0]),
													Integer.parseInt(args[1]), Integer.parseInt(args[2]));
											p1.teleport(location);
											p1.sendMessage(Mensagens.getMensagem("tppos_other").replace("@Player", sender.getName()).replace("@W", p1.getWorld().getName()).replace("@X", args[0]).replace("@Y", args[1]).replace("@Z", args[2]));
											sender.sendMessage(Mensagens.getMensagem("tppos_other_sucesso").replace("@Player", p1.getName()).replace("@W", p1.getWorld().getName()).replace("@X", args[0]).replace("@Y", args[1]).replace("@Z", args[2]));
										} else {
											sender.sendMessage(Mensagens.getErro("erro17").replace("@Valor", "Z"));
										}
									} else {
										sender.sendMessage(Mensagens.getErro("erro17").replace("@Valor", "Y"));
									}
								} else {
									sender.sendMessage(Mensagens.getErro("erro17").replace("@Valor", "X"));
								}
							}
						}else{
							sender.sendMessage(Mensagens.getErro("sem_perm"));
						}
					}
					if (args.length < 3) {
						sender.sendMessage("§cUse: /tppos <x> <y> <z> [jogador]");
					}
				}
			}
		return false;
	}
}
