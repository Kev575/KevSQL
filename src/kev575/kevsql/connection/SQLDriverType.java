package kev575.kevsql.connection;

/**
 * @author Kev575
 */
public enum SQLDriverType {
	JDBC_MYSQL("jdbc:mysql://"), MYSQL("mysql://");
	
	private final String protocol;
	
	private SQLDriverType(String protocol) {
		this.protocol = protocol;
	}
	
	/**
	 * @return e.g. jdbc:mysql://
	 */
	public String getProtocol() {
		return protocol;
	}
	
}
