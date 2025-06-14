<%-- 
    exam.jsp - Online Exam Interface
    Displays exam questions with timer functionality
    Version: 1.0
--%>

<%-- Page Directives --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.exam.Question, java.util.List" %>

<!DOCTYPE html>
<html>
<head>
    <%-- Meta and Title --%>
    <meta charset="UTF-8">
    <title>Exam - Online Exam System</title>
    
    <%-- External CSS --%>
    <link rel="stylesheet" href="css/style.css">
    
    <%-- Embedded CSS for exam-specific styling --%>
    <style>
        /* Question box styling */
        .question-box {
            background-color: white;
            border: 2px solid #ddd;
            border-radius: 5px;
            padding: 20px;
            margin-bottom: 20px;
            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
        }
        
        /* Question text styling */
        .question-box h3 {
            color: red;
        }
        
        /* Options container */
        .options {
            margin: 20px 0;
        }
        
        /* Individual option styling */
        .option {
            display: block;
            margin-bottom: 15px;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 5px;
            background-color: #f9f9f9;
            transition: all 0.3s;
            color: red;
        }
        
        /* Option hover effect */
        .option:hover {
            background-color: #f0f0f0;
        }
        
        /* Radio button alignment */
        .option input[type="radio"] {
            margin-right: 10px;
        }
        
        /* Next button styling */
        .next-btn {
            background-color: #4CAF50;
            color: white;
            padding: 12px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
            transition: background-color 0.3s;
        }
        
        /* Next button hover state */
        .next-btn:hover {
            background-color: #45a049;
        }
        
        /* Timer bar container */
        .timer-bar {
            width: 100%;
            background-color: #f1f1f1;
            border-radius: 5px;
            margin: 20px 0;
        }
        
        /* Actual timer progress bar */
        #timer {
            height: 20px;
            background-color: #4CAF50;
            border-radius: 5px;
            width: 100%;
            transition: width 1s linear;
        }
        
        /* Question progress indicator */
        .progress {
            font-weight: bold;
            margin-bottom: 15px;
            color: red;
        }
        
        /* Time left display */
        #time {
            color: red;
            font-weight: bold;
        }
        
        /* Score display styling */
        .score-display {
            color: red;
            font-weight: bold;
            font-size: 18px;
            margin: 15px 0;
        }
    </style>
    
    <%-- JavaScript for timer functionality --%>
    <script>
        // Initialize timer with 120 seconds (2 minutes)
        let timeLeft = 120;
        
        // Timer function
        function startTimer() {
            let timer = setInterval(function() {
                timeLeft--;
                // Update visual timer bar
                document.getElementById("timer").style.width = (timeLeft / 120 * 100) + "%";
                // Update time display
                document.getElementById("time").innerText = timeLeft;
                
                // Submit form when time runs out
                if (timeLeft <= 0) {
                    clearInterval(timer);
                    document.forms[0].submit();
                }
            }, 1000); // Update every second
        }
        
        // Start timer when page loads
        window.onload = startTimer;
    </script>
</head>
<body>
    <%-- Main container --%>
    <div class="container">
        <h1>Online Exam</h1>
        
        <%-- Exam content box --%>
        <div class="exam-box">
            <%-- 
                JSP Scriptlet: 
                - Retrieves exam questions from session
                - Handles error cases
                - Displays current question
            --%>
            <%
                // Get questions list from session
                Object questionsObj = session.getAttribute("questions");
                
                // Validate questions object type
                if (!(questionsObj instanceof List)) {
                    out.println("<p class='error'>Error: Session data corrupted. Please restart the exam.</p>");
                    out.println("<a href='login.jsp'>Back to Login</a>");
                    return;
                }
                
                // Cast to List<Question> (suppress unchecked warning)
                @SuppressWarnings("unchecked")
                List<Question> questions = (List<Question>) questionsObj;
                
                // Get current question index
                Integer currentQuestion = (Integer) session.getAttribute("currentQuestion");
                
                // Validate questions and current question index
                if (questions == null || currentQuestion == null || questions.isEmpty()) {
                    out.println("<p class='error'>No questions available. Please restart the exam.</p>");
                    out.println("<a href='login.jsp'>Back to Login</a>");
                    return;
                }
                
                // Get current question object
                Question q = questions.get(currentQuestion);
                
                // Display score if available
                Integer score = (Integer) session.getAttribute("score");
                Integer totalQuestions = (Integer) session.getAttribute("totalQuestions");
                if (score != null && totalQuestions != null) {
                    out.println("<div class='score-display'>Your Score: " + score + " / " + totalQuestions + "</div>");
                }
            %>
            
            <%-- Exam form (submits to ExamServlet) --%>
            <form action="ExamServlet" method="post">
                <%-- Question display --%>
                <div class="question-box">
                    <h3><%= q.getQuestionText() %></h3>
                </div>
                
                <%-- Answer options --%>
                <div class="options">
                    <% for (int i = 0; i < q.getOptions().length; i++) { %>
                        <label class="option">
                            <input type="radio" name="answer" value="<%= i %>" id="option<%= i+1 %>" required>
                            <%= q.getOptions()[i] %>
                        </label>
                    <% } %>
                </div>
                
                <%-- Next question button --%>
                <button type="submit" class="next-btn">Next Question</button>
            </form>
            
            <%-- Question progress indicator --%>
            <div class="progress">
                Question <%= currentQuestion + 1 %> of <%= questions.size() %>
            </div>
            
            <%-- Visual timer bar --%>
            <div class="timer-bar">
                <div id="timer"></div>
            </div>
            
            <%-- Time left display --%>
            <p>Time Left: <span id="time">120</span> seconds</p>
        </div>
    </div>
</body>
</html>