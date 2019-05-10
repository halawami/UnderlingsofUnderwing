package underlings.card.effect.domestic;

import underlings.card.Card;
import underlings.card.EmptyCard;
import underlings.card.effect.HatchingGroundEffect;
import underlings.element.ElementBag;
import underlings.element.utilities.ElementSpaceLogic;
import underlings.game.HatchingGround;
import underlings.gui.Gui;
import underlings.player.Player;
import underlings.utilities.EggHatchingLogic;

public class CollectAndHatchAnyUnclaimedEggEffect extends HatchingGroundEffect {
    public int points;

    @Override
    protected void apply(Card centerCard, HatchingGround hatchingGround, ElementBag elementBag,
            ElementSpaceLogic elementSpaceLogic, Gui gui, Player currentPlayer, EggHatchingLogic eggHatchingLogic) {
        Card selectedCard = gui.getEggToHatch(hatchingGround.cards, points, currentPlayer);
        if (selectedCard != EmptyCard.getInstance()) {
            eggHatchingLogic.hatchEgg(selectedCard, false, currentPlayer);
        }
    }
}
