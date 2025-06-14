<%-- 
    login.jsp - Login Page for Online Exam System
    Provides authentication interface for users to access the exam system
    Version: 1.0
--%>

<%-- Page Directive - Configures JSP page settings --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <%-- Meta tags for character encoding and responsive behavior --%>
    <meta charset="UTF-8">
    
    <%-- Page title displayed in browser tab --%>
    <title>Login - Online Exam System</title>
    
    <%-- External CSS stylesheets --%>
    <link rel="stylesheet" href="css/style.css"> <%-- Main application styles --%>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500&display=swap" rel="stylesheet"> <%-- Google Fonts --%>
</head>
<body>
    <%-- Main container for centering content --%>
    <div class="container">
        
        <%-- Login box container --%>
        <div class="login-box">
            <%-- Application title --%>
            <h1>Online Exam System</h1>
            
            <%-- Login section heading --%>
            <h2>Login</h2>
            
            <%-- Display error message if present in request --%>
            <% if (request.getAttribute("error") != null) { %>
                <p class="error"><%= request.getAttribute("error") %></p>
            <% } %>
            
            <%-- 
                Login Form 
                Submits to LoginServlet via POST method
                Prevents password exposure in URL
            --%>
            <form action="LoginServlet" method="post">
                <%-- Username input group --%>
                <div class="form-group">
                    <label for="username">Username:</label>
                    <input type="text" 
                           id="username" 
                           name="username" 
                           placeholder="Enter your username" 
                           required <%-- Client-side validation --%>
                           autocomplete="username"> <%-- Helps password managers --%>
                </div>
                
                <%-- Password input group --%>
                <div class="form-group">
                    <label for="password">Password:</label>
                    <input type="password" 
                           id="password" 
                           name="password" 
                           placeholder="Enter your password" 
                           required <%-- Client-side validation --%>
                           autocomplete="current-password"> <%-- Helps password managers --%>
                </div>
                
                <%-- Submit button --%>
                <button type="submit">Login</button>
            </form>
            
            <%-- Help note for users --%>
            <p class="note">Contact administrator if you don't have credentials</p>
        </div>
    </div>
</body>
</html>