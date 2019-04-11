package models;

import java.sql.*;

import utils.*;

public class SupplierDatabase implements DBCredentials {
    Connection connection;

    public SupplierDatabase(Connection connection) {
        this.connection = connection;
    }

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