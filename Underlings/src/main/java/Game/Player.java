package Game;

import java.util.ArrayList;
import java.util.List;

public class Player {

	private int handlerCount = 2;
	private List<Handler> handlers;
	
	public Player(){
		this.handlers = new ArrayList<Handler>();
		this.handlers.add(new Handler());
		this.handlers.add(new Handler());
	}
	
	public int getHandlerCount() {
		return handlerCount;
	}

	public List<Handler> getHandlers() {
		return this.handlers;
	}

	public void addHandler() {
		handlerCount++;
	}

}
