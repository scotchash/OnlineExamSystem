<%-- 
    JSP Page: admin.jsp
    Description: Admin interface for adding new questions to the exam system
    Author: [Your Name]
    Date: [Current Date]
--%>

<%-- Page Directive - Sets page configuration --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <%-- Meta tags for character encoding and responsive design --%>
    <meta charset="UTF-8">
    
    <%-- Page Title --%>
    <title>Admin - Online Exam System</title>
    
    <%-- Link to external CSS stylesheet --%>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <%-- Main container div --%>
    <div class="container">
        <%-- Main heading --%>
        <h1>Admin Panel</h1>
        
        <%-- Admin content box --%>
        <div class="admin-box">
            <%-- Section heading --%>
            <h2>Add New Question</h2>
            
            <%-- Display success message if present in request --%>
            <% if (request.getAttribute("message") != null) { %>
                <p class="success"><%= request.getAttribute("message") %></p>
            <% } %>
            
            <%-- 
                Form for adding new questions 
                Submits to AdminServlet via POST method
            --%>
            <form action="AdminServlet" method="post">
                <%-- Question text input --%>
                <input type="text" name="question" placeholder="Question" required>
                
                <%-- Answer options inputs --%>
                <input type="text" name="option1" placeholder="Option 1" required>
                <input type="text" name="option2" placeholder="Option 2" required>
                <input type="text" name="option3" placeholder="Option 3" required>
                <input type="text" name="option4" placeholder="Option 4" required>
                
                <%-- Dropdown for selecting correct answer --%>
                <select name="correctOption" required>
                    <option value="0">Option 1</option>
                    <option value="1">Option 2</option>
                    <option value="2">Option 3</option>
                    <option value="3">Option 4</option>
                </select>
                
                <%-- Submit button --%>
                <button type="submit">Add Question</button>
            </form>
            
            <%-- Navigation link back to login page --%>
            <a href="login.jsp">Back to Login</a>
        </div>
    </div>
</body>
</html>