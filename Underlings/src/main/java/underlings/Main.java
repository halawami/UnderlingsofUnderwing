package underlings;

import underlings.card.construction.CardFactory;
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
import underlings.phase.HandlerPhase;
import underlings.phase.Phase;
import underlings.player.PlayerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

	private final static String CARDS_JSON_FILE_PATH = "\\src\\main\\java\\underlings\\card\\construction\\cards.json";

	public static void main(String[] args ) {

		GUI gui = new GUI(new LamePrompt(), new LameGUI());
		CardFactory cardFactory = new CardFactory(CARDS_JSON_FILE_PATH);
		Deck deck = new Deck(cardFactory.getCards());
		HatchingGround hatchingGround = new HatchingGround(deck);
		HandlerFactory handlerFactory = new HandlerFactory();
		PlayerFactory playerFactory = new PlayerFactory(handlerFactory);
		
		ElementFactory elementFactory = new ElementFactory();
		Random random = new Random();
		ElementBag elementBag = new ElementBag(elementFactory, random);
		
		Game game = new Game(gui, hatchingGround, playerFactory, elementBag);
		
		List<Phase> phases = new ArrayList<>();
		phases.add(new ElementPhase());
		phases.add(new HandlerPhase());
		
		game.start(phases);
		
	}
	
}
