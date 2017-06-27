package me.ikileey.psessentials.databases;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.bukkit.Bukkit;

import me.ikileey.psessentials.Main;

public class SQLite {

	public static Connection connection;
    
    public static String sql = "org.sqlite.JDBC";
    
    public static void setConnection(){
    	try {
    		Bukkit.getConsoleSender().sendMessage("[PsEssentials] Conectando no SQLite..");
    		Class.forName("org.sqlite.JDBC");
    		connection = DriverManager.getConnection("jdbc:sqlite:" + Main.pl.getDataFolder().getAbsolutePath() + File.separator + "database.db");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
    	catch (Exception e2) {
    		e2.printStackTrace();
		}
    }
    
    
    public static void startDatabase(){
    	try {
    		setConnection();
			final Statement stmt = connection.createStatement();
			stmt.execute("CREATE TABLE IF NOT EXISTS loc (local VARCHAR(25), coords VARCHAR(40))");
			stmt.execute("CREATE TABLE IF NOT EXISTS warps (warp VARCHAR(25), coords VARCHAR(40))");
			stmt.execute("CREATE TABLE IF NOT EXISTS cooldown (registro VARCHAR(25), tempo LONG)");
			stmt.execute("CREATE TABLE IF NOT EXISTS homes (player VARCHAR(25), home VARCHAR(40), coords VARCHAR(40), invite VARCHAR(40))");
			stmt.execute("CREATE TABLE IF NOT EXISTS motd (motdatual varchar(255))");
			stmt.execute("CREATE TABLE IF NOT EXISTS banitem (item VARCHAR(255), motivo VARCHAR(255))");
			stmt.close();
			Bukkit.getConsoleSender().sendMessage("[PsEssentials] Conectado com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public static void saveLoc(String p, String loc){
    	try {
    		if(!haslocalLoc(p)){
    			Class.forName(sql);
    			final PreparedStatement pstmt2 = connection.prepareStatement("INSERT INTO loc (local, coords) VALUES (?, ?)");
    			pstmt2.setString(1, p);
    			pstmt2.setString(2, loc);
    			pstmt2.execute();	
    		}else{
    			Class.forName(sql);
    			final PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM loc WHERE local = ?;");
    			pstmt.setString(1, p);
    			
    			final ResultSet result = pstmt.executeQuery();
    			final PreparedStatement pstmt2 = connection.prepareStatement("UPDATE loc SET coords = ? WHERE local = ?;");
    			pstmt2.setString(1, loc);
    			pstmt2.setString(2, p);
    			
    			pstmt2.executeUpdate();
    			
    			result.close();
    			pstmt.close();
    			pstmt2.close();    			
    		}
    		
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	
	
    public static boolean haslocalLoc(String p){
    	try {
    		Class.forName(sql);
			final PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM loc WHERE local = ?");
			pstmt.setString(1, p);
			final ResultSet resultSet = pstmt.executeQuery();
			return resultSet.next() && resultSet.getString("local").equalsIgnoreCase(p);
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
    	
    }


    public static String getLoc(String p){
    	try {
    		Class.forName(sql);
    		final PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM loc WHERE local = ?");
    		pstmt.setString(1, p);
    		final ResultSet result = pstmt.executeQuery();
    		if(result.next()){
    			return result.getString("coords");
				}
    		result.close();
    		pstmt.close();
		} catch (Exception e) {
			System.out.println("ERRO in getLoc: "+e.getMessage().toString());
		}
    	
		return "erro";
		
    	
    }
}
