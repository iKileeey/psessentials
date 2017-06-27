package me.ikileey.psessentials.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import me.ikileey.psessentials.databases.Database;

public class UMotd {

	public static Connection connection = Database.connection;

	public static void setMotd(String motd){
    	try {
    		if(getMotd().equalsIgnoreCase("noset")){
    			Class.forName(Database.sql);
    			final PreparedStatement pstmt = connection.prepareStatement("INSERT INTO motd (motdatual) VALUES (?)");
    			
    			pstmt.setString(1, motd);
    			
    			pstmt.execute();
    			pstmt.close();    			
    		}else{
    			Class.forName(Database.sql);
    			final PreparedStatement pstmt = connection.prepareStatement("UPDATE motd SET motdatual = ?");
    			pstmt.setString(1, motd);
    			pstmt.executeUpdate();
    			pstmt.close();
    		}
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public static String getMotd(){
    	try {
    		Class.forName(Database.sql);
    		final PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM motd");
    		final ResultSet result = pstmt.executeQuery();
    		if(result.next()){
    			return result.getString("motdatual");
				}
    		result.close();
    		pstmt.close();
		} catch (Exception e) {
			System.out.println("ERRO in getMotd: "+e.getMessage().toString());
		}
		return "noset";
		
    	
    }
}
