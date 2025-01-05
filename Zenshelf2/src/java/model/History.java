/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

public class History {
    private int id;
    private Member member;
    private Book book;
    private Date borrowDate;
    private Date returnDate;
    private String status; // "borrowed", "returned", "overdue"
    
    public History(int id, Member member, Book book) {
        this.id = id;
        this.member = member;
        this.book = book;
        this.borrowDate = new Date();
        this.status = "borrowed";
    }
    
  
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public Member getMember() { return member; }
    public void setMember(Member member) { this.member = member; }
    
    public Book getBook() { return book; }
    public void setBook(Book book) { this.book = book; }
    
    public Date getBorrowDate() { return borrowDate; }
    public void setBorrowDate(Date borrowDate) { this.borrowDate = borrowDate; }
    
    public Date getReturnDate() { return returnDate; }
    public void setReturnDate(Date returnDate) { 
        this.returnDate = returnDate;
        this.status = "returned";
    }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}

