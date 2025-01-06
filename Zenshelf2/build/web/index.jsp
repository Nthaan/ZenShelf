<%-- 
    Document   : index
    Created on : Jan 5, 2025, 3:08:48?AM
    Author     : Nathania
--%>

<%@ include file="header.jsp" %>
<div class="container my-4">
    <div class="jumbotron text-center">
        <h1 class="display-4" style="font-family: Garamond; color: darkblue;">Welcome to Zenshelf</h1>
        <p class="lead">Unlock a world of stories and start your reading experience today</p>
        <img src="images/book.png" class="img-fluid" alt="Library" style="max-width: 500px; margin: 20px 0;">
        <p class="lead">ZenShelf is an innovative online library that allows you to dive into a world of books, tailored to your preferences. Whether you're at home or on the go, our platform brings reading to your fingertips with advanced features</p>
        <hr class="my-4">
        <p>Uncover a world of knowledge and imagination.</p>
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
<%@ include file="footer.jsp" %>
