package underlings.utilities;

import underlings.card.Card;
import underlings.element.ElementBag;
import underlings.field.Field;
import underlings.game.HatchingGround;
import underlings.gui.Gui;
import underlings.handler.WildHandler;
import underlings.player.Player;

public class WildEggHatchingLogic {

    private Player player;
    private Gui gui;
    private ElementBag elementBag;
    private HatchingGround hatchingGround;
    private Field field;

    public WildEggHatchingLogic(Player player, Gui gui, ElementBag elementBag, HatchingGround hatchingGround,
            Field field) {
        this.player = player;
        this.gui = gui;
        this.elementBag = elementBag;
        this.hatchingGround = hatchingGround;
        this.field = field;
    }

    public void hatchWildEgg(Card card) {
        card.handler = WildHandler.getInstance();
        for (int i = 0; i < card.wildEffects.length; i++) {
            card.wildEffects[i].on(this.elementBag).on(this.hatchingGround).on(this.player.elementSpaceLogic)
                    .on(this.player).apply();
            this.gui.notifyAction(-1, card.wildEffects[i].toString() + " has been applied");
        }
    }
}
