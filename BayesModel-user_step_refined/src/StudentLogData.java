import java.util.ArrayList;

public class StudentLogData {
	private ArrayList<String> actionList;
	private ArrayList<String> verificationList;
	private ArrayList<String> sentenceList;
	private ArrayList<Integer> userStep;
	private ArrayList<String> inputData;

	// ////
	private double guess = 0.1; // 0.25 //0.6
	private double transition = 0.1; // 0.1 //0.7 //0.4
	private double slip = 0.1; // 0.4 //0.1

	public ArrayList<String> getActionList() {
		return actionList;
	}

	public void setActionList(ArrayList<String> actionList) {
		this.actionList = actionList;
	}

	public ArrayList<String> getVerificationList() {
		return verificationList;
	}

	public void setVerificationList(ArrayList<String> verificationList) {
		this.verificationList = verificationList;
	}

	public ArrayList<String> getSentenceList() {
		return sentenceList;
	}

	public void setSentenceList(ArrayList<String> sentenceList) {
		this.sentenceList = sentenceList;
	}

	public ArrayList<Integer> getUserStep() {
		return userStep;
	}

	public void setUserStep(ArrayList<Integer> userStep) {
		this.userStep = userStep;
	}

	public double getGuess() {
		return guess;
	}

	public void setGuess(double guess) {
		this.guess = guess;
	}

	public double getTransition() {
		return transition;
	}

	public void setTransition(double transition) {
		this.transition = transition;
	}

	public double getSlip() {
		return slip;
	}

	public void setSlip(double slip) {
		this.slip = slip;
	}

	public ArrayList<String> getInputData() {
		return inputData;
	}

	public void setInputData(ArrayList<String> inputData) {
		this.inputData = inputData;
	}

}
