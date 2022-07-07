package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Comparator;


public class Util {
    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/newschema", "root", "Tuzikson2607DB");
        } catch (SQLException e) {
            System.out.println("Соединение не установлено");
        }
        return connection;
    }
}
