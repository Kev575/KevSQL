package kev575.kevsql.components;

/**
 * the base class of all KevSQL components
 * @author Kev575
 */
public interface SQLComponent {
	/**
	 * Not available for every {@link SQLComponent} 
	 * @return json serialized string
	 */
	public String toJson();
}
