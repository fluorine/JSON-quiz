package com.github.fluorine.jsonquiz;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * This class works as a container for the answers
 * to a Quiz. Unanswered questions are assumed to be 
 * 
 * @author fluorine@github.com
 */
public class QuizAnswers {
    private Boolean answers[];
    
    /**
     * Pointer to Quiz
     */
    public final Quiz quiz;
    
    /**
     * Total number of answers
     */
    public final int itemsQuantity;
    
    /**
     * Constructor to load quiz to answer.
     * @param q Instance of a Quiz
     */
    public QuizAnswers(Quiz q) {
	quiz = q;
	answers = new Boolean[q.jsonQuestions.length()];
	itemsQuantity = q.jsonQuestions.length();
	
	// Initialize answers as false
	for(int i = 0; i < answers.length; i++) {
	    answers[i] = false;
	}
    }
    
    public boolean evaluateAnswer(int questionIndex, int selectedOptionIndex) throws JSONException {
	// Cargar y corregir el item
	JSONObject item = quiz.jsonQuestions.getJSONObject(questionIndex);
	String answer = item.getString("answer");
	String groupName = item.getString("options");
	String[] options = quiz.getOptions(groupName);
	
	if(selectedOptionIndex >= 0
		&& selectedOptionIndex < options.length
		&& options[selectedOptionIndex].trim().equals(answer.trim())) {
	    answers[questionIndex] = true;
	    return true;
	} else {
	    answers[questionIndex] = false;
	    return false;
	}
    }
    
    /**
     * Get saved answer; starting from zero index as first answer
     * @param itemIndex Index of the answer
     * @return true if right answer, false if not.
     */
    public boolean getSavedAnswer(int itemIndex) {
	return answers[itemIndex];
    }
    
    /** 
     * @return Total of good answers
     */
    public int getTotalGoodAnswers() {
	int goodAnswers = 0;
	
	for(int i = 0; i < itemsQuantity; i++) {
	    if(answers[i]) {
		goodAnswers++;
	    }
	}
	
	return goodAnswers;
    }
    
    public int getTotalAnswers() {
	return itemsQuantity;
    }
   
    
    /**
     * This method returns a JSON string
     * that represents the Quiz's answers.
     */
    public String toString() {
	StringBuilder sb = new StringBuilder("{answers: {");
	for(int i = 0; i < itemsQuantity; i++) {
	    if(answers[i]) {
		sb.append("true");
	    } else {
		sb.append("false");
	    }
	    
	    if(i != itemsQuantity - 1) {
		sb.append(", ");
	    }
	}
	
	sb.append("}}");

	return sb.toString();
    }
}
