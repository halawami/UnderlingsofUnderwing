package underlings.game;

public class Handler {

	private HandlerState state;
	
	public Handler(){
		this.state = HandlerState.READY_ROOM;
	}
	
	public HandlerState getState() {
		return state;
	}

	public void moveToCard() {
		state = HandlerState.CARD;
	}

}
