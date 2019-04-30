package underlings.card.effect.wild;

import underlings.card.Card;
import underlings.card.effect.HatchingGroundEffect;
import underlings.element.ElementBag;
import underlings.element.ElementColor;
import underlings.element.utilities.ElementSpaceLogic;
import underlings.game.HatchingGround;

public class AddElementsToAllAdjacentEggsEffect extends HatchingGroundEffect {

    public ElementColor[] elementColors;

    @Override
    protected void apply(Card centerCard, HatchingGround hatchingGround, ElementBag elementBag,
                         ElementSpaceLogic elementSpaceLogic) {

    }

    public void addElementToCard(ElementColor elementColor, Card card, ElementSpaceLogic elementSpaceLogic) {

    }

    private boolean elementColorsContains(ElementColor elementColor) {
        for (int i = 0; i < this.elementColors.length; i++) {
            if (this.elementColors[i] == elementColor) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Add elements to adjacent cards";
    }

}
