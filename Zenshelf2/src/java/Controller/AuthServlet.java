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
import javax.servlet.http.HttpSession;
import java.io.IOException;

import dao.UserDAO;
import model.User;

@WebServlet("/auth") 
public class AuthServlet extends HttpServlet {
    
    private UserDAO userDAO = new UserDAO(); 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
    User user = userDAO.authenticate(username, password);
    if (user != null) {
        
    } else {
        response.getWriter().println("Authentication failed! Invalid credentials.");
        response.sendRedirect("login.jsp");
    }
} catch (Exception e) {
    e.printStackTrace(); 
    response.getWriter().println("An error occurred during authentication: " + e.getMessage());
}
    }
}