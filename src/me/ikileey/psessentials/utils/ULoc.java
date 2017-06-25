package me.ikileey.psessentials.utils;


import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import me.ikileey.psessentials.databases.SQLite;

public class ULoc {

	public static void saveLocation(Player p, String local){
		  final String w = p.getWorld().getName();
		  final double x = p.getLocation().getX();
		  final double y = p.getLocation().getY();
		  final double z = p.getLocation().getZ();
		  final float yaw = (float)p.getLocation().getYaw();
		  final float pitch = (float)p.getLocation().getPitch();
		  String loc = w+";"+x+";"+y+";"+z+";"+yaw+";"+pitch;
		  SQLite.saveLoc(local, loc);
	}
	
	public static Location getLocation(String local){
		String[] lc = SQLite.getLoc(local).split(";");
	
		final World w = Bukkit.getWorld(lc[0]);
		final double x = Double.parseDouble(lc[1]);
		final double y = Double.parseDouble(lc[2]);
		final double z = Double.parseDouble(lc[3]);
		final float yaw = Float.parseFloat(lc[4]);
		final float pitch = Float.parseFloat(lc[5]);
		Location loc = new Location(w, x, y, z, yaw, pitch);
		
		return loc;
	}
	
	
	public static Location getLocationOfHome(String p, String home){
		String[] lc = UHomes.getHomeLocation(p.toLowerCase(), home).split(";");
		
		final World w = Bukkit.getWorld(lc[0]);
		final double x = Double.parseDouble(lc[1]);
		final double y = Double.parseDouble(lc[2]);
		final double z = Double.parseDouble(lc[3]);
		final float yaw = Float.parseFloat(lc[4]);
		final float pitch = Float.parseFloat(lc[5]);
		Location loc = new Location(w, x, y, z, yaw, pitch);
		
		return loc;
		
	}
	
}
