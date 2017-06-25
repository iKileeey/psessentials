package me.ikileey.psessentials.limpeza;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;

import me.ikileey.psessentials.Main;

public class Limpeza {

	public static Integer t;
	public static Integer ctd;
	public static Integer total = 0;
	
	public static void clearItens(){
		total = 0;
		for(String w : Main.pl.getConfig().getConfigurationSection("Limpeza.Clear.Mundos").getKeys(false)){
			boolean cleardrops = Main.pl.getConfig().getBoolean("Limpeza.Clear.Mundos."+w+".clear_drops");
			boolean killmobs = Main.pl.getConfig().getBoolean("Limpeza.Clear.Mundos."+w+".clear_mobs");
			
			World world = (World) Bukkit.getServer().getWorld(w);
			if(world != null){
				if(!world.getEntities().isEmpty()){
					if(cleardrops == true){
						if(!world.getEntities().isEmpty()){
							List<Entity> tlist = world.getEntities();	
							
							for(Entity current : tlist){
								if(current instanceof Item){
									total++;
									current.remove();
								}
							}
						}			
					}
					if(killmobs == true){
						List<Entity> flist = world.getEntities();
						
						for(Entity current : flist){
							if(!(current instanceof Player) && !(current instanceof ItemFrame) && !(current instanceof Item) && !(current instanceof Villager)){
								total++;
								current.remove();
							}
						}	
					}				
				}
			}
			
		}
		if(total == 0){
			Bukkit.broadcastMessage(Main.pl.getConfig().getString("Limpeza.Avisos.0").replace("&", "§").replace("@Total", "0"));
		}else{
			Bukkit.broadcastMessage(Main.pl.getConfig().getString("Limpeza.Avisos.0").replace("&", "§").replace("@Total", Integer.toString(total)));
		}
		
	}
	
	@SuppressWarnings("deprecation")
	public static void startTask(){
		t = Main.pl.getConfig().getInt("Limpeza.Tempo");
		Bukkit.getScheduler().scheduleAsyncRepeatingTask(Main.pl, new Runnable() {
			public void run() {
				for (final String n : Main.pl.getConfig().getConfigurationSection("Limpeza.Avisos").getKeys(false)) {
					if(n.equals(t.toString())){
						Bukkit.broadcastMessage(Main.pl.getConfig().getString("Limpeza.Avisos."+n).replace("&", "§"));
					}
				}
				t--;
				if(t <= 0){
					clearItens();
					t = Main.pl.getConfig().getInt("Limpeza.Tempo");
				}
			}
		},0, 1 * 20L);
	}
}
