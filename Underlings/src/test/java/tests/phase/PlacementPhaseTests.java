package tests.phase;

import java.util.Random;

import org.junit.Test;

import underlings.card.construction.CardFactory;
import underlings.element.ElementBag;
import underlings.element.ElementFactory;
import underlings.game.Deck;
import underlings.game.Game;
import underlings.game.HatchingGround;
import underlings.gui.GUI;
import underlings.gui.LameGUI;
import underlings.gui.LamePrompt;
import underlings.handler.HandlerFactory;
import underlings.player.PlayerFactory;

public class PlacementPhaseTests {

	@Test
	public void basicTest() {
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
	}
}
