package me.ikileey.psessentials.kits;

import java.util.concurrent.TimeUnit;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.ikileey.psessentials.Main;
import me.ikileey.psessentials.messages.Mensagens;
import me.ikileey.psessentials.utils.Data;
import me.ikileey.psessentials.utils.UKits;
import me.ikileey.psessentials.utils.Utils;

public class Kits implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("skit")){
			if(sender instanceof Player){
				Player p = (Player)sender;
				if(p.hasPermission("psessentials.kits.setup")){
					if(args.length == 2 && args[0].equalsIgnoreCase("criar")){
						if(!UKits.containsKit(args[1])){
							UKits.newKit(args[1]);
							p.sendMessage(Mensagens.getMensagem("kit_criado").replace("@Kit", args[1].toLowerCase()));
						}else{
							p.sendMessage(Mensagens.getErro("erro10"));
						}
					}
					if(args.length == 2 && args[0].equalsIgnoreCase("setitens")){
						if(UKits.containsKit(args[1])){
							UKits.saveItens(p, args[1]);
						}else{
							p.sendMessage(Mensagens.getErro("erro11"));
						}
					}
					if(args.length == 3 && args[0].equalsIgnoreCase("setdelay")){
						String kit = args[1];
						if(UKits.containsKit(kit)){
							if(Utils.isInt(args[2])){
								Integer delay = Integer.parseInt(args[2]);
								UKits.setDelay(kit, delay);
								p.sendMessage(Mensagens.getMensagem("kit_cooldown_setado").replace("@Valor", Integer.toString(delay)).replace("@Kit", kit));
								
							}else{
								p.sendMessage(Mensagens.getErro("n_i"));
							}							
						}else{
							p.sendMessage(Mensagens.getErro("erro11"));
						}
					}
					if(args.length == 2 && args[0].equalsIgnoreCase("delete")){
						String kit = args[1];
						if(UKits.containsKit(kit)){
							UKits.deleteKit(kit);
							p.sendMessage(Mensagens.getMensagem("kit_deletado").replace("@Kit", kit));
						}else{
							p.sendMessage(Mensagens.getErro("erro11"));
						}
					}
					if(args.length == 2 && args[0].equalsIgnoreCase("setdelay")){
						p.sendMessage("§cUse: /skit setdelay <kit> <tempo>");
					}
					if(args.length == 1 && args[0].equalsIgnoreCase("setitens")){
						p.sendMessage("§cUse: /skit setitens <kit>");
					}
					if(args.length == 1 && args[0].equalsIgnoreCase("criar")){
						p.sendMessage("§cUse: /skit criar <kit>");
					}
					if(args.length == 0){
						p.sendMessage("§3§m----------§3[§fSetup-Kits§3]§3§m-----------");
						p.sendMessage("");
						p.sendMessage(" §e/skit criar <kit> - cria um novo kit.");
						p.sendMessage(" §e/skit setitens <kit> - seta os itens de um kit.");
						p.sendMessage(" §e/skit setdelay <kit> <tempo> - seta o cooldown de um kit.");
						p.sendMessage(" §e/skit delete <kit> - deleta um kit.");
						p.sendMessage("");
						p.sendMessage("§3§m----------§3[§fSetup-Kits§3]§3§m-----------");
					}
						
				}else{
					p.sendMessage(Mensagens.getErro("sem_perm"));
				}
			}else{
				sender.sendMessage("§cExecutável apenas in-game.");
			}
		}
		if(cmd.getName().equalsIgnoreCase("kit")){
			if(sender instanceof Player){
				Player p = (Player)sender;
				if(args.length == 1){
					if(UKits.containsKit(args[0])){
						if(p.hasPermission("psessentials.kits."+args[0].toLowerCase())){
							if(UKits.hasItensSet(args[0])){
								if(!Main.cool.temDelay(p.getName().toLowerCase(), args[0].toLowerCase())){
									Main.cool.addDelay(p.getName().toLowerCase(), args[0].toLowerCase());
									UKits.loadKit(p, args[0]);						
									p.sendMessage(Mensagens.getMensagem("kit_recebido").replace("@Kit", args[0]));
								}else{
									Long delay = Data.getInstance().getData().getLong("Kits."+args[0].toLowerCase()+".Cooldown");
									if(Main.cool.acabouDelay(p.getName().toLowerCase(), TimeUnit.SECONDS.toMillis(delay), args[0].toLowerCase())){
										UKits.loadKit(p, args[0]);							
										p.sendMessage(Mensagens.getMensagem("kit_recebido").replace("@Kit", args[0]));
										Main.cool.removeDelay(p.getName().toLowerCase());
										Main.cool.addDelay(p.getName().toLowerCase(), args[0].toLowerCase());
									}else{
										p.sendMessage(Mensagens.getMensagem("kit_cooldown").replace("@Tempo", Main.cool.getDelayString(p.getName().toLowerCase(), TimeUnit.SECONDS.toMillis(delay), args[0].toLowerCase())));
									}
								}									
							}else{
								p.sendMessage(Mensagens.getErro("erro12").replace("@Kit", args[0]));
							}
						}else{
							p.sendMessage(Mensagens.getErro("erro13"));
						}
					}else{
						p.sendMessage(Mensagens.getErro("erro14").replace("@Kit", args[0]));
					}
				}
				if(args.length == 2){
					if(p.hasPermission("psessentials.kits.givetoplayer")){
						Player p1 = Bukkit.getPlayer(args[1]);
						if(p1 == null){
							sender.sendMessage(Mensagens.getErro("pne"));
						}else{
							if(UKits.containsKit(args[0])){
								UKits.loadKit(p1, args[0]);
								p1.sendMessage(Mensagens.getMensagem("kit_recebido_by_other").replace("@Kit", args[0]).replace("@Player", p.getName()));
								sender.sendMessage(Mensagens.getMensagem("give_kit").replace("@Kit", args[0]).replace("@Player", p1.getName()));
							}else{
								sender.sendMessage(Mensagens.getErro("erro14").replace("@Kit", args[0]));
							}
						}	
					}else{
						p.sendMessage("§cUse: /kit <kit>");
					}
				}
				if(args.length == 0){
					p.sendMessage("§cUse: /kit <kit>.");
				}
			}else{
				if(args.length == 2){
					Player p1 = Bukkit.getPlayer(args[1]);
					if(p1 == null){
						sender.sendMessage(Mensagens.getErro("pne"));
					}else{
						if(UKits.containsKit(args[0])){
							UKits.loadKit(p1, args[0]);
							p1.sendMessage(Mensagens.getMensagem("kit_recebido_by_other").replace("@Kit", args[0]).replace("@Player", sender.getName()));
							sender.sendMessage(Mensagens.getMensagem("give_kit").replace("@Kit", args[0]).replace("@Player", p1.getName()));
						}else{
							sender.sendMessage(Mensagens.getErro("erro14").replace("@Kit", args[0]));
						}
					}
				}
			}
		}
		if(cmd.getName().equalsIgnoreCase("kits")){
			if(sender instanceof Player){
				Player p = (Player)sender;
				p.sendMessage(Mensagens.getMensagem("kits_disponiveis").replace("@Kits", UKits.getKits(p)));
			}else{
				sender.sendMessage("§cExecutável apenas in-game.");
			}
		}
		return false;
	}
}
