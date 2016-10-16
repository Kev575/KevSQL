package kev575.kevsql.components;

import java.util.HashMap;

import com.google.gson.Gson;

/**
 * This is an connection independent SQLEntry
 * @author Kev575
 */
public class BasicSQLEntry implements SQLEntry {

	public BasicSQLEntry() {
	}
	
	private HashMap<String, Object> entries = new HashMap<>();
	
	@Override
	public String toJson() {
		return new Gson().toJson(entries);
	}

	@Override
	public Object get(String key) {
		return entries.get(key);
	}

	@Override
	public void set(String key, Object value) {
		if (get(key) != null)
			entries.remove(key);
		entries.put(key, value);
	}
	
	@Override
	public String toString() {
		return "{BasicSQLEntry={EntryCount="+entries.size()+"}}";
	}
}
