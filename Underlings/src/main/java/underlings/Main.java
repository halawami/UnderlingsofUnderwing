package underlings;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import underlings.card.CardFactory;
import underlings.element.ElementBag;
import underlings.element.ElementFactory;
import underlings.element.utilities.ElementSpaceLogic;
import underlings.field.Field;
import underlings.field.FieldSpaceFactory;
import underlings.game.Deck;
import underlings.game.Game;
import underlings.game.HatchingGround;
import underlings.gui.ConcreteDisplay;
import underlings.gui.Gui;
import underlings.handler.HandlerFactory;
import underlings.handler.HandlerMovementLogic;
import underlings.phase.DragonPhase;
import underlings.phase.DrawingPhase;
import underlings.phase.FinalPhase;
import underlings.phase.FinalPhase.FinalPhaseType;
import underlings.phase.HandlerPhase;
import underlings.phase.Phase;
import underlings.phase.PlacementPhase;
import underlings.phase.RegularFinalPhase;
import underlings.phase.WildFinalPhase;
import underlings.player.FakePlayer;
import underlings.player.PlayerFactory;
import underlings.scoring.ScoreUtils;
import underlings.utilities.EggHatchingLogic;
import underlings.utilities.LocaleWrap;
import underlings.utilities.PlacementUtilities;

public class Main {

    private static final String CARDS_JSON_FILE_NAME = LocaleWrap.get("cards_json");

    public static void main(String[] args) {
        List<String> recipes = null;
        try {
            recipes = Resources.readLines(Resources.getResource(LocaleWrap.get("default_recipe_list")), Charsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        FakePlayer.initPlayer(recipes);
        Gui gui = new Gui(new TestPrompt(), new ConcreteDisplay());
        CardFactory cardFactory = new CardFactory(CARDS_JSON_FILE_NAME);
        Deck deck = new Deck(cardFactory.getCards(), Collections::shuffle);
        deck.shuffle();
        HatchingGround hatchingGround = new HatchingGround(deck, new ElementSpaceLogic(recipes));
        HandlerFactory handlerFactory = new HandlerFactory();
        PlayerFactory playerFactory = new PlayerFactory(handlerFactory, recipes);
        FieldSpaceFactory fieldSpaceFactory = new FieldSpaceFactory();
        Field field = new Field(fieldSpaceFactory);
        HandlerMovementLogic handlerMovementLogic = new HandlerMovementLogic(hatchingGround, gui, field);

        ElementFactory elementFactory = new ElementFactory();
        Random random = new Random();
        ElementBag elementBag = new ElementBag(elementFactory, random);

        Game game = new Game(gui, hatchingGround, playerFactory, elementBag);
        Runnable gameDisplay = game::display;

        EggHatchingLogic eggHatchingLogic =
                new EggHatchingLogic(gui, elementBag, hatchingGround, gameDisplay, game.getPlayers(), deck, handlerMovementLogic);

        List<Phase> phases = new ArrayList<>();
        phases.add(new DrawingPhase(game.getPlayers(), gui, elementBag, hatchingGround, gameDisplay, field));
        phases.add(new HandlerPhase(game.getPlayers(), gui, elementBag, hatchingGround, gameDisplay, field,
                handlerMovementLogic));

        PlacementUtilities placementUtils = new PlacementUtilities(hatchingGround, gui, gameDisplay);
        phases.add(new PlacementPhase(game.getPlayers(), gui, hatchingGround, gameDisplay, eggHatchingLogic,
                placementUtils));
        DragonPhase dragonPhase = new DragonPhase(game.getPlayers(), gui, elementBag, hatchingGround, gameDisplay,
                field, eggHatchingLogic);
        phases.add(dragonPhase);
        Map<FinalPhaseType, FinalPhase> finalPhaseMap = new HashMap<>();
        ScoreUtils scoreUtils = new ScoreUtils(game.getPlayers(), gui);
        finalPhaseMap.put(FinalPhaseType.REGULAR,
                new RegularFinalPhase(game.getPlayers(), gui, dragonPhase, scoreUtils));
        finalPhaseMap.put(FinalPhaseType.WILD, new WildFinalPhase(gui));

        Locale locale = gui.promptLocale(Locale.getAvailableLocales());
        new LocaleWrap().setLocale(locale);

        game.start(phases, finalPhaseMap);

    }

}
