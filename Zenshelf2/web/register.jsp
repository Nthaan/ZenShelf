
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.User" %>
<%@ page import="dao.UserDAO" %>
<%@ page import="model.Admin" %>
<%@ page import="model.Member" %>
<%
    String message = "";
    String alertClass = "";
    if (request.getParameter("submit") != null) {
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

        UserDAO userDAO = new UserDAO();
        try {
            userDAO.save(newUser); // Save user to database
            message = "Registration Successful! Welcome, " + newUser.getUsername() + " (" + newUser.getRole() + ")";
            alertClass = "alert-success";
        } catch (Exception e) {
            message = "Error occurred during registration: " + e.getMessage();
            alertClass = "alert-danger";
        }
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Register</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>

<%@ include file="header.jsp" %>
<body>
    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-6">
                <h1 class="text-center mb-4">Register Page</h1>
                
                <% if (!message.isEmpty()) { %>
                <div class="alert <%= alertClass %>">
                    <%= message %>
                </div>
                <% } %>
                
                <form action="register.jsp" method="post" class="card shadow p-4">
                    <div class="mb-3">
                        <label for="username" class="form-label">Username</label>
                        <input type="text" id="username" name="username" class="form-control" required>
                    </div>
                    <div class="mb-3">
                        <label for="email" class="form-label">Email</label>
                        <input type="email" id="email" name="email" class="form-control" required>
                    </div>
                    <div class="mb-3">
                        <label for="password" class="form-label">Password</label>
                        <input type="password" id="password" name="password" class="form-control" required>
                    </div>
                    <div class="mb-3">
                        <label for="role" class="form-label">Role</label>
                        <select id="role" name="role" class="form-select">
                            <option value="admin">Admin</option>
                            <option value="normal">Normal User</option>
                        </select>
                    </div>
                    <div class="d-grid">
                        <button type="submit" name="submit" class="btn btn-primary">Register</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <!-- Bootstrap JS Bundle -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
