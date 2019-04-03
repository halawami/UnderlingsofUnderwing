package underlings.player;

import java.util.ArrayList;
import java.util.List;

import underlings.element.Element;
import underlings.game.Handler;
import underlings.game.HandlerFactory;
import underlings.gui.ElementGiver;

public class Player {

	private List<Handler> handlers;
	private int maxHandlers;
	private int points;
	private boolean reached12Points;
	private boolean reached25Points;
	private HandlerFactory handlerFactory;
	private List<Element> elements;

	public Player(int maxHandlers, HandlerFactory handlerFactory) {
		this.handlers = new ArrayList<Handler>();
		this.elements = new ArrayList<Element>();
		this.handlerFactory = handlerFactory; 
		this.handlers.add(handlerFactory.createHandler());
		this.handlers.add(handlerFactory.createHandler());
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
			this.handlers.add(this.handlerFactory.createHandler());
		}
	}

	public int getMaxHandlers() {
		return this.maxHandlers;
	}

	public void addPoints(int pointsToAdd) {
		this.points += pointsToAdd;
		if(this.points >= 12 && this.handlers.size() < 3 && !this.reached12Points){
			this.handlers.add(this.handlerFactory.createHandler());
			this.reached12Points = true;
		} 
		if (this.points >= 25 && this.handlers.size() < 4 && !this.reached25Points) {
			this.handlers.add(this.handlerFactory.createHandler());
			this.reached25Points = true;
		}
//		if (this.points >= 12) {
//			this.reached12Points = true;
//		}
//		if (this.points >= 25) {
//			this.reached25Points = true;
//		}
	}

	public void loseHandler() {
		if(this.handlers.size() > 2){
			this.handlers.remove(0);
		}
	}

	public List<ElementGiver> getElementGivers() {
		return null;
	}

	public void addElement(Element elementToAdd) {
		this.elements.add(elementToAdd);
	}

	public List<Element> getElements() {
		return this.elements;
	}

}
