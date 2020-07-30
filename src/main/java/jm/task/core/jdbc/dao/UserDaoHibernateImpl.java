package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private static SessionFactory sessionFactory;
    private static Connection myConnection;
    private final String tableName = "users2";

    public UserDaoHibernateImpl() {
        myConnection = Util.getConnection();
        sessionFactory = Util.getSessionFactory();
    }

    public void createUsersTable() {
        String queryCreateTable = "CREATE TABLE IF NOT EXISTS " + tableName +
                " (id BIGINT NOT NULL AUTO_INCREMENT, " +
                "name VARCHAR(50), " +
                "lastname VARCHAR(50), " +
                "age TINYINT," +
                "PRIMARY KEY(id) )";
        try {
            Statement statement = myConnection.createStatement();
            statement.execute(queryCreateTable);
            statement.close();
        } catch (SQLException ex) {
            System.out.println("Не удалось создать таблицу " + tableName);
            ex.printStackTrace();
        }
    }

    public void dropUsersTable() {

        String deleteQuery = "DROP TABLE IF EXISTS " + tableName;
        try {
            Statement statement = myConnection.createStatement();
            statement.execute(deleteQuery);
            statement.close();
        } catch (SQLException ex) {
            System.out.println("Не удалось удалить таблицу " + tableName);
            ex.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            User user = new User(name, lastName, age);
            session.saveOrUpdate(user);
            transaction.commit();
            System.out.println("User с именем " + name + " добавлен в базу данных");
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            session.delete(user);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Не удалось удалить из БД User с id " + id);
            ex.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        return (List<User>) sessionFactory.openSession()
                .createQuery("FROM User")
                .list();
    }

    public void cleanUsersTable() {
        String cleanQuery = "TRUNCATE TABLE " + tableName;
        try {
            Statement statement = myConnection.createStatement();
            statement.execute(cleanQuery);
            statement.close();
        } catch (SQLException ex) {
            System.out.println("Ошибка работы с таблицей " + tableName);
            ex.printStackTrace();
        }
    }
}
