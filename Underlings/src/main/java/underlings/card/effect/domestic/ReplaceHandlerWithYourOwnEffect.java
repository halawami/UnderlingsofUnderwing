package underlings.card.effect.domestic;

import java.util.List;

import underlings.card.Card;
import underlings.card.effect.HandlerEffect;
import underlings.game.HatchingGround;
import underlings.gui.Gui;
import underlings.handler.Handler;
import underlings.handler.HandlerChoice;
import underlings.handler.HandlerMovementLogic;
import underlings.player.Player;
import underlings.utilities.LocaleWrap;

public class ReplaceHandlerWithYourOwnEffect extends HandlerEffect {

    @Override
    protected void apply(Player currentPlayer, HatchingGround hatchingGround, HandlerMovementLogic handlerLogic,
            Gui gui) {
        List<Card> claimedEggs = hatchingGround.getClaimedEggs();
        if (!claimedEggs.isEmpty()) {
            Card chosenEgg = gui.promptChoice("Choose a card to replace its handler", claimedEggs, currentPlayer.id);
            Handler chosenHandler = gui.promptChoice("Choose a handler to replace with", currentPlayer.getHandlers(),
                    currentPlayer.getId());
            handlerLogic.move(chosenEgg.handler, HandlerChoice.BREAK_ROOM, currentPlayer);
            handlerLogic.moveToCard(chosenHandler, chosenEgg);
        }
    }

    @Override
    public String toString() {
        return LocaleWrap.get("replace_handler_effect");
    }
}
