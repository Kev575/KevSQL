package kev575.kevsql.connection;

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
	 * Disconnect the connection<br>closes the connection first and then {@link SQLConnection#terminate()}
	 * @see SQLConnection#terminate()
	 */
	public void disconnect();
}
