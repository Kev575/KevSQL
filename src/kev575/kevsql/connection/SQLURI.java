package kev575.kevsql.connection;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author Kev575
 */
public class SQLURI {

	SQLDriverType driver;
	String address, database;
	int port;
	
	/**
	 * Get a new instance of {@link SQLURI}
	 * @param driver {@link SQLDriverType}
	 * @param address ip or hostname of the server
	 * @param port port in range of 1 - 65535 (default: 3306)
	 */
	public SQLURI(SQLDriverType driver, String address, int port) {
		if (driver == null) throw new NullPointerException("driver is null");
		if (address == null) throw new NullPointerException("address is null");
		if (port <= 0 || port > 65535) throw new IllegalArgumentException("port is invalid");
		this.driver = driver;
		this.address = address;
		this.port = port;
		this.database = null;
		try {
			new URL("http://" + address);
		} catch (MalformedURLException e) {
			throw new IllegalArgumentException(address + " is in wrong format");
		}
	}
	
	/**
	 * @param driver
	 * @param address ip or hostname of the server
	 */
	public SQLURI(SQLDriverType driver, String address) {
		if (driver == null) throw new NullPointerException("driver is null");
		if (address == null) throw new NullPointerException("address is null");
		this.driver = driver;
		this.address = address;
		this.port = 3306;
		this.database = null;
		try {
			new URL("http://" + address);
		} catch (MalformedURLException e) {
			throw new IllegalArgumentException(address + " is in wrong format");
		}
	}
	
	/**
	 * Set the sql database
	 * @param database the sql database
	 */
	public void setDatabase(String database) {
		this.database = database;
	}

	/**
	 * Get the {@link SQLDriverType}
	 * @return the driver
	 */
	public SQLDriverType getDriver() {
		return driver;
	}

	/**
	 * Get the address
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Get the database
	 * @return the database
	 */
	public String getDatabase() {
		return database;
	}

	/**
	 * Get the port
	 * @return the port
	 */
	public int getPort() {
		return port;
	}

	/**
	 * convert to String URI
	 */
	@Override
	public String toString() {
		return driver.getProtocol() + getAddress() + ":" + getPort() + "/" + (getDatabase() == null ? "" : getDatabase());
	}
}
