package me.ikileey.psessentials.utils;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

public class Data {
	
	 static Data instance;
	    Plugin p;
	    FileConfiguration data;
	    File dfile;
	    
	    static {
	        Data.instance = new Data();
	    }
	    
	    public static Data getInstance() {
	        return Data.instance;
	    }
	    
	    public void setup(final Plugin p) {
	        if (!p.getDataFolder().exists()) {
	            p.getDataFolder().mkdir();
	        }
	        this.dfile = new File(p.getDataFolder(), "kits.yml");
	        if (!this.dfile.exists()) {
	            try {
	                this.dfile.createNewFile();
	            }
	            catch (IOException e) {
	                Bukkit.getServer().getLogger().severe("§cErro ao criar o arquivo kits.yml");
	            }
	        }
	        this.data = (FileConfiguration)YamlConfiguration.loadConfiguration(this.dfile);
	    }
	    
	    public FileConfiguration getData() {
	        return this.data;
	    }
	    
	    public void saveData() {
	        try {
	            this.data.save(this.dfile);
	        }
	        catch (IOException e) {
	            Bukkit.getServer().getLogger().severe("§cErro ao salvar o arquivo kits.yml");
	        }
	    }
	    
	    public void reloadData() {
	        this.data = (FileConfiguration)YamlConfiguration.loadConfiguration(this.dfile);
	    }
}
