package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    public void createUsersTable() {
        UserDaoJDBCImpl udi = new UserDaoJDBCImpl();
        udi.createUsersTable();
    }

    public void dropUsersTable() {
        UserDaoJDBCImpl udi = new UserDaoJDBCImpl();
        udi.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        UserDaoJDBCImpl udi = new UserDaoJDBCImpl();
        udi.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        UserDaoJDBCImpl udi = new UserDaoJDBCImpl();
        udi.removeUserById(id);

    }

    public List<User> getAllUsers() {
        UserDaoJDBCImpl udi = new UserDaoJDBCImpl();
        return udi.getAllUsers();
    }

    public void cleanUsersTable() {
        UserDaoJDBCImpl udi = new UserDaoJDBCImpl();
        udi.cleanUsersTable();

    }
}
