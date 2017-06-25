package me.ikileey.psessentials.createcmds;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import me.ikileey.psessentials.messages.Mensagens;
import me.ikileey.psessentials.utils.UCreateCmds;
import me.ikileey.psessentials.vault.VaultAPI;


public class CreateCmds implements Listener {

	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onCommand(PlayerCommandPreprocessEvent e){
		String c = e.getMessage().replace("/", "");
		Player p = e.getPlayer();
		String[] comando = c.split(" ");
	    for (final String n : UCreateCmds.config.getConfigurationSection("Comandos").getKeys(false)) {
            if (comando[0].equalsIgnoreCase(n)) {
            	e.setCancelled(true);
            	if(UCreateCmds.config.contains("Comandos."+n+".permissao")){
            		String perm = UCreateCmds.config.getString("Comandos."+n+".permissao");
            		if(!p.hasPermission(perm)){
            			p.sendMessage(Mensagens.getErro("sem_perm"));
            			return;
            		}
            	}
            	if(UCreateCmds.config.contains("Comandos."+n+".mensagem")){
            		for(String msg : UCreateCmds.config.getStringList("Comandos."+n+".mensagem")){
            			if(VaultAPI.setupEconomy()){
            				p.sendMessage(msg.replace("&", "§").replace("@Player", p.getName()).replace("@Onlines", Integer.toString(Bukkit.getServer().getOnlinePlayers().length))
            						.replace("@MaxPlayers", Integer.toString(Bukkit.getServer().getMaxPlayers())).replace("@Dinheiro", Double.toString(VaultAPI.economy.getBalance(p.getName()))));            				
            			}else{
            				p.sendMessage(msg.replace("&", "§").replace("@Player", p.getName()).replace("@Onlines", Integer.toString(Bukkit.getServer().getOnlinePlayers().length))
            						.replace("@Dinheiro", "@Dinheiro"));
            			}
            		}
            	}
            	if(UCreateCmds.config.contains("Comandos."+n+".executar")){
            		String exc = UCreateCmds.config.getString("Comandos."+n+".executar").replace("@Player", p.getName());
            		Bukkit.getServer().dispatchCommand(p, exc);
            	}
            	if(UCreateCmds.config.contains("Comandos."+n+".console")){
            		String exc = UCreateCmds.config.getString("Comandos."+n+".console").replace("@Player", p.getName());
            		Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), exc);
            	}
            	if(UCreateCmds.config.contains("Comandos."+n+".som")){
            		Integer som = UCreateCmds.config.getInt("Comandos."+n+".som");
            		if(som == 1){
            			p.playSound(p.getLocation(), Sound.NOTE_BASS, 1L, 1L);
            		}
            		if(som == 2){
            			p.playSound(p.getLocation(), Sound.NOTE_BASS_DRUM, 1L, 1L);
            		}
            		if(som == 3){
            			p.playSound(p.getLocation(), Sound.NOTE_BASS_GUITAR, 1L, 1L);
            		}
            		if(som == 4){
            			p.playSound(p.getLocation(), Sound.NOTE_PIANO, 1L, 1L);
            		}
            		if(som == 5){
            			p.playSound(p.getLocation(), Sound.NOTE_PLING, 1L, 1L);
            		}
            		if(som == 6){
            			p.playSound(p.getLocation(), Sound.NOTE_SNARE_DRUM, 1L, 1L);
            		}
            		if(som == 7){
            			p.playSound(p.getLocation(), Sound.NOTE_STICKS, 1L, 1L);
            		}
            		if(som == 8){
            			p.playSound(p.getLocation(), Sound.CHICKEN_EGG_POP, 1L, 1L);
            		}
            		if(som == 9){
            			p.playSound(p.getLocation(), Sound.ORB_PICKUP, 1L, 1L);
            		}
            	}
            	if(UCreateCmds.config.contains("Comandos."+n+".loop")){
            		int tamanho = UCreateCmds.config.getInt("Comandos."+n+".loop.Tamanho");
            		for (int i = 0; i < tamanho; i++) {
            			if(UCreateCmds.config.contains("Comandos."+n+".loop.broadcast")){
            				Bukkit.broadcastMessage(UCreateCmds.config.getString("Comandos."+n+".loop.broadcast"));
            			}
					}
            	}
            	if(UCreateCmds.config.contains("Comandos."+n+".broadcast")){
            		Bukkit.broadcastMessage(UCreateCmds.config.getString("Comandos."+n+".broadcast").replace("&", "§").replace("@Player", p.getName()));
            	}
            }
	    }
	}
	
}
