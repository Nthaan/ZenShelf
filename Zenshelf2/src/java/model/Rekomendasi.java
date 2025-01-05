/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Nathania
 */
public class Rekomendasi {
    private int id;
    private Member member;
    private Book recommendedBook;
    private String reason;
    private double matchScore;
    
    public Rekomendasi(int id, Member member, Book recommendedBook) {
        this.id = id;
        this.member = member;
        this.recommendedBook = recommendedBook;
    }
    
   
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public Member getMember() { return member; }
    public void setMember(Member member) { this.member = member; }
    
    public Book getRecommendedBook() { return recommendedBook; }
    public void setRecommendedBook(Book recommendedBook) { this.recommendedBook = recommendedBook; }
    
    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }
    
    public double getMatchScore() { return matchScore; }
    public void setMatchScore(double matchScore) { this.matchScore = matchScore; }
}