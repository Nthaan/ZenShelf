<%-- 
    Document   : wishlist
    Created on : Jan 5, 2025, 3:10:39?AM
    Author     : Nathania
--%>

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
