package com.exam;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation for handling user login functionality.
 * Authenticates users and redirects them to appropriate pages based on their role.
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * Handles POST requests for user authentication.
     * @param request The HttpServletRequest object containing login credentials
     * @param response The HttpServletResponse object for sending responses
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Retrieve username and password from login form
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        // Get or create a session for the user
        HttpSession session = request.getSession();

        // Authenticate user and redirect based on role
        if (isValidAdmin(username, password)) {
            // Admin authentication successful
            session.setAttribute("userType", "admin");  // Store user type in session
            response.sendRedirect("admin.jsp");         // Redirect to admin page
        } else if (isValidStudent(username, password)) {
            // Student authentication successful
            session.setAttribute("userType", "student"); // Store user type in session
            System.out.println("Redirecting student to ExamServlet");
            response.sendRedirect("ExamServlet");       // Redirect to exam page
        } else {
            // Authentication failed
            request.setAttribute("error", "Invalid username or password");
            request.getRequestDispatcher("login.jsp").forward(request, response); // Return to login page with error
        }
    }

    /**
     * Validates admin credentials (placeholder implementation).
     * In a real application, this would check against a database.
     * @param username The username to validate
     * @param password The password to validate
     * @return true if credentials are valid, false otherwise
     */
    private boolean isValidAdmin(String username, String password) {
        // TODO: Replace with actual database validation
        return "admin".equals(username) && "admin123".equals(password);
    }

    /**
     * Validates student credentials (placeholder implementation).
     * In a real application, this would check against a database.
     * @param username The username to validate
     * @param password The password to validate
     * @return true if credentials are valid, false otherwise
     */
    private boolean isValidStudent(String username, String password) {
        // TODO: Replace with actual database validation
        return "student".equals(username) && "student123".equals(password);
    }
}