package underlings.utilities;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Test;

import underlings.card.Card;
import underlings.element.Element;
import underlings.element.ElementColor;
import underlings.element.ElementSpace;
import underlings.element.utilities.ElementSpaceLogic;
import underlings.game.HatchingGround;
import underlings.gui.Gui;
import underlings.gui.PromptHandler;
import underlings.player.Player;

public class PlacementUtilsTests {

    @SuppressWarnings("unchecked")
    @Test
    public void testSelectCard() {
        HatchingGround hatchingGround = EasyMock.mock(HatchingGround.class);
        Gui gui = EasyMock.mock(Gui.class);
        Runnable displayMethod = EasyMock.mock(Runnable.class);

        Player player = EasyMock.mock(Player.class);
        EasyMock.expect(player.getId()).andReturn(10).anyTimes();

        Card card1 = new Card();
        Card card2 = new Card();
        List<Card> cards = Arrays.asList(card1, card2);

        EasyMock.expect(gui.getCard(EasyMock.anyInt(), EasyMock.anyString(), EasyMock.anyObject(HatchingGround.class),
                EasyMock.anyObject(List.class))).andReturn(card1);

        EasyMock.replay(hatchingGround, gui, displayMethod, player);

        PlacementUtilities utils = new PlacementUtilities(hatchingGround, gui, displayMethod);
        assertEquals(card1, utils.selectCard(cards, player));

        EasyMock.verify(hatchingGround, gui, displayMethod, player);
    }

    @Test
    public void testSelectElementSpace() {
        Player player = EasyMock.mock(Player.class);
        EasyMock.expect(player.getId()).andReturn(10).anyTimes();

        List<Element> elements = Arrays.asList(new Element(ElementColor.RED));
        EasyMock.expect(player.getElements()).andReturn(elements).anyTimes();

        Card card = new Card();
        card.elementSpaces = new ElementSpace[1];
        card.elementSpaces[0] = new ElementSpace(ElementColor.RED);

        ElementSpaceLogic logic = EasyMock.mock(ElementSpaceLogic.class);
        List<ElementSpace> spaces = Arrays.asList(card.elementSpaces[0]);
        EasyMock.expect(logic.getPlayableSpaces(card, elements)).andReturn(spaces);
        player.elementSpaceLogic = logic;

        PromptHandler promptHandler = EasyMock.mock(PromptHandler.class);
        Gui gui = new Gui(promptHandler, null);
        EasyMock.expect(gui.promptChoice("Pick an element space to place an element on", spaces, 10))
                .andReturn(card.elementSpaces[0]);

        EasyMock.replay(logic, promptHandler, player);

        PlacementUtilities utils = new PlacementUtilities(null, gui, null);
        assertEquals(card.elementSpaces[0], utils.selectElementSpace(card, player));

        EasyMock.verify(logic, promptHandler, player);
    }

}
