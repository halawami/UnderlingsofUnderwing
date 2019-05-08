package underlings.card.effect;

import underlings.card.Card;
import underlings.element.ElementBag;
import underlings.element.utilities.ElementSpaceLogic;
import underlings.game.HatchingGround;
import underlings.gui.Gui;
import underlings.player.Player;

public abstract class Effect {


    private Player player;
    private HatchingGround hatchingGround;
    private ElementBag elementBag;
    private Card centerCard;
    private ElementSpaceLogic elementSpaceLogic;
    private Gui gui;

    public Effect on(HatchingGround hatchingGround) {
        this.hatchingGround = hatchingGround;
        return this;
    }

    public Effect on(Card card) {
        this.centerCard = card;
        return this;
    }

    public Effect on(ElementSpaceLogic elementSpaceLogic) {
        this.elementSpaceLogic = elementSpaceLogic;
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

    public void apply() {
        this.apply(this.centerCard, this.hatchingGround, this.elementBag, this.elementSpaceLogic, this.gui,
                this.player);
        this.apply(this.player, elementBag);
        this.apply(this.player);
    }

    protected void apply(Card centerCard, HatchingGround hatchingGround, ElementBag elementBag,
            ElementSpaceLogic elementSpaceLogic, Gui gui, Player currentPlayer) {
    }

    protected void apply(Player player, ElementBag elementBag) {
    }

    protected void apply(Player player) {
    }

}
