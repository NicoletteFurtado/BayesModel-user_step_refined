import java.util.ArrayList;
import java.util.LinkedHashMap;

public class SkillSet {
	private LinkedHashMap<String, ArrayList<Skill>> skillMap = new LinkedHashMap<String, ArrayList<Skill>>(); // word to
																												// skill

	public LinkedHashMap<String, ArrayList<Skill>> getSkillMap() {
		return skillMap;
	}

	public void setSkillMap(LinkedHashMap<String, ArrayList<Skill>> skillMap) {
		this.skillMap = skillMap;
	}
}
