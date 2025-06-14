<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Result - Online Exam System</title>
    <link rel="stylesheet" href="css/style.css">
    <style>
        body {
            color: red;
        }
        .container {
            width: 80%;
            margin: 0 auto;
            padding: 20px;
        }
        h1, h2 {
            color: red;
        }
        .result-box {
            border: 2px solid #ddd;
            border-radius: 5px;
            padding: 20px;
            margin-top: 20px;
            background-color: #f9f9f9;
        }
        .result-box.pass {
            border-color: #4CAF50;
        }
        .result-box.fail {
            border-color: #f44336;
        }
        a {
            color: red;
            text-decoration: none;
            font-weight: bold;
        }
        a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Exam Results</h1>
        <div class="result-box <%= ((Integer)request.getAttribute("score") * 100 / (Integer)request.getAttribute("totalQuestions") >= 50) ? "pass" : "fail" %>">
            <h2>Your Score: <%= request.getAttribute("score") %> / <%= request.getAttribute("totalQuestions") %></h2>
            <p>Percentage: <%= ((Integer)request.getAttribute("score") * 100 / (Integer)request.getAttribute("totalQuestions")) %>%</p>
            <p><%= ((Integer)request.getAttribute("score") * 100 / (Integer)request.getAttribute("totalQuestions") >= 50) ? "Congratulations, you passed!" : "Sorry, you failed. Try again!" %></p>
            <a href="login.jsp">Back to Login</a>
        </div>
    </div>
</body>
</html>