package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {

    private static final String HOST = "jdbc:mysql://localhost/foo1?serverTimezone=UTC&useSSL=false";
    private static final String USER = "tester";
    private static final String PASSWORD = "tester123";

    private Connection connection;

    public Util() {

        try {
            connection = DriverManager.getConnection(HOST, USER, PASSWORD);
         //   System.out.println("Connection is successful!");
//            connection.close();
        } catch (SQLException ex) {
            System.out.println("Connection failed...");
            ex.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

}
