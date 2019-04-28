package underlings.element;

public class NullElement extends Element {

	private static NullElement instance;
	
	private NullElement() {
		super(ElementColor.NULL);
	}
	
	public static NullElement getInstance() {
		if (instance == null) {
			instance = new NullElement();
		}
		return instance;
	}
}
