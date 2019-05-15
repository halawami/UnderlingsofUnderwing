package underlings.card.effect.domestic;

import java.util.List;

import underlings.card.effect.ElementEffect;
import underlings.card.effect.ObserverEffect;
import underlings.element.ElementBag;
import underlings.element.ElementGiver;
import underlings.player.Player;

public class DrawElementsOfChoiceEffect extends ElementEffect implements ObserverEffect {

    public ElementBag elementBag;
    private boolean beenUsed;

    @Override
    protected void apply(Player player, ElementBag elementBag) {
        player.addObserverEffect(this);
        this.elementBag = elementBag;
    }

    @Override
    public void onPhaseOne(Player player) {
        if (!this.beenUsed) {
            player.effectedElementGivers = this.getEffectedElementGivers(player.getElementGivers(), elementBag);
            player.useEffectedElementGivers(true);
            this.beenUsed = true;
        }
    }

    public List<ElementGiver> getEffectedElementGivers(List<ElementGiver> playerElementGivers,
            ElementBag elementBag) {
        return null;
    }
}
