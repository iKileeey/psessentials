package me.ikileey.psessentials.commands;

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

import me.ikileey.psessentials.messages.Mensagens;

public class Mobkill implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("mobkill")){
			if(sender instanceof Player){
				Player p = (Player)sender;
				if(p.hasPermission("psessentials.mobkill")){
					Integer total = 0;
					World w = p.getWorld();
					List<Entity> flist = w.getEntities();
					for(Entity current : flist){
						if(!(current instanceof Player) && !(current instanceof ItemFrame) && !(current instanceof Item) && !(current instanceof Villager)){
							total++;
							current.remove();
						}
					}
					p.sendMessage(Mensagens.getMensagem("mobkill").replace("@Qtd", Integer.toString(total)));
					
				}else{
					p.sendMessage(Mensagens.getErro("sem_perm"));
				}
			}else{
				sender.sendMessage("§cExecutável apenas in-game.");
			}
		}
		return false;
	}
}
