<%-- 
    Document   : profile
    Created on : Jan 5, 2025, 3:11:08?AM
    Author     : Nathania
--%>

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
