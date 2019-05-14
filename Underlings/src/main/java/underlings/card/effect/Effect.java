package underlings.card.effect;

import java.util.List;

import underlings.card.Card;
import underlings.element.ElementBag;
import underlings.element.ElementGiver;
import underlings.game.Deck;
import underlings.game.HatchingGround;
import underlings.gui.Gui;
import underlings.handler.HandlerMovementLogic;
import underlings.player.Player;
import underlings.utilities.EggHatchingLogic;

public abstract class Effect extends ElementGiver {

    private Player player;
    private HatchingGround hatchingGround;
    private ElementBag elementBag;
    private Card centerCard;
    private Gui gui;
    private EggHatchingLogic eggHatchingLogic;
    private List<Player> players;
    private Deck deck;
    private HandlerMovementLogic handlerMovementLogic;

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

    public Effect on(List<Player> players) {
        this.players = players;
        return this;
    }

    public Effect on(Deck deck) {
        this.deck = deck;
        return this;
    }

    public Effect on(HandlerMovementLogic handlerMovementLogic) {
        this.handlerMovementLogic = handlerMovementLogic;
        return this;
    }

    public void apply() {
        this.apply(this.centerCard, this.hatchingGround, this.elementBag, this.gui, this.player, this.eggHatchingLogic,
                this.deck, this.handlerMovementLogic);
        this.apply(this.player, this.elementBag);
        this.apply(this.player);
        this.apply(this.player, this.players, this.gui);
        this.apply(this.players, this.deck, this.gui);
        this.apply(this.player, this.players, this.gui, this.eggHatchingLogic);
        this.apply(this.player, this.hatchingGround, this.handlerMovementLogic, this.gui);
    }

    protected void apply(Card centerCard, HatchingGround hatchingGround, ElementBag elementBag, Gui gui,
            Player currentPlayer, EggHatchingLogic eggHatchingLogic, Deck deck,
            HandlerMovementLogic handlerMovementLogic) {
    }

    protected void apply(Player player, ElementBag elementBag) {
    }

    protected void apply(Player player) {
    }

    protected void apply(Player currentPlayer, List<Player> players, Gui gui) {
    }

    protected void apply(List<Player> players, Deck deck, Gui gui) {
    }

    protected void apply(Player currentPlayer, List<Player> players, Gui gui, EggHatchingLogic eggHatchingLogic) {
    }

    protected void apply(Player currentPlayer, HatchingGround hatchingGround, HandlerMovementLogic handlerLogic,
            Gui gui) {

    }
}
