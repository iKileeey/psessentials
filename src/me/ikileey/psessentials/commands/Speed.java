package me.ikileey.psessentials.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.ikileey.psessentials.messages.Mensagens;
import me.ikileey.psessentials.utils.Utils;

public class Speed implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("speed")) {
			if (sender instanceof Player) {
				Player p = (Player) sender;
				if (args.length == 1) {
					if (p.hasPermission("psessentials.speed")) {
						if (Utils.isInt(args[0])) {
							Integer valor = Integer.parseInt(args[0]);
							String f = "0." + valor;
							
							float a = Float.parseFloat(f);
							float b = (float) 0.1;

							float v = a + b;
							if (p.isFlying()) {
								p.setFlySpeed(v);
								p.sendMessage(Mensagens.getMensagem("speed_alterado").replace("@Tipo", "voando").replace("@Valor", Float.toString(valor)));
							} else {
								p.setWalkSpeed(v);
								p.sendMessage(Mensagens.getMensagem("speed_alterado").replace("@Tipo", "andando").replace("@Valor", Float.toString(valor)));
							}
						}else{
							p.sendMessage(Mensagens.getErro("n_i"));							
						}
					} else {
						p.sendMessage(Mensagens.getErro("sem_perm"));
					}
				}
				if(args.length == 2){
					if(p.hasPermission("psessentials.speed.other")){
						Player p1 = Bukkit.getPlayer(args[1]);
						if(p1 == null){
							p.sendMessage(Mensagens.getErro("pne"));
						}else{
							if (Utils.isInt(args[0])) {
								Integer valor = Integer.parseInt(args[0]);
								String f = "0." + valor;
								
								float a = Float.parseFloat(f);
								float b = (float) 0.1;

								float v = a + b;
								if (p1.isFlying()) {
									p1.setFlySpeed(v);
									p1.sendMessage(Mensagens.getMensagem("speed_alterado").replace("@Tipo", "voando").replace("@Valor", Float.toString(valor)));
									p.sendMessage(Mensagens.getMensagem("speed_alterado_by_other").replace("@Player", p1.getName()).replace("@Tipo", "voando").replace("@Valor", Float.toString(valor)));
								} else {
									p1.setWalkSpeed(v);
									p1.sendMessage(Mensagens.getMensagem("speed_alterado").replace("@Tipo", "andando").replace("@Valor", Float.toString(valor)));
									p.sendMessage(Mensagens.getMensagem("speed_alterado_by_other").replace("@Player", p1.getName()).replace("@Tipo", "andando").replace("@Valor", Float.toString(valor)));
								}
							}else{
								p.sendMessage(Mensagens.getErro("n_i"));							
							}	
						}
					}else{
						p.sendMessage(Mensagens.getErro("sem_perm"));
					}
				}
				if(args.length == 0){
					p.sendMessage("§cUse: /speed <valor> [jogador]");
				}
			}else{
				if(args.length == 2){
					if(sender.hasPermission("psessentials.speed.other")){
						Player p1 = Bukkit.getPlayer(args[1]);
						if(p1 == null){
							sender.sendMessage(Mensagens.getErro("pne"));
						}else{
							if (Utils.isInt(args[0])) {
								Integer valor = Integer.parseInt(args[0]);
								String f = "0." + valor;
								
								float a = Float.parseFloat(f);
								float b = (float) 0.1;

								float v = a + b;
								if (p1.isFlying()) {
									p1.setFlySpeed(v);
									p1.sendMessage(Mensagens.getMensagem("speed_alterado").replace("@Tipo", "voando").replace("@Valor", Float.toString(valor)));
									sender.sendMessage(Mensagens.getMensagem("speed_alterado_by_other").replace("@Player", p1.getName()).replace("@Tipo", "voando").replace("@Valor", Float.toString(valor)));
								} else {
									p1.setWalkSpeed(v);
									p1.sendMessage(Mensagens.getMensagem("speed_alterado").replace("@Tipo", "andando").replace("@Valor", Float.toString(valor)));
									sender.sendMessage(Mensagens.getMensagem("speed_alterado_by_other").replace("@Player", p1.getName()).replace("@Tipo", "andando").replace("@Valor", Float.toString(valor)));
								}
							}else{
								sender.sendMessage(Mensagens.getErro("n_i"));							
							}	
						}
					}else{
						sender.sendMessage(Mensagens.getErro("sem_perm"));
					}
				}
				if(args.length < 2){
					sender.sendMessage("§cUse: /speed <valor> [jogador]");
				}
			}
		}
		return false;
	}
}
