package underlings.phase;

import java.util.ArrayList;
import java.util.List;

import underlings.card.Card;
import underlings.element.Element;
import underlings.element.ElementBag;
import underlings.element.ElementColor;
import underlings.element.ElementSpace;
import underlings.element.utilities.ElementSpaceLogic;
import underlings.game.HatchingGround;
import underlings.gui.GUI;
import underlings.player.Player;

public class PlacementPhase extends RotationPhase {

	public PlacementPhase(List<Player> players, GUI gui, ElementBag elementBag, HatchingGround hatchingGround,
			Runnable displayMethod) {
		super(players, gui, elementBag, hatchingGround, displayMethod);
	}

	@Override
	public void execute() {
		int[] playerTurnCounts = new int[this.players.size()];
		int maxTurnCount = 0;
		for (int i = 0; i < this.players.size(); i++) {
			playerTurnCounts[i] = this.players.get(i).getHandlerCount();
			maxTurnCount = Math.max(maxTurnCount, playerTurnCounts[i]);
		}

		for (int i = 0; i < maxTurnCount; i++) {

			for (Player player : this.players) {
				// get cards that can be played on
				ElementSpaceLogic logic = player.getElementSpaceLogic();
				ElementColor[] elements = new ElementColor[player.getElements().size()];
				for (int j = 0; j < elements.length; j++)
					elements[j] = player.getElements().get(j).getColor();
				List<Card> cards = new ArrayList<Card>();
				List<Card> hgCards = new ArrayList<Card>();
				for(int r = 0; r < this.hatchingGround.cards.length; r++)
					for(int c = 0; c < this.hatchingGround.cards[0].length; c++)
						hgCards.add(this.hatchingGround.cards[r][c]);
				for (Card card : hgCards) {
					if (!logic.getPlayableSpaces(card, elements).isEmpty())
						cards.add(card);
				}

				if (cards.isEmpty()) {
					this.gui.promptHandler.displayMessage("Player has no valid placements");
				} else {
					// choose card
					Card card = this.gui.promptHandler.promptChoice("Pick a card to place an element on.", cards);

					// get element spaces that can be played on
					List<ElementSpace> spaces = logic.getPlayableSpaces(card, elements);

					// choose element space
					ElementSpace space = this.gui.promptHandler.promptChoice("Pick an element space to place an element on.",
							spaces);

					boolean moreMoves = true;
					while (moreMoves) {
						// get valid additions
						List<Element> choices = this.playableElements(logic.getValidAdditions(space), player.getElements());

						// choose element to play
						Element element = this.gui.promptHandler.promptChoice("Pick an element to place", choices);

						// play element
						space.addElements(element);
						player.removeElement(element);

						// if more elements can be played, ask if done
						// if not done then loop
						choices = this.playableElements(logic.getValidAdditions(space), player.getElements());
						if (choices.isEmpty())
							moreMoves = false;
						else
							moreMoves = this.gui.promptHandler.promptDecision("Would you like to place another element?");
					}
				}
			}
		}
	}

	public List<Element> playableElements(List<ElementColor> list, List<Element> elements) {
		elements = new ArrayList<Element>(elements);
		for (int i = 0; i < elements.size(); i++)
			if (!list.contains(elements.get(i).getColor()))
				elements.remove(i--);
		return elements;
	}

	@Override
	public void setup() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void turn(Player player) {
		// TODO Auto-generated method stub
		
	}

}
