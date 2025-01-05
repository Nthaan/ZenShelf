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
import java.util.List;

import dao.HistoryDAO;
import model.Member;
import model.History;

@WebServlet("/history") 
public class HistoryServlet extends HttpServlet {
    
    private HistoryDAO historyDAO = new HistoryDAO(); 

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        Member member = (Member) session.getAttribute("loggedInUser "); 

        try {
            List<History> histories = historyDAO.getMemberHistory(member);
            response.setContentType("text/html");
            response.getWriter().println("<h1>Riwayat Peminjaman</h1>");
            response.getWriter().println("<ul>");
            for (History history : histories) {
                response.getWriter().println("<li>Buku: " + history.getBook().getTitle() + 
                                               ", Tanggal Pinjam: " + history.getBorrowDate() + 
                                               ", Status: " + history.getStatus() + "</li>");
            }
            response.getWriter().println("</ul>");
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("<h1>Error occurred while fetching history.</h1>");
        }
    }
}