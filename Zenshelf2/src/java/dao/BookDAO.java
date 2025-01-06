/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author Nathania
 */
import model.Book;
import config.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAO implements GenericDAO<Book> {
    @Override
    public Book findById(int id) throws SQLException {
        String sql = "SELECT * FROM books WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return extractBookFromResultSet(rs);
            }
        }
        return null;
    }
    
    public List<Book> search(String keyword) throws SQLException {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books WHERE title LIKE ? OR author LIKE ? OR category LIKE ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            String searchPattern = "%" + keyword + "%";
            stmt.setString(1, searchPattern);
            stmt.setString(2, searchPattern);
            stmt.setString(3, searchPattern);
            
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                books.add(extractBookFromResultSet(rs));
            }
        }
        return books;
    }
    
    private Book extractBookFromResultSet(ResultSet rs) throws SQLException {
        Book book = new Book(
            rs.getInt("id"),
            rs.getString("title"),
            rs.getString("author"),
            rs.getString("isbn"),
            rs.getString("category")
        );
        book.setAvailable(rs.getBoolean("available"));
        book.setDescription(rs.getString("description"));
        book.setAverageRating(rs.getDouble("average_rating"));
        return book;
    }
    
    public void delete(int id) throws SQLException {
    String sql = "DELETE FROM books WHERE id = ?";
    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, id);
        stmt.executeUpdate();
        }
    }
    
    
    public void update(Book book) throws SQLException {
    String sql = "UPDATE books SET title = ?, author = ?, isbn = ?, category = ?, available = ?, description = ?, average_rating = ? WHERE id = ?";
    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, book.getTitle());
        stmt.setString(2, book.getAuthor());
        stmt.setString(3, book.getIsbn());
        stmt.setString(4, book.getCategory());
        stmt.setBoolean(5, book.isAvailable());
        stmt.setString(6, book.getDescription());
        stmt.setDouble(7, book.getAverageRating());
        stmt.setInt(8, book.getId());
        
        int rowsAffected = stmt.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Buku dengan ID " + book.getId() + " berhasil diperbarui.");
        } else {
            System.out.println("Buku dengan ID " + book.getId() + " tidak ditemukan.");
        }
        }
    }
    
    public void save(Book book) throws SQLException {
    String sql = "INSERT INTO books (title, author, isbn, category, available, description, average_rating) VALUES (?, ?, ?, ?, ?, ?, ?)";
    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
        stmt.setString(1, book.getTitle());
        stmt.setString(2, book.getAuthor());
        stmt.setString(3, book.getIsbn());
        stmt.setString(4, book.getCategory());
        stmt.setBoolean(5, book.isAvailable());
        stmt.setString(6, book.getDescription());
        stmt.setDouble(7, book.getAverageRating());
        

        stmt.executeUpdate();
        
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                book.setId(rs.getInt(1)); 
                System.out.println("Buku baru berhasil disimpan dengan ID: " + book.getId());
            }
        }
    }
    
    
    public List<Book> findAll() throws SQLException {
    List<Book> books = new ArrayList<>();
    String sql = "SELECT * FROM books";

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
          
            Book book = new Book(
                rs.getInt("id"),
                rs.getString("title"),
                rs.getString("author"),
                rs.getString("isbn"),
                rs.getString("category")
            );
            book.setAvailable(rs.getBoolean("available"));
            book.setDescription(rs.getString("description"));
            book.setAverageRating(rs.getDouble("average_rating"));
            
           
            books.add(book);
            }
        }
        return books;
    }
  
}
