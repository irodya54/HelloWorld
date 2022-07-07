package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public static final Connection CONNECTION = Util.getConnection();
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {

        String newTable = "CREATE TABLE IF NOT EXISTS users (\n" +
                "  Id INT UNSIGNED NOT NULL AUTO_INCREMENT,\n" +
                "  name VARCHAR(20)  NOT NULL,\n" +
                "  lastName VARCHAR(20)  NOT NULL,\n" +
                "  age TINYINT NOT NULL,\n" +
                "  PRIMARY KEY (id)\n" +
                ");";
        try (Statement statement = CONNECTION.createStatement()) {
            statement.execute(newTable);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void dropUsersTable() {
        String dropTableUsers = "DROP TABLE users";
        try (Statement statement = CONNECTION.createStatement()) {
            statement.execute(dropTableUsers);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String saveUser = "INSERT users (name, lastName, age) VALUES ('" + name + "', '" + lastName + "', " + age + ");";
        try (Statement statement = CONNECTION.createStatement()) {
            statement.execute(saveUser);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        String removeUserId = "DELETE FROM users WHERE id = " + id + ";";
        try (Statement statement = CONNECTION.createStatement()) {
            statement.execute(removeUserId);
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
        String cleanTable = "DELETE FROM users";
        try (Statement statement = CONNECTION.createStatement()) {
            statement.execute(cleanTable);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
