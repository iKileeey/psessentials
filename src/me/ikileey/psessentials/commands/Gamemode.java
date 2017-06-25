package me.ikileey.psessentials.commands;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.ikileey.psessentials.messages.Mensagens;

public class Gamemode implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("gm")){
			if(sender instanceof Player){
				Player p = (Player)sender;
				if(p.hasPermission("psessentials.gm")){
					if(args.length == 1 && args[0].equalsIgnoreCase("1")){
						p.setGameMode(GameMode.CREATIVE);
						p.sendMessage(Mensagens.getMensagem("gm_alterado_criativo"));
						return false;
					}
					if(args.length == 1 && args[0].equalsIgnoreCase("0")){
						p.setGameMode(GameMode.SURVIVAL);
						p.sendMessage(Mensagens.getMensagem("gm_alterado_survival"));
						return false;
					}
					if(p.hasPermission("psessentials.gm.other")){
						if(args.length == 2 && args[0].equalsIgnoreCase("1")){
							Player p2 = Bukkit.getPlayer(args[1]);
							if(p2 == null){
								sender.sendMessage(Mensagens.getErro("pne"));
							}else{
								p2.setGameMode(GameMode.CREATIVE);
								sender.sendMessage(Mensagens.getMensagem("gm_alterado_criativo_by_other").replace("@Player", p2.getName()));
								p2.sendMessage(Mensagens.getMensagem("gm_alterado_criativo"));
							}
						}
						if(args.length == 2 && args[0].equalsIgnoreCase("0")){
							Player p2 = Bukkit.getPlayer(args[1]);
							if(p2 == null){
								sender.sendMessage(Mensagens.getErro("pne"));
							}else{
								p2.setGameMode(GameMode.SURVIVAL);
								sender.sendMessage(Mensagens.getMensagem("gm_alterado_survival_by_other").replace("@Player", p2.getName()));
								p2.sendMessage(Mensagens.getMensagem("gm_alterado_survival"));
							}
						}						
					}else{
						p.sendMessage(Mensagens.getErro("erro2"));
					}
					if(args.length == 0){
						p.sendMessage("§cUse: /gm <0/1> [jogador] | sobrevivencia & criativo");
					}
				}else{
					p.sendMessage(Mensagens.getErro("sem_perm"));
				}
			}else{
				if(args.length == 0 || args.length == 1){
					sender.sendMessage("§cAltere o modo de jogo de um player");
					sender.sendMessage("§ceUse: /gm <0/1> <player>");
					return false;
				}
				if(args.length == 2 && args[0].equalsIgnoreCase("1")){
					Player p2 = Bukkit.getPlayer(args[1]);
					if(p2 == null){
						sender.sendMessage(Mensagens.getErro("pne"));
					}else{
						p2.setGameMode(GameMode.CREATIVE);
						sender.sendMessage(Mensagens.getMensagem("gm_alterado_criativo_by_other").replace("@Player", p2.getName()));
						p2.sendMessage(Mensagens.getMensagem("gm_alterado_criativo"));
					}
				}
				if(args.length == 2 && args[0].equalsIgnoreCase("0")){
					Player p2 = Bukkit.getPlayer(args[1]);
					if(p2 == null){
						sender.sendMessage(Mensagens.getErro("pne"));
					}else{
						p2.setGameMode(GameMode.SURVIVAL);
						sender.sendMessage(Mensagens.getMensagem("gm_alterado_survival_by_other").replace("@Player", p2.getName()));
						p2.sendMessage(Mensagens.getMensagem("gm_alterado_survival"));
					}
				}		
			}
		}
		return false;
	}
}
