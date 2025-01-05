<%-- 
    Document   : login
    Created on : Jan 5, 2025, 3:09:39?AM
    Author     : Nathania
--%>

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
