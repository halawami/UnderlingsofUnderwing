package underlings.card.effect.wild.adjacenteggs;

import java.util.List;

import underlings.card.Card;
import underlings.card.effect.HatchingGroundEffect;
import underlings.element.ElementBag;
import underlings.element.utilities.ElementSpaceLogic;
import underlings.game.HatchingGround;
import underlings.gui.Gui;
import underlings.player.Player;
import underlings.utilities.EggHatchingLogic;

public abstract class AdjacentEggsEffect extends HatchingGroundEffect {

    @Override
    protected void apply(Card centerCard, HatchingGround hatchingGround, ElementBag elementBag, Gui gui,
            Player currentPlayer, EggHatchingLogic eggHatchingLogic) {
        List<Card> adjacentCards = hatchingGround.getAdjacentCards(centerCard);
        for (Card adjacentCard : adjacentCards) {
            applyOnAdjacentEgg(adjacentCard, elementBag, currentPlayer.elementSpaceLogic,
                    eggHatchingLogic);
        }
    }

    public abstract void applyOnAdjacentEgg(Card adjacentEgg, ElementBag elementBag,
            ElementSpaceLogic elementSpaceLogic, EggHatchingLogic eggHatchingLogic);
}
