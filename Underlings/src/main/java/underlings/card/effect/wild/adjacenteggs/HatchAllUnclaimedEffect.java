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
        if (adjacentEgg.handler == null) {
            EggHatchingLogic wildEggHatchingLogic =
                    new EggHatchingLogic(gui, elementBag, hatchingGround, FakePlayer.getInstance());
            wildEggHatchingLogic.hatchEgg(adjacentEgg, true);
        }
    }

}
