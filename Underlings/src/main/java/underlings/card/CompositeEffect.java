package underlings.card;

public class CompositeEffect extends Effect {

    private Effect decoratedEffect;

    public CompositeEffect(Effect decoratedEffect) {
        this.decoratedEffect = decoratedEffect;
    }

    public void apply(){
        this.decoratedEffect.apply();
    }
}
