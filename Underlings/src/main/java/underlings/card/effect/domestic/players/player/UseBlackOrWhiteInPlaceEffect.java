package underlings.card.effect.domestic.players.player;

import underlings.card.effect.PlayerEffect;
import underlings.element.ElementColor;
import underlings.player.Player;
import underlings.utilities.LocaleUtilities;

public class UseBlackOrWhiteInPlaceEffect extends PlayerEffect {

    @Override
    protected void apply(Player player) {
        player.elementSpaceLogic.setOpenElement(ElementColor.BLACK);
        player.elementSpaceLogic.setOpenElement(ElementColor.WHITE);
    }

    @Override
    public String toString() {
        return LocaleUtilities.get("black_white_inplace_effect");
    }
}
