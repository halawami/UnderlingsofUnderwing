package underlings.card.effect.domestic.players.player;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import underlings.MockTest;
import underlings.card.effect.Effect;
import underlings.card.effect.PlayerEffect;
import underlings.card.effect.domestic.players.player.UseBlackOrWhiteInPlaceEffect;
import underlings.element.ElementColor;
import underlings.element.ElementSpaceUtilities;
import underlings.player.Player;
import underlings.utilities.LocaleUtilities;

public class UseBlackOrWhiteInPlaceTests extends MockTest {

    @Test
    public void testEffect() {
        Player player = this.mock(Player.class);
        ElementSpaceUtilities logic = this.mock(ElementSpaceUtilities.class);

        player.elementSpaceLogic = logic;
        logic.setOpenElement(ElementColor.BLACK);
        logic.setOpenElement(ElementColor.WHITE);

        this.replayAll();

        PlayerEffect effect = new UseBlackOrWhiteInPlaceEffect();
        effect.on(player).apply();
    }

    @Test
    public void testToString() {
        Effect effect = new UseBlackOrWhiteInPlaceEffect();
        assertEquals(LocaleUtilities.get("black_white_inplace_effect"), effect.toString());
    }
}
