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

import dao.BookDAO;
import model.Book;

@WebServlet("/search") 
public class SearchServlet extends HttpServlet {
    
    private BookDAO bookDAO = new BookDAO(); 

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
        response.setContentType("text/html");
        response.getWriter().println("<h1>Search Books</h1>");
        response.getWriter().println("<form action='search' method='post'>");
        response.getWriter().println("Keyword: <input type='text' name='keyword'><br>");
        response.getWriter().println("<input type='submit' value='Search'>");
        response.getWriter().println("</form>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
        String keyword = request.getParameter("keyword"); 

        try {
            List<Book> books = bookDAO.search(keyword); 
            response.setContentType("text/html");
            response.getWriter().println("<h1>Search Results</h1>");
            response.getWriter().println("<ul>");
            for (Book book : books) {
                response.getWriter().println("<li>" + book.getTitle() + " oleh " + book.getAuthor() + "</li>");
            }
            response.getWriter().println("</ul>");
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("<h1>Error occurred while searching for books.</h1>");
        }
    }
}