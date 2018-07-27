package codeu.model.data;

import com.paralleldots.paralleldots.App;
import org.json.simple.JSONObject;

import java.util.StringTokenizer;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

public class SA {

	App pd = new App("rwJj7ySstJht0QfJUQ0sKBErzeavAM6ydGcw8LQGLWE");
	
	public String getSentiment(String s) throws Exception {
		String result = "";
		String sentiment = pd.sentiment(s);
		System.out.println(sentiment);
		StringTokenizer st = new StringTokenizer(sentiment);
		String pos = "";
		String neg = "";
		String neut = "";
		while(st.hasMoreTokens()){
			String temp = st.nextToken();
			System.out.println(temp);
			if(temp.equals("{\"negative\":")){
				neg += st.nextToken();
				neg = neg.substring(0, neg.length() - 1);
				st.nextToken();
				neut += st.nextToken();
				neut = neut.substring(0, neut.length() -1);
				st.nextToken();
				pos += st.nextToken();
				pos = pos.substring(0, pos.length() - 2);
			}
		}
		System.out.println(pos + " " + neg + " " + neut);
		result = "Sentiment - Postive: " + pos + " Negative: " + neg + " Neutral: " + neut;
		return result;
	}
	
	public String getEmotion(String s) throws Exception {
		String result = "";
		String emotion = pd.emotion(s);
		System.out.println(emotion);
		String sad = "";
		String sarcasm = "";
		String excited = "";
		String fear = "";
		String happy = "";
		String angry = "";
		String bored = "";
		StringTokenizer st = new StringTokenizer(emotion);
		
		while(st.hasMoreTokens()) {
			String temp = st.nextToken();
			if(temp.equals("{\"Sad\":")) {
				sad += st.nextToken();
				sad = sad.substring(0, 5);
				st.nextToken();
				sarcasm += st.nextToken();
				sarcasm = sarcasm.substring(0, 5);
				st.nextToken();
				excited += st.nextToken();
				excited = excited.substring(0, 5);
				st.nextToken();
				fear += st.nextToken();
				fear = fear.substring(0, 5);
				st.nextToken();
				happy += st.nextToken();
				happy = happy.substring(0, 5);
				st.nextToken();
				angry += st.nextToken();
				angry = angry.substring(0, 5);
				st.nextToken();
				bored += st.nextToken();
				bored = bored.substring(0, 5);
				st.nextToken();
				
			}
			
		}
		
		
		result = String.format("Emotion - Sad: %s\tSarcasm: %s\tExcited: %s\tFear: %s\tHappy: %s\tAngry: %s\tBored: %s", sad, sarcasm, excited, fear, happy, angry, bored);
		
		return result;
	}
	
}
