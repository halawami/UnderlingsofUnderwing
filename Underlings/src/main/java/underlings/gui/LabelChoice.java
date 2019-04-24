package underlings.gui;

public class LabelChoice implements Choice {
	private String label;
	
	public LabelChoice(String label) {
		this.label = label;
	}
	
	@Override
	public String toString() {
		return label;
	}
}
