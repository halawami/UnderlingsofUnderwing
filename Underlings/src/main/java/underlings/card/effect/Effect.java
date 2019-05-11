package underlings.card.effect;

import underlings.card.Card;
import underlings.element.ElementBag;
import underlings.game.HatchingGround;
import underlings.gui.Gui;
import underlings.player.Player;
import underlings.utilities.EggHatchingLogic;

public abstract class Effect {

    private Player player;
    private HatchingGround hatchingGround;
    private ElementBag elementBag;
    private Card centerCard;
    private Gui gui;
    private EggHatchingLogic eggHatchingLogic;

    public Effect on(HatchingGround hatchingGround) {
        this.hatchingGround = hatchingGround;
        return this;
    }

    public Effect on(Card card) {
        this.centerCard = card;
        return this;
    }

    public Effect on(ElementBag elementBag) {
        this.elementBag = elementBag;
        return this;
    }

    public Effect on(Player player) {
        this.player = player;
        return this;
    }

    public Effect on(Gui gui) {
        this.gui = gui;
        return this;
    }

    public Effect on(EggHatchingLogic eggHatchingLogic) {
        this.eggHatchingLogic = eggHatchingLogic;
        return this;
    }

    public void apply() {
        this.apply(this.centerCard, this.hatchingGround, this.elementBag, this.gui, this.player, this.eggHatchingLogic);
        this.apply(this.player, elementBag);
        this.apply(this.player);
    }

    protected void apply(Card centerCard, HatchingGround hatchingGround, ElementBag elementBag, Gui gui,
            Player currentPlayer, EggHatchingLogic eggHatchingLogic) {}

    protected void apply(Player player, ElementBag elementBag) {}

    protected void apply(Player player) {}

}
