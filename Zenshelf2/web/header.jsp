<%-- 
    Document   : header
    Created on : Jan 5, 2025, 3:06:49 AM
    Author     : Nathania
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
    <title>Zenshelf - Online Library</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container">
            <a class="navbar-brand" href="index.jsp">Zenshelf</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav me-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="search.jsp">Search</a>
                    </li>
                    <c:if test="${sessionScope.user != null}">
                        <li class="nav-item">
                            <a class="nav-link" href="wishlist.jsp">Wishlist</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="history.jsp">History</a>
                        </li>
                    </c:if>
                </ul>
                <ul class="navbar-nav">
                    <c:choose>
                        <c:when test="${sessionScope.user != null}">
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" 
                                   data-bs-toggle="dropdown">
                                    ${sessionScope.user.username}
                                </a>
                                <ul class="dropdown-menu">
                                    <li><a class="dropdown-item" href="profile.jsp">Profile</a></li>
                                    <li><hr class="dropdown-divider"></li>
                                    <li><a class="dropdown-item" href="logout">Logout</a></li>
                                </ul>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li class="nav-item">
                                <a class="nav-link" href="login.jsp">Login</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="register.jsp">Register</a>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </ul>
            </div>
        </div>
    </nav>
</html>
