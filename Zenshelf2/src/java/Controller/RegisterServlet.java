package Controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import model.Admin;
import model.Member;
import model.User;

@WebServlet("/register") 
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
        response.setContentType("text/html");
        response.getWriter().println("<h1>Register Page</h1>");
        response.getWriter().println("<form action='register' method='post'>");
        response.getWriter().println("Username: <input type='text' name='username'><br>");
        response.getWriter().println("Email: <input type='text' name='email'><br>");
        response.getWriter().println("Password: <input type='password' name='password'><br>");
        response.getWriter().println("Role: <select name='role'>");
        response.getWriter().println("<option value='admin'>Admin</option>");
        response.getWriter().println("<option value='normal'>Normal User</option>");
        response.getWriter().println("</select><br>");
        response.getWriter().println("<input type='submit' value='Register'>");
        response.getWriter().println("</form>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String role = request.getParameter("role"); 


        User newUser;
        if ("admin".equals(role)) {
            newUser = new Admin(0, username, email, password); 
        } else {
            newUser = new Member(0, username, email, password); 
        }


        // UserDAO.save(newUser); // Simpan ke database 


        response.getWriter().println("<h1>Registration Successful!</h1>");
        response.getWriter().println("<p>Welcome, " + newUser.getUsername() + " (" + newUser.getRole() + ")</p>");
    }
}
