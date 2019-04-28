package underlings;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import underlings.card.construction.CardFactory;
import underlings.element.ElementBag;
import underlings.element.ElementFactory;
import underlings.field.Field;
import underlings.field.FieldSpaceFactory;
import underlings.game.Deck;
import underlings.game.Game;
import underlings.game.HatchingGround;
import underlings.gui.GUI;
import underlings.gui.LameGUI;
import underlings.gui.TestPrompt;
import underlings.handler.HandlerFactory;
import underlings.handler.HandlerMovementLogic;
import underlings.phase.DragonPhase;
import underlings.phase.DrawingPhase;
import underlings.phase.FinalPhase;
import underlings.phase.HandlerPhase;
import underlings.phase.Phase;
import underlings.phase.PlacementPhase;
import underlings.player.PlayerFactory;

public class Main {

	private final static String CARDS_JSON_FILE_PATH = "\\src\\main\\java\\underlings\\card\\construction\\cards.json";

	public static void main(String[] args ) {

		GUI gui = new GUI(new TestPrompt(), new LameGUI());
		CardFactory cardFactory = new CardFactory(CARDS_JSON_FILE_PATH);
		Deck deck = new Deck(cardFactory.getCards());
		HatchingGround hatchingGround = new HatchingGround(deck);
		HandlerFactory handlerFactory = new HandlerFactory();
		PlayerFactory playerFactory = new PlayerFactory(handlerFactory);
		FieldSpaceFactory fieldSpaceFactory = new FieldSpaceFactory();
		Field field = new Field(fieldSpaceFactory);
		HandlerMovementLogic handlerMovementLogic = new HandlerMovementLogic(hatchingGround, gui, field);

		ElementFactory elementFactory = new ElementFactory();
		Random random = new Random();
		ElementBag elementBag = new ElementBag(elementFactory, random);

		Game game = new Game(gui, hatchingGround, playerFactory, elementBag);

		List<Phase> phases = new ArrayList<>();
		phases.add(new DrawingPhase(game.getPlayers(), gui, elementBag, hatchingGround, () -> {game.display();}, field));
		phases.add(new HandlerPhase(game.getPlayers(), gui, elementBag, hatchingGround, () -> {game.display();}, field, handlerMovementLogic));
		phases.add(new PlacementPhase(game.getPlayers(), gui, elementBag, hatchingGround, () -> {game.display();}, field));
		phases.add(new DragonPhase(game.getPlayers(), gui, elementBag, hatchingGround, () -> {game.display();}, field));

		Phase finalPhase = new FinalPhase(game.getPlayers(), gui, elementBag, hatchingGround, () -> {game.display();}, field);

		game.start(phases, finalPhase);
		
	}
	
}
