package underlings.card;

import java.util.ArrayList;
import java.util.List;

import underlings.card.effect.Effect;
import underlings.element.ElementGiver;
import underlings.element.ElementSpace;
import underlings.handler.Handler;

public class Card {

    public String name;
    public int points;
    public Temperature temperature;
    public Family family;
    public Handler handler;
    public Effect[] domesticEffects;
    public Effect[] wildEffects;
    public ElementSpace[] elementSpaces;

    public boolean isClaimed() {
        return this.handler != null;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public List<ElementGiver> getElementGivers() {
        List<ElementGiver> elementGivers = new ArrayList<>();
        for (Effect domesticEffect : this.domesticEffects) {
            if (domesticEffect.drawChoices != null && !domesticEffect.drawChoices.isEmpty()) {
                elementGivers.add(domesticEffect);
            }
        }
        return elementGivers;
    }
}
