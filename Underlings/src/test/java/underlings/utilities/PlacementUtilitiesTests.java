package underlings.utilities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
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
import underlings.element.ElementSpacePosition;
import underlings.element.ElementSpaceUtilities;
import underlings.element.PlacementUtilities;
import underlings.gui.Gui;
import underlings.gui.PromptHandler;
import underlings.gui.YesNoChoice;
import underlings.handler.WildHandler;
import underlings.hatchingground.Deck;
import underlings.hatchingground.HatchingGround;
import underlings.player.Player;

public class PlacementUtilitiesTests extends MockTest {

    @Before
    public void init() {
        this.hatchingGround = this.mock(HatchingGround.class);
        this.gui = this.mock(Gui.class);
        this.displayMethod = this.mock(Runnable.class);
        this.player = this.mock(Player.class);
        this.elementSpaceLogic = this.mock(ElementSpaceUtilities.class);
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
        card.elementSpaces[0].position = ElementSpacePosition.L3_1;

        List<ElementSpace> spaces = Arrays.asList(card.elementSpaces[0]);
        EasyMock.expect(this.elementSpaceLogic.getPlayableSpaces(card, elements)).andReturn(spaces);
        this.player.elementSpaceLogic = this.elementSpaceLogic;

        Gui gui = EasyMock.mock(Gui.class);
        addMock(gui);
        EasyMock.expect(gui.getElementSpace(LocaleUtilities.get("prompt_element_space"), spaces, 10))
                .andReturn(card.elementSpaces[0]);

        this.replayAll();

        PlacementUtilities utils = new PlacementUtilities(null, gui, null);
        assertEquals(card.elementSpaces[0], utils.selectElementSpace(card, this.player));
    }

    @Test
    public void testPlaceElements() {
        List<Element> elements = Arrays.asList(new Element(ElementColor.BLACK));
        EasyMock.expect(this.player.getElements()).andReturn(elements);
        this.player.elementSpaceLogic = this.elementSpaceLogic;
        EasyMock.expect(this.player.getId()).andReturn(10).anyTimes();

        ElementSpace space = new ElementSpace(ElementColor.BLACK);
        EasyMock.expect(this.elementSpaceLogic.getPlayableElements(space, elements)).andReturn(elements);

        EasyMock.expect(this.gui.promptChoice(LocaleUtilities.get("prompt_element"), elements, 10))
                .andReturn(elements.get(0));

        EasyMock.expect(this.elementSpaceLogic.isOpenElement(ElementColor.BLACK)).andReturn(false);

        this.player.removeElement(elements.get(0));
        this.displayMethod.run();

        List<Element> elements2 = Arrays.asList(new Element(ElementColor.WHITE));
        EasyMock.expect(this.player.getElements()).andReturn(elements2);
        EasyMock.expect(this.elementSpaceLogic.getPlayableElements(space, elements2)).andReturn(elements2);

        EasyMock.expect(this.gui.promptChoice(LocaleUtilities.get("gui_more_moves"), YesNoChoice.getChoices(), 10))
                .andReturn(YesNoChoice.YES);

        EasyMock.expect(this.gui.promptChoice(LocaleUtilities.get("prompt_element"), elements2, 10))
                .andReturn(elements2.get(0));

        EasyMock.expect(this.elementSpaceLogic.isOpenElement(ElementColor.WHITE)).andReturn(true);

        List<ElementColor> colors = Arrays.asList(ElementColor.BLACK);
        EasyMock.expect(this.elementSpaceLogic.getValidAdditions(space)).andReturn(colors);
        EasyMock.expect(this.gui.promptChoice(LocaleUtilities.get("prompt_element_color"), colors, 10))
                .andReturn(ElementColor.BLACK);

        this.player.removeElement(elements2.get(0));
        this.displayMethod.run();
        EasyMock.expect(this.player.getElements()).andReturn(elements2);
        EasyMock.expect(this.elementSpaceLogic.getPlayableElements(space, elements2)).andReturn(Arrays.asList());

        this.replayAll();

        PlacementUtilities utils = new PlacementUtilities(null, this.gui, this.displayMethod);
        utils.placeElements(space, this.player);

        assertEquals(2, space.elements.size());
        assertTrue(space.elements.contains(elements.get(0)));
        assertTrue(space.elements.contains(elements2.get(0)));
        assertEquals(ElementColor.BLACK, elements2.get(0).getAlias());
    }

    @Test
    public void testGetPlayableCards() {
        Deck deck = EasyMock.mock(Deck.class);
        Card card1 = new Card();
        card1.name = "1";
        EasyMock.expect(deck.draw()).andReturn(card1);
        Card card2 = new Card();
        card2.name = "2";
        EasyMock.expect(deck.draw()).andReturn(card2);
        Card card3 = new Card();
        card3.name = "3";
        EasyMock.expect(deck.draw()).andReturn(card3);
        Card card4 = new Card();
        card4.name = "4";
        EasyMock.expect(deck.draw()).andReturn(card4);

        EasyMock.replay(deck);
        HatchingGround ground = new HatchingGround(deck, this.elementSpaceLogic);
        ground.setDimensions(2, 2);
        ground.populate();
        card1.handler = WildHandler.getInstance();
        card3.handler = WildHandler.getInstance();
        EasyMock.verify(deck);

        List<Element> elements = new ArrayList<>();
        EasyMock.expect(this.elementSpaceLogic.getPlayableSpaces(card2, elements)).andReturn(new ArrayList<>());
        List<ElementSpace> space = Arrays.asList(new ElementSpace(ElementColor.BLACK));
        EasyMock.expect(this.elementSpaceLogic.getPlayableSpaces(card4, elements)).andReturn(space);

        this.replayAll();

        PlacementUtilities utils = new PlacementUtilities(ground, null, null);
        List<Card> cards = utils.getPlayableCards(this.elementSpaceLogic, elements);

        assertEquals(1, cards.size());
        assertTrue(cards.contains(card4));
    }

}
