package underlings.card.effect.wild.adjacenteggs;

import java.util.Arrays;
import java.util.List;

import underlings.card.Card;
import underlings.card.Family;
import underlings.card.effect.AdjacentEggsEffect;
import underlings.player.FakePlayer;
import underlings.utilities.EggHatchingLogic;
import underlings.utilities.LocaleWrap;

public class HatchAllUnclaimedEffect extends AdjacentEggsEffect {

    public Family[] dragonFamilies;

    @Override
    public void applyOnAdjacentEgg(Card adjacentEgg, EggHatchingLogic eggHatchingLogic) {
        List<Family> dragonFamiliesList = Arrays.asList(this.dragonFamilies);
        if (adjacentEgg.handler == null && dragonFamiliesList.contains(adjacentEgg.family)) {
            eggHatchingLogic.hatchEgg(adjacentEgg, FakePlayer.getInstance());
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
