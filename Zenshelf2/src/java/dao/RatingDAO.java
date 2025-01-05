/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author Nathania
 */
import model.Rating;
import model.Member;
import model.Book;
import config.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RatingDAO implements GenericDAO<Rating> {
    public void addRating(Rating rating) throws SQLException {
        String sql = "INSERT INTO ratings (user_id, book_id, rating, review) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, rating.getMember().getId());
            stmt.setInt(2, rating.getBook().getId());
            stmt.setInt(3, rating.getRating());
            stmt.setString(4, rating.getReview());
            stmt.executeUpdate();
            
            // Update average rating
            updateBookAverageRating(rating.getBook().getId());
        }
    }
    
    private void updateBookAverageRating(int bookId) throws SQLException {
        String sql = "UPDATE books SET average_rating = " +
                    "(SELECT AVG(rating) FROM ratings WHERE book_id = ?) " +
                    "WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, bookId);
            stmt.setInt(2, bookId);
            stmt.executeUpdate();
        }
    }
    
    @Override
    public void delete(int id) throws SQLException {
    String sql = "DELETE FROM ratings WHERE id = ?";
    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, id);
        stmt.executeUpdate();
        }
    }
    
    
    @Override
    public void update(Rating rating) throws SQLException {
    String sql = "UPDATE ratings SET rating = ?, review = ? WHERE id = ?";
    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, rating.getRating());
        stmt.setString(2, rating.getReview());
        stmt.setInt(3, rating.getId());
        int rowsAffected = stmt.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Rating dengan ID " + rating.getId() + " berhasil diperbarui.");
        } else {
            System.out.println("Rating dengan ID " + rating.getId() + " tidak ditemukan.");
        }
    }
}
    
    public void save(Rating rating) throws SQLException {
    String sql = "INSERT INTO ratings (user_id, book_id, rating, review) VALUES (?, ?, ?, ?)";
    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
        stmt.setInt(1, rating.getMember().getId());
        stmt.setInt(2, rating.getBook().getId());
        stmt.setInt(3, rating.getRating());
        stmt.setString(4, rating.getReview());
        stmt.executeUpdate();
        
        // Mendapatkan ID yang dihasilkan
        ResultSet rs = stmt.getGeneratedKeys();
        if (rs.next()) {
            rating.setId(rs.getInt(1)); 
        }
    }
}
    
 @Override
public List<Rating> findAll() throws SQLException {
    List<Rating> ratings = new ArrayList<>();
    String sql = "SELECT * FROM ratings";
    
    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {
        
        while (rs.next()) {
            Rating rating = extractRatingFromResultSet(rs);
            ratings.add(rating);
        }
    }
    return ratings;
}

private Rating extractRatingFromResultSet(ResultSet rs) throws SQLException {
    int id = rs.getInt("id");
    int userId = rs.getInt("user_id");
    int bookId = rs.getInt("book_id");
    int ratingValue = rs.getInt("rating");
    String review = rs.getString("review");
    
    // Mengambil objek Member dan Book berdasarkan ID
    UserDAO userDAO = new UserDAO();
    BookDAO bookDAO = new BookDAO();
    
    Member member = (Member) userDAO.findById(userId);
    Book book = bookDAO.findById(bookId);
    
    Rating rating = new Rating(id, member, book, ratingValue);
    rating.setReview(review);
    
    return rating;
}  

@Override
public Rating findById(int id) throws SQLException {
    String sql = "SELECT * FROM ratings WHERE id = ?";
    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        
        if (rs.next()) {
            return extractRatingFromResultSet(rs);
        }
    }
    return null; // Mengembalikan null jika tidak ditemukan
}


}
    
    
    
    // Implementasi yg laen kek delete bla bla blaa
