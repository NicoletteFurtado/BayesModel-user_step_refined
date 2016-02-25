import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class WriteOutput {
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
			writer.write("Word" + Constants.CSV_FILE_SEPARATOR + "Action" + Constants.CSV_FILE_SEPARATOR + "User Step"
					+ Constants.CSV_FILE_SEPARATOR + "Verificaton" + Constants.CSV_FILE_SEPARATOR + "Skill value");
			writer.newLine();
			System.out.println("WriteOutput");
			for (int i = 0; i < student.getSentenceList().size(); i++) {
				sentenceKey = convertStringToKey(student.getSentenceList().get(i));
				currSentence = initMaps.getSentenceToText().get(sentenceKey);
				if (!prevSentence.equals(currSentence)) {
					System.out.println(!prevSentence.equals(currSentence));
					writer.write("Sentence:" + Constants.CSV_FILE_SEPARATOR + currSentence);
					writer.newLine();
					prevSentence = currSentence;
				}
				writer.flush();

				for (String word : initMaps.getSentenceToWords().get(sentenceKey)) {
					// writer.write(word + Constants.CSV_FILE_SEPARATOR);

					if (!prevWord.equals(word)) {
						// appender = "" + skill.getWord() + Constants.CSV_FILE_SEPARATOR;
						// appender = "" + word + Constants.CSV_FILE_SEPARATOR;
						appender = "" + word;
						writer.write(appender);
						prevWord = word;
						for (Skill skill : skillSet.getSkillMap().get(word)) {
							appender = Constants.CSV_FILE_SEPARATOR + skill.getAction() + Constants.CSV_FILE_SEPARATOR
									+ +skill.getUserStep() + Constants.CSV_FILE_SEPARATOR + skill.getVerification()
									+ Constants.CSV_FILE_SEPARATOR + skill.getSkillValue()
									+ Constants.CSV_FILE_SEPARATOR;
							writer.write(appender);
							writer.newLine();
						}
					}

					// writer.newLine();
					writer.flush();
				}

			}
			// writer.write(student.getParticipantId() + "\n");
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
