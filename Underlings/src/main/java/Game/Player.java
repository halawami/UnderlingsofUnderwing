package Game;

import java.util.ArrayList;
import java.util.List;

public class Player {

	private List<Handler> handlers;
	private int maxHandlers;
	
	public Player(int maxHandlers){
		this.handlers = new ArrayList<Handler>();
		this.handlers.add(new Handler());
		this.handlers.add(new Handler());
		this.maxHandlers = maxHandlers;
	}
	
	public int getHandlerCount() {
		return this.handlers.size();
	}

	public List<Handler> getHandlers() {
		return this.handlers;
	}

	public void addHandler() {
		if(this.handlers.size() != this.maxHandlers){
			this.handlers.add(new Handler());
		}
		
	}

}
