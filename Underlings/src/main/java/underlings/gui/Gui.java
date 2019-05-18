package underlings.gui;

import java.awt.Dimension;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.swing.JOptionPane;

import underlings.card.Card;
import underlings.element.Element;
import underlings.element.ElementBag;
import underlings.element.ElementColor;
import underlings.element.ElementSpace;
import underlings.element.NullElement;
import underlings.field.Field;
import underlings.field.FieldSpace;
import underlings.game.HatchingGround;
import underlings.handler.Handler;
import underlings.handler.HandlerChoice;
import underlings.handler.HandlerDecision;
import underlings.player.Player;
import underlings.utilities.LocaleWrap;

public class Gui {

    private PromptHandler promptHandler;
    private Display display;

    public enum PromptType {
        REGULAR, WARNING, ERROR;

        public int jOptionMessageType;

        static {
            REGULAR.jOptionMessageType = JOptionPane.PLAIN_MESSAGE;
            WARNING.jOptionMessageType = JOptionPane.WARNING_MESSAGE;
            ERROR.jOptionMessageType = JOptionPane.ERROR_MESSAGE;
        }

    }

    public Gui(PromptHandler promptHandler, Display display) {
        this.promptHandler = promptHandler;
        this.display = display;
    }

    public HandlerDecision getHandlerDecision(List<Handler> handlers, int playerId, HatchingGround hatchingGround) {
        Handler handler = this.promptHandler.promptChoice(LocaleWrap.get("gui_handler"), handlers, playerId);
        handlers.remove(handler);

        List<HandlerChoice> possibleChoices = handler.getPossibleChoices();
        possibleChoices = new ArrayList<>(possibleChoices);
        if (hatchingGround.getUnclaimedEggs().isEmpty()) {
            possibleChoices.remove(HandlerChoice.CARD);
        }

        HandlerChoice handlerChoice = this.promptHandler.promptChoice(
                MessageFormat.format(LocaleWrap.get("gui_handler_choice"), handler), possibleChoices, playerId);

        return new HandlerDecision(handler, handlerChoice);
    }

    public Card getCard(int playerId, String prompt, HatchingGround hatchingGround, List<Card> validCards) {
        Card[][] cards = new Card[hatchingGround.getHeight()][hatchingGround.getWidth()];
        for (int w = 0; w < cards.length; w++) {
            for (int h = 0; h < cards[w].length; h++) {
                if (validCards.contains(hatchingGround.cards[w][h])) {
                    cards[w][h] = hatchingGround.cards[w][h];
                }
            }
        }

        Card card = this.promptHandler.pickFromGrid(prompt, cards, playerId);
        return card;
    }

    public void notifyAction(int playerId, String message) {
        this.promptHandler.displayMessage(message, playerId, JOptionPane.PLAIN_MESSAGE);
    }

    public FieldSpace getFieldSpace(Player player, Field field) {
        FieldSpace[][] grid = field.getGrid();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                FieldSpace space = grid[i][j];

                if (space == null) {
                    continue;
                }

                int count = 0;
                for (Handler handler : player.getHandlers()) {
                    if (space.contains(handler)) {
                        count++;
                    }
                }

                if (count == player.maxHandlersOnSpace) {
                    grid[i][j] = null;
                }
            }
        }

        String prompt = LocaleWrap.get("gui_field_space");
        FieldSpace val = this.promptHandler.pickFromGrid(prompt, grid, player.getId());

        return val;
    }

    public int getPlayerCount(int minPlayers, int maxPlayers) {
        return this.promptHandler.promptInt(LocaleWrap.get("prompt_player_count"), minPlayers, maxPlayers);
    }

    public Element getElementOfColorsFromSpace(ElementColor[] elementChoices, ElementSpace elementSpace,
            int playerNum) {
        List<ElementColor> validColors = new ArrayList<>(Arrays.asList(elementChoices));
        Set<Element> colors = new HashSet<>();

        for (Element element : elementSpace.elements) {
            if (validColors.contains(element.getColor())) {
                colors.add(element);
            }
        }
        colors.add(NullElement.getInstance());

        List<Element> choices = new ArrayList<>(colors);
        return this.promptHandler.promptChoice(LocaleWrap.get("gui_element_collect"), choices, playerNum);
    }

    public Locale promptLocale(Locale[] locales) {
        Locale locale;
        do {
            locale = this.promptHandler.promptChoiceDropdown(LocaleWrap.get("choose_language"), Arrays.asList(locales),
                    Locale.ENGLISH);
        } while (locale == null);
        return locale;
    }

    public void displayPlayers(List<Player> players) {

        for (int playerNumber = 0; playerNumber < players.size(); playerNumber++) {
            Player player = players.get(playerNumber);
            this.display.displayPlayer(playerNumber, player);
            List<Handler> handlers = player.getHandlers();
            this.display.displayHandlers(playerNumber, handlers);
        }

    }

    public void display(int roundsLeft, int currentPhase, int turnLeader, HatchingGround hatchingGround,
            List<Player> players, ElementBag elementBag) {
        this.display.displayBackground();
        this.displayHatchingGround(hatchingGround);
        this.displayPlayers(players);
        this.display.displayStats(elementBag, roundsLeft, currentPhase, turnLeader + 1);
        this.display.update();
    }

    public void displayHatchingGround(HatchingGround hatchingGround) {
        Dimension hgDimensions = hatchingGround.getDimensions();

        for (int row = 0; row < hgDimensions.height; row++) {
            for (int col = 0; col < hgDimensions.width; col++) {
                this.display.displayCard(row, col, hatchingGround.cards[row][col]);
            }
        }
    }

    public <T> T promptChoice(String prompt, List<T> choices, int playerId) {
        return this.promptHandler.promptChoice(prompt, choices, playerId);
    }

    public ElementSpace getElementSpaceWithColors(List<Card> cards, ElementColor[] colorChoices, int playerId) {
        List<Card> cardOptions = new ArrayList<>();
        for (Card card : cards) {
            if (!getSpacesWithColors(card, colorChoices).isEmpty()) {
                cardOptions.add(card);
            }
        }
        if (cardOptions.isEmpty()) {
            return null;
        }

        YesNoChoice choice = this.promptHandler.promptChoice(LocaleWrap.get("take_element_yesno"),
                YesNoChoice.getChoices(), playerId);
        if (choice == YesNoChoice.NO) {
            return null;
        }

        Card card = this.promptHandler.promptChoice(LocaleWrap.get("take_element_card"), cardOptions, playerId);
        List<ElementSpace> spaces = getSpacesWithColors(card, colorChoices);
        ElementSpace space = this.promptHandler.promptChoice(LocaleWrap.get("take_element_space"), spaces, playerId);
        return space;
    }

    private List<ElementSpace> getSpacesWithColors(Card card, ElementColor[] colorChoices) {
        List<ElementSpace> spaces = new ArrayList<>();
        for (ElementSpace space : card.elementSpaces) {
            for (ElementColor color : colorChoices) {
                if (space.getElementColors().contains(color)) {
                    spaces.add(space);
                    break;
                }
            }
        }
        return spaces;
    }

    public void alert(String message, PromptType messageType) {
        this.promptHandler.displayMessage(message, messageType.jOptionMessageType);
    }

    public void alert(String message, int playerId, PromptType messageType) {
        this.promptHandler.displayMessage(message, playerId, messageType.jOptionMessageType);
    }

    public List<Card> reorderCards(List<Card> cards) {
        return null;
    }

}
