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

import dao.WishlistDAO;
import model.Member;
import model.Book;

/**
 *
 * @author Nathania
 */
@WebServlet("/wishlist") 
public class WishlistServlet extends HttpServlet {
    
    private WishlistDAO wishlistDAO = new WishlistDAO(); 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        Member member = (Member) session.getAttribute("loggedInUser "); 
        int bookId = Integer.parseInt(request.getParameter("bookId")); 
        try {
            Book book = new Book(); 
            book.setId(bookId);
            wishlistDAO.addToWishlist(member, book);
            response.getWriter().println("<h1>Buku berhasil ditambahkan ke wishlist!</h1>");
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("<h1>Error occurred while adding to wishlist.</h1>");
        }
    }
}