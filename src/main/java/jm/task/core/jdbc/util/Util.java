package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.sql.*;

public class Util {

    private static final String HOST = "jdbc:mysql://localhost/foo1?serverTimezone=UTC&useSSL=false";
    private static final String USER = "tester";
    private static final String PASSWORD = "tester123";

    private static Connection connection;
    private static SessionFactory sessionFactory;

    public Util() {
    }

    public static Connection getConnection() {

        try {
            connection = DriverManager.getConnection(HOST, USER, PASSWORD);
        } catch (SQLException ex) {
            System.out.println("Connection failed...");
            ex.printStackTrace();
        }
        return connection;
    }

    public static SessionFactory getSessionFactory() {
            try {
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(User.class);
                StandardServiceRegistryBuilder builder =
                        new StandardServiceRegistryBuilder()
                                .applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());
            } catch (Exception ex) {
                System.out.println("Исключение при создании Session Factory!");
                ex.printStackTrace();
            }
        return sessionFactory;
    }
}
