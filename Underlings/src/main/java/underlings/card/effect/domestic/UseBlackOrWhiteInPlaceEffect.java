package underlings.card.effect.domestic;

import underlings.card.effect.PlayerEffect;
import underlings.element.ElementColor;
import underlings.player.Player;
import underlings.utilities.LocaleWrap;

public class UseBlackOrWhiteInPlaceEffect extends PlayerEffect {

    @Override
    protected void apply(Player player) {
        player.elementSpaceLogic.setOpenElement(ElementColor.BLACK);
        player.elementSpaceLogic.setOpenElement(ElementColor.WHITE);
    }

    @Override
    public String toString() {
        return LocaleWrap.get("black_white_inplace_effect");
    }
}
