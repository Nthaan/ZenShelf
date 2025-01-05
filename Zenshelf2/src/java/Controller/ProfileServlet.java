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
import model.Member;

@WebServlet("/profile") 
public class ProfileServlet extends HttpServlet {
    
    private UserDAO userDAO = new UserDAO(); 

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        Member member = (Member) session.getAttribute("loggedInUser "); 

        if (member != null) {
            response.setContentType("text/html");
            response.getWriter().println("<h1>User Profile</h1>");
            response.getWriter().println("<p>Username: " + member.getUsername() + "</p>");
            response.getWriter().println("<p>Email: " + member.getEmail() + "</p>");
            response.getWriter().println("<form action='profile' method='post'>");
            response.getWriter().println("New Password: <input type='password' name='newPassword'><br>");
            response.getWriter().println("<input type='submit' value='Update Profile'>");
            response.getWriter().println("</form>");
        } else {
            response.getWriter().println("<h1>Please log in to view your profile.</h1>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        Member member = (Member) session.getAttribute("loggedInUser "); 
        if (member != null) {
            String newPassword = request.getParameter("newPassword");

            try {
              
                if (newPassword != null && !newPassword.isEmpty()) {
                    member.setPassword(newPassword); 
                    userDAO.update(member);
                    response.getWriter().println("<h1>Profile updated successfully!</h1>");
                } else {
                    response.getWriter().println("<h1>Password cannot be empty!</h1>");
                }
            } catch (Exception e) {
                e.printStackTrace();
                response.getWriter().println("<h1>Error occurred while updating profile.</h1>");
            }
        } else {
            response.getWriter().println("<h1>Please log in to update your profile.</h1>");
        }
    }
}