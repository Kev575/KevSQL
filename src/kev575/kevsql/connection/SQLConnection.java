package kev575.kevsql.connection;

import kev575.kevsql.components.SQLTable;

/**
 * @author Kev575
 */
public interface SQLConnection {
	/**
	 * Terminate the connection<br>just setting con variable to <b>null</b><br><br><b><color style="color: red;">Warning: This is really ressource encumbering!</color></b>
	 * @see SQLConnection#disconnect()
	 */
	public void terminate();
	/**
	 * Set the URI
	 * @param uri {@link SQLURI}
	 */
	public void setURI(SQLURI uri);
	/**
	 * Get the URI
	 * @return {@link SQLURI}
	 */
	public SQLURI getURI();
	/**
	 * Initiate the connection
	 * @param username
	 * @param password
	 */
	public void connect(String username, String password);
	/**
	 * Initiate the connection with neither username nor password
	 */
	public void connect();
	/**
	 * Disconnect the connection<br>closes the connection first and then {@link SQLConnection#terminate()}
	 * @see SQLConnection#terminate()
	 */
	public void disconnect();
	
	/**
	 * Get a table
	 * @param name the name of the table
	 */
	public SQLTable getTable(String name);
}
