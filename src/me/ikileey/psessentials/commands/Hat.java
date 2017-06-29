package me.ikileey.psessentials.commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import me.ikileey.psessentials.messages.Mensagens;

public class Hat implements CommandExecutor {

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("hat")){
			if(sender instanceof Player){
				Player p = (Player)sender;
				if(args.length == 0){
					if(p.hasPermission("psessentials.hat")){
						if(p.getItemInHand().getType() != Material.AIR){
							ItemStack hand = p.getItemInHand();
							if(hand.getDurability() == 0){
								PlayerInventory inv = p.getInventory();
								ItemStack head2 = inv.getHelmet();
								
								inv.setHelmet(hand);
								p.setItemInHand(head2);
								
								p.updateInventory();
								p.sendMessage(Mensagens.getMensagem("chapeu"));
								
							}else{
								p.sendMessage(Mensagens.getErro("erro24"));
							}
						}else{
							p.sendMessage(Mensagens.getErro("erro25"));
						}
					}else{
						p.sendMessage(Mensagens.getErro("sem_perm"));
					}
				}
			}else{
				sender.sendMessage("§cExecutável apenas in-game.");
			}
		}
		return false;
	}
}
