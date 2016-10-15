package kev575.kevsql.connection;

/**
 * the base class
 * @author Kev575
 */
public interface SQLComponent {
	/**
	 * Not available for every {@link SQLComponent} 
	 * @return json serialized string
	 */
	public String toJson();
}
