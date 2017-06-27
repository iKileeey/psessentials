package me.ikileey.psessentials.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import me.ikileey.psessentials.databases.Database;

public class UCooldown {

	public static Connection connection = Database.connection;

	public static void setDelay(String nome, Long l) {
		try {
			if (!containsRegistro(nome)) {
				Class.forName(Database.sql);
				final PreparedStatement pstmt2 = connection
						.prepareStatement("INSERT INTO cooldown (registro, tempo) VALUES (?, ?)");
				
				pstmt2.setString(1, nome);
				pstmt2.setLong(2, l);
				pstmt2.execute();
				
			} else {
				Class.forName(Database.sql);
				final PreparedStatement pstmt = connection
						.prepareStatement("SELECT * FROM cooldown WHERE registro = ?;");
				
				pstmt.setString(1, nome);
				final ResultSet result = pstmt.executeQuery();
				final PreparedStatement pstmt2 = connection
						.prepareStatement("UPDATE cooldown SET tempo = ? WHERE registro = ?;");
				
				pstmt2.setLong(1, l);
				pstmt2.setString(2, nome);
				
				pstmt2.executeUpdate();

				result.close();
				pstmt.close();
				pstmt2.close();
				
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	

	public static boolean containsRegistro(String nome) {
		try {
			Class.forName(Database.sql);
			final PreparedStatement pstmt = connection
					.prepareStatement("SELECT * FROM cooldown WHERE registro = ?");
			
			pstmt.setString(1, nome);
			final ResultSet resultSet = pstmt.executeQuery();
			return resultSet.next() && resultSet.getString("registro").equalsIgnoreCase(nome);

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}


	public static Long getRegistro(String nome) {
		try {
			Class.forName(Database.sql);
			final PreparedStatement pstmt = connection
					.prepareStatement("SELECT * FROM cooldown WHERE registro = ?");
			
			pstmt.setString(1, nome);
			final ResultSet result = pstmt.executeQuery();
			if (result.next()) {
				return result.getLong("tempo");
			}
			result.close();
			pstmt.close();
		} catch (Exception e) {
			System.out.println("ERRO in getRegistro: " + e.getMessage().toString());
		}
		return null;

	}
	
	
	public static void deleteRegistro(String nome){
		try {
			Class.forName(Database.sql);
			final PreparedStatement pstmt2 = connection
					.prepareStatement("DELETE FROM cooldown WHERE registro = ?;");
			pstmt2.execute();
			pstmt2.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
