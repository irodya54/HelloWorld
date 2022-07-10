package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.SessionFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    private static SessionFactory sessionFactory;
    public static Connection getConnection() throws SQLException {

        return DriverManager.getConnection("jdbc:mysql://localhost:3306/newschema", "root", "Tuzikson2607DB");

    }
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();

                Properties properties = new Properties();
                properties.put(Environment.DRIVER,"com.mysql.cj.jdbc.Driver");
                properties.put(Environment.URL,"jdbc:mysql://localhost:3306/newschema");
                properties.put(Environment.USER,"root");
                properties.put(Environment.PASS,"Tuzikson2607DB");
                properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
                properties.put(Environment.SHOW_SQL, "true");
                properties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                properties.put(Environment.HBM2DDL_AUTO, "create-drop");

                configuration.setProperties(properties);
                configuration.addAnnotatedClass(User.class);

                StandardServiceRegistry service = new StandardServiceRegistryBuilder().
                        applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(service);

            } catch (Exception e) {
                System.out.println("Исключение!" + e);
            }
        }
        return sessionFactory;
    }
}
