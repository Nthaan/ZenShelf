<%-- 
    Document   : search
    Created on : Jan 5, 2025, 3:10:07?AM
    Author     : Nathania
--%>

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
