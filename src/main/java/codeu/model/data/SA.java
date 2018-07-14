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
		StringTokenizer st = new StringTokenizer(sentiment);
		String pos = "";
		String neg = "";
		String neut = "";
		while(st.hasMoreTokens()){
			if(st.nextToken().equals("{\"positive\":")){
				pos += st.nextToken();
				pos = pos.substring(0, pos.length() - 1);
				st.nextToken();
				neg += st.nextToken();
				neg = neg.substring(0, neg.length() -1);
				st.nextToken();
				neut += st.nextToken();
				neut = neut.substring(0, neut.length() - 2);
			}
		}
		System.out.println(pos + " " + neg + " " + neut);
		result = "Postive: " + pos + " Negative: " + neg + " Neutral: " + neut;
		return result;
	}
	
}
