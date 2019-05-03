

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import card.CardFactory;
import element.ElementBag;
import element.ElementFactory;
import field.Field;
import field.FieldSpaceFactory;
import game.Deck;
import game.Game;
import game.HatchingGround;
import gui.Gui;
import gui.LameGui;
import gui.LamePrompt;
import handler.HandlerFactory;
import handler.HandlerMovementLogic;
import phase.DragonPhase;
import phase.DrawingPhase;
import phase.FinalPhase;
import phase.HandlerPhase;
import phase.Phase;
import phase.PlacementPhase;
import player.PlayerFactory;

public class Main {

    private static final String CARDS_JSON_FILE_NAME = "cards.json";

    public static void main(String[] args) {

        Gui gui = new Gui(new LamePrompt(), new LameGui());
        CardFactory cardFactory = new CardFactory(CARDS_JSON_FILE_NAME);
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
        Runnable gameDisplay = () -> {
            game.display();
        };

        List<Phase> phases = new ArrayList<>();
        phases.add(new DrawingPhase(game.getPlayers(), gui, elementBag, hatchingGround, gameDisplay, field));
        phases.add(new HandlerPhase(game.getPlayers(), gui, elementBag, hatchingGround, gameDisplay, field,
                handlerMovementLogic));
        phases.add(new PlacementPhase(game.getPlayers(), gui, elementBag, hatchingGround, gameDisplay, field));
        phases.add(new DragonPhase(game.getPlayers(), gui, elementBag, hatchingGround, gameDisplay, field));

        Phase finalPhase = new FinalPhase(game.getPlayers(), gui, elementBag, hatchingGround, gameDisplay, field);

        game.start(phases, finalPhase);

    }

}
