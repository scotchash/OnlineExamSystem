package com.exam;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

/**
 * Servlet implementation class ResultServlet
 * This servlet handles the display of exam results to the user.
 */
@WebServlet("/ResultServlet")
public class ResultServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * Handles the HTTP GET request to display exam results.
     * 
     * @param request  The HttpServletRequest object containing client request data
     * @param response The HttpServletResponse object for sending responses
     * @throws ServletException If a servlet-specific error occurs
     * @throws IOException If an I/O error occurs
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // Get the current session
        HttpSession session = request.getSession();
        
        // Retrieve the user's score from session attributes
        int score = (Integer) session.getAttribute("score");
        
        // Retrieve the list of questions from session attributes
        Object questionsObj = session.getAttribute("questions");
        
        // Validate that the questions object is actually a List
        if (!(questionsObj instanceof List)) {
            // If corrupted, set error message and redirect to login page
            request.setAttribute("error", "Session data corrupted. Please restart the exam.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        // Cast the questions object to a List of Question objects
        // Suppress warnings about unchecked cast (we validated with instanceof)
        @SuppressWarnings("unchecked")
        List<Question> questions = (List<Question>) questionsObj;
        
        // Calculate total number of questions
        int totalQuestions = questions.size();
        
        // Set attributes to be displayed in the result page
        request.setAttribute("score", score);
        request.setAttribute("totalQuestions", totalQuestions);
        
        // Forward to the result page for display
        request.getRequestDispatcher("result.jsp").forward(request, response);
    }
}