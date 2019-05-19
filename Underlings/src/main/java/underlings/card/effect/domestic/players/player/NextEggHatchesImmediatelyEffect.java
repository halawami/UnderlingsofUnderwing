package underlings.card.effect.domestic.players.player;

import underlings.card.effect.PlayerEffect;
import underlings.player.Player;
import underlings.utilities.LocaleUtilities;

public class NextEggHatchesImmediatelyEffect extends PlayerEffect {

    @Override
    protected void apply(Player player) {
        player.hatchingTime = 0;
    }

    @Override
    public String toString() {
        return LocaleUtilities.get("egg_hatches_early_effect");
    }

}
