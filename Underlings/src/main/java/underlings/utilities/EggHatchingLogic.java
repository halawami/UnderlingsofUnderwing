package underlings.utilities;

import underlings.card.Card;
import underlings.element.ElementBag;
import underlings.game.HatchingGround;
import underlings.gui.Gui;
import underlings.handler.WildHandler;
import underlings.player.Player;


public class EggHatchingLogic {

    private Gui gui;
    private ElementBag elementBag;
    private HatchingGround hatchingGround;
    private Player player;

    public EggHatchingLogic(Gui gui, ElementBag elementBag, HatchingGround hatchingGround, Player player) {
        this.gui = gui;
        this.elementBag = elementBag;
        this.hatchingGround = hatchingGround;
        this.player = player;
    }

    public void hatchEgg(Card card) {
        card.handler = WildHandler.getInstance();
        for (int i = 0; i < card.wildEffects.length; i++) {
            card.wildEffects[i].on(this.elementBag).on(this.hatchingGround).on(player.elementSpaceLogic).on(this.player)
                    .on(this.gui).apply();
            this.gui.notifyAction(-1, card.wildEffects[i].toString() + " has been applied");
        }
    }
}
