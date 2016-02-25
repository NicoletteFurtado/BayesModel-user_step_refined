public class Skill {
	private String word = "";
	private String action = "";
	private String verification = "";
	private double skillValue = 0.0;
	private int userStep = 0;
	private String sentence = "";

	public int getUserStep() {
		return userStep;
	}

	public void setUserStep(int userStep) {
		this.userStep = userStep;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public double getSkillValue() {
		return skillValue;
	}

	public void setSkillValue(double skillValue) {
		this.skillValue = skillValue;
	}

	public String getVerification() {
		return verification;
	}

	public void setVerification(String verification) {
		this.verification = verification;
	}

	public String getSentence() {
		return sentence;
	}

	public void setSentence(String sentence) {
		this.sentence = sentence;
	}
}
