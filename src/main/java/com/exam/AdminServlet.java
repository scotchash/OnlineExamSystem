package com.exam;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation for admin functionality to add new questions to the exam system.
 */
@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * Handles POST requests to add new questions to the question bank.
     * @param request The HttpServletRequest object containing client request data
     * @param response The HttpServletResponse object for sending responses
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // Retrieve form parameters from the request
        String questionText = request.getParameter("question"); // The question text
        String[] options = new String[4]; // Array to store the four answer options
        
        // Get each option from the request parameters
        options[0] = request.getParameter("option1"); // First option
        options[1] = request.getParameter("option2"); // Second option
        options[2] = request.getParameter("option3"); // Third option
        options[3] = request.getParameter("option4"); // Fourth option
        
        // Get the index of the correct option (converted from String to int)
        int correctOption = Integer.parseInt(request.getParameter("correctOption"));

        // Create a new Question object with the collected data
        Question question = new Question(questionText, options, correctOption);
        
        // Add the new question to the QuestionBank
        QuestionBank.addQuestion(question);

        // Set success message as a request attribute
        request.setAttribute("message", "Question added successfully!");
        
        // Forward the request to the admin JSP page to display the result
        request.getRequestDispatcher("admin.jsp").forward(request, response);
    }
}