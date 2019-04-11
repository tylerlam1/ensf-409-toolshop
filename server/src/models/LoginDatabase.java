package models;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import utils.*;

/**
 * LoginDatabase
 */
public class LoginDatabase implements DBCredentials {
    Connection connection;

    public LoginDatabase(Connection connection) {
        this.connection = connection;
    }

    public void readFromFile(String loginFileName) throws FileNotFoundException, IOException {

        File file = new File(loginFileName);
        BufferedReader br = new BufferedReader(new FileReader(file));

        String inputStr;
        while ((inputStr = br.readLine()) != null) {
            addLoginsFromText(inputStr);
        }

        br.close();
    }

    public void clearDatabase() {
        String query = "DELETE FROM logins";
        try {
            PreparedStatement deleteStatement = connection.prepareStatement(query);
            deleteStatement.execute();
            deleteStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addLoginsFromText(String textLine) {
        String[] dataValues = textLine.split(";");

        String id = dataValues[0];
        String password = dataValues[1];
        UserInformation newUser = new UserInformation();
        newUser.setId(id);
        newUser.setPassword(password);
        addUser(newUser);
    }

    public void addUser(UserInformation user) {
        try {
            PreparedStatement newUser = connection.prepareStatement(ADD_USER);
            newUser.setString(1, user.getId());
            newUser.setString(2, user.getPassword());
            newUser.execute();
            newUser.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean checkUser(UserInformation user) {
        try {
            PreparedStatement findUser = connection.prepareStatement(CHECK_USER);
            findUser.setString(1, user.getId());
            findUser.setString(2, user.getPassword());
            ResultSet rs = findUser.executeQuery();
            if (rs.next()) {
                return true;
            }
            findUser.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}