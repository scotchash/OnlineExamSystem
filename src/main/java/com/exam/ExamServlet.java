package com.exam;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation for handling exam functionality.
 * Manages the exam flow including question retrieval, answer processing, and score tracking.
 */
@WebServlet("/ExamServlet")
public class ExamServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * Handles GET requests to initialize the exam session.
     * Sets up questions, initial score, and current question index.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Get or create session
        HttpSession session = request.getSession();
        System.out.println("Initializing exam session in ExamServlet");
        
        // Get random questions from QuestionBank (converted to mutable ArrayList)
        List<Question> questions = new ArrayList<>(QuestionBank.getRandomQuestions(3));
        
        // Fallback mechanism if QuestionBank is empty
        if (questions.isEmpty()) {
            // Add default questions to our mutable list
            questions.add(new Question("What is 2 + 2?", new String[]{"3", "4", "5", "6"}, 1));
            questions.add(new Question("What is the capital of France?", 
                new String[]{"London", "Berlin", "Paris", "Madrid"}, 2));
            System.out.println("Using fallback questions as QuestionBank is empty");
        }
        
        // Set session attributes for exam state management
        session.setAttribute("questions", questions);
        session.setAttribute("currentQuestion", 0);  // Start with first question
        session.setAttribute("score", 0);            // Initialize score to 0
        System.out.println("Session attributes set: questions.size=" + questions.size() + 
            ", currentQuestion=0, score=0");
        
        // Forward to exam JSP page
        request.getRequestDispatcher("exam.jsp").forward(request, response);
    }

    /**
     * Handles POST requests to process user answers and manage exam progression.
     * Updates score and moves to next question or redirects to results when complete.
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Retrieve existing session
        HttpSession session = request.getSession();
        Object questionsObj = session.getAttribute("questions");
        System.out.println("Processing post request, questionsObj=" + questionsObj);
        
        // Validate session data
        if (!(questionsObj instanceof List)) {
            request.setAttribute("error", "Session data corrupted. Please restart the exam.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        // Cast session attributes to their proper types
        @SuppressWarnings("unchecked")
        List<Question> questions = (List<Question>) questionsObj;
        int currentQuestion = (Integer) session.getAttribute("currentQuestion");
        int score = (Integer) session.getAttribute("score");

        // Process user answer if provided
        String answer = request.getParameter("answer");
        if (answer != null && Integer.parseInt(answer) == questions.get(currentQuestion).getCorrectOption()) {
            score++;  // Increment score if answer is correct
            session.setAttribute("score", score);
        }

        // Move to next question or finish exam
        currentQuestion++;
        if (currentQuestion < questions.size()) {
            // More questions remain - update current question and continue
            session.setAttribute("currentQuestion", currentQuestion);
            request.getRequestDispatcher("exam.jsp").forward(request, response);
        } else {
            // Exam completed - redirect to results page
            response.sendRedirect("ResultServlet");
        }
    }
}