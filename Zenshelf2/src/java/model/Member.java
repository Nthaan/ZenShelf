/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Nathania
 */
import java.util.ArrayList;
import java.util.List;

public class Member extends User {
    private List<Book> wishlist;
    private List<Rating> ratings;
    private List<History> borrowHistory;
    
    public Member(int id, String username, String email, String password) {
        super(id, username, email, password, "member");
        this.wishlist = new ArrayList<>();
        this.ratings = new ArrayList<>();
        this.borrowHistory = new ArrayList<>();
    }
    
    @Override
    public String getRole() {
        return "Normal User"; // Role untuk NormalUser selalu "Normal User"
    }
    
public void addToWishlist(Book book) {
        wishlist.add(book);
    }
    
    public void removeFromWishlist(Book book) {
        wishlist.remove(book);
    }
    
    public List<Book> getWishlist() {
        return wishlist;
    }
    
    
    public void addRating(Rating rating) {
        ratings.add(rating);
    }
    
    public List<Rating> getRatings() {
        return ratings;
    }
    
   
    public void addToHistory(History history) {
        borrowHistory.add(history);
    }
    
    public List<History> getBorrowHistory() {
        return borrowHistory;
    }
}