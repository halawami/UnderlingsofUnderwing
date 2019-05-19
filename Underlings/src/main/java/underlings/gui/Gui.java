package underlings.gui;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import javax.swing.JOptionPane;
import underlings.card.Card;
import underlings.card.EmptyCard;
import underlings.element.Element;
import underlings.element.ElementBag;
import underlings.element.ElementColor;
import underlings.element.ElementSpace;
import underlings.element.NullElement;
import underlings.field.Field;
import underlings.field.FieldSpace;
import underlings.handler.Handler;
import underlings.handler.HandlerChoice;
import underlings.handler.HandlerDecision;
import underlings.hatchingground.HatchingGround;
import underlings.player.Player;
import underlings.utilities.LocaleUtilities;

public class Gui {

    private PromptHandler promptHandler;
    private Display display;

    public enum PromptType {
        REGULAR, WARNING, ERROR;

        public int messageType;

        static {
            REGULAR.messageType = JOptionPane.PLAIN_MESSAGE;
            WARNING.messageType = JOptionPane.WARNING_MESSAGE;
            ERROR.messageType = JOptionPane.ERROR_MESSAGE;
        }

    }

    public Gui(PromptHandler promptHandler, Display display) {
        this.promptHandler = promptHandler;
        this.display = display;
    }

    public HandlerDecision getHandlerDecision(List<Handler> handlers, int playerId, HatchingGround hatchingGround) {
        Handler handler = this.promptHandler.promptChoice(LocaleUtilities.get("gui_handler"), handlers, playerId);

        handlers.remove(handler);

        List<HandlerChoice> possibleChoices = handler.getPossibleChoices();
        if (hatchingGround.getUnclaimedEggs().isEmpty()) {
            possibleChoices.remove(HandlerChoice.CARD);
        }

        HandlerChoice handlerChoice = this.promptHandler.promptChoice(
                MessageFormat.format(LocaleUtilities.get("gui_handler_choice"), handler), possibleChoices, playerId);

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

        return this.promptHandler.pickFromGrid(prompt, cards, playerId);
    }

    public void notifyAction(int playerId, String message) {
        this.promptHandler.displayMessage(message, playerId, JOptionPane.PLAIN_MESSAGE);
    }

    public FieldSpace getFieldSpace(Player player, Field field) {
        FieldSpace[][] grid = field.getValidFieldSpaces(player);

        String prompt = LocaleUtilities.get("gui_field_space");
        FieldSpace val = this.promptHandler.pickFromGrid(prompt, grid, player.getId());

        return val;
    }

    public int getPlayerCount(int minPlayers, int maxPlayers) {
        return this.promptHandler.promptInt(LocaleUtilities.get("prompt_player_count"), minPlayers, maxPlayers);
    }

    public Element getElementOfColorsFromSpace(ElementColor[] elementChoices, ElementSpace elementSpace,
            int playerNum) {
        List<ElementColor> validColors = new ArrayList<>(Arrays.asList(elementChoices));
        List<Element> colors = new ArrayList<>();

        for (Element element : elementSpace.elements) {
            if (validColors.contains(element.getColor())) {
                colors.add(element);
            }
        }
        colors.add(NullElement.getInstance());

        List<Element> choices = new ArrayList<>(colors);
        return this.promptHandler.promptChoice(LocaleUtilities.get("gui_element_collect"), choices, playerNum);
    }

    public Locale promptLocale(Locale[] locales) {
        Locale locale;
        do {
            locale = this.promptHandler.promptChoiceDropdown(LocaleUtilities.get("choose_language"),
                    Arrays.asList(locales), Locale.ENGLISH);
        } while (locale == null);
        return locale;
    }

    public void display(int roundsLeft, int currentPhase, int turnLeader, HatchingGround hatchingGround,
            List<Player> players, ElementBag elementBag) {
        this.display.displayBackground();
        this.display.displayHatchingGround(hatchingGround);
        this.display.displayPlayers(players);
        this.display.displayStats(elementBag, roundsLeft, currentPhase, turnLeader + 1);
        this.display.update();
    }

    public <T> T promptChoice(String prompt, List<T> choices, int playerId) {
        return this.promptHandler.promptChoice(prompt, choices, playerId);
    }

    public ElementSpace getElementSpaceWithColors(List<Card> cards, ElementColor[] colorChoices, int playerId) {
        List<Card> cardOptions = new ArrayList<>();
        for (Card card : cards) {
            if (!this.getSpacesWithColors(card, colorChoices).isEmpty()) {
                cardOptions.add(card);
            }
        }
        if (cardOptions.isEmpty()) {
            return null;
        }

        YesNoChoice choice = this.promptHandler.promptChoice(LocaleUtilities.get("take_element_yesno"),
                YesNoChoice.getChoices(), playerId);
        if (choice == YesNoChoice.NO) {
            return null;
        }

        Card card = this.promptHandler.promptChoice(LocaleUtilities.get("take_element_card"), cardOptions, playerId);

        List<ElementSpace> validSpaces = this.getSpacesWithColors(card, colorChoices);
        return this.getElementSpace(LocaleUtilities.get("take_element_space"), validSpaces, playerId);
    }

    public ElementSpace getElementSpace(String prompt, List<ElementSpace> spaces, int playerId) {
        ElementSpace[][] spaceGrid = this.getElementSpaceGrid(spaces);
        ElementSpace space = this.promptHandler.pickFromGrid(prompt, spaceGrid, playerId);
        return space;
    }

    public ElementSpace[][] getElementSpaceGrid(List<ElementSpace> validSpaces) {
        ElementSpace[][] spaceGrid = new ElementSpace[4][2];
        for (ElementSpace space : validSpaces) {
            spaceGrid[space.position.posY][space.position.posX] = space;
        }
        return spaceGrid;
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
        this.promptHandler.displayMessage(message, messageType.messageType);
    }

    public void alert(String message, int playerId, PromptType messageType) {
        this.promptHandler.displayMessage(message, playerId, messageType.messageType);
    }

    public List<Card> reorderCards(List<Card> cards) {
        while (cards.contains(EmptyCard.getInstance())) {
            cards.remove(EmptyCard.getInstance());
        }

        List<Card> ordered = new ArrayList<>();
        while (!cards.isEmpty()) {
            Card card = this.promptChoice(LocaleUtilities.get("choose_card"), cards, 0);
            cards.remove(card);
            ordered.add(card);
        }
        return ordered;
    }

}
