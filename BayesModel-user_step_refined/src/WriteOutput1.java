import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class WriteOutput1 {
	public void writeCSV(StudentLogData student, SkillSet skillSet, InitMaps initMaps) {
		BufferedWriter writer = null;
		String prevSentence = "";
		String currSentence = "";
		String prevWord = "";
		String sentenceKey = "";
		String appender = "";
		ArrayList<Skill> skillList = new ArrayList();
		String outputFile = "C:\\Users\\Nicolette\\OneDrive\\Documents\\EMBRACE\\Analysis\\output_2016.csv";
		try {
			FileWriter fw = new FileWriter(outputFile);
			writer = new BufferedWriter(fw);
			writer.write("Student id B0039EE\n");
			for (int i = 0; i < student.getVerificationList().size(); i++) {
				sentenceKey = convertStringToKey(student.getSentenceList().get(i));

				for (String word : initMaps.getSentenceToWords().get(sentenceKey)) {
					// writer.write(word + Constants.CSV_FILE_SEPARATOR);

					if (!prevWord.equals(word)) {
						// appender = "" + skill.getWord() + Constants.CSV_FILE_SEPARATOR;
						// appender = "" + word + Constants.CSV_FILE_SEPARATOR;
						appender = "" + word;
						writer.write(Constants.CSV_FILE_SEPARATOR + appender);
						writer.newLine();
						prevWord = word;
						for (Skill skill : skillSet.getSkillMap().get(word)) {
							writer.write(initMaps.getSentenceToText().get(sentenceKey) + Constants.CSV_FILE_SEPARATOR);
							// appender = Constants.CSV_FILE_SEPARATOR + skill.getAction() +
							// Constants.CSV_FILE_SEPARATOR
							// + +skill.getUserStep() + Constants.CSV_FILE_SEPARATOR + skill.getVerification()
							// + Constants.CSV_FILE_SEPARATOR + skill.getSkillValue()
							// + Constants.CSV_FILE_SEPARATOR;

							appender = student.getVerificationList().get(i) + Constants.CSV_FILE_SEPARATOR
									+ skill.getSkillValue();
							writer.write(appender);
						}
					}

					// writer.newLine();
					writer.flush();
					// writer.write(student.getSentenceList().get(i) + Constants.CSV_FILE_SEPARATOR);
					// writer.newLine();
				}
				// writer.write(student.getParticipantId() + "\n");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private String convertStringToKey(String sentence) {
		String s = sentence.toUpperCase();
		String[] s1 = s.split(" ");
		String key = s1[0].trim() + Constants.KEY_SEPARATOR + s1[1].trim() + Constants.KEY_SEPARATOR + s1[3].trim();
		return key;
	}
}
