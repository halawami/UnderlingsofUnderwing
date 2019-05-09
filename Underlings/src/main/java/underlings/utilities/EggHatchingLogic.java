package underlings.utilities;

import underlings.card.Card;
import underlings.card.effect.Effect;
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

    public void hatchEgg(Card card, boolean domestic) {
        Effect[] effects;
        int id;
        if (domestic) {
            card.handler = WildHandler.getInstance();
            effects = card.wildEffects;
            id = -1;
        } else {
            effects = card.domesticEffects;
            id = this.player.getPlayerId();
        }
        for (int i = 0; i < effects.length; i++) {
            effects[i].on(this.elementBag).on(this.hatchingGround).on(player.elementSpaceLogic).on(this.player)
                    .on(this.gui).apply();
            this.gui.notifyAction(id, effects[i].toString() + " has been applied");
        }
    }
}
