package underlings.card.effect.domestic;

import java.util.List;

import underlings.card.Card;
import underlings.card.EmptyCard;
import underlings.card.effect.DiverseHatchingGroundEffect;
import underlings.element.ElementBag;
import underlings.game.Deck;
import underlings.game.HatchingGround;
import underlings.gui.Gui;
import underlings.handler.HandlerMovementLogic;
import underlings.player.Player;
import underlings.utilities.EggHatchingLogic;
import underlings.utilities.LocaleWrap;

public class CollectAndHatchAnyUnclaimedEggEffect extends DiverseHatchingGroundEffect {

    public int points;

    @Override
    protected void apply(Card centerCard, HatchingGround hatchingGround, ElementBag elementBag, Gui gui,
            Player currentPlayer, EggHatchingLogic eggHatchingLogic, Deck deck,
            HandlerMovementLogic handlerMovementLogic) {
        List<Card> validCards = hatchingGround.getDragons(points, true);
        Card selectedCard = gui.getCard(currentPlayer.getId(), LocaleWrap.get("gui_card"), hatchingGround, validCards);
        if (selectedCard != EmptyCard.getInstance()) {
            eggHatchingLogic.hatchEgg(selectedCard, currentPlayer);
        }
    }

    @Override
    public String toString() {
        return LocaleWrap.format("hatch_unclaimed_dragon_effect", points);
    }
}
