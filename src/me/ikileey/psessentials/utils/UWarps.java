package me.ikileey.psessentials.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import me.ikileey.psessentials.databases.SQLite;

public class UWarps {

	public static Connection connection = SQLite.connection;

	public static String sql = "org.sqlite.JDBC";

	public static void saveWarp(String nome, String loc) {
		try {
			if (!containsWarp(nome)) {
				Class.forName(sql);
				final PreparedStatement pstmt2 = connection
						.prepareStatement("INSERT INTO warps (warp, coords) VALUES (?, ?)");
				
				pstmt2.setString(1, nome);
				pstmt2.setString(2, loc);
				
				pstmt2.execute();
			} else {
				Class.forName(sql);
				final PreparedStatement pstmt = connection
						.prepareStatement("SELECT * FROM warps WHERE warp = ?;");
				
				pstmt.setString(1, nome);
				
				final ResultSet result = pstmt.executeQuery();
				final PreparedStatement pstmt2 = connection
						.prepareStatement("UPDATE warps SET coords = ? WHERE warp = ?;");
				
				pstmt2.setString(1, loc);
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
	
	

	public static boolean containsWarp(String nome) {
		try {
			Class.forName(sql);
			final PreparedStatement pstmt = connection
					.prepareStatement("SELECT * FROM warps WHERE warp = ?");
			
			pstmt.setString(1, nome);
			final ResultSet resultSet = pstmt.executeQuery();
			return resultSet.next() && resultSet.getString("warp").equalsIgnoreCase(nome);

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}
	
	public static void deleteWarp(String nome){
		try {
			Class.forName(sql);
			final PreparedStatement pstmt2 = connection
					.prepareStatement("DELETE FROM warps WHERE warp = ?;");
			
			pstmt2.setString(1, nome);
			
			pstmt2.execute();
			pstmt2.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static ArrayList<String> getWarps() {
		ArrayList<String> lista = new ArrayList<>();
		try {
			Class.forName(sql);
			final PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM warps");
			final ResultSet resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				lista.add(resultSet.getString("warp"));
			}

			return lista;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String getWarp(String nome) {
		try {
			Class.forName(sql);
			final PreparedStatement pstmt = connection
					.prepareStatement("SELECT * FROM warps WHERE warp = ?");
			
			pstmt.setString(1, nome);
			
			final ResultSet result = pstmt.executeQuery();
			if (result.next()) {
				return result.getString("coords");
			}
			result.close();
			pstmt.close();
		} catch (Exception e) {
			System.out.println("ERRO in getwarps: " + e.getMessage().toString());
		}
		return "erro";

	}
}
