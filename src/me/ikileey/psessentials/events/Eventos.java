package me.ikileey.psessentials.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

import me.ikileey.psessentials.Main;
import me.ikileey.psessentials.messages.Mensagens;
import me.ikileey.psessentials.utils.UBanItens;
import me.ikileey.psessentials.utils.ULoc;
import me.ikileey.psessentials.utils.UMotd;
import me.ikileey.psessentials.utils.Utils;
import me.ikileey.psessentials.vault.VaultAPI;

public class Eventos implements Listener {

	@EventHandler
	public void inventoryClick(InventoryClickEvent e){
		if(e.getInventory().getName().equalsIgnoreCase("Inventário")){
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onDamage(EntityDamageEvent e){
		if(e.getEntity() instanceof Player){
			Player p = (Player) e.getEntity();
			if(Utils.hasGod(p)){
				e.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public void onDamageBy(EntityDamageByEntityEvent e){
		if(e.getEntity() instanceof Player){ 
			if(e.getDamager() instanceof Player){
				Player p = (Player) e.getEntity();
				Player d = (Player) e.getDamager();
				if(Utils.hasGod(p)){
					d.sendMessage(Mensagens.getMensagem("god_ativo_pvp").replace("@Player", p.getName()));
					e.setCancelled(true);
				}
			}
		}
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e){
		if(Utils.entrou == false){
			e.setJoinMessage(null);
		}
		if(Utils.motd == true){
			Bukkit.getScheduler().runTaskLater(Main.pl, new Runnable() {
				@SuppressWarnings("deprecation")
				public void run() {
					for(String msg : Mensagens.getMotd()){
						if(VaultAPI.setupEconomy()){
							e.getPlayer().sendMessage(msg.replace("&", "§").replace("@Player", e.getPlayer().getName())
									.replace("%>%", "»").replace("%>%", "")
									.replace("@MaxPlayers", Integer.toString(Main.pl.getServer().getMaxPlayers()))
									.replace("@Onlines", Integer.toString(Main.pl.getServer().getOnlinePlayers().length))
									.replace("@Money", Double.toString(VaultAPI.economy.getBalance(e.getPlayer().getName()))));
							
										
							
						}else{
							e.getPlayer().sendMessage(msg.replace("&", "§").replace("@Player", e.getPlayer().getName())
									.replace("%>%", "»")
									.replace("@MaxPlayers", Integer.toString(Main.pl.getServer().getMaxPlayers())).replace("@Onlines", Integer.toString(Main.pl.getServer().getOnlinePlayers().length)));
						}
					}
				}
			}, 5L);
		}
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent e){
		if(Utils.saiu == false){
			e.setQuitMessage(null);
		}
		if(Utils.forcespawn){
			for(String mundos : Main.pl.getConfig().getStringList("Server.ForceSpawn.Mundos")){
				if(e.getPlayer().getWorld().getName().equalsIgnoreCase(mundos)){
					try {
						e.getPlayer().teleport(ULoc.getLocation("spawn"));						
					} catch (Exception e2) {
						return;
					}
				}
			}
		}
	}
	
	@EventHandler
	public void onKick(PlayerKickEvent e){
		if(Utils.saiu == false){
			e.setLeaveMessage(null);
		}
	}
	
	@EventHandler
	public void onDeath(PlayerDeathEvent e){
		if(Utils.morreu == false){
			e.setDeathMessage(null);
		}
	}
	
	@EventHandler
	public void onFood(FoodLevelChangeEvent e){
		if(Utils.fome == false){
			e.setFoodLevel(20);
		}
	}
	
	
	@EventHandler
	public void onWeather(WeatherChangeEvent e){
		if(Utils.chuva == false){
			e.setCancelled(e.toWeatherState());
		}
	}
	
	@EventHandler
	public void onSign(SignChangeEvent e) {
		Player p = e.getPlayer();
		if(Utils.corplaca){
			if (p.hasPermission("psessentials.placa.cor")) {
				String CHAR = "&";
				final char[] ch = CHAR.toCharArray();

				for (int i = 0; i <= 3; ++i) {
					String line = e.getLine(i);
					line = ChatColor.translateAlternateColorCodes(ch[0], line);
					e.setLine(i, line);
				}
			} else {
				return;
			}			
		}

	}
	
	@EventHandler
	public void onCommand(PlayerCommandPreprocessEvent e){
		Player p = e.getPlayer();
		String cmd = e.getMessage().toLowerCase();
		if(!p.hasPermission("psessentials.dcmds.ignorar")){
			for(String dcmd : Main.pl.getConfig().getStringList("DesativarComandos.Comandos")){
				if(cmd.equalsIgnoreCase(dcmd)){
					p.sendMessage(Main.pl.getConfig().getString("DesativarComandos.Mensagem").replace("&", "§"));
					e.setCancelled(true);
				}else{
					return;
				}
			}			
		}else{
			return;
		}
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onServerList(ServerListPingEvent e) {
		if(Utils.motd){
			if (!UMotd.getMotd().equalsIgnoreCase("noset")) {
				e.setMotd(UMotd.getMotd().replace("&", "§"));
			} else {
				return;
			}			
		}else{
			return;
		}
	}
	
	@EventHandler
	public void banItem(PlayerInteractEvent e){
		if(Utils.banitem && !e.getPlayer().hasPermission("psessentials.banitem.ignore")){
			if(e.getItem() != null){
				String item = e.getItem().getType().toString().toLowerCase();
				if(UBanItens.containsItemBanido(item)){
					e.getPlayer().sendMessage(Mensagens.getMensagem("item_banido_use").replace("@Motivo", UBanItens.getItemBanidoMotivo(item)));
					e.setCancelled(true);
				}else{
					return;
				}			
			}
		}else{
			return;
		}
	}
	
	@EventHandler
	public void banItem2(PlayerPickupItemEvent e){
		if(Utils.banitem && !e.getPlayer().hasPermission("psessentials.banitem.ignore")){
			String item = e.getItem().getItemStack().getType().toString().toLowerCase();
			if(UBanItens.containsItemBanido(item)){
				e.getPlayer().sendMessage(Mensagens.getMensagem("item_banido_use").replace("@Motivo", UBanItens.getItemBanidoMotivo(item)));
				e.setCancelled(true);
			}else{
				return;
			}			
		}else{
			return;
		}
	}
	
	@EventHandler
	public void banItem3(PlayerDropItemEvent e){
		if(Utils.banitem && !e.getPlayer().hasPermission("psessentials.banitem.ignore")){
			String item = e.getItemDrop().getItemStack().getType().toString().toLowerCase();
			if(UBanItens.containsItemBanido(item)){
				e.getPlayer().sendMessage(Mensagens.getMensagem("item_banido_use").replace("@Motivo", UBanItens.getItemBanidoMotivo(item)));
				e.setCancelled(true);
			}else{
				return;
			}			
		}else{
			return;
		}
	}
	
	@EventHandler
	public void banItem4(CraftItemEvent e){
		Player player = (Player) e.getWhoClicked();
		if(Utils.banitem && player.hasPermission("psessentials.banitem.ignore")){
			String item = e.getRecipe().getResult().getType().toString().toLowerCase();
			if(UBanItens.containsItemBanido(item)){
				player.sendMessage(Mensagens.getMensagem("item_banido_use").replace("@Motivo", UBanItens.getItemBanidoMotivo(item)));
				e.setCancelled(true);
			}else{
				return;
			}			
		}else{
			return;
		}
	}
	
	
}
