package underlings.card.effect.domestic;

import java.util.List;

import underlings.card.effect.ObserverEffect;
import underlings.card.effect.PlayerEffect;
import underlings.element.ElementGiver;
import underlings.player.Player;

public class DrawElementsOfChoiceEffect extends PlayerEffect implements ObserverEffect {

    @Override
    protected void apply(Player player) {
        player.addObserverEffect(this);
    }

    @Override
    public void onPhaseOne(Player player) {

    }

    public List<ElementGiver> getEffectedElementGivers(List<ElementGiver> playerElementGivers) {
        return null;
    }
}
