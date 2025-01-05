/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Nathania
 */
public class Admin extends User {
    public Admin(int id, String username, String email, String password) {
        super(id, username, email, password, "admin");
    }
    
      @Override
    public String getRole() {
        return "Admin"; // Role untuk AdminUser selalu "Admin"
    }
    
    // Admin specific gatau mau nulis apa lg
}
