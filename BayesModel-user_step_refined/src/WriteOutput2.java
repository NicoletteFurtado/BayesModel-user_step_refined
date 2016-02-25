import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class WriteOutput2 {
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
			writeSentenceList(student, initMaps, writer, sentenceKey, 0);
			writeVerification(student, initMaps, writer, sentenceKey, 1);
			for (int i = 0; i < student.getVerificationList().size(); i++) {

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

	private void writeSentenceList(StudentLogData student, InitMaps initMaps, BufferedWriter writer,
			String sentenceKey, int columnNumber) {
		try {
			writer.newLine();
			writer.newLine();
			String sentence = "";
			for (int i = 0; i < student.getSentenceList().size(); i++) {
				sentenceKey = convertStringToKey(student.getSentenceList().get(i));
				sentence = initMaps.getSentenceToText().get(sentenceKey);
				writer.write(sentence);
				writer.newLine();
			}
			writer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void writeVerification(StudentLogData student, InitMaps initMaps, BufferedWriter writer,
			String sentenceKey, int columnNumber) {
		try {
			writer.newLine();
			writer.newLine();
			String appender = columnNumberToCsvSeparator(columnNumber);
			String verification = "";
			for (int i = 0; i < student.getVerificationList().size(); i++) {
				verification = appender + student.getVerificationList().get(i);
				writer.write(verification);
				writer.newLine();
			}
			writer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private String columnNumberToCsvSeparator(int columnNumber) {
		String appender = "";
		for (int i = 0; i < columnNumber; i++) {
			appender += Constants.CSV_FILE_SEPARATOR;
		}
		return appender;
	}
}
