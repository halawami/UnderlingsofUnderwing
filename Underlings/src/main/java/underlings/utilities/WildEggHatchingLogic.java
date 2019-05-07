package underlings.utilities;

import underlings.card.Card;
import underlings.element.ElementBag;
import underlings.element.utilities.ElementSpaceLogic;
import underlings.game.HatchingGround;
import underlings.gui.Gui;
import underlings.handler.WildHandler;
import underlings.player.Player;


public class WildEggHatchingLogic {

    private Gui gui;
    private ElementBag elementBag;
    private HatchingGround hatchingGround;
    private Player player;

    public WildEggHatchingLogic(Gui gui, ElementBag elementBag, HatchingGround hatchingGround, Player player) {
        this.gui = gui;
        this.elementBag = elementBag;
        this.hatchingGround = hatchingGround;
        this.player = player;
    }

    public void hatchWildEgg(Card card) {
        card.handler = WildHandler.getInstance();
        for (int i = 0; i < card.wildEffects.length; i++) {
            card.wildEffects[i].on(this.elementBag).on(this.hatchingGround).on(new ElementSpaceLogic()).on(this.player)
                    .apply();
            this.gui.notifyAction(-1, card.wildEffects[i].toString() + " has been applied");
        }
    }
}
