/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import dao.UserDAO;
import dao.BookDAO;
import model.User;
import model.Admin;  
import model.Member;
import model.Book;

@WebServlet("/admin") 
public class AdminServlet extends HttpServlet {

    private UserDAO userDAO = new UserDAO(); 
    private BookDAO bookDAO = new BookDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        response.setContentType("text/html");
        response.getWriter().println("<h1>Admin Dashboard</h1>");
        
       
        response.getWriter().println("<h2>Daftar Pengguna</h2>");
        try {
            List<User> users = userDAO.findAll();
            response.getWriter().println("<ul>");
            for (User user : users) {
                response.getWriter().println("<li>ID: " + user.getId() + ", Username: " + user.getUsername() + ", Email: " + user.getEmail() + ", Role: " + user.getRole() + "</li>");
            }
            response.getWriter().println("</ul>");
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("<h3>Error occurred while fetching users.</h3>");
        }

        
        response.getWriter().println("<h2>Daftar Buku</h2>");
        try {
            List<Book> books = bookDAO.findAll();
            response.getWriter().println("<ul>");
            for (Book book : books) {
                response.getWriter().println("<li>ID: " + book.getId() + ", Title: " + book.getTitle() + ", Author: " + book.getAuthor() + "</li>");
            }
            response.getWriter().println("</ul>");
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("<h3>Error occurred while fetching books.</h3>");
        }
        
       
        response.getWriter().println("<h2>Tambah Pengguna Baru</h2>");
        response.getWriter().println("<form action='admin' method='post'>");
        response.getWriter().println("Username: <input type='text' name='username'><br>");
        response.getWriter().println("Email: <input type='text' name='email'><br>");
        response.getWriter().println("Password: <input type='password' name='password'><br>");
        response.getWriter().println("Role: <select name='role'><option value='admin'>Admin</option><option value='member'>Member</option></select><br>");
        response.getWriter().println("<input type='submit' value='Add User'>");
        response.getWriter().println("</form>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        try {
            User newUser;

          
            if ("admin".equals(role)) {
                newUser = new Admin(0, username, email, password);  
            } else {
                newUser = new Member(0, username, email, password);  
            }

            userDAO.save(newUser); 
            response.getWriter().println("<h1>User added successfully!</h1>");
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("<h1>Error occurred while adding user.</h1>");
        }
    }
}
