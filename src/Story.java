import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Story {
	private static String question;
	private static JSONArray level_question_set;
	private static ArrayList<String> answers;
	private static final int NUMBEROFQUESTIONS = 2;
	
	public static void getLevelQuestions(int level) {
		JSONParser parser = new JSONParser();
		
		
		try {
			Object obj = new Object();
			try {
				obj = parser.parse(new FileReader("JSONS/questions.json"));
			} catch (org.json.simple.parser.ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
			JSONObject json_questions = (JSONObject) obj;

			JSONArray questions = (JSONArray) json_questions.get("level_"+level);
			
			level_question_set=questions;
		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		};
	}
	
	public static void findQuestion(int level) {
		Random randomGenerator = new Random();
		int random_int= randomGenerator.nextInt(NUMBEROFQUESTIONS);
		getLevelQuestions(level);
		//System.out.println(level_question_set); //testing purposes
		HashMap<String, ArrayList<String>> question_and_ans_struct = (HashMap) level_question_set.get(random_int);
		
		question = (String) question_and_ans_struct.keySet().toArray()[0];
		answers = (ArrayList<String>) question_and_ans_struct.get(question);
	}
	
	public static String getQuestion() {
		return question;
	}
	
	public static ArrayList<String> getAnswers() {
		return answers;
	}
	
	public static String getRightAnswer() {
		return answers.get(0);
	}
}
