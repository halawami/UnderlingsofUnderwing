package underlings.elementspace.add.combo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import underlings.element.Element;
import underlings.element.ElementColor;
import underlings.element.ElementSpace;
import underlings.element.utilities.ElementSpaceLogic;

public class ValidAdditionsTests {

    ElementSpaceLogic logic;

    @Before
    public void init() throws Exception {
        List<String> recipes = Resources.readLines(Resources.getResource("DefaultRecipeList.txt"), Charsets.UTF_8);
        this.logic = new ElementSpaceLogic(recipes);
    }

    @Test
    public void testEmptyGreen() {
        ElementSpace elementSpace = new ElementSpace(ElementColor.GREEN);
        List<ElementColor> expected = Arrays.asList(ElementColor.GREEN, ElementColor.YELLOW, ElementColor.BLUE);

        List<ElementColor> actual = logic.getValidAdditions(elementSpace);
        Collections.sort(expected);
        Collections.sort(actual);

        assertEquals(expected, actual);
    }

    @Test
    public void testCompleteGreen() {
        ElementSpace elementSpace = new ElementSpace(ElementColor.GREEN);
        elementSpace.addElements(new Element(ElementColor.GREEN));

        assertTrue(logic.getValidAdditions(elementSpace).isEmpty());
    }

    @Test
    public void testEmptyOrange() {
        ElementSpace elementSpace = new ElementSpace(ElementColor.ORANGE);
        List<ElementColor> expected = Arrays.asList(ElementColor.ORANGE, ElementColor.RED, ElementColor.YELLOW);

        List<ElementColor> actual = logic.getValidAdditions(elementSpace);
        Collections.sort(expected);
        Collections.sort(actual);

        assertEquals(expected, actual);
    }

    @Test
    public void testCompleteOrange() {
        ElementSpace elementSpace = new ElementSpace(ElementColor.ORANGE);
        elementSpace.addElements(new Element(ElementColor.ORANGE));

        assertTrue(logic.getValidAdditions(elementSpace).isEmpty());
    }

    @Test
    public void testOpenElements() {
        assertFalse(logic.isOpenElement(ElementColor.WHITE));
        logic.setOpenElement(ElementColor.WHITE);
        assertTrue(logic.isOpenElement(ElementColor.WHITE));

        ElementSpace elementSpace = new ElementSpace(ElementColor.ORANGE);
        List<ElementColor> expected = Arrays.asList(ElementColor.ORANGE, ElementColor.RED, ElementColor.YELLOW);

        List<ElementColor> actual = logic.getValidAdditions(elementSpace);
        Collections.sort(expected);
        Collections.sort(actual);

        assertEquals(expected, actual);
    }

    @Test
    public void testOpenElementsBlack() {
        assertFalse(logic.isOpenElement(ElementColor.BLACK));
        logic.setOpenElement(ElementColor.BLACK);
        assertTrue(logic.isOpenElement(ElementColor.BLACK));

        ElementSpace elementSpace = new ElementSpace(ElementColor.ORANGE);
        List<ElementColor> expected = Arrays.asList(ElementColor.ORANGE, ElementColor.RED, ElementColor.YELLOW);

        List<ElementColor> actual = logic.getValidAdditions(elementSpace);
        Collections.sort(expected);
        Collections.sort(actual);

        assertEquals(expected, actual);
    }

    @Test
    public void testOpenElementsComplete() {
        logic.setOpenElement(ElementColor.WHITE);

        ElementSpace elementSpace = new ElementSpace(ElementColor.ORANGE);
        elementSpace.addElements(new Element(ElementColor.ORANGE));

        List<ElementColor> actual = logic.getValidAdditions(elementSpace);
        assertTrue(actual.isEmpty());
    }
}
