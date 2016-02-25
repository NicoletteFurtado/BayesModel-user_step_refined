import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class KnowledgeTracer {

	HashMap<String, Skill> wordToValueMap; // word to skill map
	LinkedHashMap<String, ArrayList<Skill>> skillMap;

	public KnowledgeTracer() {
		wordToValueMap = new HashMap<String, Skill>();
		skillMap = new LinkedHashMap<String, ArrayList<Skill>>();
	}

	public SkillSet calculateSkill(StudentLogData studentLogData, SkillSet skillSet, InitMaps initMaps) {
		SkillSet skillSet1 = new SkillSet();
		double skillEvaluated = 0.0;
		double newSkill = 0.0;
		double prevSkillValue = 0.0;
		int prevUserStep = 0;
		String prevSentence = "";
		skillMap = skillSet.getSkillMap();
		// temporary variables
		String sent1 = "";
		String word1 = "";
		// initialize wordToValueMap so that the last skill value of the word is stored
		for (String word : skillMap.keySet()) {
			wordToValueMap.put(word, skillMap.get(word).get(0));
			// System.out.println("wordToValue " + word);
		}
		System.out.println(wordToValueMap);
		for (int i = 0; i < studentLogData.getVerificationList().size(); i++) {
			// get the current sentence
			sent1 = studentLogData.getSentenceList().get(i);
			// System.out.println(sent1);
			// update only for the first attempt of every step
			if (!(prevSentence.equalsIgnoreCase(sent1) && (prevUserStep == studentLogData.getUserStep().get(i)
					.intValue()))) {
				System.out
						.println("entered for step " + studentLogData.getUserStep().get(i) + " for sentence " + sent1);
				// get the first(or any) word from the current sentence and its skill value
				word1 = initMaps.getSentenceToWords().get(AnalysisUtil.convertStringToKey(sent1)).get(0);
				// if (wordToValueMap.containsKey(word1)) { // because sentence to words can contain XYZ
				if (!word1.equalsIgnoreCase(Constants.DEFAULT_WORD)) {
					// System.out.println(word1);
					// Skill s = wordToValueMap.get(word1);
					// prevSkillValue = s.getSkillValue(); // this must be used only if correct
					prevSentence = sent1;
					prevUserStep = studentLogData.getUserStep().get(i);
					// if correct
					if (studentLogData.getVerificationList().get(i).equals(Constants.CORRECT)) {
						// for (String word : sentenceToWords.get(AnalysisUtil.convertStringToKey(sent1))) {
						System.out.println(initMaps.getSentenceToWords().keySet());
						String objectsMoved[] = studentLogData.getInputData().get(i)
								.split(Constants.STUDENT_INPUT_DATA_SEPARATOR);
						HashMap<String, Boolean> wordToVerif = checkIncorrectSkill2(studentLogData, initMaps, i);
						System.out.println("in correct 1");
						for (String word : wordToVerif.keySet()) {
							if (wordToValueMap.keySet().contains(word)) { // some words that the student moved to are
																			// not skills
								if (wordToVerif.get(word)) {
									prevSkillValue = wordToValueMap.get(word).getSkillValue();
									skillEvaluated = this.calcCorrect(studentLogData, prevSkillValue);
									// System.out.println("in incorrect correct " + word);
								} else {
									prevSkillValue = wordToValueMap.get(word).getSkillValue();
									skillEvaluated = this.calcIncorrect(studentLogData, prevSkillValue);
									// System.out.println("in incorrect incorrect " + word);
									System.out.println("shouldn't be herre");
								}
								newSkill = calcNewSkillValue(studentLogData, skillEvaluated);
								updateSkillsIncorrect(studentLogData, initMaps, newSkill, word, i);
								System.out.println("updated correct");
							}
							// for (int j = 0; j < objectsMoved.length; j++) {
							// // if (initMaps.getSentenceToWords().get(AnalysisUtil.convertStringToKey(sent1))
							// // .contains(objectsMoved[j])) {
							// // objects moved in the sentence words
							// // only increase for object moved
							// int wordIndex = initMaps.getSentenceToWords()
							// .get(AnalysisUtil.convertStringToKey(sent1)).indexOf(objectsMoved[j]);
							// String word = initMaps.getSentenceToWords().get(AnalysisUtil.convertStringToKey(sent1))
							// .get(wordIndex);
							// prevSkillValue = wordToValueMap.get(word).getSkillValue();
							// skillEvaluated = this.calcCorrect(studentLogData, prevSkillValue);
							// newSkill = calcNewSkillValue(studentLogData, skillEvaluated);
							// updateSkillsCorrect(studentLogData, newSkill, word, i);
							// }
							// }
						}
					}
					// if incorrect
					else if (studentLogData.getVerificationList().get(i).equals(Constants.INCORRECT)) {
						// boolean skillToUpdate[] = checkIncorrectSkill(studentLogData, i);
						HashMap<String, Boolean> wordToVerif = checkIncorrectSkill2(studentLogData, initMaps, i);
						String objectsMoved[] = studentLogData.getInputData().get(i)
								.split(Constants.STUDENT_INPUT_DATA_SEPARATOR);
						System.out.println("Objects moved = " + Arrays.toString(objectsMoved));
						System.out.println(wordToVerif);
						for (String word : wordToVerif.keySet()) {
							if (wordToValueMap.keySet().contains(word)) { // some words that the student moved to are
																			// not skills
								if (wordToVerif.get(word)) {
									prevSkillValue = wordToValueMap.get(word).getSkillValue();
									skillEvaluated = this.calcCorrect(studentLogData, prevSkillValue);
									// System.out.println("in incorrect correct " + word);
								} else {
									prevSkillValue = wordToValueMap.get(word).getSkillValue();
									skillEvaluated = this.calcIncorrect(studentLogData, prevSkillValue);
									// System.out.println("in incorrect incorrect " + word);
								}
								newSkill = calcNewSkillValue(studentLogData, skillEvaluated);
								updateSkillsIncorrect(studentLogData, initMaps, newSkill, word, i);
								System.out.println("updated incorrect");
							}
						}
					}
				}
				// prevSentence = sent1;
				// prevUserStep = studentLogData.getUserStep().get(i);
			} else {
				System.out.println(i + " ignored step " + prevUserStep + "for sentence " + prevSentence);
				continue;
			}
		}
		ArrayList<Skill> s;
		for (String str : skillMap.keySet()) {
			s = skillMap.get(str);
			// System.out.print("word=" + str);
			// for (Skill si : s) {
			// System.out.print(si.getSkillValue() + " ");
			// }
			// System.out.println();
		}
		skillSet1.setSkillMap(skillMap);
		return skillSet1;
	}

	// check this
	private double calcCorrect(StudentLogData student, double prevSkillValue) {

		return (prevSkillValue * (1 - student.getSlip()))
				/ (prevSkillValue * (1 - student.getSlip()) + (1 - prevSkillValue) * student.getGuess());

	}

	private double calcIncorrect(StudentLogData student, double prevSkillValue) {

		return (prevSkillValue * student.getSlip())
				/ ((student.getSlip() * prevSkillValue) + ((1 - student.getGuess()) * (1 - prevSkillValue)));

	}

	private double calcNewSkillValue(StudentLogData student, double skillEvaluated) {
		return skillEvaluated + ((1 - skillEvaluated) * student.getTransition());
	}

	private void updateSkillsCorrect(StudentLogData studentLogData, double newSkill, String word, int count) {
		// ArrayList<String> wordList = sentenceToWords.get(AnalysisUtil.convertStringToKey(currSentence));

		// for (String word : wordList) {
		Skill skill = new Skill();
		skill.setAction(studentLogData.getActionList().get(count));
		skill.setSkillValue(newSkill);
		skill.setWord(word);
		skill.setUserStep(studentLogData.getUserStep().get(count).intValue());
		skill.setVerification(studentLogData.getVerificationList().get(count));
		// System.out.println(studentLogData.getSentenceList().get(i));
		skill.setSentence(studentLogData.getSentenceList().get(count));
		skillMap.get(word).add(skill);
		//
		wordToValueMap.put(word, skill);
		System.out.println("correct changed value " + word + " " + newSkill + " for sentence " + skill.getSentence()
				+ " for step " + studentLogData.getUserStep().get(count));

		// }
	}

	private void updateSkillsIncorrect(StudentLogData studentLogData, InitMaps initMaps, double newSkill, String word,
			int count) {
		String currSentence = studentLogData.getSentenceList().get(count);
		String currAction = studentLogData.getActionList().get(count);
		ArrayList<String> wordList = initMaps.getSentenceToWords().get(AnalysisUtil.convertStringToKey(currSentence));
		// for (String wordInList : wordList) {
		// if (wordInList.contains(word) || word.contains(wordInList)) {
		Skill skill = new Skill();
		skill.setAction(currAction);
		skill.setSkillValue(newSkill);
		// skill.setWord(wordInList);
		skill.setWord(word);
		skill.setUserStep(studentLogData.getUserStep().get(count).intValue());
		skill.setVerification(studentLogData.getVerificationList().get(count));
		// System.out.println(studentLogData.getSentenceList().get(i));
		skill.setSentence(studentLogData.getSentenceList().get(count));
		// skillMap.get(wordInList).add(skill);
		skillMap.get(word).add(skill);
		//
		// wordToValueMap.put(wordInList, skill);
		wordToValueMap.put(word, skill);
		// System.out.println("in incorrect");
		// System.out.println("changed value " + wordInList + " " + newSkill + " " + skill.getSentence());
		// }
		// }
		System.out.println("incorrect changed value " + word + " " + newSkill + " for sentence " + skill.getSentence()
				+ " for step " + studentLogData.getUserStep().get(count));
	}

	// set false for the word that the student got wrong
	private HashMap<String, Boolean> checkIncorrectSkill(StudentLogData student, InitMaps initMaps, int count) {
		String objectsMoved[] = student.getInputData().get(count).split(Constants.STUDENT_INPUT_DATA_SEPARATOR);
		String currSentence = student.getSentenceList().get(count);
		// String wordsInSentenceArr[] = currSentence.split(Constants.WORD_SEPARATOR);
		// ArrayList<String> wordsInSentence1 = new ArrayList<String>();
		// for (String word : wordsInSentenceArr) {
		// wordsInSentence1.add(word);
		// }
		HashMap<String, Boolean> wordToVerif = new HashMap<String, Boolean>();
		ArrayList<String> wordsInSentence = initMaps.getSentenceToWords().get(
				AnalysisUtil.convertStringToKey(currSentence));
		int firstWordIndex = -1;
		int secondWordIndex = -1;
		// these are for both words to be contained in the sentence
		boolean containsFirstWord = false;
		boolean containsSecondWord = false;
		boolean containsBothWords = false;
		// case for pen
		for (int i = 0; i < objectsMoved.length; i++) {
			if (objectsMoved[i].contains("pen2")) {
				objectsMoved[i] = "pen";
			}
		}
		if (wordsInSentence.contains(objectsMoved[0])) {
			firstWordIndex = wordsInSentence.indexOf(objectsMoved[0]);
			containsFirstWord = true;
		}
		if (wordsInSentence.contains(objectsMoved[1])) {
			secondWordIndex = wordsInSentence.indexOf(objectsMoved[1]);
			containsSecondWord = true;
		}
		if (containsFirstWord && containsSecondWord) {
			containsBothWords = true;
		}
		if (containsBothWords) {
			if (firstWordIndex < secondWordIndex) {
				wordToVerif.put(objectsMoved[0], true);
				wordToVerif.put(objectsMoved[1], false);
			} else if (secondWordIndex > firstWordIndex) {
				wordToVerif.put(objectsMoved[1], true);
				wordToVerif.put(objectsMoved[0], false);
			}
		} else {
			if (containsFirstWord) {
				if (firstWordIndex == 0) {
					wordToVerif.put(objectsMoved[0], true);
					wordToVerif.put(objectsMoved[1], false);
				}
			} else if (containsSecondWord) {
				if (secondWordIndex != 0) {
					wordToVerif.put(objectsMoved[1], true);
					wordToVerif.put(objectsMoved[0], false);
				}
			} else {
				wordToVerif.put(objectsMoved[1], false);
				wordToVerif.put(objectsMoved[0], false);
			}
		}
		return wordToVerif;
	}

	// set false for the word that the student got wrong
	private HashMap<String, Boolean> checkIncorrectSkill2(StudentLogData student, InitMaps initMaps, int count) {
		String objectsMoved[] = student.getInputData().get(count).split(Constants.STUDENT_INPUT_DATA_SEPARATOR);
		String currSentence = student.getSentenceList().get(count);
		HashMap<String, Boolean> wordToVerif = new HashMap<String, Boolean>();
		// case for pen
		for (int i = 0; i < objectsMoved.length; i++) {
			if (objectsMoved[i].contains("pen2")) {
				objectsMoved[i] = "pen";
			} else if (objectsMoved[i].contains("corralDoor")) {
				objectsMoved[i] = "corral";
			}
		}
		// get the action for this step
		ArrayList<String> actionWords = initMaps.getSentenceToActions()
				.get(AnalysisUtil.convertStringToKey(currSentence)).get(student.getUserStep().get(count) - 1);
		for (int i = 0; i < actionWords.size(); i++) {
			if (actionWords.get(i).equalsIgnoreCase(objectsMoved[i])) {
				wordToVerif.put(actionWords.get(i), true);
			} else {
				wordToVerif.put(actionWords.get(i), false);
			}
		}
		return wordToVerif;
	}
}