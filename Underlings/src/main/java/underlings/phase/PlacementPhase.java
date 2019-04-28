package underlings.phase;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import underlings.card.Card;
import underlings.element.Element;
import underlings.element.ElementBag;
import underlings.element.ElementColor;
import underlings.element.ElementSpace;
import underlings.element.utilities.ElementSpaceLogic;
import underlings.field.Field;
import underlings.game.HatchingGround;
import underlings.gui.GUI;
import underlings.gui.PromptHandler;
import underlings.player.Player;

public class PlacementPhase extends RotationPhase {
	
	private int turnCount;

	public PlacementPhase(List<Player> players, GUI gui, ElementBag elementBag, HatchingGround hatchingGround,
			Runnable displayMethod, Field field) {
		super(players, gui, elementBag, hatchingGround, displayMethod, field);
	}

	@Override
	public void setup() {
		this.turnCount = 0;
		for (Player player : this.players)
			this.turnCount = Math.max(this.turnCount, player.getHandlerCount());
		this.turnCount *= this.players.size();
	}

	@Override
	public void turn(Player player) {
		PromptHandler prompts = this.gui.promptHandler;
		int playerNum = player.getPlayerId();

		ElementSpaceLogic logic = player.getElementSpaceLogic();
		List<Card> cards = getPlayableCards(logic, player.getElements());

		if (cards.isEmpty()) {
			prompts.displayMessage("Player has no valid placements", playerNum, JOptionPane.WARNING_MESSAGE);
		} else {
			this.phaseComplete = false;
			Card card = prompts.promptChoice("Pick a card to place an element on.", cards, playerNum);
			List<ElementSpace> spaces = logic.getPlayableSpaces(card, player.getElements());
			ElementSpace space = prompts.promptChoice("Pick an element space to place an element on.", spaces,
					playerNum);

			boolean moreMoves = true;
			while (moreMoves) {
				List<Element> choices = this.playableElements(logic.getValidAdditions(space), player.getElements());
				Element element = prompts.promptChoice("Pick an element to place", choices, playerNum);

				space.addElements(element);
				player.removeElement(element);
				this.displayMethod.run();

				choices = this.playableElements(logic.getValidAdditions(space), player.getElements());
				if (choices.isEmpty())
					moreMoves = false;
				else
					moreMoves = prompts.promptDecision("Would you like to place another element?", playerNum);
			}
		}
		if(--this.turnCount == 0)
			this.phaseComplete = true;
	}

	public List<Element> playableElements(List<ElementColor> list, List<Element> elements) {
		elements = new ArrayList<Element>(elements);
		for (int i = 0; i < elements.size(); i++)
			if (!list.contains(elements.get(i).getColor()))
				elements.remove(i--);
		return elements;
	}
	
	public List<Card> getPlayableCards(ElementSpaceLogic logic, List<Element> elements) {
		List<Card> cards = new ArrayList<Card>();
		for (Card card : this.hatchingGround) {
			if (!logic.getPlayableSpaces(card, elements).isEmpty())
				cards.add(card);
		}
		return cards;
	}

}
