package underlings.utilities;

import static org.junit.Assert.assertEquals;
import java.util.Arrays;
import java.util.List;
import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;
import underlings.MockTest;
import underlings.card.Card;
import underlings.element.Element;
import underlings.element.ElementColor;
import underlings.element.ElementSpace;
import underlings.element.utilities.ElementSpaceLogic;
import underlings.game.HatchingGround;
import underlings.gui.Gui;
import underlings.gui.PromptHandler;
import underlings.player.Player;

public class PlacementUtilsTests extends MockTest {

    @Before
    public void init() {
        this.hatchingGround = this.mock(HatchingGround.class);
        this.gui = this.mock(Gui.class);
        this.displayMethod = this.mock(Runnable.class);
        this.player = this.mock(Player.class);
        this.elementSpaceLogic = this.mock(ElementSpaceLogic.class);
        this.promptHandler = this.mock(PromptHandler.class);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testSelectCard() {
        EasyMock.expect(this.player.getId()).andReturn(10).anyTimes();

        Card card1 = new Card();
        Card card2 = new Card();
        List<Card> cards = Arrays.asList(card1, card2);

        EasyMock.expect(this.gui.getCard(EasyMock.anyInt(), EasyMock.anyString(),
                EasyMock.anyObject(HatchingGround.class), EasyMock.anyObject(List.class))).andReturn(card1);

        this.replayAll();

        PlacementUtilities utils = new PlacementUtilities(this.hatchingGround, this.gui, this.displayMethod);
        assertEquals(card1, utils.selectCard(cards, this.player));
    }

    @Test
    public void testSelectElementSpace() {
        EasyMock.expect(this.player.getId()).andReturn(10).anyTimes();

        List<Element> elements = Arrays.asList(new Element(ElementColor.RED));
        EasyMock.expect(this.player.getElements()).andReturn(elements).anyTimes();

        Card card = new Card();
        card.elementSpaces = new ElementSpace[1];
        card.elementSpaces[0] = new ElementSpace(ElementColor.RED);

        List<ElementSpace> spaces = Arrays.asList(card.elementSpaces[0]);
        EasyMock.expect(this.elementSpaceLogic.getPlayableSpaces(card, elements)).andReturn(spaces);
        this.player.elementSpaceLogic = this.elementSpaceLogic;

        Gui gui = new Gui(this.promptHandler, null);
        EasyMock.expect(gui.promptChoice("Pick an element space to place an element on", spaces, 10))
                .andReturn(card.elementSpaces[0]);

        this.replayAll();

        PlacementUtilities utils = new PlacementUtilities(null, gui, null);
        assertEquals(card.elementSpaces[0], utils.selectElementSpace(card, this.player));
    }

}
