package me.ikileey.psessentials.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import me.ikileey.psessentials.databases.SQLite;

public class UBanItens {

	public static Connection connection = SQLite.connection;

	public static String sql = "org.sqlite.JDBC";

	public static void saveItem(String item2, String motivo) {
		try {
			if (!containsItemBanido(item2)) {
				Class.forName(sql);
				final PreparedStatement pstmt2 = connection
						.prepareStatement("INSERT INTO banitem (item, motivo) VALUES ('" + item2 + "', '" + motivo + "')");
				pstmt2.execute();
			} else {
				Class.forName(sql);
				final PreparedStatement pstmt = connection
						.prepareStatement("SELECT * FROM banitem WHERE item = '" + item2 + "';");
				final ResultSet result = pstmt.executeQuery();
				final PreparedStatement pstmt2 = connection
						.prepareStatement("UPDATE banitem SET motivo = '" + motivo + "' WHERE item = '" + item2 + "';");
				pstmt2.executeUpdate();

				result.close();
				pstmt.close();
				pstmt2.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	

	public static boolean containsItemBanido(String item) {
		try {
			Class.forName(sql);
			final PreparedStatement pstmt = connection
					.prepareStatement("SELECT * FROM banitem WHERE item='" + item + "'");
			final ResultSet resultSet = pstmt.executeQuery();
			return resultSet.next() && resultSet.getString("item").equalsIgnoreCase(item);

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}
	
	public static void removeBanOfItem(String item){
		try {
			Class.forName(sql);
			final PreparedStatement pstmt2 = connection
					.prepareStatement("DELETE FROM banitem WHERE item='"+item+"';");
			pstmt2.execute();
			pstmt2.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static ArrayList<String> getItens() {
		ArrayList<String> lista = new ArrayList<>();
		try {
			Class.forName(sql);
			final PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM banitem");
			final ResultSet resultSet = pstmt.executeQuery();
			while (resultSet.next()) {
				lista.add(resultSet.getString("item"));
			}

			return lista;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String getItemBanidoMotivo(String item) {
		try {
			Class.forName(sql);
			final PreparedStatement pstmt = connection
					.prepareStatement("SELECT * FROM banitem WHERE item='" + item + "'");
			final ResultSet result = pstmt.executeQuery();
			if (result.next()) {
				return result.getString("motivo");
			}
			result.close();
			pstmt.close();
		} catch (Exception e) {
			System.out.println("ERRO in getitems: " + e.getMessage().toString());
		}
		return "erro";

	}
}
