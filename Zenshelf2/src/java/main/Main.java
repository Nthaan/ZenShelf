/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import config.DBConnection;
import dao.UserDAO;
import model.User;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        
        if (DBConnection.testConnection()) {
            System.out.println("Koneksi ke database berhasil!");
        } else {
            System.out.println("Koneksi ke database gagal.");
            return; // Hentikan eksekusi jika koneksi gagal
        }

        
        UserDAO userDAO = new UserDAO();

        try {
            
            List<User> users = userDAO.findAll();
            System.out.println("Daftar Pengguna:");
            for (User  user : users) {
                System.out.println("ID: " + user.getId() + ", Username: " + user.getUsername() + ", Email: " + user.getEmail() + ", Role: " + user.getRole());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            
            DBConnection.closeConnection(null);
        }
    }
}
