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


import dao.RatingDAO;
import model.Member;
import model.Book;
import model.Rating;
@WebServlet("/rate") 
public class RatingServlet extends HttpServlet {
    
    private RatingDAO ratingDAO = new RatingDAO(); 

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        response.setContentType("text/html");
        response.getWriter().println("<h1>Rate a Book</h1>");
        response.getWriter().println("<form action='rate' method='post'>");
        response.getWriter().println("Book ID: <input type='text' name='bookId'><br>");
        response.getWriter().println("Rating (1-5): <input type='number' name='rating' min='1' max='5'><br>");
        response.getWriter().println("Review: <textarea name='review'></textarea><br>");
        response.getWriter().println("<input type='submit' value='Submit Rating'>");
        response.getWriter().println("</form>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      
        HttpSession session = request.getSession();
        Member member = (Member) session.getAttribute("loggedInUser "); 

        int bookId = Integer.parseInt(request.getParameter("bookId")); 
        int ratingValue = Integer.parseInt(request.getParameter("rating")); 
        String review = request.getParameter("review");

        try {
            Book book = new Book(); 
            book.setId(bookId);
            Rating rating = new Rating(0, member, book, ratingValue); 
            rating.setReview(review); 

            ratingDAO.save(rating); 
            response.getWriter().println("<h1>Rating submitted successfully!</h1>");
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("<h1>Error occurred while submitting rating.</h1>");
        }
    }
}