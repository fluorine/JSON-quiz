package com.github.fluorine.jsonquiz;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class QuizTools {
    private static Scanner sc = new Scanner(System.in);

    /**
     * This function converts a JSONArray into an array of strings.
     * @param ja An instance of JSONArray
     * @return An array of Strings.
     * @throws JSONException
     */
    public static String[] jsonToStringArray(JSONArray ja) throws JSONException {
	    String[] options = new String[ja.length()];
	    
	    for(int i = 0; i < ja.length(); i++) {
		options[i] = ja.getString(i);
	    }
	    
	    return options;
    }
    
    /*
     * This prompt asks for a character and returns an index
     * 0 for 'a', 1 for 'b', 2 for 'c'...
     * 
     * It's based on 'a' but it's not safe. This function
     * may return a negative number.
     */
    public static int getLetterOptionIndex() {
	System.out.print("       Provide a valid answer: ");
	String input = sc.nextLine();
	
	// Convert the letter to an index and return it.
	return (input.charAt(0) - 'a');
    }
    
    /**
     * This function interprets a Quiz on console.
     * It is like a lightweight platform for testing Quiz JSON files.
     * 
     * @param qz An instance of Quiz class
     * @throws JSONException
     */
    public static void run(Quiz qz) throws JSONException {
	QuizAnswers answers = new QuizAnswers(qz);
	
	// Print quiz's header
	System.out.println(qz.subject);
	System.out.println("-----------------------");
	System.out.println(qz.description + "\n");

	// Print questions and options
	for(int i = 0; i < qz.jsonQuestions.length(); i++) {
	    JSONObject item = qz.jsonQuestions.getJSONObject(i);
	    
	    // Print question
	    System.out.println("   " + (i + 1) + ". " + item.getString("question"));
	    
	    // Print options
	    //String answer = item.getString("answer");
	    String groupName = item.getString("options");
	    String[] options = qz.getOptions(groupName);
	    
	    char letter = 'a';
	    for(int k = 0; k < options.length; letter++, k++) {
		System.out.println("      " + letter + ") " + options[k]);
	    }
	    
	    // Get answer and check it
	    int selectedOptionIndex = QuizTools.getLetterOptionIndex();

	    // Evaluate answer and display result
	    if(answers.evaluateAnswer(i, selectedOptionIndex)) {
		System.out.println("       Right answer.\n");
	    } else {
		System.out.println("       Wrong answer.\n");
	    }
	    
	    System.out.println();
	}
	 
	System.out.println("       Grade: "
	   + answers.getTotalGoodAnswers() + " / " + answers.getTotalAnswers());
	System.out.println("[" + answers.toString() + "]");
    }
    
    /**
     * @param args an argument is a path to a JSON file.
     * @throws IOException 
     * @throws UnsupportedEncodingException 
     */
    public static void main(String[] args) throws IOException {
	if(args.length != 1) {
	    System.out.println("Use a valid path to a JSON quiz as parameter.");
	    return;
	}
	 // Read file
	 String text = new String(Files.readAllBytes(Paths.get(args[0])), StandardCharsets.UTF_8);
	 
	 // Load file
	try {
                JSONObject raw_quiz = new JSONObject(text);
                Quiz quiz = new Quiz(raw_quiz);
                QuizTools.run(quiz);
        } catch (JSONException e) {
                System.out.println("ERROR: Problem found in JSON file.");
                e.printStackTrace();
	}
    }
}
