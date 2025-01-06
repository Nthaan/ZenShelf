<%-- 
    Document   : footer
    Created on : Jan 5, 2025, 3:11:35 AM
    Author     : Nathania
--%>

<footer class="bg-dark text-white mt-5">
    <div class="container py-4">
        <div class="row">
            <div class="col-md-6">
                <h5>About Zenshelf</h5>
                <p>Your digital library for endless reading possibilities.</p>
            </div>
            <div class="col-md-3">
                <h5>Quick Links</h5>
                <ul class="list-unstyled">
                    <li><a href="index.jsp" class="text-white">Home</a></li>
                    <li><a href="search.jsp" class="text-white">Search</a></li>
                    <li><a href="#" class="text-white">Contact</a></li>
                </ul>
            </div>
            <div class="col-md-3">
                <h5>Follow Us</h5>
                <div class="social-links">
                    <a href="#" class="text-white me-2"><i class="bi bi-facebook"></i></a>
                    <a href="#" class="text-white me-2"><i class="bi bi-twitter"></i></a>
                    <a href="#" class="text-white"><i class="bi bi-instagram"></i></a>
                </div>
            </div>
        </div>
    </div>
</footer>

Subscribe to Pro

Zenshelf Complete JSP Frontend

<!-- header.jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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

<!-- index.jsp -->
<%@ include file="header.jsp" %>
<div class="container my-5">
    <div class="jumbotron text-center">
        <h1 class="display-4">Welcome to Zenshelf</h1>
        <p class="lead">Your Digital Library Experience</p>
        <hr class="my-4">
        <p>Browse through our collection of books and start your reading journey today.</p>
        <a class="btn btn-primary btn-lg" href="search.jsp" role="button">Browse Books</a>
    </div>

    <div class="row mt-5">
        <h2 class="text-center mb-4">Popular Books</h2>
        <c:forEach items="${popularBooks}" var="book">
            <div class="col-md-4 mb-4">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">${book.title}</h5>
                        <p class="card-text">By ${book.author}</p>
                        <p class="card-text">
                            <small class="text-muted">Rating: ${book.averageRating}/5</small>
                        </p>
                        <a href="book?id=${book.id}" class="btn btn-primary">View Details</a>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>

<!-- login.jsp -->
<%@ include file="header.jsp" %>
<div class="container my-5">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card">
                <div class="card-header">
                    <h4 class="mb-0">Login</h4>
                </div>
                <div class="card-body">
                    <c:if test="${not empty error}">
                        <div class="alert alert-danger" role="alert">
                            ${error}
                        </div>
                    </c:if>
                    <form action="auth" method="post">
                        <input type="hidden" name="action" value="login">
                        <div class="mb-3">
                            <label class="form-label">Username</label>
                            <input type="text" class="form-control" name="username" required>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Password</label>
                            <input type="password" class="form-control" name="password" required>
                        </div>
                        <button type="submit" class="btn btn-primary">Login</button>
                    </form>
                    <p class="mt-3">
                        Don't have an account? <a href="register.jsp">Register here</a>
                    </p>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- search.jsp -->
<%@ include file="header.jsp" %>
<div class="container my-5">
    <div class="row">
        <div class="col-md-3">
            <div class="card mb-4">
                <div class="card-header">
                    <h5 class="mb-0">Filters</h5>
                </div>
                <div class="card-body">
                    <form action="search" method="get">
                        <div class="mb-3">
                            <label class="form-label">Category</label>
                            <select class="form-select" name="category">
                                <option value="">All Categories</option>
                                <c:forEach items="${categories}" var="category">
                                    <option value="${category}">${category}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Rating</label>
                            <select class="form-select" name="rating">
                                <option value="">Any Rating</option>
                                <option value="4">4+ Stars</option>
                                <option value="3">3+ Stars</option>
                                <option value="2">2+ Stars</option>
                            </select>
                        </div>
                        <button type="submit" class="btn btn-primary">Apply Filters</button>
                    </form>
                </div>
            </div>
        </div>
        
        <div class="col-md-9">
            <div class="mb-4">
                <form class="d-flex" action="search" method="get">
                    <input class="form-control me-2" type="search" name="keyword" 
                           placeholder="Search books..." value="${param.keyword}">
                    <button class="btn btn-primary" type="submit">Search</button>
                </form>
            </div>
            
            <div class="row">
                <c:forEach items="${books}" var="book">
                    <div class="col-md-4 mb-4">
                        <div class="card h-100">
                            <div class="card-body">
                                <h5 class="card-title">${book.title}</h5>
                                <p class="card-text">By ${book.author}</p>
                                <p class="card-text">
                                    <small class="text-muted">Category: ${book.category}</small>
                                </p>
                                <div class="d-flex justify-content-between align-items-center">
                                    <a href="book?id=${book.id}" class="btn btn-primary">View</a>
                                    <c:if test="${sessionScope.user != null}">
                                        <button onclick="addToWishlist(${book.id})" 
                                                class="btn btn-outline-secondary">
                                            <i class="bi bi-bookmark-plus"></i>
                                        </button>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</div>

<!-- wishlist.jsp -->
<%@ include file="header.jsp" %>
<div class="container my-5">
    <h2 class="mb-4">My Wishlist</h2>
    <div class="row">
        <c:forEach items="${wishlist}" var="book">
            <div class="col-md-4 mb-4">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">${book.title}</h5>
                        <p class="card-text">By ${book.author}</p>
                        <div class="d-flex justify-content-between">
                            <a href="book?id=${book.id}" class="btn btn-primary">View</a>
                            <button onclick="removeFromWishlist(${book.id})" 
                                    class="btn btn-danger">Remove</button>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>

<!-- history.jsp -->
<%@ include file="header.jsp" %>
<div class="container my-5">
    <h2 class="mb-4">Borrowing History</h2>
    <div class="table-responsive">
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>Book Title</th>
                    <th>Author</th>
                    <th>Borrow Date</th>
                    <th>Return Date</th>
                    <th>Status</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${history}" var="record">
                    <tr>
                        <td>${record.book.title}</td>
                        <td>${record.book.author}</td>
                        <td>${record.borrowDate}</td>
                        <td>${record.returnDate}</td>
                        <td>
                            <span class="badge bg-${record.status == 'returned' ? 'success' : 
                                                   record.status == 'borrowed' ? 'primary' : 'warning'}">
                                ${record.status}
                            </span>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<!-- profile.jsp -->
<%@ include file="header.jsp" %>
<div class="container my-5">
    <div class="row">
        <div class="col-md-4">
            <div class="card">
                <div class="card-header">
                    <h5 class="mb-0">Profile Information</h5>
                </div>
                <div class="card-body">
                    <form action="profile" method="post">
                        <div class="mb-3">
                            <label class="form-label">Username</label>
                            <input type="text" class="form-control" value="${user.username}" readonly>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Email</label>
                            <input type="email" class="form-control" name="email" 
                                   value="${user.email}" required>
                        </div>
                        <button type="submit" class="btn btn-primary">Update Profile</button>
                    </form>
                </div>
            </div>
        </div>
        
        <div class="col-md-8">
            <div class="card">
                <div class="card-header">
                    <h5 class="mb-0">Reading Statistics</h5>
                </div>
                <div class="card-body">
                    <div class="row">
                        <div class="col-md-4 text-center">
                            <h3>${totalBooks}</h3>
                            <p>Books Read</p>
                        </div>
                        <div class="col-md-4 text-center">
                            <h3>${averageRating}</h3>
                            <p>Average Rating</p>
                        </div>
                        <div class="col-md-4 text-center">
                            <h3>${wishlistCount}</h3>
                            <p>Wishlist Items</p>
                        </div>
                    </div>
                </div>
            </div>
            
            <div class="card mt-4">
                <div class="card-header">
                    <h5 class="mb-0">Recent Activity</h5>
                </div>
                <div class="card-body">
                    <ul class="list-group">
                        <c:forEach items="${recentActivity}" var="activity">
                            <li class="list-group-item">
                                <div class="d-flex justify-content-between">
                                    <span>${activity.description}</span>
                                    <small class="text-muted">${activity.date}</small>
                                </div>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- footer.jsp -->
<footer class="bg-dark text-white mt-5">
    <div class="container py-4">
        <div class="row">
            <div class="col-md-6">
                <h5>About Zenshelf</h5>
                <p>Your digital library for endless reading possibilities.</p>
            </div>
            <div class="col-md-3">
                <h5>Quick Links</h5>
                <ul class="list-unstyled">
                    <li><a href="index.jsp" class="text-white">Home</a></li>
                    <li><a href="search.jsp" class="text-white">Search</a></li>
                    <li><a href="#" class="text-white">Contact</a></li>
                </ul>
            </div>
            <div class="col-md-3">
                <h5>Follow Us</h5>
                <div class="social-links">
                    <a href="#" class="text-white me-2"><i class="bi bi-facebook"></i></a>
                    <a href="#" class="text-white me-2"><i class="bi bi-twitter"></i></a>
                    <a href="#" class="text-white"><i class="bi bi-instagram"></i></a>
                </div>
            </div>
        </div>
    </div>
</footer>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1
