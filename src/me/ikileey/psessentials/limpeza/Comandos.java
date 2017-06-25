package me.ikileey.psessentials.limpeza;

import java.util.List;

import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;


public class Comandos implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("limpeza")){
			if(sender instanceof Player){
				Player p = (Player)sender;
				if(p.hasPermission("psessentials.limpeza")){
					if(args.length == 1 && args[0].equalsIgnoreCase("mobs")){
						Integer total = 0;
						World world = (World) p.getWorld();
						List<Entity> flist = world.getEntities();
						
						for(Entity current : flist){
							if(!(current instanceof Player) && !(current instanceof ItemFrame) && !(current instanceof Item) && !(current instanceof Villager)){
								total++;
								current.remove();
							}
						}	
						p.sendMessage("§eVocê matou "+total+" mobs!");
					}
					if(args.length == 1 && args[0].equalsIgnoreCase("itens")){
						Integer total = 0;
						World world = (World) p.getWorld();
						List<Entity> tlist = world.getEntities();	
						
						for(Entity current : tlist){
							if(current instanceof Item){
								total++;
								current.remove();
							}
						}
						p.sendMessage("§eVocê limpou "+total+" itens!");
					}				
					if(args.length == 0){
						p.sendMessage("§cUse: /limpeza <mobs/itens>");
					}
				}else{
					p.sendMessage("§cVocê não tem acesso a este comando!");
				}
			}
		}
		return false;
	}
}
