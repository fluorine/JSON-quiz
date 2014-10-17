package com.github.fluorine.jsonquiz;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 
 * @author flurine@github
 */
public class Quiz {
    // Quiz's general information
    public final String subject;
    public final String description;
    
    // Questions and answers
    public final JSONArray jsonQuestions;
    public final JSONObject jsonOptions;
    
    /**
     * Get options from a given group name.
     * 
     * @param groupName A group name for the options.
     * @return An array of strings of options.
     */
    public final String[] getOptions(String groupName) {
	try {
	    return QuizTools.jsonToStringArray(jsonOptions.getJSONArray(groupName));
	} catch (JSONException e) {
	    // Return empty string is group name is not found.
	    return new String[] {};
	}
    }
    
    // Constructor
    public Quiz(JSONObject raw_qz) throws JSONException {
	JSONObject qz = raw_qz.getJSONObject("quiz");

	// Load quiz's subject, description and items
	subject = qz.getString("subject");
	description = qz.getString("description");
	jsonQuestions = qz.getJSONArray("questions");
	jsonOptions = qz.getJSONObject("options");
    }
    
}
