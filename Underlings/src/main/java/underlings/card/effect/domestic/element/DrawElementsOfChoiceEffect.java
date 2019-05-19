package underlings.card.effect.domestic.element;

import java.util.ArrayList;
import java.util.List;

import underlings.card.effect.ElementEffect;
import underlings.card.effect.ObserverEffect;
import underlings.element.ElementBag;
import underlings.element.ElementGiver;
import underlings.element.ElementGiverFactory;
import underlings.gui.DrawChoice;
import underlings.player.Player;

public class DrawElementsOfChoiceEffect extends ElementEffect implements ObserverEffect {

    public ElementGiverFactory elementGiverFactory;
    public ElementBag bag;
    public boolean beenUsed;

    @Override
    protected void apply(Player player, ElementBag elementBag) {
        player.addObserverEffect(this);
        this.bag = elementBag;
    }

    @Override
    public void onPhaseOne(Player player) {
        if (!this.beenUsed) {
            player.effectElementGivers = this.getEffectElementGivers(player.getElementGivers(), bag);
            player.useEffectElementGivers(true);
            this.beenUsed = true;
        }
    }

    public List<ElementGiver> getEffectElementGivers(List<ElementGiver> playerElementGivers, ElementBag elementBag) {
        List<ElementGiver> effectedElementGivers = new ArrayList<>();
        List<DrawChoice> availableDrawChoices = elementBag.getAvailableDrawChoices();
        for (int i = 0; i < playerElementGivers.size(); i++) {
            effectedElementGivers.add(this.elementGiverFactory.createElementGiver(availableDrawChoices));
        }
        return effectedElementGivers;
    }

    // TODO: to string?

}
