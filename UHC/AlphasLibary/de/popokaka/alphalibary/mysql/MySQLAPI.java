package de.popokaka.alphalibary.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public class MySQLAPI {
	private static String username;
	private static String password;
	private static String database;
	private static String host;
	private static String port;
	private static Connection con;
	private static Plugin plugin;

	public static void setMySQLConnection(String username, String pass,
			String database, String host, String port) {
		MySQLAPI.username = username;
		MySQLAPI.password = pass;
		MySQLAPI.database = database;
		MySQLAPI.host = host;
		MySQLAPI.port = port;
	}

	public static Connection getMySQLConnection() {
		return con;
	}

	public static String getMySQLFilePath() {
		return "./plugins/MySQLAPI/";
	}

	public static boolean isConnected() {
		return con != null;
	}

	public static Plugin getPlugin() {
		return MySQLAPI.plugin;
	}

	public static void initMySQLAPI(Plugin plugin) {
		MySQLAPI.plugin = plugin;

		MySQLFileManager.setStandardMySQL();
		MySQLFileManager.readMySQL();

		if (!isConnected()) {
			try {
				con = DriverManager.getConnection("jdbc:mysql://" + host + ":"
						+ port + "/" + database, username, password);
			} catch (SQLException e) {
				Bukkit.getConsoleSender().sendMessage(
						"Fehler bei Anmeldung in Database!");
				e.printStackTrace();
			}
		}
	}

	public static void closeMySQLConnection() throws SQLException {

		if (isConnected()) {
			con.close();
		}
	}
}
