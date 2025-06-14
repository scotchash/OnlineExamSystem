package com.exam;

/**
 * Represents an exam question with its text, answer options, and correct answer index.
 * This class encapsulates all data related to a single multiple-choice question.
 */
public class Question {
    // Fields to store question data
    private String questionText;    // The text of the question
    private String[] options;      // Array of answer options (typically 4 options)
    private int correctOption;     // Index of the correct answer (0-based)

    /**
     * Constructs a new Question with the specified details.
     * 
     * @param questionText The text of the question
     * @param options Array of answer options
     * @param correctOption Index of the correct answer (0-based)
     * @throws IllegalArgumentException if options array is null or empty,
     *         or if correctOption is out of bounds
     */
    public Question(String questionText, String[] options, int correctOption) {
        // Validate parameters
        if (options == null || options.length == 0) {
            throw new IllegalArgumentException("Options array cannot be null or empty");
        }
        if (correctOption < 0 || correctOption >= options.length) {
            throw new IllegalArgumentException("Correct option index out of bounds");
        }

        this.questionText = questionText;
        this.options = options.clone();  // Defensive copy to prevent external modification
        this.correctOption = correctOption;
    }

    /**
     * Returns the question text.
     * @return The text of the question
     */
    public String getQuestionText() {
        return questionText;
    }

    /**
     * Returns a copy of the answer options array.
     * @return Array of answer options
     */
    public String[] getOptions() {
        return options.clone();  // Return a copy to maintain encapsulation
    }

    /**
     * Returns the index of the correct answer.
     * @return 0-based index of the correct option
     */
    public int getCorrectOption() {
        return correctOption;
    }

    /**
     * Returns a string representation of the question.
     * @return Formatted string showing question and options
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Question: ").append(questionText).append("\n");
        for (int i = 0; i < options.length; i++) {
            sb.append(i + 1).append(") ").append(options[i]).append("\n");
        }
        sb.append("Correct answer: ").append(correctOption + 1);
        return sb.toString();
    }
}