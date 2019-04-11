package utils;

public interface DBCredentials {
	/**
	 * allows user to add item to the SQL database
	 */
	public static final String ADD_ITEM = "INSERT INTO item (id, description, quantity, price, supplierID) values(?,?,?,?,?)";

	/**
	 * deletes a item from the SQL database
	 */
	public static final String DELETE_ITEM = "DELETE FROM item WHERE id=?";

	/**
	 * adds a supplier into database
	 */
	public static final String ADD_SUPPLIER = "INSERT INTO suppliers(id,description,address,owner) values(?,?,?,?)";
	/**
	 * searches for an item from the SQL database through description
	 */
	public static final String GET_ITEM_BY_DESCRIPTION = "SELECT * FROM item WHERE description=?";

	/**
	 * searches for a item from the SQL database through ID
	 */
	public static final String GET_ITEM_BY_ID = "SELECT * FROM item WHERE id=?";

	/**
	 * user can purchase an item
	 */
	public static final String BUY_ITEM = "UPDATE item SET quantity=? WHERE id=?";

	/**
	 * checks the user information to see if its in the database
	 */
	public static final String CHECK_USER = "SELECT * FROM logins WHERE username=? and password=?";

	/**
	 * adds a user into the pre-existing database
	 */
	public static final String ADD_USER = "INSERT INTO logins (username, password) values(?,?)";
}
