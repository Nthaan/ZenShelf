/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Nathania
 */
public class Book {
    private int id;
    private String title;
    private String author;
    private String isbn;
    private String category;
    private boolean available;
    private String description;
    private double averageRating;
    
    
    // Default Constructor
    public Book() {
        this.id = 0;
        this.title = "";
        this.author = "";
        this.isbn = "";
        this.category = "";
        this.available = true;
        this.averageRating = 0.0;
    }
    
    public Book(int id, String title, String author, String isbn, String category) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.category = category;
        this.available = true;
        this.averageRating = 0.0;
    }


    
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }
    
    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }
    
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    
    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public double getAverageRating() { return averageRating; }
    public void setAverageRating(double averageRating) { this.averageRating = averageRating; }
}
