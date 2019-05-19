package underlings.hatchingground;

import java.util.ArrayList;
import java.util.List;

import underlings.card.Card;
import underlings.card.effect.Effect;
import underlings.element.Element;
import underlings.element.ElementBag;
import underlings.element.ElementSpace;
import underlings.gui.Gui;
import underlings.handler.HandlerMovementLogic;
import underlings.handler.HandlerState;
import underlings.handler.WildHandler;
import underlings.player.Player;
import underlings.utilities.LocaleUtilities;

public class EggHatchingUtilities {

    private Gui gui;
    private ElementBag elementBag;
    private HatchingGround hatchingGround;
    private Runnable displayMethod;
    private List<Player> players;
    private Deck deck;
    private HandlerMovementLogic handlerMovementLogic;

    public EggHatchingUtilities(Gui gui, ElementBag elementBag, HatchingGround hatchingGround, Runnable displayMethod,
            List<Player> players, Deck deck, HandlerMovementLogic handlerMovementLogic) {
        this.gui = gui;
        this.elementBag = elementBag;
        this.hatchingGround = hatchingGround;
        this.displayMethod = displayMethod;
        this.players = players;
        this.deck = deck;
        this.handlerMovementLogic = handlerMovementLogic;
    }

    public void hatchEgg(Card card, Player player) {
        Effect[] effects;
        if (!card.isClaimed()) {
            card.handler = WildHandler.getInstance();
            effects = card.wildEffects;
            this.returnElementsToBag(card);
        } else {
            effects = card.domesticEffects;
            card.handler.moveToState(HandlerState.READY_ROOM);
            player.hatchedCards.add(card);
        }
        for (int i = 0; i < effects.length; i++) {
            effects[i].on(card).on(this.elementBag).on(this.hatchingGround).on(player).on(this.gui).on(this)
                    .on(this.deck).on(this.players).on(handlerMovementLogic).apply();
            this.gui.notifyAction(player.getId(), LocaleUtilities.format("effect_applied", effects[i].toString()));
            this.displayMethod.run();
        }
        for (Card egg : hatchingGround.getUnclaimedEggs()) {
            if (hatchingGround.logic.isComplete(egg)) {
                this.hatchEgg(egg, player);
            }
        }
    }

    public void returnElementsToBag(Card dragon) {
        for (ElementSpace space : dragon.elementSpaces) {
            for (Element element : space.elements) {
                this.elementBag.putElement(element.getColor());
            }
            space.elements = new ArrayList<>();
        }
    }
}
