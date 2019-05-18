package underlings.card.effect.domestic;

import underlings.card.effect.PlayerEffect;
import underlings.player.Player;
import underlings.utilities.LocaleWrap;

public class NextEggHatchesImmediatelyEffect extends PlayerEffect {

    @Override
    protected void apply(Player player) {
        player.hatchingTime = 0;
    }

    @Override
    public String toString() {
        return LocaleWrap.get("egg_hatches_early_effect");
    }

}
