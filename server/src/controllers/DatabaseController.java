package controllers;

import java.sql.*;
import utils.Item;

public class DatabaseController {
	private Connection connection;
	// private Statement stmt;
	// private ResultSet rs;
	private String ADD_ITEM = "INSERT INTO item (id, description, quantity, price, supplierID) values(?,?,?,?,?)";

	public DatabaseController() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/toolshop?user=root", "root", "root");
			// stmt = connection.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void addItem(Item item) {
		try {
			PreparedStatement addItemStatement = connection.prepareStatement(ADD_ITEM);
			addItemStatement.setInt(1, item.getId());
			addItemStatement.setString(2, item.getDescription());
			addItemStatement.setInt(3, item.getQuantity());
			addItemStatement.setDouble(4, item.getPrice());
			addItemStatement.setInt(5, item.getSupplier().getId());

			addItemStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void clearDatabase() {
		String query = "DELETE FROM item";
		try {
			PreparedStatement deleteStatement = connection.prepareStatement(query);
			deleteStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
