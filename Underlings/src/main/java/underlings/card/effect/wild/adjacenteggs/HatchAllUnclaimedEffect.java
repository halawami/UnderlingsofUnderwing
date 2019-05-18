package underlings.card.effect.wild.adjacenteggs;

import java.util.Arrays;
import java.util.List;

import underlings.card.Card;
import underlings.card.Family;
import underlings.element.ElementBag;
import underlings.element.utilities.ElementSpaceLogic;
import underlings.game.Deck;
import underlings.game.HatchingGround;
import underlings.handler.HandlerMovementLogic;
import underlings.player.FakePlayer;
import underlings.utilities.EggHatchingLogic;
import underlings.utilities.LocaleWrap;

public class HatchAllUnclaimedEffect extends AdjacentEggsEffect {

    public Family[] dragonFamilies;

    @Override
    public void applyOnAdjacentEgg(Card adjacentEgg, ElementBag elementBag, ElementSpaceLogic elementSpaceLogic,
            EggHatchingLogic eggHatchingLogic, Deck deck, HandlerMovementLogic handlerMovementLogic,
            HatchingGround hatchingGround) {
        List<Family> dragonFamiliesList = Arrays.asList(dragonFamilies);
        if (adjacentEgg.handler == null && dragonFamiliesList.contains(adjacentEgg.family)) {
            eggHatchingLogic.hatchEgg(adjacentEgg, true, FakePlayer.getInstance());
        }
    }

    @Override
    public String toString() {
        StringBuilder families = new StringBuilder();
        for (Family family : this.dragonFamilies) {
            families.append(family);
            families.append(" ");
        }
        return LocaleWrap.format("hatch_all_unclaimed_adjacent_eggs_effect", families);
    }

}
