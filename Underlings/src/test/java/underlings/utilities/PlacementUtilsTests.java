package underlings.utilities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.google.common.base.Function;

import java.util.Arrays;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Test;

import underlings.card.Card;
import underlings.game.HatchingGround;
import underlings.gui.Gui;
import underlings.player.Player;
import underlings.utilities.PlacementUtilities;

public class PlacementUtilsTests {

    @SuppressWarnings("unchecked")
    @Test
    public void testSelectCard() {
        HatchingGround hatchingGround = EasyMock.mock(HatchingGround.class);
        Gui gui = EasyMock.mock(Gui.class);
        Runnable displayMethod = EasyMock.mock(Runnable.class);

        Player player = EasyMock.mock(Player.class);
        EasyMock.expect(player.getPlayerId()).andReturn(10).anyTimes();

        Card card1 = new Card();
        Card card2 = new Card();
        List<Card> cards = Arrays.asList(card1, card2);

        EasyMock.expect(gui.getCard(EasyMock.anyInt(), EasyMock.anyString(), EasyMock.anyObject(HatchingGround.class),
                EasyMock.anyObject(Function.class))).andReturn(card1);

        EasyMock.replay(hatchingGround, gui, displayMethod, player);

        PlacementUtilities utils = new PlacementUtilities(hatchingGround, gui, displayMethod);
        assertEquals(card1, utils.selectCard(cards, player));

        EasyMock.verify(hatchingGround, gui, displayMethod, player);

        assertTrue(utils.isValid(card1));
        assertTrue(utils.isValid(card2));
        assertFalse(utils.isValid(new Card()));
    }

}
