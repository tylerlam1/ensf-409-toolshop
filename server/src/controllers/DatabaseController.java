package controllers;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import models.*;

import utils.*;

public class DatabaseController implements DBCredentials {
	private Connection connection;
	private ItemDatabase itemDatabase;
	private LoginDatabase loginDatabase;
	private SupplierDatabase supplierDatabase;

	public DatabaseController() throws FileNotFoundException, IOException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/toolshop", "root", "root");
			itemDatabase = new ItemDatabase(connection);
			loginDatabase = new LoginDatabase(connection);
			supplierDatabase = new SupplierDatabase(connection);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public ItemDatabase getItemDatabase() {
		return itemDatabase;
	}

	public LoginDatabase getLoginDatabase() {
		return loginDatabase;
	}

	public SupplierDatabase getSupplierDatabase() {
		return supplierDatabase;
	}

	public void close() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * The main part of the application that will begin server side of application
	 * 
	 * @param args the command line argument that will not be used
	 */
	public static void main(String[] args) {
		try {
			SupplierList suppliers = new SupplierList("suppliers.txt");
			ItemList items = new ItemList("items.txt", suppliers);
			DatabaseController databaseControl = new DatabaseController();
			databaseControl.getItemDatabase().setSuppliers(suppliers);
			databaseControl.getItemDatabase().setItems(items);
			databaseControl.getItemDatabase().readIntoDatabase();
			databaseControl.getSupplierDatabase().readSuppliersIntoDatabase(suppliers);
			databaseControl.getLoginDatabase().readFromFile("logins.txt");
			Server theServer = new Server(3000);
			theServer.communicateClient(databaseControl);
		} catch (FileNotFoundException e) {
			System.out.println("File(s) \"suppliers.txt\" and/or \"items.txt\" not found. Please try again.");
			System.exit(1);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
