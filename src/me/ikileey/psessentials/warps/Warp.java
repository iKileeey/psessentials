package me.ikileey.psessentials.warps;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.ikileey.psessentials.Main;
import me.ikileey.psessentials.messages.Mensagens;
import me.ikileey.psessentials.utils.UWarps;
import me.ikileey.psessentials.utils.Utils;

public class Warp implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("setwarp")) {
			if (sender instanceof Player) {
				Player p = (Player) sender;
				if (p.hasPermission("psessentials.setwarp")) {
					if (args.length == 1) {
						String warp = args[0];
						final String w = p.getWorld().getName();
						final double x = p.getLocation().getX();
						final double y = p.getLocation().getY();
						final double z = p.getLocation().getZ();
						final float yaw = (float) p.getLocation().getYaw();
						final float pitch = (float) p.getLocation().getPitch();
						String loc = w + ";" + x + ";" + y + ";" + z + ";" + yaw + ";" + pitch;
						UWarps.saveWarp(warp, loc);
						p.sendMessage(Mensagens.getMensagem("warp_setada").replace("@Warp", warp));
					}else{
						p.sendMessage("§cUse: /setwarp <warp>");
					}
				}
			} else {
				sender.sendMessage("§cExecutável apenas in-game.");
			}
		}
		if (cmd.getName().equalsIgnoreCase("warp")) {
			if (sender instanceof Player) {
				Player p = (Player) sender;
				if (args.length == 1) {
					if (UWarps.containsWarp(args[0])) {
						if (p.hasPermission("psessentials.warps." + args[0])) {
							String[] lc = UWarps.getWarp(args[0]).split(";");

							final World w = Bukkit.getWorld(lc[0]);
							final double x = Double.parseDouble(lc[1]);
							final double y = Double.parseDouble(lc[2]);
							final double z = Double.parseDouble(lc[3]);
							final float yaw = Float.parseFloat(lc[4]);
							final float pitch = Float.parseFloat(lc[5]);
							Location loc = new Location(w, x, y, z, yaw, pitch);

							if(Utils.delaytp && !p.hasPermission("psessentials.teleport.nodelay")){
								if(!Utils.c.contains(p)){
									p.sendMessage(Mensagens.getMensagem("aguarde"));
									Utils.c.add(p);
									Bukkit.getScheduler().runTaskLater(Main.pl, new Runnable() {
										public void run() {
											Utils.c.remove(p);
											p.teleport(loc);
											p.sendMessage(Mensagens.getMensagem("warp_tp").replace("@Warp", args[0]));
										}
									}, Utils.tempodelaytp * 20L);									
								}else{
									p.sendMessage("§cHá um teleporte pendente.");
								}
							}else{
								p.teleport(loc);
								p.sendMessage(Mensagens.getMensagem("warp_tp").replace("@Warp", args[0]));
							}
						} else {
							p.sendMessage(Mensagens.getErro("erro19"));
						}

					} else {
						p.sendMessage(Mensagens.getErro("erro20"));
					}
				}
				if (args.length == 2) {
					if (p.hasPermission("psessentials.warps.other")) {
						Player p1 = Bukkit.getPlayer(args[1]);
						if (p1 == null) {
							p.sendMessage(Mensagens.getErro("pne"));
						} else {
							String[] lc = UWarps.getWarp(args[0]).split(";");

							final World w = Bukkit.getWorld(lc[0]);
							final double x = Double.parseDouble(lc[1]);
							final double y = Double.parseDouble(lc[2]);
							final double z = Double.parseDouble(lc[3]);
							final float yaw = Float.parseFloat(lc[4]);
							final float pitch = Float.parseFloat(lc[5]);
							Location loc = new Location(w, x, y, z, yaw, pitch);

							p1.teleport(loc);
							p1.sendMessage(Mensagens.getMensagem("warp_tp").replace("@Warp", args[0]));

							p.sendMessage(Mensagens.getMensagem("warp_tp_other").replace("@Player", p1.getName()).replace("@Warp", args[0]));
						}
					} else {
						p.sendMessage(Mensagens.getErro("sem_perm"));
					}
				}
				if(args.length == 0){
					p.sendMessage("§cUse: /warp <warp>");
				}

			} else {
				if (args.length == 2) {
					if (sender.hasPermission("psessentials.warps.other")) {
						Player p1 = Bukkit.getPlayer(args[1]);
						if (p1 == null) {
							sender.sendMessage(Mensagens.getErro("pne"));
						} else {
							String[] lc = UWarps.getWarp(args[0]).split(";");

							final World w = Bukkit.getWorld(lc[0]);
							final double x = Double.parseDouble(lc[1]);
							final double y = Double.parseDouble(lc[2]);
							final double z = Double.parseDouble(lc[3]);
							final float yaw = Float.parseFloat(lc[4]);
							final float pitch = Float.parseFloat(lc[5]);
							Location loc = new Location(w, x, y, z, yaw, pitch);

							p1.teleport(loc);
							p1.sendMessage(Mensagens.getMensagem("warp_tp").replace("@Warp", args[0]));

							sender.sendMessage(Mensagens.getMensagem("warp_tp_other").replace("@Player", p1.getName()).replace("@Warp", args[0]));
						}
					} else {
						sender.sendMessage(Mensagens.getErro("sem_perm"));
					}
				} else {
					sender.sendMessage("§cUse: /warp <warp> [jogador]");
				}
			}
		}
		if (cmd.getName().equalsIgnoreCase("warps")) {
			if (sender instanceof Player) {
				Player p = (Player) sender;
				if (p.hasPermission("psessentials.warps")) {
					try {
						p.sendMessage(Mensagens.getMensagem("warps").replace("@Warps", UWarps.getWarps().toString().replace("[", "").replace("]", "")));
					} catch (NullPointerException e) {
						p.sendMessage(Mensagens.getErro("erro21"));
					}
				}else{
					p.sendMessage(Mensagens.getErro("sem_perm"));
				}
			} else {
				sender.sendMessage("§cExecutável apenas in-game.");
			}
		}
		if (cmd.getName().equalsIgnoreCase("delwarp")) {
			if (sender instanceof Player) {
				Player p = (Player) sender;
				if (p.hasPermission("psessentials.delwarp")) {
					if (args.length == 1) {
						String warp = args[0];
						if (UWarps.containsWarp(warp)) {
							UWarps.deleteWarp(warp);
							p.sendMessage(Mensagens.getMensagem("delwarp").replace("@Warp", warp));
						} else {
							p.sendMessage(Mensagens.getErro("erro20"));
						}
					}
					if(args.length == 0){
						p.sendMessage("§cUse: /delwarp <warp>");
					}
				} else {
					p.sendMessage(Mensagens.getErro("sem_perm"));
				}
			} else {
				if (sender.hasPermission("psessentials.delwarp")) {
					if (args.length == 1) {
						String warp = args[0];
						if (UWarps.containsWarp(warp)) {
							UWarps.deleteWarp(warp);
							sender.sendMessage(Mensagens.getMensagem("delwarp").replace("@Warp", warp));
						} else {
							sender.sendMessage(Mensagens.getErro("erro20"));
						}
					}
					if(args.length == 0){
						sender.sendMessage("§cUse: /delwarp <warp>");
					}
				} else {
					sender.sendMessage(Mensagens.getErro("sem_perm"));
				}
			}
		}
		return false;
	}
}
