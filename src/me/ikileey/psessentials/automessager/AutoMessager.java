package me.ikileey.psessentials.automessager;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.confuser.barapi.BarAPI;
import me.ikileey.psessentials.Main;

public class AutoMessager {

	public static void startAutoMessagerChat() {
		Integer delay = Main.pl.getConfig().getInt("AutoMessager.Chat.Delay");
		Bukkit.getScheduler().runTaskTimer(Main.pl, new Runnable() {
			public void run() {
				for(Player p : Bukkit.getOnlinePlayers()){
					Random r = new Random();
					Integer valor = r.nextInt(Main.pl.getConfig().getStringList("AutoMessager.Chat.Mensagens").size());
					String fmsg = Main.pl.getConfig().getStringList("AutoMessager.Chat.Mensagens").get(valor).replace("&", "§").replace("@Player", p.getName()).replace("@OnlinePlayers", Integer.toString(Bukkit.getOnlinePlayers().length)).replace("@MaxPlayers", Integer.toString(Bukkit.getServer().getMaxPlayers()));
					
					p.sendMessage(fmsg);
				}

				
			}
		}, 0, delay * 20L);
	}
	
	public static void startAutoMessagerBossBar() {
		Integer delay = Main.pl.getConfig().getInt("AutoMessager.BossBar.Delay");
		Integer tipo = Main.pl.getConfig().getInt("AutoMessager.BossBar.Tipo");
		Bukkit.getScheduler().runTaskTimer(Main.pl, new Runnable() {
			public void run() {
				for(Player p : Bukkit.getOnlinePlayers()){
					Random r = new Random();
					Integer valor = r.nextInt(Main.pl.getConfig().getStringList("AutoMessager.BossBar.Mensagens").size());
					String fmsg = Main.pl.getConfig().getStringList("AutoMessager.BossBar.Mensagens").get(valor).replace("&", "§").replace("@Player", p.getName()).replace("@OnlinePlayers", Integer.toString(Bukkit.getOnlinePlayers().length)).replace("@MaxPlayers", Integer.toString(Bukkit.getServer().getMaxPlayers()));
					if(tipo == 1){
						BarAPI.setMessage(p, fmsg);						
					}
					if(tipo == 2){
						BarAPI.setMessage(p, fmsg, delay);
					}
				}

				
			}
		}, 0, delay * 20L);
	}
}
