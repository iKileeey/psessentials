package me.ikileey.psessentials.messages;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.configuration.file.YamlConfiguration;

import com.google.common.io.Resources;

import me.ikileey.psessentials.Main;

public class Mensagens {

	public static HashMap<String, String> mensagens = new HashMap<>();
	public static HashMap<String, String> erros = new HashMap<>();
	public static ArrayList<String> motd = new ArrayList<>();
	
	public static void loadMensagens(){
		mensagens.clear();
		motd.clear();
		
        try {
            final File file3 = new File(Main.pl.getDataFolder(), "mensagens.yml");
            
            if (!file3.exists()) {
                Main.pl.saveResource("mensagens.yml", false);
                Main.pl.getLogger().info("mensagens.yml carregada!");
            }
        }
        catch (Exception ex2) {}
        
        try {
            final File file3 = new File(Main.pl.getDataFolder(), "mensagens-com-acentos.txt");
            
            if (!file3.exists()) {
                Main.pl.saveResource("mensagens-com-acentos.txt", false);
            }
        }
        catch (Exception ex2) {}
        
		final File f_msgs = new File(Main.pl.getDataFolder(), "mensagens.yml");
        final YamlConfiguration msgs = YamlConfiguration.loadConfiguration(f_msgs);
        
        
        try {
        	 String alltext = Resources.toString(f_msgs.toURI().toURL(), Charset.forName("UTF-8"));
             msgs.loadFromString(alltext);			
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        for (final String n : msgs.getConfigurationSection("Mensagens").getKeys(false)) {
            if (!Mensagens.mensagens.containsKey(n.toLowerCase())) {
                Mensagens.mensagens.put(n.toLowerCase(), msgs.getString("Mensagens."+n));
            }
        }
        for (final String n : msgs.getConfigurationSection("Erros").getKeys(false)) {
            if (!Mensagens.erros.containsKey(n.toLowerCase())) {
                Mensagens.erros.put(n.toLowerCase(), msgs.getString("Erros."+n));
            }
        }
        
        try {
            final File file3 = new File(Main.pl.getDataFolder(), "motd.txt");
            if (!file3.exists()) {
                Main.pl.saveResource("motd.txt", false);
                Main.pl.getLogger().info("motd.txt carregada!");
            }
        }
        catch (Exception ex3) {}
        
        
    	final File f_motd = new File(Main.pl.getDataFolder(), "motd.txt");
		try {
			final BufferedReader reader = new BufferedReader(new FileReader(f_motd));
			String line = null;
			while ((line = reader.readLine()) != null) {
				motd.add(line);
			}
			reader.close();
		}
		catch(Exception ex){}
        
	}
	
    public static String getMensagem(final String n) {
        return Mensagens.mensagens.get(n.toLowerCase()).replaceAll("&", "§");
    }
    public static String getErro(final String n) {
        return Mensagens.erros.get(n.toLowerCase()).replaceAll("&", "§");
    }
    
    public static List<String> getMotd() {
        return motd;
    }
    
}
