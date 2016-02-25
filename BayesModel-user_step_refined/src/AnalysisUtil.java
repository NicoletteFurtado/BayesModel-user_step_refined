public class AnalysisUtil {
	public static String convertStringToKey(String sentence) {
		String s = sentence.toUpperCase();
		String[] s1 = s.split(" ");
		String key = "";
		int i = 0;
		for (i = 0; i < s1.length - 1; i++) {
			// String key = s1[0].trim() + Constants.KEY_SEPARATOR + s1[1].trim() + Constants.KEY_SEPARATOR +
			// s1[3].trim();
			if (!s1[i].equals("-")) {
				key += s1[i].trim() + Constants.KEY_SEPARATOR;
			}
			// System.out.println(s1[i]);
			// key += s1[i].trim() + Constants.KEY_SEPARATOR;
		}
		key += s1[i];
		// System.out.println(key);
		return key;
	}
}
