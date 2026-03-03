<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login – Ocean View Resort</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body class="login-body">
<div class="login-container">
    <div class="login-header">
        <div class="logo"></div>
        <h1>Ocean View Resort</h1>
        <p>Reservation Management System</p>
    </div>

    <% if (request.getAttribute("error") != null) { %>
    <div class="alert alert-error"> <%= request.getAttribute("error") %></div>
    <% } %>

    <form action="${pageContext.request.contextPath}/login" method="post">
        <div class="form-group">
            <label for="username">Username</label>
            <input type="text" id="username" name="username" placeholder="Enter your username"
                   value="<%= request.getAttribute("lastUsername") != null ? request.getAttribute("lastUsername") : "" %>"
                   required autocomplete="username">
        </div>
        <div class="form-group">
            <label for="password">Password</label>
            <input type="password" id="password" name="password" placeholder="Enter your password" required>
        </div>
        <button type="submit" class="btn btn-primary btn-full"> Sign In</button>
    </form>

    <div class="login-footer">
        <p> Ocean View Resort </p>

    </div>
</div>
</body>
</html>
