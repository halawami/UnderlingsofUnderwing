package underlings.card.effect.wild.alleggsinplay;

import java.util.List;

import underlings.card.Card;
import underlings.card.effect.HatchingGroundEffect;
import underlings.element.ElementBag;
import underlings.element.utilities.ElementSpaceLogic;
import underlings.game.HatchingGround;
import underlings.gui.Gui;
import underlings.player.Player;
import underlings.utilities.EggHatchingLogic;

public abstract class AllEggsInPlayEffect extends HatchingGroundEffect {

    @Override
    protected void apply(Card centerCard, HatchingGround hatchingGround, ElementBag elementBag, Gui gui,
            Player currentPlayer, EggHatchingLogic eggHatchingLogic) {
        List<Card> cardsInPlay = hatchingGround.getAllCards();
        for (Card cardInPlay : cardsInPlay) {
            applyOnCardInPlay(cardInPlay, currentPlayer.elementSpaceLogic, elementBag);
        }
    }

    public abstract void applyOnCardInPlay(Card cardInPlay, ElementSpaceLogic elementSpaceLogic, ElementBag elementBag);

}
