package underlings.card.effect.wild;

import java.util.List;

import underlings.card.Card;
import underlings.card.Family;
import underlings.card.effect.HatchingGroundEffect;
import underlings.element.ElementBag;
import underlings.element.utilities.ElementSpaceLogic;
import underlings.game.HatchingGround;
import underlings.gui.Gui;
import underlings.player.FakePlayer;
import underlings.utilities.WildEggHatchingLogic;

public class HatchAllUnclaimedAdjacentEggsEffect extends HatchingGroundEffect {

    public Family[] dragonFamilies;

    @Override
    protected void apply(Card centerCard, HatchingGround hatchingGround, ElementBag elementBag,
            ElementSpaceLogic elementSpaceLogic, Gui gui) {
        List<Card> adjacentCards = hatchingGround.getAdjacentCards(centerCard);
        WildEggHatchingLogic wildEggHatchingLogic = new WildEggHatchingLogic(gui, elementBag, hatchingGround, FakePlayer.getInstance());
        for (Card adjacentCard : adjacentCards) {
            if (){
                
            }
        }
    }

}
