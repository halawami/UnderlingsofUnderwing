package card.effect;

import card.Card;
import element.ElementBag;
import element.utilities.ElementSpaceLogic;
import game.HatchingGround;
import player.Player;

public abstract class Effect {


    private Player player;
    private HatchingGround hatchingGround;
    private ElementBag elementBag;
    private Card centerCard;
    private ElementSpaceLogic elementSpaceLogic;

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

    public void apply() {
        this.apply(this.centerCard, this.hatchingGround, this.elementBag, this.elementSpaceLogic);
        this.apply(this.player, elementBag);
        this.apply(this.player);
    }

    protected void apply(Card centerCard, HatchingGround hatchingGround, ElementBag elementBag,
            ElementSpaceLogic elementSpaceLogic) {}

    protected void apply(Player player, ElementBag elementBag) {}

    protected void apply(Player player) {}


}
