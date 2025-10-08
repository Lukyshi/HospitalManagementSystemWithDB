package util;

import java.io.FilterInputStream;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;

//import file
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class DBConnection {
    private static final String CONFIG_FILE = "src/config.properties";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Properties props = new Properties();
            props.load(new FileInputStream(CONFIG_FILE));

            String url = props.getProperty("db.url");
            String username = props.getProperty("db.username");
            String password = props.getProperty("db.password");

            connection = DriverManager.getConnection(url, username, password);

        }catch (IOException | SQLException e) {
            System.out.println("Database connection failed: " + e.getMessage());
        }
        return connection;
    }
}
