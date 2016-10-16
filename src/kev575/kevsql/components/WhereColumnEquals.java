package kev575.kevsql.components;

import com.google.gson.Gson;

/**
 * @author Kev575
 */
public class WhereColumnEquals implements SQLComponent {

	private final String key;
	private final Object val;
	
	/**
	 * @param key the key
	 * @param val the value
	 */
	public WhereColumnEquals(String key, Object val) {
		this.key = key;
		this.val = val;
	}

	@Override
	public String toJson() {
		return new Gson().toJson(this);
	}
	
	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * @return the value
	 */
	public Object getValue() {
		return val;
	}
	
}
