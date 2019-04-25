package underlings.handler;

public class HandlerDecision {

	public HandlerChoice choice;
	public Handler handler;

	public HandlerDecision(Handler handler, HandlerChoice choice) {
		this.handler = handler;
		this.choice = choice;
	}

}
