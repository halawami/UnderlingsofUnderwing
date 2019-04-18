package underlings.element;

public class BlackElementSpace extends ElementSpace {

	private ElementSpace redElementSpace, blueElementSpace, yellowElementSpace;
	
	public BlackElementSpace() {
		super(ElementColor.BLACK);
		
		this.redElementSpace = new ElementSpace(ElementColor.RED);
		this.blueElementSpace = new ElementSpace(ElementColor.BLUE);
		this.yellowElementSpace = new ElementSpace(ElementColor.YELLOW);
	}
	
	@Override
	public void addElements(Element... elementsToAdd) {
		// TODO Auto-generated method stub
		super.addElements(elementsToAdd);
	}

	@Override
	public boolean isComplete() {
		return this.redElementSpace.isComplete() && this.blueElementSpace.isComplete() && this.yellowElementSpace.isComplete();
	}
	
}
