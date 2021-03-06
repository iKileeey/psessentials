package me.ikileey.psessentials.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.bukkit.entity.Player;

import me.ikileey.psessentials.databases.Database;

public class UHomes {

	public static Connection connection = Database.connection;

	public static void setHome(Player p, String home, String loc, String invite) {
		try {
			if (!containsHomeSetada(p.getName().toLowerCase(), home)) {
				Class.forName(Database.sql);
				final PreparedStatement pstmt2 = connection
						.prepareStatement("INSERT INTO homes (player, home, coords, invite) VALUES (?, ?, ?, ?)");
			
				pstmt2.setString(1, p.getName().toLowerCase());
				pstmt2.setString(2, home);
				pstmt2.setString(3, loc);
				pstmt2.setString(4, invite);
				
				
				pstmt2.execute();
			} else {
				Class.forName(Database.sql);
				final PreparedStatement pstmt = connection
						.prepareStatement("SELECT * FROM homes WHERE player = ?;");
				
				pstmt.setString(1, p.getName().toLowerCase());
				
				final ResultSet result = pstmt.executeQuery();
				final PreparedStatement pstmt2 = connection
						.prepareStatement("UPDATE homes SET coords = ? WHERE home = ?;");
				
				pstmt2.setString(1, loc);
				pstmt2.setString(2, home);
				
				pstmt2.executeUpdate();

				result.close();
				pstmt.close();
				pstmt2.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void setDefaultHome(Player p, String home, String loc, String invite) {
		try {
			if (!containsDefaultHomeSetada(p.getName().toLowerCase())) {
				Class.forName(Database.sql);
				final PreparedStatement pstmt2 = connection
						.prepareStatement("INSERT INTO homes (player, home, coords, invite) VALUES (?, ?, ?, ?)");
				
				pstmt2.setString(1, p.getName().toLowerCase());
				pstmt2.setString(2, home);
				pstmt2.setString(3, loc);
				pstmt2.setString(4, invite);
				
				pstmt2.execute();
			} else {
				Class.forName(Database.sql);
				final PreparedStatement pstmt = connection
						.prepareStatement("SELECT * FROM homes WHERE player = ?");
				
				pstmt.setString(1, p.getName().toLowerCase());
				
				final ResultSet result = pstmt.executeQuery();
				final PreparedStatement pstmt2 = connection
						.prepareStatement("UPDATE homes SET coords = ? WHERE home = ?;");
				
				pstmt2.setString(1, loc);
				pstmt2.setString(2, home);
				
				pstmt2.executeUpdate();

				result.close();
				pstmt.close();
				pstmt2.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean containsDefaultHomeSetada(String p) {
		String home = "(Padrao)";
		try {
			Class.forName(Database.sql);
			final PreparedStatement pstmt = connection
					.prepareStatement("SELECT * FROM homes WHERE player = ? AND home = ?");
			
			pstmt.setString(1, p.toLowerCase());
			pstmt.setString(2, home);
			
			final ResultSet resultSet = pstmt.executeQuery();
			if(resultSet.next()){
				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;

	}
	
	
	public static boolean containsHomeSetada(String p, String home) {
		try {
			Class.forName(Database.sql);
			final PreparedStatement pstmt = connection
					.prepareStatement("SELECT * FROM homes WHERE player = ? AND home = ?");
			
			pstmt.setString(1, p);
			pstmt.setString(2, home);
			
			
			final ResultSet resultSet = pstmt.executeQuery();
			if(resultSet.next()){
				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return false;

	}
	
	public static String getHomeLocation(String p, String home) {
		try {
			Class.forName(Database.sql);
			final PreparedStatement pstmt = connection
					.prepareStatement("SELECT * FROM homes WHERE player = ? AND home = ?");
			
			pstmt.setString(1, p.toLowerCase());
			pstmt.setString(2, home);
			
			
			final ResultSet result = pstmt.executeQuery();
			if (result.next()) {
				return result.getString("coords");
			}
			result.close();
			pstmt.close();
		} catch (Exception e) {
			System.out.println("ERRO in getHomeLocation: " + e.getMessage().toString());
		}
		return "erro";

	}
	
	public static boolean isPrivate(String p, String home){
		try {
			Class.forName(Database.sql);
			final PreparedStatement pstmt = connection
					.prepareStatement("SELECT * FROM homes WHERE player = ? AND home = ?");
			
			pstmt.setString(1, p.toLowerCase());
			pstmt.setString(2, home);
			
			
			final ResultSet resultSet = pstmt.executeQuery();
			return resultSet.next() && resultSet.getString("invite").equalsIgnoreCase("privado");

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static ArrayList<String> getHomesOfPlayer(String p) {
		ArrayList<String> lista = new ArrayList<>();
		try {
			Class.forName(Database.sql);
			final PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM homes WHERE player = ?");
			
			pstmt.setString(1, p.toLowerCase());
			
			final ResultSet resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				lista.add(resultSet.getString("home"));
			}

			return lista;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void deleteHomeOfPlayer(String p, String home){
		try {
			Class.forName(Database.sql);
			final PreparedStatement pstmt2 = connection
					.prepareStatement("DELETE FROM homes WHERE player = ? AND home = ?");
			
			pstmt2.setString(1, p.toLowerCase());
			pstmt2.setString(2, home);
			
			pstmt2.execute();
			pstmt2.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void setHomeToPublica(Player p, String home){
		try {
			Class.forName(Database.sql);
			final PreparedStatement pstmt = connection
					.prepareStatement("SELECT * FROM homes WHERE player = ?;");
			
			pstmt.setString(1, p.getName().toLowerCase());
			
			final ResultSet result = pstmt.executeQuery();
			final PreparedStatement pstmt2 = connection
					.prepareStatement("UPDATE homes SET invite = 'publico' WHERE home = ?;");
			
			pstmt2.setString(1, home);
			pstmt2.executeUpdate();

			result.close();
			pstmt.close();
			pstmt2.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void setHomeToPrivado(Player p, String home){
		try {
			Class.forName(Database.sql);
			final PreparedStatement pstmt = connection
					.prepareStatement("SELECT * FROM homes WHERE player = ?;");
			
			pstmt.setString(1, p.getName().toLowerCase());
			
			final ResultSet result = pstmt.executeQuery();
			final PreparedStatement pstmt2 = connection
					.prepareStatement("UPDATE homes SET invite = 'privado' WHERE home = ?;");
			
			pstmt2.setString(1, home);
			
			pstmt2.executeUpdate();

			result.close();
			pstmt.close();
			pstmt2.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
