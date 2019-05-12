package underlings.effect.player;

import org.easymock.EasyMock;
import org.junit.Test;

import underlings.card.effect.PlayerEffect;
import underlings.card.effect.domestic.UseBlackOrWhiteInPlaceEffect;
import underlings.element.ElementColor;
import underlings.element.utilities.ElementSpaceLogic;
import underlings.player.Player;

public class UseBlackOrWhiteInPlaceTests {

    @Test
    public void testEffect() {
        Player player = EasyMock.mock(Player.class);
        ElementSpaceLogic logic = EasyMock.mock(ElementSpaceLogic.class);

        player.elementSpaceLogic = logic;
        logic.setOpenElement(ElementColor.BLACK);
        logic.setOpenElement(ElementColor.WHITE);

        EasyMock.replay(player, logic);

        PlayerEffect effect = new UseBlackOrWhiteInPlaceEffect();
        effect.on(player).apply();

        EasyMock.verify(player, logic);
    }
}
