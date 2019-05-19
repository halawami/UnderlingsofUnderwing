package underlings.element;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import underlings.card.Card;
import underlings.utilities.LocaleUtilities;

public class GetValidElementSpacesTest {

    ElementSpaceUtilities logic;
    ElementSpace redSpace;
    ElementSpace blueSpace;
    Element redElement;
    Element blueElement;

    @Before
    public void init() throws IOException {
        redSpace = new ElementSpace(ElementColor.RED);
        blueSpace = new ElementSpace(ElementColor.BLUE);

        redElement = new Element(ElementColor.RED);
        blueElement = new Element(ElementColor.BLUE);

        List<String> recipes =
                Resources.readLines(Resources.getResource(LocaleUtilities.get("default_recipe_list")), Charsets.UTF_8);
        logic = new ElementSpaceUtilities(recipes);
    }

    @Test
    public void noSpaces() {
        ElementSpace[] cardSpaces = {};

        Card testCard = new Card();
        testCard.elementSpaces = cardSpaces;

        List<ElementSpace> actual = logic.getPlayableSpaces(testCard, ElementColor.RED);
        assertEquals(Arrays.asList(), actual);
    }

    @Test
    public void noElements() {
        ElementSpace[] cardSpaces = {redSpace, blueSpace};

        Card testCard = new Card();
        testCard.elementSpaces = cardSpaces;

        List<ElementSpace> actual = logic.getPlayableSpaces(testCard, Arrays.asList());
        assertEquals(Arrays.asList(), actual);
    }

    @Test
    public void redElement() {
        ElementSpace[] cardSpaces = {redSpace, blueSpace};

        Card testCard = new Card();
        testCard.elementSpaces = cardSpaces;

        List<ElementSpace> actual = logic.getPlayableSpaces(testCard, Arrays.asList(redElement));
        assertEquals(Arrays.asList(redSpace), actual);
    }

    @Test
    public void blueElement() {
        ElementSpace[] cardSpaces = {redSpace, blueSpace};

        Card testCard = new Card();
        testCard.elementSpaces = cardSpaces;

        List<ElementSpace> actual = logic.getPlayableSpaces(testCard, Arrays.asList(blueElement));
        assertEquals(Arrays.asList(blueSpace), actual);
    }

    @Test
    public void blueAndRedElements() {
        ElementSpace[] cardSpaces = {redSpace, blueSpace};

        Card testCard = new Card();
        testCard.elementSpaces = cardSpaces;

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

        List<ElementSpace> actual =
                logic.getPlayableSpaces(testCard, Arrays.asList(blueElement, new Element(ElementColor.WHITE)));
        assertEquals(2, actual.size());
        assertTrue(actual.contains(space2));
        assertTrue(actual.contains(space4));
    }

    @Test
    public void testOpenElements() {
        ElementSpace space1 = new ElementSpace(ElementColor.ORANGE);
        ElementSpace space2 = new ElementSpace(ElementColor.BLUE);
        space2.addElements(blueElement);
        ElementSpace[] cardSpaces = {space1, space2};

        Card testCard = new Card();
        testCard.elementSpaces = cardSpaces;

        logic.setOpenElement(ElementColor.WHITE);
        List<ElementSpace> actual = logic.getPlayableSpaces(testCard, Arrays.asList(new Element(ElementColor.WHITE)));
        assertEquals(1, actual.size());
        assertTrue(actual.contains(space1));
    }
}
