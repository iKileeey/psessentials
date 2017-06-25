package me.ikileey.psessentials.commands;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.ikileey.psessentials.messages.Mensagens;
import me.ikileey.psessentials.utils.UItens;
import me.ikileey.psessentials.utils.Utils;

public class Give implements CommandExecutor {

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("i")) {
			if (sender instanceof Player) {
				Player p = (Player) sender;
				if(p.hasPermission("psessentials.give")){
					if (args.length == 1) {
						if (Utils.isInt(args[0])) {
							try {
								ItemStack item = new ItemStack(Material.getMaterial(Integer.parseInt(args[0])));
								p.getInventory().addItem(item);			
								p.sendMessage(Mensagens.getMensagem("give_i").replace("@Qtd", "1").replace("@Item", item.getType().toString()));
							} catch (NullPointerException e) {
								p.sendMessage(Mensagens.getErro("i_n_e"));
							}
							p.updateInventory();
						} else {
							try {
								ItemStack item = new ItemStack(Material.getMaterial(args[0].toUpperCase()), 1);
								p.sendMessage(Mensagens.getMensagem("give_i").replace("@Qtd", "1").replace("@Item", item.getType().toString()));
								p.getInventory().addItem(item);							
							} catch (NullPointerException e) {
								p.sendMessage(Mensagens.getErro("i_n_e"));
							}
							p.updateInventory();
						}
					}
					if (args.length == 2) {
						if (Utils.isInt(args[0])) {
							try {
								ItemStack item = new ItemStack(Material.getMaterial(Integer.parseInt(args[0])),
										Integer.parseInt(args[1]));
								p.getInventory().addItem(item);
								p.sendMessage(Mensagens.getMensagem("give_i").replace("@Qtd", args[1]).replace("@Item", item.getType().toString()));
							} catch (NullPointerException e) {
								p.sendMessage(Mensagens.getErro("i_n_e"));
							}
							p.updateInventory();
						} else {
							try {
								ItemStack item = UItens.getItem(args[0].toUpperCase(), Integer.parseInt(args[1]));
								p.getInventory().addItem(item);
								p.sendMessage(Mensagens.getMensagem("give_i").replace("@Qtd", args[1]).replace("@Item", item.getType().toString()));
							} catch (NullPointerException e) {
								p.sendMessage(Mensagens.getErro("i_n_e"));
							}
							p.updateInventory();

						}
					}
					if(args.length == 0){
						p.sendMessage("§cUse: /i <item> <quantidade>");
					}					
				}else{
					p.sendMessage(Mensagens.getErro("sem_perm"));
				}
			}else{
				sender.sendMessage("§cExecutável apenas in-game.");
			}
		}
		if (cmd.getName().equalsIgnoreCase("give")) {
			if (sender instanceof Player) {
				Player p = (Player) sender;
				if(p.hasPermission("psessentials.give")){
					if (args.length == 2) {
						Player p1 = Bukkit.getPlayer(args[0]);
						if (p1 == null) {
							p.sendMessage(Mensagens.getErro("pne"));
						} else {
							if (Utils.isInt(args[1])) {
								ItemStack item = new ItemStack(Material.getMaterial(Integer.parseInt(args[1])));
								p1.getInventory().addItem(item);
								p1.updateInventory();
								p1.sendMessage(Mensagens.getMensagem("give_item_by_other").replace("@Qtd", "1").replace("@Item", item.getType().toString()).replace("@Player", p.getName()));
								p.sendMessage(Mensagens.getMensagem("give_item").replace("@Qtd", "1").replace("@Item", item.getType().toString()).replace("@Player", p1.getName()));
							} else {
								ItemStack item = new ItemStack(Material.getMaterial(args[1].toUpperCase()), 1);
								p1.getInventory().addItem(item);
								p1.updateInventory();
								p1.sendMessage(Mensagens.getMensagem("give_item_by_other").replace("@Qtd", "1").replace("@Item", item.getType().toString()).replace("@Player", p.getName()));
								p.sendMessage(Mensagens.getMensagem("give_item").replace("@Qtd", "1").replace("@Item", item.getType().toString()).replace("@Player", p1.getName()));
							}
						}
					}
					if (args.length == 3) {
						Player p1 = Bukkit.getPlayer(args[0]);
						if (p1 == null) {
							p.sendMessage(Mensagens.getErro("pne"));
						} else {
							if (Utils.isInt(args[1])) {
								try {
									ItemStack item = new ItemStack(Material.getMaterial(Integer.parseInt(args[1])),
											Integer.parseInt(args[2]));
									p1.getInventory().addItem(item);
									
									p1.sendMessage(Mensagens.getMensagem("give_item_by_other").replace("@Qtd", args[2]).replace("@Item", item.getType().toString()).replace("@Player", p.getName()));
									
									p.sendMessage(Mensagens.getMensagem("give_item").replace("@Qtd", args[2]).replace("@Item", item.getType().toString()).replace("@Player", p1.getName()));
								} catch (NullPointerException e) {
									p.sendMessage(Mensagens.getErro("i_n_e"));
								}
								p1.updateInventory();
							} else {
								try {
									ItemStack item = UItens.getItem(args[1].toUpperCase(), Integer.parseInt(args[2]));
									p1.getInventory().addItem(item);
									p1.sendMessage(Mensagens.getMensagem("give_item_by_other").replace("@Qtd", args[2]).replace("@Item", item.getType().toString()).replace("@Player", p.getName()));
									
									p.sendMessage(Mensagens.getMensagem("give_item").replace("@Qtd", args[2]).replace("@Item", item.getType().toString()).replace("@Player", p1.getName()));
								} catch (NullPointerException e) {
									p.sendMessage(Mensagens.getErro("i_n_e"));
								}
								p1.updateInventory();

							}
						}
					}
					if(args.length == 0 || args.length == 1){
						p.sendMessage("§cUse: /give <jogador> <item> <quantidade>");
					}					
				}else{
					p.sendMessage(Mensagens.getErro("sem_perm"));
				}
			}else{
				if (args.length == 2) {
					Player p1 = Bukkit.getPlayer(args[0]);
					if (p1 == null) {
						sender.sendMessage(Mensagens.getErro("pne"));
					} else {
						if (Utils.isInt(args[1])) {
							ItemStack item = new ItemStack(Material.getMaterial(Integer.parseInt(args[1])));
							p1.getInventory().addItem(item);
							p1.updateInventory();
							
							p1.sendMessage(Mensagens.getMensagem("give_item_by_other").replace("@Qtd", "1").replace("@Item", item.getType().toString()).replace("@Player", sender.getName()));
							
							sender.sendMessage(Mensagens.getMensagem("give_item").replace("@Qtd", "1").replace("@Item", item.getType().toString()).replace("@Player", p1.getName()));
						} else {
							ItemStack item = new ItemStack(Material.getMaterial(args[1].toUpperCase()), 1);
							p1.getInventory().addItem(item);
							p1.updateInventory();
							p1.sendMessage(Mensagens.getMensagem("give_item_by_other").replace("@Qtd", "1").replace("@Item", item.getType().toString()).replace("@Player", sender.getName()));
							
							sender.sendMessage(Mensagens.getMensagem("give_item").replace("@Qtd", "1").replace("@Item", item.getType().toString()).replace("@Player", p1.getName()));
						}
					}
				}
				if (args.length == 3) {
					Player p1 = Bukkit.getPlayer(args[0]);
					if (p1 == null) {
						sender.sendMessage(Mensagens.getErro("pne"));
					} else {
						if (Utils.isInt(args[1])) {
							try {
								ItemStack item = new ItemStack(Material.getMaterial(Integer.parseInt(args[1])),
										Integer.parseInt(args[2]));
								p1.getInventory().addItem(item);
								
								p1.sendMessage(Mensagens.getMensagem("give_item_by_other").replace("@Qtd", args[2]).replace("@Item", item.getType().toString()).replace("@Player", sender.getName()));
								sender.sendMessage(Mensagens.getMensagem("give_item").replace("@Qtd", args[2]).replace("@Item", item.getType().toString()).replace("@Player", p1.getName()));
							} catch (NullPointerException e) {
								sender.sendMessage(Mensagens.getErro("i_n_e"));
							}
							p1.updateInventory();
						} else {
							try {
								ItemStack item = UItens.getItem(args[1].toUpperCase(), Integer.parseInt(args[2]));
								p1.getInventory().addItem(item);
								p1.sendMessage(Mensagens.getMensagem("give_item_by_other").replace("@Qtd", args[2]).replace("@Item", item.getType().toString()).replace("@Player", sender.getName()));
								sender.sendMessage(Mensagens.getMensagem("give_item").replace("@Qtd", args[2]).replace("@Item", item.getType().toString()).replace("@Player", p1.getName()));
							} catch (NullPointerException e) {
								sender.sendMessage(Mensagens.getErro("i_n_e"));
							}
							p1.updateInventory();

						}
					}
				}
				if(args.length == 0 || args.length == 1){
					sender.sendMessage("§cUse: /give <jogador> <item> <quantidade>");
				}					
			}
		}
		return false;
	}
}
