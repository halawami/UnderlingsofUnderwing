package underlings.gui;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Test;

import underlings.card.Card;
import underlings.element.Element;
import underlings.element.ElementColor;
import underlings.element.ElementSpace;
import underlings.element.ElementSpacePosition;
import underlings.utilities.LocaleUtilities;

public class GetElementSpaceWithColorsTests {

    @Test
    public void testNoSpaces() {
        Card card = new Card();
        card.name = "test_card";
        card.elementSpaces = new ElementSpace[0];

        List<Card> cards = Arrays.asList(card);
        ElementColor[] colorChoices = {ElementColor.RED};
        Gui gui = new Gui(null, null);
        ElementSpace space = gui.getElementSpaceWithColors(cards, colorChoices, 1);
        assertEquals(null, space);
    }

    @Test
    public void testNoColors() {
        Card card = new Card();
        card.name = "test_card";
        card.elementSpaces = new ElementSpace[1];
        card.elementSpaces[0] = new ElementSpace(ElementColor.RED);

        ElementColor[] colorChoices = {};

        Gui gui = new Gui(null, null);
        List<Card> cards = Arrays.asList(card);
        ElementSpace space = gui.getElementSpaceWithColors(cards, colorChoices, 1);
        assertEquals(null, space);
    }

    @Test
    public void testOneSpaceOneColorInvalid() {
        Card card = new Card();
        card.name = "test_card";
        card.elementSpaces = new ElementSpace[1];
        card.elementSpaces[0] = new ElementSpace(ElementColor.RED);
        card.elementSpaces[0].addElements(new Element(ElementColor.RED));

        ElementColor[] colorChoices = {ElementColor.BLUE};

        Gui gui = new Gui(null, null);
        List<Card> cards = Arrays.asList(card);
        ElementSpace space = gui.getElementSpaceWithColors(cards, colorChoices, 1);
        assertEquals(null, space);
    }

    @Test
    public void testOneSpaceOneColorValid() {
        Card card = new Card();
        card.name = "test_card";
        card.elementSpaces = new ElementSpace[1];
        card.elementSpaces[0] = new ElementSpace(ElementColor.RED);
        card.elementSpaces[0].position = ElementSpacePosition.L3_1;
        card.elementSpaces[0].addElements(new Element(ElementColor.RED));

        PromptHandler prompt = EasyMock.mock(PromptHandler.class);
        EasyMock.expect(prompt.promptChoice(LocaleUtilities.get("take_element_yesno"), YesNoChoice.getChoices(), 1))
                .andReturn(YesNoChoice.YES);
        EasyMock.expect(prompt.promptChoice(LocaleUtilities.get("take_element_card"), Arrays.asList(card), 1))
                .andReturn(card);
        EasyMock.expect(prompt.pickFromGrid(EasyMock.anyString(), EasyMock.anyObject(ElementSpace[][].class),
                EasyMock.anyInt())).andReturn(card.elementSpaces[0]);
        EasyMock.replay(prompt);

        Gui gui = new Gui(prompt, null);
        List<Card> cards = Arrays.asList(card);
        ElementColor[] colorChoices = {ElementColor.RED};
        ElementSpace space = gui.getElementSpaceWithColors(cards, colorChoices, 1);
        assertEquals(card.elementSpaces[0], space);

        EasyMock.verify(prompt);
    }

    @Test
    public void testTwoSpacesMultiColorsValid() {
        Card card = new Card();
        card.name = "test_card";
        card.elementSpaces = new ElementSpace[2];
        card.elementSpaces[0] = new ElementSpace(ElementColor.RED);
        card.elementSpaces[0].addElements(new Element(ElementColor.RED));
        card.elementSpaces[0].position = ElementSpacePosition.L3_1;
        card.elementSpaces[1] = new ElementSpace(ElementColor.BLUE);
        card.elementSpaces[1].addElements(new Element(ElementColor.BLUE));
        card.elementSpaces[1].position = ElementSpacePosition.L3_1;

        PromptHandler prompt = EasyMock.mock(PromptHandler.class);
        EasyMock.expect(prompt.promptChoice(LocaleUtilities.get("take_element_yesno"), YesNoChoice.getChoices(), 1))
                .andReturn(YesNoChoice.YES);
        EasyMock.expect(prompt.promptChoice(LocaleUtilities.get("take_element_card"), Arrays.asList(card), 1))
                .andReturn(card);
        EasyMock.expect(prompt.pickFromGrid(EasyMock.anyString(), EasyMock.anyObject(ElementSpace[][].class),
                EasyMock.anyInt())).andReturn(card.elementSpaces[1]);
        EasyMock.replay(prompt);

        Gui gui = new Gui(prompt, null);
        List<Card> cards = Arrays.asList(card);
        ElementColor[] colorChoices = {ElementColor.RED, ElementColor.BLUE};
        ElementSpace space = gui.getElementSpaceWithColors(cards, colorChoices, 1);
        assertEquals(card.elementSpaces[1], space);

        EasyMock.verify(prompt);
    }

    @Test
    public void testChoseNo() {
        Card card = new Card();
        card.name = "test_card";
        card.elementSpaces = new ElementSpace[2];
        card.elementSpaces[0] = new ElementSpace(ElementColor.RED);
        card.elementSpaces[0].addElements(new Element(ElementColor.RED));
        card.elementSpaces[1] = new ElementSpace(ElementColor.BLUE);
        card.elementSpaces[1].addElements(new Element(ElementColor.BLUE));

        PromptHandler prompt = EasyMock.mock(PromptHandler.class);
        EasyMock.expect(prompt.promptChoice(LocaleUtilities.get("take_element_yesno"), YesNoChoice.getChoices(), 1))
                .andReturn(YesNoChoice.NO);
        EasyMock.replay(prompt);

        Gui gui = new Gui(prompt, null);
        List<Card> cards = Arrays.asList(card);
        ElementColor[] colorChoices = {ElementColor.RED, ElementColor.BLUE};
        ElementSpace space = gui.getElementSpaceWithColors(cards, colorChoices, 1);
        assertEquals(null, space);

        EasyMock.verify(prompt);
    }

    @Test
    public void testSecondaryColors() {
        Card card = new Card();
        card.name = "test_card";
        card.elementSpaces = new ElementSpace[2];
        card.elementSpaces[0] = new ElementSpace(ElementColor.ORANGE);
        card.elementSpaces[0].addElements(new Element(ElementColor.RED));
        card.elementSpaces[0].position = ElementSpacePosition.L3_1;
        card.elementSpaces[1] = new ElementSpace(ElementColor.BLUE);
        card.elementSpaces[1].addElements(new Element(ElementColor.BLUE));
        card.elementSpaces[1].position = ElementSpacePosition.L3_1;

        PromptHandler prompt = EasyMock.mock(PromptHandler.class);
        EasyMock.expect(prompt.promptChoice(LocaleUtilities.get("take_element_yesno"), YesNoChoice.getChoices(), 1))
                .andReturn(YesNoChoice.YES);
        EasyMock.expect(prompt.promptChoice(LocaleUtilities.get("take_element_card"), Arrays.asList(card), 1))
                .andReturn(card);
        EasyMock.expect(prompt.pickFromGrid(EasyMock.anyString(), EasyMock.anyObject(ElementSpace[][].class),
                EasyMock.anyInt())).andReturn(card.elementSpaces[1]);
        EasyMock.replay(prompt);

        Gui gui = new Gui(prompt, null);
        List<Card> cards = Arrays.asList(card);
        ElementColor[] colorChoices = {ElementColor.RED, ElementColor.BLUE};
        ElementSpace space = gui.getElementSpaceWithColors(cards, colorChoices, 1);
        assertEquals(card.elementSpaces[1], space);

        EasyMock.verify(prompt);
    }

    @Test
    public void testCardWithValidAndInvalidSpaces() {
        Card card = new Card();
        card.name = "test_card";
        card.elementSpaces = new ElementSpace[2];
        card.elementSpaces[0] = new ElementSpace(ElementColor.ORANGE);
        card.elementSpaces[0].addElements(new Element(ElementColor.RED));
        card.elementSpaces[0].position = ElementSpacePosition.L3_1;
        card.elementSpaces[1] = new ElementSpace(ElementColor.BLUE);
        card.elementSpaces[1].addElements(new Element(ElementColor.BLUE));
        card.elementSpaces[1].position = ElementSpacePosition.L3_1;

        PromptHandler prompt = EasyMock.mock(PromptHandler.class);
        EasyMock.expect(prompt.promptChoice(LocaleUtilities.get("take_element_yesno"), YesNoChoice.getChoices(), 2))
                .andReturn(YesNoChoice.YES);
        EasyMock.expect(prompt.promptChoice(LocaleUtilities.get("take_element_card"), Arrays.asList(card), 2))
                .andReturn(card);
        EasyMock.expect(prompt.pickFromGrid(EasyMock.anyString(), EasyMock.anyObject(ElementSpace[][].class),
                EasyMock.anyInt())).andReturn(card.elementSpaces[0]);
        EasyMock.replay(prompt);

        Gui gui = new Gui(prompt, null);
        List<Card> cards = Arrays.asList(card);
        ElementColor[] colorChoices = {ElementColor.RED};
        ElementSpace space = gui.getElementSpaceWithColors(cards, colorChoices, 2);
        assertEquals(card.elementSpaces[0], space);

        EasyMock.verify(prompt);
    }

}
