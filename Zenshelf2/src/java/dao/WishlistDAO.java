/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author Nathania
 */
import model.Member;
import model.Book;
import config.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WishlistDAO {
    public void addToWishlist(Member member, Book book) throws SQLException {
        String sql = "INSERT INTO wishlist (user_id, book_id) VALUES (?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, member.getId());
            stmt.setInt(2, book.getId());
            stmt.executeUpdate();
        }
    }
    
    public List<Book> getWishlist(Member member) throws SQLException {
        List<Book> wishlist = new ArrayList<>();
        String sql = "SELECT b.* FROM books b " +
                    "JOIN wishlist w ON b.id = w.book_id " +
                    "WHERE w.user_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, member.getId());
            ResultSet rs = stmt.executeQuery();
            BookDAO bookDAO = new BookDAO();
            while (rs.next()) {
                wishlist.add(bookDAO.findById(rs.getInt("id")));
            }
        }
        return wishlist;
    }
    
    public void removeFromWishlist(Member member, Book book) throws SQLException {
        String sql = "DELETE FROM wishlist WHERE user_id = ? AND book_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, member.getId());
            stmt.setInt(2, book.getId());
            stmt.executeUpdate();
        }
    }
}
