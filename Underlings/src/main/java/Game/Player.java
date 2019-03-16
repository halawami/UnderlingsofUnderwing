package Game;

import java.util.ArrayList;
import java.util.List;

public class Player {

	private List<Handler> handlers;
	
	public Player(){
		this.handlers = new ArrayList<Handler>();
		this.handlers.add(new Handler());
		this.handlers.add(new Handler());
	}
	
	public int getHandlerCount() {
		return this.handlers.size();
	}

	public List<Handler> getHandlers() {
		return this.handlers;
	}

	public void addHandler() {
		this.handlers.add(new Handler());
	}

}
