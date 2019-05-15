package underlings.gui;

import java.awt.Dimension;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.swing.JOptionPane;

import underlings.card.Card;
import underlings.card.EmptyCard;
import underlings.element.Element;
import underlings.element.ElementBag;
import underlings.element.ElementColor;
import underlings.element.ElementGiver;
import underlings.element.ElementSpace;
import underlings.element.NullElement;
import underlings.game.HatchingGround;
import underlings.handler.Handler;
import underlings.handler.HandlerChoice;
import underlings.handler.HandlerDecision;
import underlings.player.FakePlayer;
import underlings.player.Player;
import underlings.utilities.LocaleWrap;

public class Gui {

    private PromptHandler promptHandler;
    private Display display;
    private Map<PromptType, Integer> promptTypes;

    public enum PromptType {
        REGULAR, WARNING, ERROR;
    }

    public Gui(PromptHandler promptHandler, Display display) {
        this.promptHandler = promptHandler;
        this.display = display;
        this.promptTypes = new HashMap<>();
        this.promptTypes.put(PromptType.ERROR, JOptionPane.ERROR_MESSAGE);
        this.promptTypes.put(PromptType.REGULAR, JOptionPane.PLAIN_MESSAGE);
        this.promptTypes.put(PromptType.WARNING, JOptionPane.WARNING_MESSAGE);
    }

    public DrawChoice getDrawChoice(List<ElementGiver> elementGivers, int playerId) {
        ElementGiver elementGiver =
                this.promptHandler.promptChoice(LocaleWrap.get("gui_element_giver"), elementGivers, playerId);
        elementGivers.remove(elementGiver);
        return this.promptHandler.promptChoice(LocaleWrap.get("gui_draw_choice"), elementGiver.drawChoices, playerId);
    }

    public boolean getMoreMovesDecision(int choiceNum, int playerId) {
        if (choiceNum == 0) {
            return false;
        }

        return this.promptHandler.promptDecision(LocaleWrap.get("gui_more_moves"), playerId);
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

    public int getFieldSpace(int playerId) {
        // 21 0 1 2 3 4 5 6 7
        // 20 8
        // 19 9
        // 18 blue green yellow blue red orange black 10

        Integer[][] field = new Integer[4][9];
        field[0][0] = 21;
        field[0][1] = 0;
        field[0][2] = 1;
        field[0][3] = 2;
        field[0][4] = 3;
        field[0][5] = 4;
        field[0][6] = 5;
        field[0][7] = 6;
        field[0][8] = 7;
        field[1][0] = 20;
        field[1][8] = 8;
        field[2][0] = 19;
        field[2][8] = 9;
        field[3][0] = 18;
        field[3][1] = 17;
        field[3][2] = 16;
        field[3][3] = 15;
        field[3][4] = 14;
        field[3][5] = 13;
        field[3][6] = 12;
        field[3][7] = 11;
        field[3][8] = 10;

        String prompt = LocaleWrap.get("gui_field_space");
        Integer val = this.promptHandler.pickFromGrid(prompt, field, playerId);

        return (int) val;
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

    public Card getEggToHatch(Card[][] cards, int maxPoints, Player player) {
        Card[][] validCards = new Card[cards.length][cards[0].length];

        boolean noValidCards = true;
        for (int row = 0; row < cards.length; row++) {
            for (int col = 0; col < cards[row].length; col++) {
                if (cards[row][col].points <= maxPoints && cards[row][col].handler == null) {
                    validCards[row][col] = (cards[row][col]);
                    noValidCards = false;
                }
            }
        }

        if (noValidCards) {
            return EmptyCard.getInstance();
        } else {
            Card choice = this.promptHandler.pickFromGrid(LocaleWrap.get("gui_card"), validCards, player.getPlayerId());
            return choice;
        }
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

    public ElementSpace getElementSpaceContainingElementOfColors(List<Card> cards, ElementColor[] colorChoices) {
        // TODO: implement this method for CollectUpToElementsFromAnyEggInPlayEffect,
        // ask Mohammad for information
        return null;
    }

    public boolean promptDecision(String message, int playerId) {
        // TODO
        return true;
    }

    public Card promptCard(String message, List<Card> cards) {
        // TODO
        return EmptyCard.getInstance();
    }

    public Player promptPlayer(String message, Player currentPlayer, List<Player> players) {
        // TODO, Mohammad is using this for Ignatius's StealAllStoredElementsEffect
        return null;
    }

    public Card promptCardToSteal(String message, int playerId, Map<Player, List<Card>> playerCards) {
        // TODO
        return null;
    }

    public Handler promptHandler(String message, int playerId, List<Handler> handlers) {
        // TODO
        return null;
    }

    public void alert(String message, PromptType messageType) {
        this.promptHandler.displayMessage(message, FakePlayer.getInstance().getPlayerId(),
                promptTypes.get(messageType));
    }

    public void alert(String message, int playerId, PromptType messageType) {
        this.promptHandler.displayMessage(message, playerId, promptTypes.get(messageType));
    }

}
