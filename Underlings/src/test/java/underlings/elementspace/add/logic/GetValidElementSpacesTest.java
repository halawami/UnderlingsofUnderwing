package underlings.elementspace.add.logic;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import underlings.card.Card;
import underlings.element.Element;
import underlings.element.ElementColor;
import underlings.element.ElementSpace;
import underlings.element.utilities.ElementSpaceLogic;

public class GetValidElementSpacesTest {

    ElementSpace redSpace;
    ElementSpace blueSpace;
    Element redElement;
    Element blueElement;

    @Before
    public void init() {
        redSpace = new ElementSpace(ElementColor.RED);
        blueSpace = new ElementSpace(ElementColor.BLUE);

        redElement = new Element(ElementColor.RED);
        blueElement = new Element(ElementColor.BLUE);
    }

    @Test
    public void noSpaces() {
        ElementSpace[] cardSpaces = {};

        Card testCard = new Card();
        testCard.elementSpaces = cardSpaces;

        ElementSpaceLogic logic = new ElementSpaceLogic();
        List<ElementSpace> actual = logic.getPlayableSpaces(testCard, ElementColor.RED);
        assertEquals(Arrays.asList(), actual);
    }

    @Test
    public void noElements() {
        ElementSpace[] cardSpaces = {redSpace, blueSpace};

        Card testCard = new Card();
        testCard.elementSpaces = cardSpaces;

        ElementSpaceLogic logic = new ElementSpaceLogic();
        List<ElementSpace> actual = logic.getPlayableSpaces(testCard, Arrays.asList());
        assertEquals(Arrays.asList(), actual);
    }

    @Test
    public void redElement() {
        ElementSpace[] cardSpaces = {redSpace, blueSpace};

        Card testCard = new Card();
        testCard.elementSpaces = cardSpaces;

        ElementSpaceLogic logic = new ElementSpaceLogic();
        List<ElementSpace> actual = logic.getPlayableSpaces(testCard, Arrays.asList(redElement));
        assertEquals(Arrays.asList(redSpace), actual);
    }

    @Test
    public void blueElement() {
        ElementSpace[] cardSpaces = {redSpace, blueSpace};

        Card testCard = new Card();
        testCard.elementSpaces = cardSpaces;

        ElementSpaceLogic logic = new ElementSpaceLogic();
        List<ElementSpace> actual = logic.getPlayableSpaces(testCard, Arrays.asList(blueElement));
        assertEquals(Arrays.asList(blueSpace), actual);
    }

    @Test
    public void blueAndRedElements() {
        ElementSpace[] cardSpaces = {redSpace, blueSpace};

        Card testCard = new Card();
        testCard.elementSpaces = cardSpaces;

        ElementSpaceLogic logic = new ElementSpaceLogic();
        List<ElementSpace> actual = logic.getPlayableSpaces(testCard, Arrays.asList(blueElement, redElement));
        assertEquals(2, actual.size());
        assertTrue(actual.contains(redSpace));
        assertTrue(actual.contains(blueSpace));
    }

    @Test
    public void secondaryElements() {
        ElementSpace space1 = new ElementSpace(ElementColor.ORANGE);
        ElementSpace space2 = new ElementSpace(ElementColor.GREEN);
        ElementSpace space3 = new ElementSpace(ElementColor.BLACK);
        space3.addElements(blueElement, blueElement);
        ElementSpace space4 = new ElementSpace(ElementColor.WHITE);
        ElementSpace[] cardSpaces = {space1, space2, space3, space4};

        Card testCard = new Card();
        testCard.elementSpaces = cardSpaces;

        ElementSpaceLogic logic = new ElementSpaceLogic();
        List<ElementSpace> actual =
                logic.getPlayableSpaces(testCard, Arrays.asList(blueElement, new Element(ElementColor.WHITE)));
        assertEquals(2, actual.size());
        assertTrue(actual.contains(space2));
        assertTrue(actual.contains(space4));
    }
}
