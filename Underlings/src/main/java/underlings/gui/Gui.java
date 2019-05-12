package underlings.gui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.function.Function;
import javax.swing.JOptionPane;
import underlings.card.Card;
import underlings.card.EmptyCard;
import underlings.element.Element;
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

    public PromptHandler promptHandler;
    public Display display;

    public Gui(PromptHandler promptHandler, Display display) {
        this.promptHandler = promptHandler;
        this.display = display;
    }

    public DrawChoice getDrawChoice(List<ElementGiver> elementGivers, int playerId) {
        ElementGiver elementGiver = this.promptHandler.promptChoice("Choose an Element Giver", elementGivers, playerId);
        elementGivers.remove(elementGiver);
        return this.promptHandler.promptChoice("Choose a Draw Choice", elementGiver.drawChoices, playerId);
    }

    public boolean getMoreMovesDecision(int choiceNum, int playerId) {
        if (choiceNum == 0) {
            return false;
        }

        return this.promptHandler.promptDecision("Would you like to place another element?", playerId);
    }

    public boolean promptDecision(String toDisplay, int playerId) {
        return true;
    }

    public HandlerDecision getHandlerDecision(List<Handler> handlers, int playerId, HatchingGround hatchingGround) {
        Handler handler = this.promptHandler.promptChoice("Choose a Handler", handlers, playerId);
        handlers.remove(handler);

        List<HandlerChoice> possibleChoices = handler.getPossibleChoices();
        possibleChoices = new ArrayList<>(possibleChoices);
        if (hatchingGround.getUnclaimedEggs().isEmpty()) {
            possibleChoices.remove(HandlerChoice.CARD);
        }

        HandlerChoice handlerChoice =
                this.promptHandler.promptChoice("Choose a movement for " + handler, possibleChoices, playerId);

        return new HandlerDecision(handler, handlerChoice);
    }

    public Card getCard(int playerId, String prompt, HatchingGround hatchingGround, Function<Card, Boolean> isValid) {
        Card[][] cards = new Card[hatchingGround.getHeight()][hatchingGround.getWidth()];
        for (int w = 0; w < cards.length; w++) {
            for (int h = 0; h < cards[w].length; h++) {
                if (isValid.apply(hatchingGround.cards[w][h])) {
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
        return this.promptHandler.promptInt("Enter Field Space", 0, 21);
    }

    public int getPlayerCount(int minPlayers, int maxPlayers) {
        return this.promptHandler.promptInt(LocaleWrap.get("prompt_player_count"), minPlayers, maxPlayers);
    }

    public ElementSpace getElementSpaceContainingElementOfColors(List<Card> cards, ElementColor[] colorChoices) {
        // TODO: implement this method for CollectUpToTwoElementsFromAnyEggInPlayEffect,
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
        return this.promptHandler.promptChoice("Pick an element to collect.", choices, playerNum);
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
            Card choice = this.promptHandler.pickCard("Choose a card", validCards, player.getPlayerId());
            return choice;
        }
    }

    public Card promptCard(String toDisplay, List<Card> cards) {
        return EmptyCard.getInstance();
    }

    public Locale promptLocale(Locale[] locales) {
        return this.promptHandler.promptChoiceDropdown("Choose Language", Arrays.asList(locales), Locale.ENGLISH);
    }
}
