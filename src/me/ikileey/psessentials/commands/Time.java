package me.ikileey.psessentials.commands;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.ikileey.psessentials.messages.Mensagens;
import me.ikileey.psessentials.utils.Utils;

public class Time implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("time")){
			if(sender instanceof Player){
				Player p = (Player)sender;
				if(p.hasPermission("psessentials.time")){
					if(args.length == 1 && args[0].equalsIgnoreCase("day")){
						World w = p.getWorld();
						w.setTime(1000L);
						p.sendMessage(Mensagens.getMensagem("tempo_alterado").replace("@World", w.getName()).replace("@Tempo", "dia"));
					}
					if(args.length == 1 && args[0].equalsIgnoreCase("night")){
						World w = p.getWorld();
						w.setTime(14000L);
						p.sendMessage(Mensagens.getMensagem("tempo_alterado").replace("@World", w.getName()).replace("@Tempo", "noite"));
					}
					if(args.length == 2 && args[0].equalsIgnoreCase("set")){
						if(Utils.isInt(args[1])){
							World w = p.getWorld();
							w.setTime(Integer.parseInt(args[1]));
							p.sendMessage(Mensagens.getMensagem("tempo_alterado").replace("@World", w.getName()).replace("@Tempo", args[1]));
						}else{
							if(args[1].equalsIgnoreCase("day")){
								World w = p.getWorld();
								w.setTime(1000L);
								p.sendMessage(Mensagens.getMensagem("tempo_alterado").replace("@World", w.getName()).replace("@Tempo", "dia"));
							}
							if(args[1].equalsIgnoreCase("night")){
								World w = p.getWorld();
								w.setTime(14000L);
								p.sendMessage(Mensagens.getMensagem("tempo_alterado").replace("@World", w.getName()).replace("@Tempo", "noite"));
							}
						}
					}
					if(args.length == 0){
						p.sendMessage("§cUse: /time <day/night>");
					}
				}else{
					p.sendMessage("§cVocê não tem acesso a este comando!");
				}
			}else{
				if(args.length == 2 && args[0].equalsIgnoreCase("day")){
					World w = Bukkit.getWorld(args[1]);
					try {
						w.setTime(1000L);
						sender.sendMessage(Mensagens.getMensagem("tempo_alterado").replace("@World", w.getName()).replace("@Tempo", "dia"));
					} catch (NullPointerException e) {
						sender.sendMessage(Mensagens.getErro("erro4"));
					}
				}
				if(args.length == 2 && args[0].equalsIgnoreCase("night")){
					World w = Bukkit.getWorld(args[1]);
					try {
						w.setTime(14000L);
						sender.sendMessage(Mensagens.getMensagem("tempo_alterado").replace("@World", w.getName()).replace("@Tempo", "noite"));						
					} catch (NullPointerException e) {
						sender.sendMessage(Mensagens.getErro("erro4"));
					}
				}
				if(args.length == 3 && args[0].equalsIgnoreCase("set")){
					if(Utils.isInt(args[1])){
						World w = Bukkit.getWorld(args[2]);
						try {
							w.setTime(Integer.parseInt(args[1]));
							sender.sendMessage(Mensagens.getMensagem("tempo_alterado").replace("@World", w.getName()).replace("@Tempo", args[1]));
						} catch (NullPointerException e) {
							sender.sendMessage(Mensagens.getErro("erro4"));
						}
					}else{
						if(args[1].equalsIgnoreCase("day")){
							World w = Bukkit.getWorld(args[2]);
							try {
								w.setTime(1000L);
								sender.sendMessage(Mensagens.getMensagem("tempo_alterado").replace("@World", w.getName()).replace("@Tempo", "dia"));						
							} catch (NullPointerException e) {
								sender.sendMessage(Mensagens.getErro("erro4"));
							}
						}
						if(args[1].equalsIgnoreCase("night")){
							World w = Bukkit.getWorld(args[2]);
							try {
								w.setTime(14000L);
								sender.sendMessage(Mensagens.getMensagem("tempo_alterado").replace("@World", w.getName()).replace("@Tempo", "noite"));				
							} catch (NullPointerException e) {
								sender.sendMessage(Mensagens.getErro("erro4"));
							}
						}
					}
				}
				if(args.length == 0){
					sender.sendMessage("§cUse: /time <day/night> [Mundo]");
				}
			}
		}
		return false;
	}
}
