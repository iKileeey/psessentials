package me.ikileey.psessentials.utils;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.ikileey.psessentials.Main;

public class Utils {

	private static ArrayList<String> god = new ArrayList<>();
	private static HashMap<String, String> call = new HashMap<>();
	public static ArrayList<Player> c = new ArrayList<>();
	
	public static boolean entrou, saiu, morreu, fome, chuva, noite, motd, clearlag, delaytp, forcespawn, limpeza, corplaca, automchat, autombar, banitem, protecao, contemPex, mySql, protectVoid;
	public static int tempodelaytp, protectVoidTipo;
	public static String mysqluser, mysqlpass, mysqldbase, mysqlhost;
	
	public static void setup(){
		entrou = Main.pl.getConfig().getBoolean("Server.Mensagens.Entrou");
		saiu = Main.pl.getConfig().getBoolean("Server.Mensagens.Saiu");
		morreu = Main.pl.getConfig().getBoolean("Server.Mensagens.Morreu");
		fome = Main.pl.getConfig().getBoolean("Server.Configuracao.Fome");
		chuva = Main.pl.getConfig().getBoolean("Server.Configuracao.Chuva");
		noite = Main.pl.getConfig().getBoolean("Server.Configuracao.Noite");
		motd = Main.pl.getConfig().getBoolean("Motd.Ativar");
	    clearlag = Main.pl.getConfig().getBoolean("Limpeza.Ativar");
	    delaytp = Main.pl.getConfig().getBoolean("Teleporte.Delay");
	    forcespawn = Main.pl.getConfig().getBoolean("Server.ForceSpawn.Ativar");
	    tempodelaytp = Main.pl.getConfig().getInt("Teleporte.Tempo");
	    limpeza = Main.pl.getConfig().getBoolean("Limpeza.Ativar");
	    corplaca = Main.pl.getConfig().getBoolean("Placa.Cor");
	    automchat = Main.pl.getConfig().getBoolean("AutoMessager.Chat.Ativar");
	    autombar = Main.pl.getConfig().getBoolean("AutoMessager.BossBar.Ativar");
	    banitem = Main.pl.getConfig().getBoolean("BanirItem.Ativar");
	    protecao = Main.pl.getConfig().getBoolean("Protecao.AntiForceOP");
	    mySql = Main.pl.getConfig().getBoolean("MySQL.Ativar");
	    protectVoid = Main.pl.getConfig().getBoolean("Protecao-Void.Ativar");
	    protectVoidTipo = Main.pl.getConfig().getInt("Protecao-Void.Teleportar");
	    if(mySql){
	    	setupMySQL();
	    }
	    if(containsPermissionsEx()){
	    	contemPex = true;
	    }else{
	    	contemPex = false;
	    }
	}
	
	public static void setGod(Player p, boolean b){
		if(b == true){
			god.add(p.getName().toLowerCase());			
		}else{
			god.remove(p.getName().toLowerCase());
		}
	}
	
	public static boolean hasGod(Player p){
		if(god.contains(p.getName().toLowerCase())){
			return true;
		}else{
			return false;
		}
	}
	
	public static boolean isInt(String s) {
	    try {
	        Integer.parseInt(s);
	    } catch (NumberFormatException nfe) {
	        return false;
	    }
	    return true;
	}
	
	public static boolean containsCall(Player p){
		if(call.containsValue(p.getName().toLowerCase())){
			return true;
		}else{
			return false;
		}
	}
	
	public static HashMap<String, String> getCall(){
		return call;
	}
	
	public static boolean containsPermissionsEx(){
		if(Main.pl.getServer().getPluginManager().getPlugin("PermissionsEx") != null){
			return true;
		}else{
			return false;
		}
	
	}
	
	public static void setupMySQL(){
		mysqluser = Main.pl.getConfig().getString("MySQL.Usuario");
		mysqlpass = Main.pl.getConfig().getString("MySQL.Senha");
		mysqldbase = Main.pl.getConfig().getString("MySQL.Database");
		mysqlhost = Main.pl.getConfig().getString("MySQL.Host");
	}
	
	   public static boolean isInventoryEmpty(final Player p) {
	        ItemStack[] contents;
	        for (int length = (contents = p.getInventory().getContents()).length, j = 0; j < length; ++j) {
	            final ItemStack i = contents[j];
	            ItemStack[] armorContents;
	            for (int length2 = (armorContents = p.getInventory().getArmorContents()).length, k = 0; k < length2; ++k) {
	                final ItemStack a = armorContents[k];
	                if (a.getType() != Material.AIR) {
	                    return false;
	                }
	            }
	            if (i != null && i.getType() != Material.AIR) {
	                return false;
	            }
	        }
	        return true;
	    }
	   
	   
	
}
