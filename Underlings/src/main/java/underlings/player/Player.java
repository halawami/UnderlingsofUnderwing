package underlings.player;

import java.util.ArrayList;
import java.util.List;

import underlings.element.Element;
import underlings.element.ElementGiver;
import underlings.game.Handler;
import underlings.game.HandlerFactory;

public class Player {

	private List<Handler> handlers;
	private int maxHandlers;
	private int points;
	private boolean reached12Points;
	private boolean reached25Points;
	private HandlerFactory handlerFactory;
	private List<Element> elements;
	private List<ElementGiver> elementGivers;

	public Player(int maxHandlers, HandlerFactory handlerFactory) {
		this.handlers = new ArrayList<Handler>();
		this.elements = new ArrayList<Element>();
		this.elementGivers = new ArrayList<ElementGiver>();
		this.handlerFactory = handlerFactory;
		this.maxHandlers = maxHandlers;
		this.gainHandler();
		this.gainHandler();
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

	public void gainHandler() {
		if (this.handlers.size() != this.maxHandlers) {
			this.handlers.add(this.handlerFactory.createHandler());
			this.elementGivers.add(new ElementGiver());
		}
	}

	public int getMaxHandlers() {
		return this.maxHandlers;
	}

	public void addPoints(int pointsToAdd) {
		this.points += pointsToAdd;
		if (!this.reached12Points) {
			if (this.points >= 12 && this.handlers.size() < 3) {
				this.handlers.add(this.handlerFactory.createHandler());
				this.reached12Points = true;
			}
		}
		if (!this.reached25Points) {
			if (this.points >= 25 && this.handlers.size() < 4) {
				this.handlers.add(this.handlerFactory.createHandler());
				this.reached25Points = true;
			}
		}
	}

	public void loseHandler() {
		if (this.handlers.size() > 2) {
			this.handlers.remove(0);
		}
	}

	public List<ElementGiver> getElementGivers() {
		return this.elementGivers;
	}

	public void addElement(Element elementToAdd) {
		this.elements.add(elementToAdd);
	}

	public List<Element> getElements() {
		return this.elements;
	}

	public void losePoints(int pointsToLose) {
		this.points -= pointsToLose;
	}

	public void removeElement(Element elementToRemove) {
		this.elements.remove(elementToRemove);
	}
}
