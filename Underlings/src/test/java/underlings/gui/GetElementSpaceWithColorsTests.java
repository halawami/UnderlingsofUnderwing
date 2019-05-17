package underlings.gui;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import underlings.card.Card;
import underlings.element.ElementColor;
import underlings.element.ElementSpace;
import underlings.element.NullElement;

public class GetElementSpaceWithColorsTests {

    @Test
    public void testNoSpaces() {
        Card card = new Card();
        card.elementSpaces = new ElementSpace[0];

        List<Card> cards = Arrays.asList(card);
        ElementColor[] colorChoices = {ElementColor.RED};

        Gui gui = new Gui(null, null);
        ElementSpace space = gui.getElementSpaceWithColors(cards, colorChoices);
        assertEquals(NullElement.getInstance(), space);
    }

    @Test
    public void testNoColors() {
        Card card = new Card();
        card.elementSpaces = new ElementSpace[1];
        card.elementSpaces[0] = new ElementSpace(ElementColor.RED);

        ElementColor[] colorChoices = {};

        Gui gui = new Gui(null, null);
        List<Card> cards = Arrays.asList(card);
        ElementSpace space = gui.getElementSpaceWithColors(cards, colorChoices);
        assertEquals(NullElement.getInstance(), space);
    }

}
