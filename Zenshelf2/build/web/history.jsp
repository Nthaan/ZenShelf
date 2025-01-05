<%-- 
    Document   : history
    Created on : Jan 5, 2025, 7:07:59 PM
    Author     : Nathania
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.History" %>
<%@ page import="model.Member" %>
<%@ page import="java.util.List" %>
<%@ page import="dao.HistoryDAO" %>
<%@ page import="dao.UserDAO" %>
<%@ page import="config.DBConnection" %>
<%
    // Check if the user is logged in
    Member member = (Member) session.getAttribute("loggedInUser ");
    if (member == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    // Fetch the user's borrowing history
    HistoryDAO historyDAO = new HistoryDAO();
    List<History> histories = null;
    try {
        histories = historyDAO.getMemberHistory(member);
    } catch (Exception e) {
        e.printStackTrace();
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Borrowing History</title>
    <link rel="stylesheet" href="styles.css"> <!-- Link to your CSS file -->
</head>
<body>
    <header>
        <h1>Borrowing History</h1>
        <nav>
            <ul>
                <li><a href="index.jsp">Home</a></li>
                <li><a href="profile.jsp">Profile</a></li>
                <li><a href="logout">Logout</a></li>
            </ul>
        </nav>
    </header>

    <main>
        <h2>Your Borrowing History</h2>
        <%
            if (histories != null && !histories.isEmpty()) {
        %>
            <table>
                <thead>
                    <tr>
                        <th>Book Title</th>
                        <th>Borrow Date</th>
                        <th>Status</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        for (History history : histories) {
                    %>
                        <tr>
                            <td><%= history.getBook().getTitle() %></td>
                            <td><%= history.getBorrowDate() %></td>
                            <td><%= history.getStatus() %></td>
                        </tr>
                    <%
                        }
                    %>
                </tbody>
            </table>
        <%
            } else {
        %>
            <p>You have no borrowing history.</p>
        <%
            }
        %>
    </main>

    <footer>
        <p>&copy; 2023 Zenshelf2. All rights reserved.</p>
    </footer>
</body>
</html>
