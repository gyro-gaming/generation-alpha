package com.generation_alpha.puzzle;

import com.generation_alpha.locations.JsonParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoadPuzzleFromJson {

    public static List<Puzzle> loadPuzzleFromJson() {
        List<Puzzle> puzzleList= new ArrayList<>();

        Map<String, Object> map = JsonParser.parseJson("JsonObjects/puzzle.json");
        List<Map<String, Object>> puzzles = (List)map.get("results");

        for (Map<String, Object> puzzleMap: puzzles) {
            String question = puzzleMap.get("question").toString();
            List choicesInList = (List<String>)puzzleMap.get("incorrect_answers");
            // Convert List to String
            String choices = String.join("\n", choicesInList);
            String correctAnswer = puzzleMap.get("correct_answer").toString();
            Puzzle puzzle = new Puzzle(question, choices,correctAnswer);
            puzzleList.add(puzzle);
        }
        return puzzleList;
    }
}