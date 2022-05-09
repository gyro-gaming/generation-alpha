package com.generation_alpha.puzzle;

import com.generation_alpha.client.JsonParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Puzzle {
    private List<Puzzle> puzzleList;
    private String question;
    private String choices;
    private String correctAnswer;
    private static Puzzle instance = new Puzzle();

    private Puzzle() {}

    public static Puzzle getInstance() {
        instance.setPuzzleList();
        return instance;
    }

    // getters and setters
    public void setPuzzleList() {
        this.puzzleList = loadPuzzleFromJson();
    }

    public List<Puzzle> getPuzzleList() {
        return puzzleList;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getQuestion() {
        return question;
    }

    public void setChoices(String choices) {
        this.choices = choices;
    }

    public String getChoices() {
        return choices;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }
    // end getters and setters

    /**
     * helper method to display question
     */
    public void askQuestion() {
        String question = getQuestion();
        String choices = getChoices();
        System.out.println(question + "\n" + choices + "\n");
        System.out.print(">");
    }

    /**
     * helper method to check is answer is correct
     * @param inputAnswer
     * @return boolean
     */
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

    /**
     * helper method to create List of Puzzle from json File
     * @return List<Puzzle>
     */
    public static List<Puzzle> loadPuzzleFromJson() {
        List<Puzzle> puzzleList= new ArrayList<>();

        Map<String, Object> map = JsonParser.parseJson("resources/JsonObjects/puzzle.json");
        List<Map<String, Object>> puzzles = (List)map.get("results");

        for (Map<String, Object> puzzleMap: puzzles) {
            Puzzle puzzle = new Puzzle();
            String ques = puzzleMap.get("question").toString();
            puzzle.setQuestion(ques);
            List choicesInList = (List<String>)puzzleMap.get("incorrect_answers");
            String choice = String.join("\n", choicesInList);
            puzzle.setChoices(choice);
            String correct = puzzleMap.get("correct_answer").toString();
            puzzle.setCorrectAnswer(correct);
            puzzleList.add(puzzle);
        }
        return puzzleList;
    }
}