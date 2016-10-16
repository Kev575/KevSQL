package kev575.kevsql.components;

/**
 * A default SQL Table
 * @author Kev575
 */
public interface SQLTable extends SQLComponent {

	/**
	 * Select all elements of this {@link SQLTable}
	 * @return {@link SQLEntry} Array
	 */
	public SQLEntry[] selectAll();
	
}
