import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
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
	private static final int NUMBEROFQUESTIONS = 3;
	private static final int STORYLEVELS = 3;
	
	
	//JSON file is of this format
	//{level: {question_i:[answer1,answer2,answer3]}}
	
	//parses JSON file to grab the respective level's Q&A pool
	public static void getLevelQuestions(int level) {
		JSONParser parser = new JSONParser();
		
		try {
			Object obj = new Object();
			try {
				 //new FileReader("JSONS/questions.json")
				obj = parser.parse(new InputStreamReader(new FileInputStream("JSONS/questions.json"),"UTF-8"));
			} catch (org.json.simple.parser.ParseException e) {
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
	
	//chooses and updates the question and the correlated answers every level
	public static void findQuestion(int level) {
		Random randomGenerator = new Random();
		int random_int = randomGenerator.nextInt(NUMBEROFQUESTIONS);
		getLevelQuestions(level);
		//transforms random question from JSON into hashmap -> question: [answers]
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
	
	public static String getFirstRightAnswer() {
		return answers.get(0);
	}
	
	public static String getSecondRightAnswer() {
		return answers.get(1);
	}
	
	public static int getNumberOfLevels() {
		return STORYLEVELS;
	}
}
