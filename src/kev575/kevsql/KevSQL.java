package kev575.kevsql;

import java.util.HashMap;

import kev575.kevsql.connection.KevSQLConnection;
import kev575.kevsql.connection.SQLConnection;

public final class KevSQL {

	private static final HashMap<String, SQLConnection> connection = new HashMap<>(); 
	
	private KevSQL() {
	}
	
	/**
	 * Create a connection
	 * @param name
	 * @return the created {@link SQLConnection}<br>It's in version 0.1 an instance of {@link KevSQLConnection}.
	 */
	public static SQLConnection createConnection(String name) {
		if (connection.containsKey(name))
			throw new IllegalStateException(new IllegalArgumentException("connection with key " + name + " already exists"));
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
	
	/**
	 * Removes a {@link SQLConnection} from the internal list, but doesn't closes it<br><br><b>Does not throw a {@link NullPointerException} when connection is illegal</b>
	 * @param name {@link SQLConnection}'s Name
	 * @return if the connection existsted and got removed
	 */
	public static boolean removeConnection(String name) {
		if (!isConnectionExisting(name))
			return false;
		return connection.remove(name) != null;
	}
	
}
