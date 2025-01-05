package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * DBConnection class to manage database connections.
 */
public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/user_management_db"; // Update with your database name
    private static final String USER = "root"; // Update with your database username
    private static final String PASSWORD = ""; // Update with your database password

    /**
     * Get a connection to the database.
     * 
     * @return Connection object
     * @throws SQLException if a database access error occurs
     */
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Load MySQL JDBC Driver
            return DriverManager.getConnection(URL, USER, PASSWORD); // Establish connection
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL JDBC Driver not found", e); // Handle driver not found
        }
    }

    /**
     * Close the database connection.
     * 
     * @param connection the Connection object to close
     */
    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close(); // Close the connection
            } catch (SQLException e) {
                e.printStackTrace(); // Print stack trace if an error occurs
            }
        }
    }

    /**
     * Test the database connection.
     * 
     * @return true if the connection is successful, false otherwise
     */
    public static boolean testConnection() {
        try (Connection conn = getConnection()) {
            return conn != null && !conn.isClosed(); // Check if connection is valid
        } catch (SQLException e) {
            e.printStackTrace(); // Print stack trace if an error occurs
            return false; // Return false if connection fails
        }
    }
}