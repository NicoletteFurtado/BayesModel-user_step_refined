import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class MainClass {
	public static void main(String args[]) {

		ReadFiles reader = new ReadFiles();
		// read properties file into LinkedHashMap
		InitMaps initMaps = reader.readPropertiesFile("cleaning_up_sentences.properties");
		// InitMaps initMaps = reader.readPropertiesFile("the_contest_sentences.properties");
		// InitMaps initMaps = reader.readPropertiesFile("getting_ready.properties");
		// InitMaps initMaps = reader.readPropertiesFile("who_is_the_best_animal.properties");
		// InitMaps initMaps = reader.readPropertiesFile("combined.properties");
		// InitMaps initMaps = reader.readPropertiesFile("everyone_helps.properties");
		// InitMaps initMaps = reader.readPropertiesFile("the_wise_owl.properties");
		// InitMaps initMaps = reader.readPropertiesFile("the_best_farm_award.properties");
		// InitMaps initMaps = reader.readPropertiesFile("everyone_helps_actions.properties");
		// InitMaps initMaps = reader.readPropertiesFile("cleaning_up_actions.properties");
		// read log data into StudentModel
		// StudentLogData studentLogData = reader
		// .readLogData("C:\\Users\\Nicolette\\OneDrive\\Documents\\EMBRACE\\Analysis\\With user step\\log_data_B0039EE.csv");
		// StudentLogData studentLogData = reader
		// .readLogData("C:\\Users\\Nicolette\\OneDrive\\Documents\\EMBRACE\\Analysis\\With user step\\log_data_B0039EE_the_contest.csv");
		// StudentLogData studentLogData = reader
		// .readLogData("C:\\Users\\Nicolette\\OneDrive\\Documents\\EMBRACE\\Analysis\\With user step\\log_data_B0039EE_getting_ready.csv");
		// StudentLogData studentLogData = reader
		// .readLogData("C:\\Users\\Nicolette\\OneDrive\\Documents\\EMBRACE\\Analysis\\With user step\\log_data_B0039EE_who_is_the_best_animal.csv");
		// StudentLogData studentLogData = reader
		// .readLogData("C:\\Users\\Nicolette\\OneDrive\\Documents\\EMBRACE\\Analysis\\With user step\\log_data_B0039EE_chapters_1_3.csv");
		// StudentLogData studentLogData = reader
		// .readLogData("C:\\Users\\Nicolette\\OneDrive\\Documents\\EMBRACE\\Analysis\\With user step\\log_data_B0039EE_chapters_1_4.csv");
		// StudentLogData studentLogData = reader
		// .readLogData("C:\\Users\\Nicolette\\OneDrive\\Documents\\EMBRACE\\Analysis\\With user step\\log_data_B0039EE_the_wise_owl.csv");
		// StudentLogData studentLogData = reader
		// .readLogData("C:\\Users\\Nicolette\\OneDrive\\Documents\\EMBRACE\\Analysis\\With user step\\log_data_B0039EE_chapters_1_5.csv");
		// StudentLogData studentLogData = reader
		// .readLogData("C:\\Users\\Nicolette\\OneDrive\\Documents\\EMBRACE\\Analysis\\With user step\\log_data_B0039EE_everyone_helps.csv");
		// StudentLogData studentLogData = reader
		// .readLogData("C:\\Users\\Nicolette\\OneDrive\\Documents\\EMBRACE\\Analysis\\With user step\\log_data_B0039EE_chapters_1_6.csv");
		// StudentLogData studentLogData = reader
		// .readLogData("C:\\Users\\Nicolette\\OneDrive\\Documents\\EMBRACE\\Analysis\\With user step\\log_data_B0039EE_the_best_farm_award.csv");
		// StudentLogData studentLogData = reader
		// .readLogData("C:\\Users\\Nicolette\\OneDrive\\Documents\\EMBRACE\\Analysis\\With user step\\log_data_B0039EE_chapters_1_6.csv");
		// StudentLogData studentLogData = reader
		// .readLogData("C:\\Users\\Nicolette\\OneDrive\\Documents\\EMBRACE\\Analysis\\With user step\\log data by student\\log_data_B009BE.csv");
		StudentLogData studentLogData = reader
				.readLogData("C:\\Users\\Nicolette\\OneDrive\\Documents\\EMBRACE\\Analysis\\With user step\\log data by student\\log_data_P0017EE_cleaning_up.csv");

		// ArrayList<String> inputData = reader.readInputData("inputdata/text.txt", studentLogData);
		ArrayList<String> inputData = reader.readInputData("inputdata/input_data_P0017EE", studentLogData);
		studentLogData.setInputData(inputData);
		System.out.println(Arrays.toString(inputData.toArray()));

		// initialize all skills
		HashMap<String, ArrayList<String>> sentenceToWords = initMaps.getSentenceToWords();
		System.out.println(initMaps.getSentenceToWords());

		SkillSet skillSet = new SkillSet();
		LinkedHashMap<String, ArrayList<Skill>> skillMap = skillSet.getSkillMap();
		ArrayList<String> wordList = null;
		ArrayList<String> allWordsList = new ArrayList<String>(); // list of all unique words, we do this so that
																	// duplicate skills are not added later
		for (String sentence : sentenceToWords.keySet()) {
			// System.out.println(sentence);
			// System.out.println("mmmm=" + Arrays.toString(sentenceToWords.get(sentence).toArray()));
			for (String word1 : sentenceToWords.get(sentence)) {
				// System.out.println(word1);
				wordList = new ArrayList<String>();
				if (!word1.equals(Constants.DEFAULT_WORD)) {
					// System.out.println("1=" + !word1.equals(Constants.DEFAULT_WORD));
					if (!allWordsList.contains(word1)) {
						// System.out.println("2=" + !allWordsList.contains(word1));
						allWordsList.add(word1);
						wordList.add(word1);
						// System.out.println("Added word=" + word1);
					}
				}
			}
		}

		// if (!wordList.isEmpty() || !(wordList == null)) {
		if (!allWordsList.isEmpty() || !(allWordsList == null)) { // add each unique word as a skill in skillMap in
																	// skillSet
			// for (String word : wordList) {
			for (String word : allWordsList) {

				Skill skill = new Skill();
				skill.setWord(word);
				skill.setAction(Constants.DEFAULT_ACTION);
				skill.setSkillValue(Constants.INITIAL_SKILL_VALUE);
				// skill.setSentence(Constants.DEFAULT_SENTENCE);
				ArrayList<Skill> skillList = new ArrayList<Skill>();
				skillList.add(skill);
				skillMap.put(word, skillList);
			}
			// }
			// skillMap.put(sentence, new ArrayList<Skill>());
		}
		skillSet.setSkillMap(skillMap);
		// calculate
		KnowledgeTracer k = new KnowledgeTracer();
		SkillSet skillSet1 = k.calculateSkill(studentLogData, skillSet, initMaps);

		OutputLists outputLists = new OutputLists();
		outputLists.createLists(studentLogData, skillSet1, initMaps);

		WriteOutput3 writeOutput = new WriteOutput3();
		writeOutput.writeCSV(outputLists, studentLogData);
	}
}
