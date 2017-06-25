package me.ikileey.psessentials.spawn;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.ikileey.psessentials.Main;
import me.ikileey.psessentials.utils.ULoc;
import me.ikileey.psessentials.utils.Utils;

public class Spawn implements CommandExecutor {

	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("setspawn")){
			if(sender instanceof Player){
				Player p = (Player)sender;
				if(p.hasPermission("psessentials.setspawn")){
					if(args.length == 0){
						ULoc.saveLocation(p, "spawn");
						p.sendMessage("�eSpawn setado com sucesso.");
					}else{
						p.sendMessage("�eUse: /setspawn");
					}
				}else{
					p.sendMessage("�cVoc� n�o tem acesso a este comando!");
				}
			}else{
				sender.sendMessage("�eExecut�vel apenas in-game.");
			}
		}
		if(cmd.getName().equalsIgnoreCase("spawn")){
			if(sender instanceof Player){
				Player p = (Player)sender;
				if(args.length == 0){
					try {
						if(Utils.delaytp && !p.hasPermission("psessentials.teleport.nodelay")){
							if(!Utils.c.contains(p)){
								Utils.c.add(p);
								p.sendMessage("�eAguarde 3 segundos.");
								Bukkit.getScheduler().runTaskLater(Main.pl, new Runnable() {
									public void run() {
										Utils.c.remove(p);
										p.teleport(ULoc.getLocation("spawn"));	
									}
								}, Utils.tempodelaytp * 20L);								
							}else{
								p.sendMessage("�cH� um teleporte pendente.");
							}
						}else{
							p.teleport(ULoc.getLocation("spawn"));	
						}
					} catch (Exception e) {
						p.sendMessage("�eO spawn n�o foi setado.");
					}					
				}
				if(args.length == 1){
					if(p.hasPermission("psessentials.spawn.other")){
						Player p1 = Bukkit.getPlayer(args[0]);
						if(p1 == null){
							p.sendMessage("�eJogador n�o encontrado.");
						}else{
							try {
								p1.teleport(ULoc.getLocation("spawn"));
								p1.sendMessage("�eVoc� foi forcado para o spawn.");
								p.sendMessage("�eVoc� forcou "+p1.getName()+" �epara o spawn.");								
							} catch (Exception e) {
								p.sendMessage("�eO spawn n�o foi setado.");
							}
						}
					}else{
						try {
							p.teleport(ULoc.getLocation("spawn"));					
						} catch (Exception e) {
							p.sendMessage("�eO spawn n�o foi setado.");
						}	
					}
				}
			}else{
				if(args.length == 1){
					if(sender.hasPermission("psessentials.spawn.other")){
						Player p1 = Bukkit.getPlayer(args[0]);
						if(p1 == null){
							sender.sendMessage("�eJogador n�o encontrado.");
						}else{
							try {
								p1.teleport(ULoc.getLocation("spawn"));
								p1.sendMessage("�eVoc� foi forcado para o spawn.");
								sender.sendMessage("�eVoc� forcou "+p1.getName()+" �epara o spawn.");								
							} catch (Exception e) {
								sender.sendMessage("�eO spawn n�o foi setado.");
							}
						}

					}
				}else{
					sender.sendMessage("�eUse: /spawn <jogador>");
				}
			}
		}
		return false;
	}
}
