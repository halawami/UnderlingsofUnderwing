package underlings.gui;

import java.awt.Dimension;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
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
import underlings.player.Player;
import underlings.utilities.LocaleWrap;

public class Gui {

    protected PromptHandler promptHandler;
    protected Display display;

    public enum PromptType {
        REGULAR, WARNING, ERROR;
    }

    public Gui(PromptHandler promptHandler, Display display) {
        this.promptHandler = promptHandler;
        this.display = display;
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

    public boolean promptDecision(String toDisplay, int playerId) {
        // TODO
        return true;
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

        Card card = this.promptHandler.pickCard(prompt, cards, playerId);
        return card;
    }

    public void notifyAction(int playerId, String message) {
        this.promptHandler.displayMessage(message, playerId, JOptionPane.PLAIN_MESSAGE);
    }

    public int getFieldSpace() {
        return this.promptHandler.promptInt(LocaleWrap.get("gui_field_space"), 0, 21);
    }

    public int getPlayerCount(int minPlayers, int maxPlayers) {
        return this.promptHandler.promptInt(LocaleWrap.get("prompt_player_count"), minPlayers, maxPlayers);
    }

    public ElementSpace getElementSpaceContainingElementOfColors(List<Card> cards, ElementColor[] colorChoices) {
        // TODO: implement this method for CollectUpToElementsFromAnyEggInPlayEffect,
        // ask Mohammad for information
        return null;
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
            Card choice = this.promptHandler.pickCard(LocaleWrap.get("gui_card"), validCards, player.getPlayerId());
            return choice;
        }
    }

    public Card promptCard(String toDisplay, List<Card> cards) {
        // TODO
        return EmptyCard.getInstance();
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

    public Player promptPlayer(String toDispaly, Player currentPlayer, List<Player> players) {
        // TODO, Mohammad is using this for Ignatius's StealAllStoredElementsEffect
        return null;
    }

    public Card promptCardToSteal(String toDispaly, int playerId, Map<Player, List<Card>> playerCards) {
        // TODO
        return null;
    }

    public void alert(String message, PromptType messageType) {

    }

    public void alert(String message, int playerId, PromptType messageType) {

    }

    public <T> T promptChoice(String prompt, List<T> choices, int playerId) {
        return this.promptHandler.promptChoice(prompt, choices, playerId);
    }

}
