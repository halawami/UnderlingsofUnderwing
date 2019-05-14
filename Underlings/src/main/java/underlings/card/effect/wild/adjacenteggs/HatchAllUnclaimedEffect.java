package underlings.card.effect.wild.adjacenteggs;

import java.util.Arrays;
import java.util.List;

import underlings.card.Card;
import underlings.card.Family;
import underlings.element.ElementBag;
import underlings.element.utilities.ElementSpaceLogic;
import underlings.gui.Gui;
import underlings.player.FakePlayer;
import underlings.utilities.EggHatchingLogic;

public class HatchAllUnclaimedEffect extends AdjacentEggsEffect {

    public Family[] dragonFamilies;

    @Override
    public void applyOnAdjacentEgg(Card adjacentEgg, ElementBag elementBag, ElementSpaceLogic elementSpaceLogic,
            Gui gui, EggHatchingLogic eggHatchingLogic) {
        List<Family> dragonFamiliesList = Arrays.asList(dragonFamilies);
        if (adjacentEgg.handler == null && dragonFamiliesList.contains(adjacentEgg.family)) {
            eggHatchingLogic.hatchEgg(adjacentEgg, true, FakePlayer.getInstance());
        }
    }

}
