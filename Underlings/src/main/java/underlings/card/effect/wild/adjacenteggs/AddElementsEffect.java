package underlings.card.effect.wild.adjacenteggs;

import java.util.List;

import underlings.card.Card;
import underlings.element.Element;
import underlings.element.ElementBag;
import underlings.element.ElementColor;
import underlings.element.ElementSpace;
import underlings.element.utilities.ElementSpaceLogic;
import underlings.game.HatchingGround;
import underlings.gui.Gui;
import underlings.utilities.EggHatchingLogic;
import underlings.utilities.LocaleWrap;

public class AddElementsEffect extends AdjacentEggsEffect {

    public ElementColor[] elementColors;

    @Override
    public void applyOnAdjacentEgg(Card adjacentEgg, ElementBag elementBag, ElementSpaceLogic elementSpaceLogic,
            HatchingGround hatchingGround, Gui gui, EggHatchingLogic eggHatchingLogic) {
        for (ElementColor elementColorToAdd : this.elementColors) {
            this.addElementToCard(elementColorToAdd, adjacentEgg, elementSpaceLogic, elementBag);
        }
    }

    public void addElementToCard(ElementColor elementColorToAdd, Card card, ElementSpaceLogic elementSpaceLogic,
            ElementBag elementBag) {
        List<ElementSpace> playableSpaces = elementSpaceLogic.getPlayableSpaces(card, elementColorToAdd);
        if (!playableSpaces.isEmpty()) {
            Element elementToAdd = elementBag.drawElementFromList(elementColorToAdd);
            playableSpaces.get(0).addElements(elementToAdd);
        }
    }

    @Override
    public String toString() {
        return LocaleWrap.get("add_elements_effect");
    }

}
