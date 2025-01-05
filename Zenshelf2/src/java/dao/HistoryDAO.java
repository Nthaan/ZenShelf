/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author Nathania
 */
import model.History;
import model.Member;
import model.Book;
import config.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HistoryDAO implements GenericDAO<History> {
    public void borrowBook(Member member, Book book) throws SQLException {
        String sql = "INSERT INTO history (user_id, book_id, borrow_date, status) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, member.getId());
            stmt.setInt(2, book.getId());
            stmt.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
            stmt.setString(4, "borrowed");
            stmt.executeUpdate();
            
            // Update book availability
            updateBookAvailability(book.getId(), false);
        }
    }
    
    public void returnBook(History history) throws SQLException {
        String sql = "UPDATE history SET return_date = ?, status = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
            stmt.setString(2, "returned");
            stmt.setInt(3, history.getId());
            stmt.executeUpdate();
            
            // Update book availability
            updateBookAvailability(history.getBook().getId(), true);
        }
    }
    
    private void updateBookAvailability(int bookId, boolean available) throws SQLException {
        String sql = "UPDATE books SET available = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setBoolean(1, available);
            stmt.setInt(2, bookId);
            stmt.executeUpdate();
        }
    }
    
    public List<History> getMemberHistory(Member member) throws SQLException {
        List<History> histories = new ArrayList<>();
        String sql = "SELECT * FROM history WHERE user_id = ? ORDER BY borrow_date DESC";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, member.getId());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                histories.add(extractHistoryFromResultSet(rs));
            }
        }
        return histories;
    }
    
    private History extractHistoryFromResultSet(ResultSet rs) throws SQLException {
        BookDAO bookDAO = new BookDAO();
        UserDAO userDAO = new UserDAO();
        
        Member member = (Member) userDAO.findById(rs.getInt("user_id"));
        Book book = bookDAO.findById(rs.getInt("book_id"));
        
        History history = new History(rs.getInt("id"), member, book);
        history.setBorrowDate(rs.getTimestamp("borrow_date"));
        if (rs.getTimestamp("return_date") != null) {
            history.setReturnDate(rs.getTimestamp("return_date"));
        }
        history.setStatus(rs.getString("status"));
        
        return history;
    }
    
    @Override
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM history WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
    
    public void update(History history) throws SQLException {
    String sql = "UPDATE history SET return_date = ?, status = ? WHERE id = ?";
    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setTimestamp(1, new Timestamp(System.currentTimeMillis())); // Set return date to current time
        stmt.setString(2, history.getStatus()); // Set status (e.g., "returned")
        stmt.setInt(3, history.getId()); // Set the ID of the history record to update
        int rowsAffected = stmt.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("History dengan ID " + history.getId() + " berhasil diperbarui.");
        } else {
            System.out.println("History dengan ID " + history.getId() + " tidak ditemukan.");
        }
        }
    }
    
    public void save(History history) throws SQLException {
    String sql = "INSERT INTO history (user_id, book_id, borrow_date, status) VALUES (?, ?, ?, ?)";
    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
        stmt.setInt(1, history.getMember().getId());
        stmt.setInt(2, history.getBook().getId());
        stmt.setTimestamp(3, new Timestamp(history.getBorrowDate().getTime())); // Set borrow date
        stmt.setString(4, history.getStatus()); // Set status (e.g., "borrowed")
        stmt.executeUpdate();
        
        // Mendapatkan ID yang dihasilkan
        ResultSet rs = stmt.getGeneratedKeys();
        if (rs.next()) {
            history.setId(rs.getInt(1)); // Set ID yang dihasilkan ke objek history
        }
    }
    }
    
    @Override
    public List<History> findAll() throws SQLException {
        List<History> histories = new ArrayList<>();
        String sql = "SELECT * FROM history ORDER BY borrow_date DESC"; // Mengambil semua entri sejarah

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                histories.add(extractHistoryFromResultSet(rs)); // Menambahkan setiap entri ke dalam daftar
            }
        }
        return histories; // Mengembalikan daftar entri sejarah
    }

    public History findById(int id) throws SQLException {
        String sql = "SELECT * FROM history WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return extractHistoryFromResultSet(rs); // Mengembalikan objek History jika ditemukan
            }
        }
        return null; // Mengembalikan null jika tidak ditemukan
    }
    
}
