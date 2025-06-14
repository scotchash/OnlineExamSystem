// Package declaration
package com.exam;

// Importing necessary classes
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Class that acts as a repository for all questions
public class QuestionBank {

    // A thread-safe list to store questions
    private static final List<Question> questions = Collections.synchronizedList(new ArrayList<>());

    // Method to add a new question to the question bank
    public static synchronized void addQuestion(Question question) {
        if (question != null) {
            questions.add(question); // Add the question to the list
            System.out.println("Question added: " + question.getQuestionText() + 
                ", Size: " + questions.size()); // Log the added question and new size
        }
    }

    // Method to fetch a random set of questions
    public static List<Question> getRandomQuestions(int count) {
        System.out.println("Fetching random questions, current size: " + questions.size());

        // Synchronizing on the questions list to ensure thread safety
        synchronized (questions) {
            if (questions.isEmpty()) {
                System.out.println("No questions available in QuestionBank.");
                return new ArrayList<>(); // Return an empty mutable list
            }
            
            // Create a copy of the original list to avoid modifying it
            List<Question> questionsCopy = new ArrayList<>(questions);
            
            // Shuffle the copied list to randomize the order
            Collections.shuffle(questionsCopy);
            
            // Return a sublist containing the requested number of questions (or fewer if not enough)
            return new ArrayList<>(questionsCopy.subList(0, Math.min(count, questionsCopy.size())));
        }
    }
}
