package underlings.card;

import underlings.card.effect.Effect;

public class CompositeEffect extends Effect {

    private Effect decoratedEffect;

    public CompositeEffect(Effect decoratedEffect) {
        this.decoratedEffect = decoratedEffect;
    }

    public void apply(){
        this.decoratedEffect.apply();
    }
}
