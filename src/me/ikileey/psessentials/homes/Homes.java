package me.ikileey.psessentials.homes;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.ikileey.psessentials.messages.Mensagens;
import me.ikileey.psessentials.utils.UHomes;
import me.ikileey.psessentials.utils.ULoc;

public class Homes implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("sethome")) {
			if (sender instanceof Player) {
				Player p = (Player) sender;
				if (args.length == 1) {
					if(!args[0].contains(":")){
						String home = args[0].toLowerCase();
						
						final String w = p.getWorld().getName();
						final double x = p.getLocation().getX();
						final double y = p.getLocation().getY();
						final double z = p.getLocation().getZ();
						final float yaw = (float) p.getLocation().getYaw();
						final float pitch = (float) p.getLocation().getPitch();
						String loc = w + ";" + x + ";" + y + ";" + z + ";" + yaw + ";" + pitch;

						UHomes.setHome(p, home, loc, "privado");
						
						p.sendMessage(Mensagens.getMensagem("home_setada").replace("@Home", args[0]));
					}else{
						p.sendMessage(Mensagens.getErro("h_i"));
					}
				}
				if(args.length == 0){
					
					String home = "(Padrao)";
					
					final String w = p.getWorld().getName();
					final double x = p.getLocation().getX();
					final double y = p.getLocation().getY();
					final double z = p.getLocation().getZ();
					final float yaw = (float) p.getLocation().getYaw();
					final float pitch = (float) p.getLocation().getPitch();
					String loc = w + ";" + x + ";" + y + ";" + z + ";" + yaw + ";" + pitch;

					UHomes.setDefaultHome(p, home, loc, "privado");
					
					p.sendMessage(Mensagens.getMensagem("home_padrao_setada"));
					
				}
			}
		}
		if(cmd.getName().equalsIgnoreCase("home")){
			if(sender instanceof Player){
				Player p = (Player)sender;
				if(args.length == 1){
					if(!args[0].contains(":")){
						String home = args[0].toLowerCase();
						if(UHomes.containsHomeSetada(p.getName().toLowerCase(), home)){
							p.teleport(ULoc.getLocationOfHome(p.getName(), home));
							p.sendMessage(Mensagens.getMensagem("home_tp").replace("@Home", home));
						}else{
							p.sendMessage(Mensagens.getErro("h_n_e"));
						}						
					}else{
						String[] split = args[0].split(":");
						if(split.length == 2){
							String jg = split[0].toLowerCase();
							String home = split[1].toLowerCase();
							
							if(UHomes.containsHomeSetada(jg, home)){
								if(!p.hasPermission("psessentials.homes.admin")){
									if(!UHomes.isPrivate(jg, home)){
										p.teleport(ULoc.getLocationOfHome(jg, home));
										p.sendMessage(Mensagens.getMensagem("home_tp_other").replace("@Home", home).replace("@Player", jg.toLowerCase()));
									}else{
										p.sendMessage(Mensagens.getErro("erro5"));
									}								
								}else{
									p.teleport(ULoc.getLocationOfHome(jg, home));
									
									p.sendMessage(Mensagens.getMensagem("home_tp_other").replace("@Home", home).replace("@Player", jg.toLowerCase()));
								}
							}else{
								p.sendMessage(Mensagens.getErro("h_n_e"));
							}							
						}else{
							p.sendMessage("§cUse: /home <jogador:home>");
						}
						
					}
				}
				if(args.length == 0){
					String home = "(Padrao)";
					if(UHomes.containsDefaultHomeSetada(p.getName().toLowerCase())){
						p.teleport(ULoc.getLocationOfHome(p.getName(), home));	
						p.sendMessage(Mensagens.getMensagem("home_padrao_tp"));
					}else{
						p.sendMessage(Mensagens.getErro("erro6"));
					}
				}
			}
		}
		if(cmd.getName().equalsIgnoreCase("homes")){
			if(sender instanceof Player){
				Player p = (Player)sender;
				if(args.length == 0){
					if(!UHomes.getHomesOfPlayer(p.getName()).isEmpty()){
						p.sendMessage(Mensagens.getMensagem("homes").replace("@Homes", UHomes.getHomesOfPlayer(p.getName()).toString().replace("[", "").replace("]", "")));
					}else{
						p.sendMessage(Mensagens.getErro("erro7"));
					}
				}
				if(args.length == 1){
					if(p.hasPermission("psessentials.homes.admin")){
						String jg = args[0].toLowerCase();
						if(!UHomes.getHomesOfPlayer(jg).isEmpty()){
							p.sendMessage(Mensagens.getMensagem("homes_other").replace("@Player", jg).replace("@Homes", UHomes.getHomesOfPlayer(jg).toString().replace("[", "").replace("]", "")));
						}else{
							p.sendMessage(Mensagens.getErro("erro7"));
						}
					}
				}
			}
		}
		if(cmd.getName().equalsIgnoreCase("delhome")){
			if(sender instanceof Player){
				Player p = (Player)sender;
				if(args.length == 1){
					if(!args[0].contains(":")){
						String home = args[0].toLowerCase();
						if(UHomes.containsHomeSetada(p.getName().toLowerCase(), home)){
							UHomes.deleteHomeOfPlayer(p.getName(), home);
							p.sendMessage(Mensagens.getMensagem("delhome").replace("@Home", home));
						}else{
							p.sendMessage(Mensagens.getErro("h_n_e"));
						}						
					}else{
						if(p.hasPermission("psessentials.homes.admin")){
							String[] split = args[0].split(":");
							if(split.length == 2){
								String jg = split[0].toLowerCase();
								String home = split[1].toLowerCase();
								
								if(UHomes.containsHomeSetada(jg.toLowerCase(), home)){
									UHomes.deleteHomeOfPlayer(jg, home);
									p.sendMessage(Mensagens.getMensagem("delhome_other").replace("@Home", home).replace("@Player", jg));
								}else{
									p.sendMessage(Mensagens.getErro("h_n_e"));
								}								
							}else{
								p.sendMessage("§cUse: /delhome <jogador:home>");
							}
						}else{
							p.sendMessage(Mensagens.getErro("sem_perm"));
						}
					}
				}
			}
		}
		if(cmd.getName().equalsIgnoreCase("publica")){
			if(sender instanceof Player){
				Player p = (Player)sender;
				if(args.length == 1){
					String home = args[0].toLowerCase();
					if(UHomes.containsHomeSetada(p.getName().toLowerCase(), home)){
						if(UHomes.isPrivate(p.getName(), home)){
							UHomes.setHomeToPublica(p, home);
							p.sendMessage(Mensagens.getMensagem("home_publica").replace("@Home", home));
						}else{
							p.sendMessage(Mensagens.getErro("erro8"));
						}
					}else{
						p.sendMessage(Mensagens.getErro("h_n_e"));
					}
				}else{
					p.sendMessage("§cUse: /publica <home>");
				}
			}
		}
		if(cmd.getName().equalsIgnoreCase("particular")){
			if(sender instanceof Player){
				Player p = (Player)sender;
				if(args.length == 1){
					String home = args[0].toLowerCase();
					if(UHomes.containsHomeSetada(p.getName().toLowerCase(), home)){
						if(!UHomes.isPrivate(p.getName(), home)){
							UHomes.setHomeToPrivado(p, home);
							p.sendMessage(Mensagens.getMensagem("home_particular").replace("@Home", home));
						}else{
							p.sendMessage(Mensagens.getErro("erro9"));
						}
					}else{
						p.sendMessage(Mensagens.getErro("h_n_e"));
					}
				}else{
					p.sendMessage("§cUse: /particular <home>");
				}
			}
		}
		if(cmd.getName().equalsIgnoreCase("infohome")){
			if(sender instanceof Player){
				Player p = (Player)sender;
				if(p.hasPermission("psessentials.homes.infohome")){
					if(args.length == 1){
						if(args[0].contains(":")){
							String[] split = args[0].split(":");
							if(split.length == 2){
								String jg = split[0].toLowerCase();
								String home = split[1].toLowerCase();
								
								if(UHomes.containsHomeSetada(jg, home)){
									p.sendMessage("§6Informações da home:");
									p.sendMessage("");
									p.sendMessage("§6 Home: §f"+home+".");
									p.sendMessage("§6 Dono: §f"+jg+".");
									if(UHomes.isPrivate(jg, home)){
										p.sendMessage("§6 Status: §fparticular.");
									}else{
										p.sendMessage("§6 Status: §fpública.");
									}
									p.sendMessage("");
								}else{
									p.sendMessage(Mensagens.getErro("h_n_e"));
								}									
							}else{
								p.sendMessage("§cUse: /infohome <jogador:home>");
							}
						}else{
							p.sendMessage("§cUse: /infohome <jogador:home>");
						}
						
					}else{
						p.sendMessage("§cUse: /infohome <jogador:home>");
					}
				}else{
					p.sendMessage(Mensagens.getErro("sem_perm"));
				}
			}
		}
		return false;
	}
}
