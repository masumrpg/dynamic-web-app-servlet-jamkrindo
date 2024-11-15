<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
        $(document).ready(function() {
            $("form").submit(function(event) {
                event.preventDefault();
                let userId = $("#userId").val();
                let password = $("#password").val();
                if (userId === "" || password === "") {
                    alert("Please fill in all required fields.");
                    return;
                }
                this.submit();
            });
        });
    </script>
    <style>
        <%@ include file="/styling/index.css"%>
    </style>
</head>
<body>
<div class="login-card">
    <h1>Login</h1>
    <% if (request.getAttribute("errorMessage") != null) { %>
    <p class="error-message"><%= request.getAttribute("errorMessage") %></p>
    <% } %>
    <form action="login" method="post">
        <label for="userId">User ID or Username:</label>
        <input type="text" id="userId" name="userId" required>
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required>
        <input type="submit" value="Login">
    </form>
</div>
</body>
</html>