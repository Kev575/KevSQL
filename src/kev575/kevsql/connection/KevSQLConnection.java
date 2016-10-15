package kev575.kevsql.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author Kev575
 */
public class KevSQLConnection implements SQLConnection {

	public KevSQLConnection() {
		timer = new Timer();
		final Thread main = Thread.currentThread();
		new Thread(new Runnable() {
			@Override
			public void run() {
				timer.schedule(new TimerTask() {
					
					@Override
					public void run() {
						if (!main.isAlive()) {
							cancel();
							timer.cancel();
							return;
						}
						if (con == null)
							return;
						try {
							if (con.isClosed()) {
								con = null;
							}
						} catch (Exception e) {
							con = null;
						}
					}
				}, 20, 1000*10);
			}
		}).start();
	}
	
	private SQLURI uri;
	private Connection con;
	private Timer timer;
	@Override
	public void terminate() {
		con = null;
	}

	@Override
	public void setURI(SQLURI uri) {
		this.uri = uri;
	}
	
	@Override
	public SQLURI getURI() {
		return uri;
	}
	
	@Override
	public void connect(String username, String password) {
		if (uri == null) throw new IllegalStateException(new NullPointerException("the sqluri is null"));
		if (con != null) throw new IllegalStateException("already connected");
		if (username == null) throw new IllegalArgumentException(new NullPointerException("the username is null"));
		if (password == null) throw new IllegalArgumentException(new NullPointerException("the password is null"));
		try {
			con = DriverManager.getConnection(uri.toString(), username, password);
		} catch (SQLException e) {
			throw new IllegalStateException("failed to connect to the server", e);
		}
	}
	
	@Override
	public void disconnect() {
		if (con == null)
			return;
		try {
			con.close();
			con = null;
		} catch (SQLException e) {
			con = null;
		}
	}
}
