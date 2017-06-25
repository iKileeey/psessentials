package me.ikileey.psessentials.teleports;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.ikileey.psessentials.Main;
import me.ikileey.psessentials.messages.Mensagens;
import me.ikileey.psessentials.utils.Utils;

public class Call implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("call")) {
			if (sender instanceof Player) {
				Player p = (Player) sender;
				if (args.length == 1) {
					if (p.hasPermission("psessentials.call")) {
						Player p1 = Bukkit.getPlayer(args[0]);
						if (p1 == null) {
							p.sendMessage(Mensagens.getErro("pne"));
							return false;
						}
						if (p == p1) {
							p.sendMessage(Mensagens.getErro("erro18"));
							return false;
						}
						if (!Utils.containsCall(p1)) {
							Utils.getCall().put(p.getName().toLowerCase(), p1.getName().toLowerCase());
							p.sendMessage(Mensagens.getMensagem("call_pedido_enviado").replace("@Player", p1.getName()));
							p1.sendMessage(Mensagens.getMensagem("call_pedido_recebido").replace("@Player", p.getName()));

							Bukkit.getScheduler().runTaskLater(Main.pl, new Runnable() {
								public void run() {
									if (Utils.getCall().containsKey(p.getName().toLowerCase())
											&& Utils.getCall().containsValue(p1.getName().toLowerCase())) {
										p.sendMessage(Mensagens.getMensagem("call_pedido_expirado").replace("@Player", p1.getName()));
										p1.sendMessage(Mensagens.getMensagem("call_pedido_expirado_by_other").replace("@Player", p.getName()));
										Utils.getCall().remove(p.getName().toLowerCase());
									}
								}
							}, 15 * 20L);
						} else {
							p.sendMessage(Mensagens.getErro("erro15").replace("@Player", p1.getName()));
						}
					}else{
						p.sendMessage(Mensagens.getErro("sem_perm"));
					}
				}else{
					p.sendMessage("§cUse: /call <jogador>");
				}
			}
		}
		if (cmd.getName().equalsIgnoreCase("bring")) {
			if (sender instanceof Player) {
				Player p = (Player) sender;
				if(p.hasPermission("psessentials.bring")){
					if (args.length == 1) {
						Player p1 = Bukkit.getPlayer(args[0]);
						if (p1 == null) {
							p.sendMessage(Mensagens.getErro("pne"));
						} else {
							if (Utils.getCall().containsKey(p1.getName().toLowerCase())
									&& Utils.getCall().containsValue(p.getName().toLowerCase())) {
								p1.sendMessage(Mensagens.getMensagem("call_pedido_aceito").replace("@Player", p.getName()));
								if(Utils.delaytp && !p1.hasPermission("psessentials.teleport.nodelay")){
									p1.sendMessage(Mensagens.getMensagem("aguarde"));
									Bukkit.getScheduler().runTaskLater(Main.pl, new Runnable() {
										public void run() {
											p1.teleport(p);
										}
									}, Utils.tempodelaytp * 20L);
								}else{
									p1.teleport(p);
								}
								p.sendMessage(Mensagens.getMensagem("call_pedido_aceito_other"));
								Utils.getCall().remove(p1.getName().toLowerCase());
							} else {
								p.sendMessage(Mensagens.getErro("erro16"));
							}
						}
					}else{	
						p.sendMessage("§cUse: /bring <jogador>");
					}
				}else{
					p.sendMessage(Mensagens.getErro("sem_perm"));
				}
			}
		}
		return false;
	}
}
