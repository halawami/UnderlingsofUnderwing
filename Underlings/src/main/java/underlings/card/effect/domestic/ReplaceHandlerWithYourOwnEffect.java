package underlings.card.effect.domestic;

import underlings.card.Card;
import underlings.card.effect.HandlerEffect;
import underlings.game.HatchingGround;
import underlings.gui.Gui;
import underlings.handler.Handler;
import underlings.handler.HandlerChoice;
import underlings.handler.HandlerMovementLogic;
import underlings.player.Player;

public class ReplaceHandlerWithYourOwnEffect extends HandlerEffect {

    @Override
    protected void apply(Player currentPlayer, HatchingGround hatchingGround, HandlerMovementLogic handlerLogic,
            Gui gui) {
        Card chosenEgg = gui.promptCard("Choose a card to replace its handler", hatchingGround.getClaimedEggs());
        Handler chosenHandler = gui.promptHandler("Choose a handler to replace with", 0, currentPlayer.getHandlers());
        handlerLogic.move(chosenEgg.handler, HandlerChoice.BREAK_ROOM, currentPlayer.getPlayerId());
        handlerLogic.moveToCard(chosenHandler, chosenEgg);
    }
}
