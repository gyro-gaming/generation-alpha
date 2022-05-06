package com.generation_alpha.puzzle;

public class Puzzle {
    private String question;
    private String choices;
    private String correctAnswer;

    public Puzzle(String question, String choices, String correctAnswer) {
        this.question = question;
        this.choices = choices;
        this.correctAnswer = correctAnswer;
    }

    public void askQuestion() {
        String question = getQuestion();
        String choices = getChoices();
        System.out.println(question + "\n" + choices + "\n");
        System.out.print(">");
    }

    public boolean checkAnswer(String inputAnswer) {
        boolean isCorrect = false;
        if (this.getCorrectAnswer().equals(inputAnswer)) {
            System.out.println("\n------------");
            System.out.println(inputAnswer + " is Correct!");
            isCorrect = true;
        } else if(!(this.getCorrectAnswer().equals(inputAnswer))) {
            System.out.println("\n Wrong answer!");
        }
        return isCorrect;
    }

    public String getQuestion() {
        return question;
    }

    public String getChoices() {
        return choices;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }
}