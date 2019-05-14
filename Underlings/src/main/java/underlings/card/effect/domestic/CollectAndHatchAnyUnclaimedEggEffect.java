package underlings.card.effect.domestic;

import underlings.card.Card;
import underlings.card.EmptyCard;
import underlings.card.effect.HatchingGroundEffect;
import underlings.element.ElementBag;
import underlings.game.Deck;
import underlings.game.HatchingGround;
import underlings.gui.Gui;
import underlings.handler.HandlerMovementLogic;
import underlings.player.Player;
import underlings.utilities.EggHatchingLogic;

public class CollectAndHatchAnyUnclaimedEggEffect extends HatchingGroundEffect {
    public int points;

    @Override
    protected void apply(Card centerCard, HatchingGround hatchingGround, ElementBag elementBag, Gui gui,
            Player currentPlayer, EggHatchingLogic eggHatchingLogic, Deck deck,
            HandlerMovementLogic handlerMovementLogic) {
        Card selectedCard = gui.getEggToHatch(hatchingGround.cards, points, currentPlayer);
        if (selectedCard != EmptyCard.getInstance()) {
            eggHatchingLogic.hatchEgg(selectedCard, false, currentPlayer);
        }
    }
}
