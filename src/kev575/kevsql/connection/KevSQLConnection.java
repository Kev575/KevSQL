package kev575.kevsql.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import com.google.gson.Gson;

import kev575.kevsql.components.SQLEntry;
import kev575.kevsql.components.SQLTable;

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
	public void connect() {
		if (uri == null) throw new IllegalStateException(new NullPointerException("the sqluri is null"));
		if (con != null) throw new IllegalStateException("already connected");
		try {
			con = DriverManager.getConnection(uri.toString());
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

	@Override
	public SQLTable getTable(final String name) {
		if (con == null)
			throw new IllegalStateException("connection is disconnected");
		try {
			con.createStatement().executeQuery("SELECT * FROM `"+name+"`");
		} catch (SQLException e) {
			return null;
		}
		return new SQLTable() {
			
			@Override
			public String toJson() {
				return null;
			}
			
			@Override
			public SQLEntry[] selectAll() {
				ArrayList<SQLEntry> se = new ArrayList<>();
				try {
					ResultSet set = con.createStatement().executeQuery("SELECT * FROM `" + name + "`");
					while (set.next()) {
						SQLEntry current = new SQLEntry() {
							HashMap<String, Object> values = new HashMap<>();
							
							@Override
							public String toJson() {
								return new Gson().toJson(values);
							}
							
							@Override
							public void set(String key, Object value) {
								if (key == null || value == null) throw new IllegalArgumentException(new NullPointerException("one ore more arguments are nulll"));
								if (values.containsKey(key))
									throw new IllegalStateException("already contains " + key + "'s value");
								values.put(key, value);
							}
							
							@Override
							public Object get(String key) {
								return values.get(key);
							}
						};
						for (int i = 0; i < set.getMetaData().getColumnCount(); i++) {
							current.set(set.getMetaData().getColumnLabel(i), set.getObject(i));
						}
					}
				} catch (Exception e) {
					return null;
				}
				return se.toArray(new SQLEntry[] {});
			}
		};
	}
}
