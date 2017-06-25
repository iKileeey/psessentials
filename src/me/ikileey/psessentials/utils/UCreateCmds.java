package me.ikileey.psessentials.utils;

import java.io.File;

import org.bukkit.configuration.file.YamlConfiguration;

import me.ikileey.psessentials.Main;

public class UCreateCmds {
	
	public static YamlConfiguration config;
	
	public static void setupCommands(){
		 try {
	            final File file3 = new File(Main.pl.getDataFolder(), "comandos.yml");
	            
	            if (!file3.exists()) {
	                Main.pl.saveResource("comandos.yml", false);
	                Main.pl.getLogger().info("comandos.yml carregada!");
	            }
	        }
	        catch (Exception ex2) {}
	        
			final File f_msgs = new File(Main.pl.getDataFolder(), "comandos.yml");
	        final YamlConfiguration msgs = YamlConfiguration.loadConfiguration(f_msgs);
	        config = msgs;
	}
	
}
