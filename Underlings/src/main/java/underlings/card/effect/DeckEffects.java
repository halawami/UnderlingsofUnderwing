package underlings.card.effect;

import java.util.List;

import underlings.gui.Gui;
import underlings.hatchingground.Deck;
import underlings.player.Player;

public abstract class DeckEffects extends Effect {

    @Override
    protected abstract void apply(List<Player> players, Deck deck, Gui gui);
}