package kev575.kevsql.components;

/**
 * The base SQLEntry class
 * @author Kev575
 */
public interface SQLEntry extends SQLComponent {

	public Object get(String key);
	public void set(String key, Object value);
}
