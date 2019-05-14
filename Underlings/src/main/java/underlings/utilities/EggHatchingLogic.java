package underlings.utilities;

import java.util.List;

import underlings.card.Card;
import underlings.card.effect.Effect;
import underlings.element.Element;
import underlings.element.ElementBag;
import underlings.element.ElementSpace;
import underlings.game.Deck;
import underlings.game.HatchingGround;
import underlings.gui.Gui;
import underlings.handler.HandlerState;
import underlings.handler.WildHandler;
import underlings.player.Player;

public class EggHatchingLogic {

    private Gui gui;
    private ElementBag elementBag;
    private HatchingGround hatchingGround;
    private Runnable displayMethod;
    private List<Player> players;
    private Deck deck;

    public EggHatchingLogic(Gui gui, ElementBag elementBag, HatchingGround hatchingGround, Runnable displayMethod,
            List<Player> players, Deck deck) {
        this.gui = gui;
        this.elementBag = elementBag;
        this.hatchingGround = hatchingGround;
        this.displayMethod = displayMethod;
        this.players = players;
        this.deck = deck;
    }

    public void hatchEgg(Card card, boolean wild, Player player) {
        Effect[] effects;
        if (wild) {
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
                    .on(this.deck).on(this.players).apply();
            this.gui.notifyAction(player.getPlayerId(), LocaleWrap.format("effect_applied", effects[i].toString()));
            this.displayMethod.run();
        }
    }

    public void returnElementsToBag(Card dragon) {
        for (ElementSpace space : dragon.elementSpaces) {
            for (Element element : space.elements) {
                this.elementBag.putElement(element.getColor());
            }
        }
    }
}
