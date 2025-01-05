/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import dao.UserDAO;
import model.User;
/**
 *
 * @author Nathania
 */
@WebServlet("/login") 
public class LoginServlet extends HttpServlet {
    
    private UserDAO userDAO = new UserDAO(); 

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        response.setContentType("text/html");
        response.getWriter().println("<h1>Login Page</h1>");
        response.getWriter().println("<form action='login' method='post'>");
        response.getWriter().println("Username: <input type='text' name='username'><br>");
        response.getWriter().println("Password: <input type='password' name='password'><br>");
        response.getWriter().println("<input type='submit' value='Login'>");
        response.getWriter().println("</form>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            
            User user = userDAO.authenticate(username, password);
            if (user != null) {
                response.getWriter().println("<h1>Login Successful! Welcome, " + user.getUsername() + "!</h1>");
            } else {
                response.getWriter().println("<h1>Login Failed! Invalid credentials.</h1>");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("<h1>Error occurred during login.</h1>");
        }
    }
}