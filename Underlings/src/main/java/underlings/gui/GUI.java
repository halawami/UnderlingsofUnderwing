package underlings.gui;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import underlings.card.Card;
import underlings.element.ElementGiver;
import underlings.game.HatchingGround;
import underlings.handler.Handler;
import underlings.handler.HandlerChoice;
import underlings.handler.HandlerDecision;

public class GUI {
    public PromptHandler promptHandler;
    public Display display;

    public GUI(PromptHandler promptHandler, Display display) {
        this.promptHandler = promptHandler;
        this.display = display;
    }

    public DrawChoice getDrawChoice(List<ElementGiver> elementGivers,
            int playerId) {
        ElementGiver elementGiver = this.promptHandler.promptChoice(
                "Choose an Element Giver", elementGivers, playerId);
        elementGivers.remove(elementGiver);
        return this.promptHandler.promptChoice("Choose a Draw Choice",
                elementGiver.drawChoices, playerId);
    }

    public HandlerDecision getHandlerDecision(List<Handler> handlers,
            int playerId, HatchingGround hatchingGround) {
        Handler handler = this.promptHandler.promptChoice("Choose a Handler",
                handlers, playerId);
        handlers.remove(handler);

        List<HandlerChoice> possibleChoices = handler.getPossibleChoices();
        possibleChoices = new ArrayList<>(possibleChoices);
        if (hatchingGround.getUnclaimedEggs().isEmpty()) {
            possibleChoices.remove(HandlerChoice.CARD);
        }

        HandlerChoice handlerChoice = this.promptHandler.promptChoice(
                "Choose a movement for " + handler, possibleChoices, playerId);

        return new HandlerDecision(handler, handlerChoice);
    }

    public Card getCard(List<Card> cards, int playerId) {
        Card card = this.promptHandler.promptChoice("Choose a card", cards,
                playerId);
        return card;
    }

    public void notifyAction(int playerId, String message) {
        this.promptHandler.displayMessage(message, playerId,
                JOptionPane.PLAIN_MESSAGE);
    }

    public int getFieldSpace() {
        return this.promptHandler.promptInt("Enter Field Space", 0, 21);
    }

    public int getPlayerCount(int minPlayers, int maxPlayers) {
        return this.promptHandler.promptInt("Enter Player Count", minPlayers,
                maxPlayers);
    }

}
