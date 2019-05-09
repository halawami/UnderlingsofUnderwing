package underlings.card.effect.wild.adjacenteggs;

import underlings.card.Card;
import underlings.card.Family;
import underlings.element.ElementBag;
import underlings.element.utilities.ElementSpaceLogic;
import underlings.game.HatchingGround;
import underlings.gui.Gui;
import underlings.player.FakePlayer;
import underlings.utilities.EggHatchingLogic;

public class HatchAllUnclaimedEffect extends AdjacentEggsEffect {

    public Family[] dragonFamilies;

    @Override
    public void applyOnAdjacentEgg(Card adjacentEgg, ElementBag elementBag, ElementSpaceLogic elementSpaceLogic,
            HatchingGround hatchingGround, Gui gui) {
        EggHatchingLogic wildEggHatchingLogic =
                new EggHatchingLogic(gui, elementBag, hatchingGround, FakePlayer.getInstance());
        System.out.println("here2");
        wildEggHatchingLogic.hatchEgg(adjacentEgg);
    }

}
