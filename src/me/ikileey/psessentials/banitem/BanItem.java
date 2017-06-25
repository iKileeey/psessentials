package me.ikileey.psessentials.banitem;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.ikileey.psessentials.messages.Mensagens;
import me.ikileey.psessentials.utils.UBanItens;

public class BanItem implements CommandExecutor {

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("item")){
			if(sender instanceof Player){
				Player p = (Player)sender;
				if(args.length > 1 && args[0].equalsIgnoreCase("banir")){
					if(!p.hasPermission("psessentials.item.banir")){
						p.sendMessage(Mensagens.getErro("sem_perm"));
						return false;
					}
					StringBuilder sb = new StringBuilder();
					for (int i = 1; i < args.length; i++){
						sb.append(args[i] + " ");							
					}
					String msg = sb.toString();
					if(p.getItemInHand().getType() != Material.AIR){
						String item = p.getItemInHand().getType().toString().toLowerCase();
						if(!UBanItens.containsItemBanido(item)){
							UBanItens.saveItem(item, msg.substring(0, msg.length() - 1));
							p.sendMessage(Mensagens.getMensagem("item_banido").replace("@Item", item).replace("@Motivo", msg.substring(0, msg.length() - 1)));
						}else{
							p.sendMessage(Mensagens.getErro("erro22"));
						}
					}else{
						p.sendMessage(Mensagens.getErro("erro1"));
					}					
				}
				if(args.length == 1 && args[0].equalsIgnoreCase("desbanir")){
					if(!p.hasPermission("psessentials.item.desbanir")){
						p.sendMessage(Mensagens.getErro("sem_perm"));
						return false;
					}
					if(p.getItemInHand().getType() != Material.AIR){
						String item = p.getItemInHand().getType().toString().toLowerCase();
						if(UBanItens.containsItemBanido(item)){
							UBanItens.removeBanOfItem(item);
							p.sendMessage(Mensagens.getMensagem("item_desbanido").replace("@Item", item));
						}else{
							p.sendMessage(Mensagens.getErro("erro23"));
						}
					}					
				}
				if(args.length == 1 && args[0].equalsIgnoreCase("info")){
					if(!p.hasPermission("psessentials.item.info")){
						p.sendMessage(Mensagens.getErro("sem_perm"));
						return false;
					}
					if(p.getItemInHand().getType() != Material.AIR){
						String item = p.getItemInHand().getType().toString().toLowerCase();

						p.sendMessage("§cInformações do item:");
						p.sendMessage("");
						p.sendMessage("§c Item: §f"+item);
						p.sendMessage(" §cID: §f"+p.getItemInHand().getType().getId());
						if(UBanItens.containsItemBanido(item)){
							p.sendMessage("§c Status: §festá banido.");
							p.sendMessage("§c Motivo: §f"+UBanItens.getItemBanidoMotivo(item));
						}else{
							p.sendMessage("§c Status: §fNão está banido.");
						}
					}else{
						p.sendMessage(Mensagens.getErro("erro1"));
					}
				}
				if(args.length > 1 && args[0].equalsIgnoreCase("setdisplay")){
					if(!p.hasPermission("psessentials.item.setdisplay")){
						p.sendMessage(Mensagens.getErro("sem_perm"));
						return false;
					}
					if(p.getItemInHand().getType() != Material.AIR){
						ItemStack item = (ItemStack) p.getItemInHand();
						ItemMeta itemMeta = item.getItemMeta();

						StringBuilder sb = new StringBuilder();
						for (int i = 1; i < args.length; i++){
							sb.append(args[i] + " ");							
						}
						String msg = sb.toString();
						
						itemMeta.setDisplayName(msg.substring(0, msg.length() - 1));
						
						item.setItemMeta(itemMeta);
						
						p.setItemInHand(item);
						p.updateInventory();
						
						p.sendMessage(Mensagens.getMensagem("item_setdisplay").replace("@Display", msg.substring(0, msg.length() - 1)));
					}else{
						p.sendMessage(Mensagens.getErro("erro1"));
					}
					
					
				}
				if(args.length == 0){
					if(!p.hasPermission("psessentials.item.banir")){
						p.sendMessage(Mensagens.getErro("sem_perm"));
						return false;
					}
					p.sendMessage("§cUse: /item <banir/desbanir/setdisplay/info>");
				}
				if(args.length == 1 && args[0].equalsIgnoreCase("banir")){
					if(!p.hasPermission("psessentials.item.banir")){
						p.sendMessage(Mensagens.getErro("sem_perm"));
						return false;
					}
					p.sendMessage("§cUse: /item banir <motivo>");
				}
				if(args.length == 1 && args[0].equalsIgnoreCase("setdisplay")){
					if(!p.hasPermission("psessentials.item.setdisplay")){
						p.sendMessage(Mensagens.getErro("sem_perm"));
						return false;
					}
					p.sendMessage("§cUse: /item banir <display>");
				}
				
				
			}else{
				sender.sendMessage("§eExecutável apenas in-game.");
			}
		}
		return false;
	}
}
