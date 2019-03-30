package underlings.game;

public class Handler {

	private HandlerState state;
	
	public Handler(){
		this.state = HandlerState.READY_ROOM;
	}
	
	public HandlerState getState() {
		return this.state;
	}

	public void moveToCard() {
		this.state = HandlerState.CARD;
	}

	public void moveToField() {
		this.state = HandlerState.FIELD;
	}
	
	public void moveToFieldWhite(){
		this.state = HandlerState.FIELD_WHITESPACE;
	}

}
