package models;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import utils.*;

/**
 * Database that allows interaction between the MySQL database and the Java
 * program. works with queries
 * 
 * @author Navjot Brar, Jofred Cayabyab and Tyler Lam
 * @version 1.0.0
 * @since March 31, 2019
 */
public class LoginDatabase implements DBCredentials {

    /**
     * holds the connection to the mySQL database
     */
    Connection connection;

    /**
     * constructs the login database
     * 
     * @param connection the controller connection used for database as well
     */
    public LoginDatabase(Connection connection) {
        this.connection = connection;
    }

    /**
     * reads login information from the text filed
     * 
     * @param loginFileName the name of the text file
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void readFromFile(String loginFileName) throws FileNotFoundException, IOException {

        File file = new File(loginFileName);
        BufferedReader br = new BufferedReader(new FileReader(file));

        String inputStr;
        clearDatabase();
        while ((inputStr = br.readLine()) != null) {
            addLoginsFromText(inputStr);
        }

        br.close();
    }

    /**
     * clears the database of all the information
     */
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

    /**
     * takes the login information available and writes it directly into the
     * database
     * 
     * @param textLine the string of text to be written into database
     */
    public void addLoginsFromText(String textLine) {
        String[] dataValues = textLine.split(";");

        String id = dataValues[0];
        String password = dataValues[1];
        boolean isOwner = false;
        if (dataValues[2].equals("true")) {
            isOwner = true;
        }
        UserInformation newUser = new UserInformation();
        newUser.setId(id);
        newUser.setPassword(password);
        addUser(newUser, isOwner);
    }

    /**
     * adds a new user into the list of users available
     * 
     * @param user    the new user
     * @param isOwner determines whether the user is a owner or not
     */
    public void addUser(UserInformation user, boolean isOwner) {
        try {
            PreparedStatement newUser = connection.prepareStatement(ADD_USER);
            newUser.setString(1, user.getId());
            newUser.setString(2, user.getPassword());
            newUser.setBoolean(3, isOwner);
            newUser.execute();
            newUser.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * validates the user's login and checks if they are a owner or not
     * 
     * @param user the user information
     */
    public boolean[] checkUser(UserInformation user) {
        boolean[] retVal = new boolean[2];
        try {
            PreparedStatement findUser = connection.prepareStatement(CHECK_USER);
            findUser.setString(1, user.getId());
            findUser.setString(2, user.getPassword());
            ResultSet rs = findUser.executeQuery();
            if (rs.next()) {

                retVal[0] = true;
                retVal[1] = rs.getBoolean("isOwner");
            }
            findUser.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return retVal;
    }
}