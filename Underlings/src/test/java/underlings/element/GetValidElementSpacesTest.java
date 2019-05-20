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
        this.redSpace = new ElementSpace(ElementColor.RED);
        this.blueSpace = new ElementSpace(ElementColor.BLUE);

        this.redElement = new Element(ElementColor.RED);
        this.blueElement = new Element(ElementColor.BLUE);

        List<String> recipes =
                Resources.readLines(Resources.getResource(LocaleUtilities.get("default_recipe_list")), Charsets.UTF_8);
        this.logic = new ElementSpaceUtilities(recipes);
    }

    @Test
    public void testNoSpaces() {
        ElementSpace[] cardSpaces = {};

        Card testCard = new Card();
        testCard.elementSpaces = cardSpaces;

        List<ElementSpace> actual = this.logic.getPlayableSpaces(testCard, ElementColor.RED);
        assertEquals(Arrays.asList(), actual);
    }

    @Test
    public void testNoElements() {
        ElementSpace[] cardSpaces = {this.redSpace, this.blueSpace};

        Card testCard = new Card();
        testCard.elementSpaces = cardSpaces;

        List<ElementSpace> actual = this.logic.getPlayableSpaces(testCard, Arrays.asList());
        assertEquals(Arrays.asList(), actual);
    }

    @Test
    public void testRedElement() {
        ElementSpace[] cardSpaces = {this.redSpace, this.blueSpace};

        Card testCard = new Card();
        testCard.elementSpaces = cardSpaces;

        List<ElementSpace> actual = this.logic.getPlayableSpaces(testCard, Arrays.asList(this.redElement));
        assertEquals(Arrays.asList(this.redSpace), actual);
    }

    @Test
    public void testBlueElement() {
        ElementSpace[] cardSpaces = {this.redSpace, this.blueSpace};

        Card testCard = new Card();
        testCard.elementSpaces = cardSpaces;

        List<ElementSpace> actual = this.logic.getPlayableSpaces(testCard, Arrays.asList(this.blueElement));
        assertEquals(Arrays.asList(this.blueSpace), actual);
    }

    @Test
    public void testBlueAndRedElements() {
        ElementSpace[] cardSpaces = {this.redSpace, this.blueSpace};

        Card testCard = new Card();
        testCard.elementSpaces = cardSpaces;

        List<ElementSpace> actual =
                this.logic.getPlayableSpaces(testCard, Arrays.asList(this.blueElement, this.redElement));
        assertEquals(2, actual.size());
        assertTrue(actual.contains(this.redSpace));
        assertTrue(actual.contains(this.blueSpace));
    }

    @Test
    public void testSecondaryElements() {
        ElementSpace space1 = new ElementSpace(ElementColor.ORANGE);
        ElementSpace space2 = new ElementSpace(ElementColor.GREEN);
        ElementSpace space3 = new ElementSpace(ElementColor.BLACK);
        space3.addElements(this.blueElement, this.blueElement);
        ElementSpace space4 = new ElementSpace(ElementColor.WHITE);
        ElementSpace[] cardSpaces = {space1, space2, space3, space4};

        Card testCard = new Card();
        testCard.elementSpaces = cardSpaces;

        List<ElementSpace> actual = this.logic.getPlayableSpaces(testCard,
                Arrays.asList(this.blueElement, new Element(ElementColor.WHITE)));
        assertEquals(2, actual.size());
        assertTrue(actual.contains(space2));
        assertTrue(actual.contains(space4));
    }

    @Test
    public void testOpenElements() {
        ElementSpace space1 = new ElementSpace(ElementColor.ORANGE);
        ElementSpace space2 = new ElementSpace(ElementColor.BLUE);
        space2.addElements(this.blueElement);
        ElementSpace[] cardSpaces = {space1, space2};

        Card testCard = new Card();
        testCard.elementSpaces = cardSpaces;

        this.logic.setOpenElement(ElementColor.WHITE);
        List<ElementSpace> actual =
                this.logic.getPlayableSpaces(testCard, Arrays.asList(new Element(ElementColor.WHITE)));
        assertEquals(1, actual.size());
        assertTrue(actual.contains(space1));
    }
}
