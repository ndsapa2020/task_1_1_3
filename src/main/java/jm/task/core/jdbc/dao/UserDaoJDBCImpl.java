package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private final Connection myConnection;
    private final String tableName = "users";

    public UserDaoJDBCImpl() {
        Util myConnectUtil = new Util();
        myConnection = myConnectUtil.getConnection();
    }

    public void createUsersTable() {
        String queryCreateTable = "CREATE TABLE IF NOT EXISTS " + tableName +
                " (id INT NOT NULL AUTO_INCREMENT, " +
                "name VARCHAR(50), " +
                "lastname VARCHAR(50), " +
                "age MEDIUMINT," +
                "PRIMARY KEY(id) )";

        try {
            Statement statement = myConnection.createStatement();
            statement.execute(queryCreateTable);
            System.out.println("Создана таблица " + tableName);
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
            System.out.println("Удалена таблица " + tableName);
            statement.close();
        } catch (SQLException ex) {
            System.out.println("Не удалось удалить таблицу " + tableName);
            ex.printStackTrace();
        }
    }

    public void saveUser(String aName, String aLastName, byte aAge) {

        String addUserQuery = "INSERT INTO " + tableName + "(name, lastname, age) values (?,?,?)";

        try {
            PreparedStatement preparedStatement = myConnection.prepareStatement(addUserQuery);
            preparedStatement.setString(1, aName);
            preparedStatement.setString(2, aLastName);
            preparedStatement.setInt(3, aAge);
            preparedStatement.executeUpdate();

            System.out.println("User с именем " + aName + " добавлен в базу данных");
            preparedStatement.close();
        } catch (SQLException ex) {
            System.out.println("Не удалось добавить в БД нового User " + aName);
            ex.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        String removeQuery = "DELETE FROM " + tableName +
                " WHERE id = " + id;
        try {
            Statement statement = myConnection.createStatement();
            statement.execute(removeQuery);
            System.out.println("User с id " + id + " удален из базы данных");
            statement.close();
        } catch (SQLException ex) {
            System.out.println("Не удалось удалить из БД User с id " + id);
            ex.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        String query = "SELECT * FROM " + tableName;
        List<User> usersList = new ArrayList<>();
        try {
            Statement statement = myConnection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastname"));
                user.setAge(resultSet.getByte("age"));

                System.out.println(user);
                usersList.add(user);
            }
            statement.close();
        } catch (SQLException ex) {
            System.out.println("Не удалось создать List <User> from " + tableName);
            ex.printStackTrace();
        }
        return usersList;
    }

    public void cleanUsersTable() {
        String cleanQuery = "TRUNCATE TABLE " + tableName;
        try {
            Statement statement = myConnection.createStatement();
            statement.execute(cleanQuery);
            System.out.println("Очищена таблица " + tableName);
            statement.close();
        } catch (SQLException ex) {
            System.out.println("Не удалось найти таблицу " + tableName);
            ex.printStackTrace();
        }
    }
}
