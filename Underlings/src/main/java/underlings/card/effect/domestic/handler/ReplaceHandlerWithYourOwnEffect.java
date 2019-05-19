package underlings.card.effect.domestic.handler;

import java.util.List;
import underlings.card.Card;
import underlings.card.effect.HandlerEffect;
import underlings.gui.Gui;
import underlings.handler.Handler;
import underlings.handler.HandlerChoice;
import underlings.handler.HandlerMovementLogic;
import underlings.hatchingground.HatchingGround;
import underlings.player.Player;
import underlings.utilities.LocaleUtilities;

public class ReplaceHandlerWithYourOwnEffect extends HandlerEffect {

    @Override
    protected void apply(Player currentPlayer, HatchingGround hatchingGround, HandlerMovementLogic handlerLogic,
            Gui gui) {
        List<Card> claimedEggs = hatchingGround.getClaimedEggs();
        if (!claimedEggs.isEmpty()) {
            Card chosenEgg =
                    gui.promptChoice(LocaleUtilities.get("choose_card_replace_handler"), claimedEggs, currentPlayer.id);
            Handler chosenHandler = gui.promptChoice(LocaleUtilities.get("choose_replace_handler"), currentPlayer.handlers,
                    currentPlayer.getId());
            handlerLogic.move(chosenEgg.handler, HandlerChoice.BREAK_ROOM, currentPlayer);
            handlerLogic.moveToCard(chosenHandler, chosenEgg);
        }
    }

    @Override
    public String toString() {
        return LocaleUtilities.get("replace_handler_effect");
    }
}
