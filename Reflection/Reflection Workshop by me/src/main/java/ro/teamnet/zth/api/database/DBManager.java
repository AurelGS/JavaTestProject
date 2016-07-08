package ro.teamnet.zth.api.database;

import java.sql.*;
import java.lang.*;

public class DBManager {

    final static String CONNECTION_STRING = "jdbc:oracle:thin:@" + DBProperties.IP + ":" + DBProperties.PORT;

    private DBManager() {
        throw new UnsupportedOperationException();
    }

    private static void registerDriver(){
        //System.out.println("-------- Oracle JDBC Connection Testing ------");

        try {

            Class.forName(DBProperties.DRIVER_CLASS);

        } catch (ClassNotFoundException e) {

            //System.out.println("Where is your Oracle JDBC Driver?");
            e.printStackTrace();
        }

        //System.out.println("Oracle JDBC Driver Registered!");
    }

    public static Connection getConnection(){
        Connection connection = null;
        registerDriver();
        try {

            connection = DriverManager.getConnection(CONNECTION_STRING, DBProperties.USER, DBProperties.PASS);

        } catch (SQLException e) {

            //System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
        }

        if (connection != null) {
            //System.out.println("You made it, take control your database now!");
            return connection;
        } else {
            //System.out.println("Failed to make connection!");
            return null;
        }
    }

    public static boolean checkConnection(Connection connection){
        boolean flag = false;
        Statement statement = null;
        String query = "SELECT 1 FROM DUAL";
        try {
            statement = connection.createStatement();
            flag = statement.execute(query);
        } catch (SQLException e ) {
            e.printStackTrace();
        }
        return flag;
    }

}
