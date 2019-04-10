package controllers;
import java.sql.*;
import utils.*;

public class DatabaseController {
	private Connection connection;
	private Statement stmt;
	private ResultSet rs;
	private String ADD_ITEM = "INSERT INTO item (id, description, quantity, price, supplierID) values(?,?,?,?,?)";
	private String DELETE_ITEM = "DELETE FROM item WHERE id=?";
	private String GET_ITEM_BY_DESCRIPTION = "SELECT * FROM item WHERE description=?";
	private String GET_ITEM_BY_ID = "SELECT * FROM item WHERE id=?";
	private String BUY_ITEM = "UPDATE item SET quantity=? WHERE id=?";
	private String CHECK_USER = "SELECT * FROM logins WHERE username=? and password=?";
	private String ADD_USER = "INSERT INTO logins (username, password) values(?,?)";

	public DatabaseController() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/toolshop?user=root", "root", "root");
			stmt = connection.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void addUser(UserInformation user) {
		try {
			PreparedStatement newUser = connection.prepareStatement(ADD_USER);
			newUser.setString(1, user.getId());
			newUser.setString(2, user.getPassword());
			newUser.execute();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean checkUser(UserInformation user) {
		try {
			PreparedStatement findUser = connection.prepareStatement(CHECK_USER);
			findUser.setString(1, user.getId());
			findUser.setString(2, user.getPassword());
			ResultSet rs = findUser.executeQuery();
			if(rs.next()) {
				return true;
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public int getIdByDescription(String description) {
		try {
			PreparedStatement searchByDescriptionStatement = connection.prepareStatement(GET_ITEM_BY_DESCRIPTION);
			searchByDescriptionStatement.setString(1, description);
			ResultSet rs = searchByDescriptionStatement.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return -1;
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
	
	public void deleteItem(Item item) {
		try {
		PreparedStatement deleteItemStatement = connection.prepareStatement(DELETE_ITEM);
		deleteItemStatement.setInt(1, item.getId());
		deleteItemStatement.execute();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int getItemById(int id) {
		try {
			PreparedStatement searchByIdStatement = connection.prepareStatement(GET_ITEM_BY_ID);
			searchByIdStatement.setInt(1, id);
			ResultSet rs = searchByIdStatement.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public void buyItem(Item itemToDecrease, int count) {
		if(itemToDecrease.getQuantity() < count) {
			deleteItem(itemToDecrease);
			return;
		}
		try {
			PreparedStatement buy = connection.prepareStatement(BUY_ITEM);
			int finalQuantity = itemToDecrease.getQuantity() -count;
			buy.setInt(1,finalQuantity);
			buy.setInt(2,itemToDecrease.getId());
			buy.execute();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
