package underlings.card.effect.wild;

import java.util.List;

import underlings.card.Card;
import underlings.card.effect.HatchingGroundEffect;
import underlings.element.ElementBag;
import underlings.element.ElementColor;
import underlings.element.utilities.ElementSpaceLogic;
import underlings.game.HatchingGround;
import underlings.gui.Gui;
import underlings.player.Player;
import underlings.utilities.EggHatchingLogic;

public class AddElementToAllSpacesInPlayEffect extends HatchingGroundEffect {

    public ElementColor elementColor;

    @Override
    protected void apply(Card centerCard, HatchingGround hatchingGround, ElementBag elementBag, Gui gui,
            Player currentPlayer, EggHatchingLogic eggHatchingLogic) {
        List<Card> cardsInPlay = hatchingGround.getAllCards();
        for (Card cardInPlay : cardsInPlay) {
            this.addElementsToCard(this.elementColor, cardInPlay, currentPlayer.elementSpaceLogic, elementBag);
        }
    }

    public void addElementsToCard(ElementColor color, Card cardToAddTo, ElementSpaceLogic elementSpaceLogic,
            ElementBag elementBag) {

    }
}
