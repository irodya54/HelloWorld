package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public static final Connection CONNECTION;

    static {
        try {
            CONNECTION = Util.getConnection();
            CONNECTION.setAutoCommit(false);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {

        String newTable = "CREATE TABLE IF NOT EXISTS users ( Id INT UNSIGNED NOT NULL AUTO_INCREMENT, " +
                "name VARCHAR(20)  NOT NULL, lastName VARCHAR(20)  NOT NULL, age TINYINT NOT NULL, PRIMARY KEY (id));";
        try (Statement statement = CONNECTION.createStatement()) {
            statement.execute(newTable);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void dropUsersTable() {
        String dropTableUsers = "DROP TABLE IF EXISTS users";
        try (Statement statement = CONNECTION.createStatement()) {
            statement.execute(dropTableUsers);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement preparedStatement = CONNECTION.prepareStatement("INSERT INTO Users (Name, LastName, age) VALUES (?, ?, ?)")) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (PreparedStatement preparedStatement = CONNECTION.prepareStatement("DELETE FROM users WHERE id = ?")) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        List<User> allUsers = new ArrayList<>();
        String users = "SELECT * FROM users;";
        try (Statement statement = CONNECTION.createStatement()) {

            ResultSet res = statement.executeQuery(users);

            while (res.next()) {
                User user = new User(res.getString("name"), res.getString("lastName"), res.getByte("age"));
                user.setId(res.getLong("Id"));
                allUsers.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return allUsers;
    }

    public void cleanUsersTable() {
        String cleanTable = "TRUNCATE TABLE users";
        try (Statement statement = CONNECTION.createStatement()) {
            statement.execute(cleanTable);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
