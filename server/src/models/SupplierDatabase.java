package models;

import java.sql.*;

import utils.*;

/**
 * The SQL database for the supplier information.   
 * @author Navjot Brar, Tyler Lam, Jofred Cayabyab
 * @since April 11,2019
 * @version 1.0
 */
public class SupplierDatabase implements DBCredentials {
    /**
     * Connection to the SQL database
     */
    Connection connection;

    /**
     * Constructor for the supplier database
     * @param connection the connection to be established
     */
    public SupplierDatabase(Connection connection) {
        this.connection = connection;
    }

    /**
     * Clears the entire database
     */
    public void clearDatabase() {
        String query = "DELETE FROM suppliers";
        try {
            PreparedStatement deleteStatement = connection.prepareStatement(query);
            deleteStatement.execute();
            deleteStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds the supplier information into the supplier database with the information from the supplier text file
     * @param suppliers the list of suppliers
     */
    public void readSuppliersIntoDatabase(SupplierList suppliers) {
        PreparedStatement addSuppliers = null;
        clearDatabase();
        try {
            for (Supplier a : suppliers.getList()) {
                addSuppliers = connection.prepareStatement(ADD_SUPPLIER);
                addSuppliers.setInt(1, a.getId());
                addSuppliers.setString(2, a.getName());
                addSuppliers.setString(3, a.getAddress());
                addSuppliers.setString(4, a.getContact());
                addSuppliers.execute();
            }
            addSuppliers.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}