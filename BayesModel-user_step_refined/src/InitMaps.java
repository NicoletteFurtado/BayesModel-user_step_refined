import java.util.ArrayList;
import java.util.HashMap;

public class InitMaps {
	private HashMap<String, String> sentenceToText;
	private HashMap<String, ArrayList<String>> sentenceToWords;
	private HashMap<String, ArrayList<ArrayList<String>>> sentenceToActions;

	public HashMap<String, String> getSentenceToText() {
		return sentenceToText;
	}

	public void setSentenceToText(HashMap<String, String> sentenceToText) {
		this.sentenceToText = sentenceToText;
	}

	public HashMap<String, ArrayList<String>> getSentenceToWords() {
		return sentenceToWords;
	}

	public void setSentenceToWords(HashMap<String, ArrayList<String>> sentenceToWords) {
		this.sentenceToWords = sentenceToWords;
	}

	public HashMap<String, ArrayList<ArrayList<String>>> getSentenceToActions() {
		return sentenceToActions;
	}

	public void setSentenceToActions(HashMap<String, ArrayList<ArrayList<String>>> sentenceToActions) {
		this.sentenceToActions = sentenceToActions;
	}

}
