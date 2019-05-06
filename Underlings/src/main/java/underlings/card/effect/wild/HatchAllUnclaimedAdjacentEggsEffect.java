package underlings.card.effect.wild;

import java.util.List;

import underlings.card.Card;
import underlings.card.Family;
import underlings.card.effect.HatchingGroundEffect;
import underlings.element.ElementBag;
import underlings.element.utilities.ElementSpaceLogic;
import underlings.game.HatchingGround;

public class HatchAllUnclaimedAdjacentEggsEffect extends HatchingGroundEffect {

    public Family[] dragonFamilies;

    @Override
    protected void apply(Card centerCard, HatchingGround hatchingGround, ElementBag elementBag,
            ElementSpaceLogic elementSpaceLogic) {
        List<Card> adjacentCards = hatchingGround.getAdjacentCards(centerCard);
        List<Card> hatchableEggs = getHatchableEggs(adjacentCards);

    }

    public List<Card> getHatchableEggs(List<Card> adjacentCards) {
        return null;
    }

    public void hatchEgg(Card hatchableEgg) {

    }
}
