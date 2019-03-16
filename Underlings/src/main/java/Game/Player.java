package Game;

import java.util.ArrayList;
import java.util.List;

public class Player {

	private int handlerCount = 2;

	public int getHandlerCount() {
		return handlerCount;
	}

	public List<Handler> getHandlers() {
		return new ArrayList<Handler>();
	}

	public void addHandler() {
		handlerCount++;
	}

}
