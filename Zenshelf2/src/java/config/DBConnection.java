package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/zenshelf2"; // Update with your database name
    private static final String USER = "root"; // Update with your database username
    private static final String PASSWORD = ""; // Update with your database password

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Load MySQL JDBC Driver
            return DriverManager.getConnection(URL, USER, PASSWORD); // Establish connection
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL JDBC Driver not found", e); // Handle driver not found
        }
    }

   
    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close(); // Close the connection
            } catch (SQLException e) {
                e.printStackTrace(); // Print stack trace if an error occurs
            }
        }
    }

    public static boolean testConnection() {
        try (Connection conn = getConnection()) {
            return conn != null && !conn.isClosed(); // Check if connection is valid
        } catch (SQLException e) {
            e.printStackTrace(); // Print stack trace if an error occurs
            return false; // Return false if connection fails
        }
    }
}
