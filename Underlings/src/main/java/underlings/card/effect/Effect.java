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

    private Player currentPlayer;
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

    public Effect on(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
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
        this.apply(this.currentPlayer);
        this.apply(this.currentPlayer, this.elementBag);
        this.apply(this.hatchingGround, this.gui);
        this.apply(this.players, this.deck, this.gui);
        this.apply(this.currentPlayer, this.players, this.gui);
        this.apply(this.currentPlayer, this.hatchingGround, this.handlerMovementLogic, this.gui);
        this.apply(this.hatchingGround, this.eggHatchingLogic, this.currentPlayer, this.gui);
        this.apply(this.centerCard, this.hatchingGround, this.elementBag, this.currentPlayer, this.eggHatchingLogic,
                this.deck, this.handlerMovementLogic);
    }

    protected void apply(Player currentPlayer, ElementBag elementBag) {
    }

    protected void apply(Player currentPlayer) {
    }

    protected void apply(HatchingGround hatchingGround, Gui gui) {
    }

    protected void apply(Player currentPlayer, List<Player> players, Gui gui) {
    }

    protected void apply(List<Player> players, Deck deck, Gui gui) {
    }

    protected void apply(Player currentPlayer, HatchingGround hatchingGround, HandlerMovementLogic handlerLogic,
            Gui gui) {
    }

    protected void apply(HatchingGround hatchingGround, EggHatchingLogic hatchingLogic, Player currentPlayer, Gui gui) {
    }

    protected void apply(Card centerCard, HatchingGround hatchingGround, ElementBag elementBag,
            Player currentPlayer, EggHatchingLogic eggHatchingLogic, Deck deck,
            HandlerMovementLogic handlerMovementLogic) {
    }
}
