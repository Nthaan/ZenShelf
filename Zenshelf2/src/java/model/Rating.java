/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Nathania
 */
public class Rating {
    private int id;
    private Member member;
    private Book book;
    private int rating;  // 1-5 stars
    private String review;
    
    public Rating(int id, Member member, Book book, int rating) {
        this.id = id;
        this.member = member;
        this.book = book;
        this.rating = rating;
    }
    
   
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public Member getMember() { return member; }
    public void setMember(Member member) { this.member = member; }
    
    public Book getBook() { return book; }
    public void setBook(Book book) { this.book = book; }
    
    public int getRating() { return rating; }
    public void setRating(int rating) { this.rating = rating; }
    
    public String getReview() { return review; }
    public void setReview(String review) { this.review = review; }
}

