package kev575.kevsql;

import java.util.HashMap;

import kev575.kevsql.connection.KevSQLConnection;
import kev575.kevsql.connection.SQLConnection;

public final class KevSQL {

	private static final HashMap<String, SQLConnection> connection = new HashMap<>(); 
	
	private KevSQL() {
	}
	
	/**
	 * 
	 * @param name
	 * @return created {@link SQLConnection}
	 */
	public static SQLConnection createConnection(String name) {
		connection.put(name, new KevSQLConnection() {
		});
		return connection.get(name);
	}
	
	/**
	 * Get a {@link SQLConnection}
	 * @param name {@link SQLConnection}'s Name
	 */
	public static SQLConnection getConnection(String name) {
		if (!connection.containsKey(name))
			return null;
		return connection.get(name);
	}
	
	/**
	 * Check if a {@link SQLConnection} exists
	 * @param name {@link SQLConnection}'s Name
	 */
	public static boolean isConnectionExisting(String name) {
		return getConnection(name) != null;
	}
	
	/**
	 * Terminates a {@link SQLConnection}
	 * @param name {@link SQLConnection}'s Name
	 * @throws NullPointerException if connection does not exist
	 */
	public static void terminateConnection(String name) {
		if (!isConnectionExisting(name))
			throw new NullPointerException(name + " is not a valid connection");
		getConnection(name).terminate();
		connection.remove(name);
	}
	
}
