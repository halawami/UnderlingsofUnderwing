package underlings;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import underlings.card.CardFactory;
import underlings.element.ElementBag;
import underlings.element.ElementFactory;
import underlings.game.Deck;
import underlings.game.Game;
import underlings.game.HandlerFactory;
import underlings.game.HatchingGround;
import underlings.gui.GUI;
import underlings.gui.LameGUI;
import underlings.gui.LamePrompt;
import underlings.phase.ElementPhase;
import underlings.player.PlayerFactory;

public class Main {

	public static void main(String[] args ) {

		GUI gui = new GUI(new LamePrompt(), new LameGUI());
		CardFactory cardFactory = new CardFactory();
		Deck deck = new Deck(cardFactory.getCards());
		HatchingGround hatchingGround = new HatchingGround(deck);
		HandlerFactory handlerFactory = new HandlerFactory();
		PlayerFactory playerFactory = new PlayerFactory(handlerFactory);
		
		ElementFactory elementFactory = new ElementFactory();
		Random random = new Random();
		ElementBag elementBag = new ElementBag(elementFactory, random);
		
		Game game = new Game(gui, hatchingGround, playerFactory, elementBag);
		
		List<ElementPhase> elementPhases = new ArrayList<ElementPhase>();
		elementPhases.add(new ElementPhase());
		
		game.start(elementPhases);
		
	}
	
}
