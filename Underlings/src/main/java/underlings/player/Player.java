package underlings.player;

import java.util.ArrayList;
import java.util.List;

import underlings.game.Handler;

public class Player {

	private List<Handler> handlers;
	private int maxHandlers;
	private int points;
	private boolean reached12Points;
	private boolean reached25Points;

	public Player(int maxHandlers) {
		this.handlers = new ArrayList<Handler>();
		this.handlers.add(new Handler());
		this.handlers.add(new Handler());
		this.maxHandlers = maxHandlers;
		this.points = 0;
		this.reached12Points = false;
		this.reached25Points = false;
	}

	public int getHandlerCount() {
		return this.handlers.size();
	}

	public List<Handler> getHandlers() {
		return this.handlers;
	}

	public void addHandler() {
		if (this.handlers.size() != this.maxHandlers) {
			this.handlers.add(new Handler());
		}
	}

	public int getMaxHandlers() {
		return this.maxHandlers;
	}

	public void addPoints(int pointsToAdd) {
		this.points += pointsToAdd;
		if(this.points >= 12 && this.handlers.size() < 3 && !this.reached12Points){
			this.handlers.add(new Handler());
			this.reached12Points = true;
		} 
		if (this.points >= 25 && this.handlers.size() < 4 && !this.reached25Points 
				&& !this.reached12Points) {
			this.handlers.add(new Handler());
			this.handlers.add(new Handler());
			this.reached25Points = true;
			this.reached12Points = true;
		}
		if (this.points >= 25 && this.handlers.size() < 4 && !this.reached25Points 
				&& this.reached12Points) {
			this.handlers.add(new Handler());
			this.reached25Points = true;
		}
	}

	// which one do we lose
	public void loseHandler() {
		if(this.handlers.size() > 2){
			this.handlers.remove(0);
		}
	}

}
