package underlings.gui;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import underlings.card.Card;
import underlings.element.ElementColor;
import underlings.element.ElementSpace;

public class GetElementSpaceWithColorsTests {

    @Test
    public void testNoSpaces() {
        Card card = new Card();
        card.elementSpaces = new ElementSpace[0];

        List<Card> cards = Arrays.asList(card);
        ElementColor[] colorChoices = {ElementColor.RED};

        Gui gui = new Gui(null, null);
        ElementSpace space = gui.getElementSpaceWithColors(cards, colorChoices);
        assertEquals(null, space);
    }

}
