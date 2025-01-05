/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author Nathania
 */
import model.User;
import model.Member;
import model.Admin;
import config.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements GenericDAO<User> {
    @Override
    public User findById(int id) throws SQLException {
        String sql = "SELECT * FROM users WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                if (rs.getString("role").equals("admin")) {
                    return new Admin(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("password")
                    );
                } else {
                    return new Member(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("password")
                    );
                }
            }
        }
        return null;
    }
    
    public User authenticate(String username, String password) throws SQLException {
    String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, username);
        stmt.setString(2, password); // In a real application, use password hashing
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            // Return the appropriate user type based on the role
            if (rs.getString("role").equals("admin")) {
                return new Admin(
                    rs.getInt("id"),
                    rs.getString("username"),
                    rs.getString("email"),
                    rs.getString("password")
                );
            } else {
                return new Member(
                    rs.getInt("id"),
                    rs.getString("username"),
                    rs.getString("email"),
                    rs.getString("password")
                );
            }
        }
    }
    return null; // Return null if no user is found
}
    
    @Override
    public void save(User user) throws SQLException {
        String sql = "INSERT INTO users (username, email, password, role) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            stmt.setString(4, user.getRole());
            stmt.executeUpdate();
            
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                user.setId(rs.getInt(1));
            }
        }
    }
    
    @Override
    public void delete(int id) throws SQLException {
    String sql = "DELETE FROM users WHERE id = ?";
    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, id);
        stmt.executeUpdate();
        }
    }
    
    @Override
    public void update(User user) throws SQLException {
        String sql = "UPDATE users SET username = ?, email = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getEmail());
            stmt.setInt(3, user.getId());
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("User  dengan ID " + user.getId() + " berhasil diperbarui.");
            } else {
                System.out.println("User  dengan ID " + user.getId() + " tidak ditemukan.");
            }
        }
    }
    
    @Override
public List<User> findAll() throws SQLException {
    List<User> users = new ArrayList<>();
    String sql = "SELECT * FROM users"; // Pastikan tabel dan kolom sesuai dengan database Anda

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            int id = rs.getInt("id");
            String username = rs.getString("username");
            String email = rs.getString("email");
            String password = rs.getString("password");
            String role = rs.getString("role");

            // Tentukan tipe user berdasarkan kolom "role"
            if ("admin".equalsIgnoreCase(role)) {
                users.add(new Admin(id, username, email, password));
            } else if ("member".equalsIgnoreCase(role)) {
                users.add(new Member(id, username, email, password));
            } else {
                System.err.println("Role tidak dikenal untuk user dengan ID: " + id);
            }
        }
    }
    return users;
}

    // Implementasi yg laen kek delete bla bla blaa
}