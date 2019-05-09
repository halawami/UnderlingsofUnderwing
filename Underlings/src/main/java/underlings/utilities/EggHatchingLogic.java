package underlings.utilities;

import underlings.card.Card;
import underlings.card.effect.Effect;
import underlings.element.ElementBag;
import underlings.element.ElementColor;
import underlings.element.ElementSpace;
import underlings.game.HatchingGround;
import underlings.gui.Gui;
import underlings.handler.WildHandler;
import underlings.player.Player;
import underlings.player.PlayerFactory;


public class EggHatchingLogic {

    private Gui gui;
    private ElementBag elementBag;
    private HatchingGround hatchingGround;
    private Player player;

    public EggHatchingLogic(Gui gui, ElementBag elementBag, HatchingGround hatchingGround,
            PlayerFactory playerFactory) {
        this.gui = gui;
        this.elementBag = elementBag;
        this.hatchingGround = hatchingGround;
        this.player = playerFactory.createFakePlayer();
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

    public void returnElementsToBag(Card dragon) {
        for (ElementSpace space : dragon.elementSpaces) {
            for (ElementColor color : space.elements) {
                this.elementBag.putElement(color);
            }
        }
    }
}
