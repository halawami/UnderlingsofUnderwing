package underlings.element;

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

import underlings.utilities.LocaleUtilities;

public class ValidAdditionsTests {

    ElementSpaceUtilities logic;

    @Before
    public void init() throws Exception {
        List<String> recipes =
                Resources.readLines(Resources.getResource(LocaleUtilities.get("default_recipe_list")), Charsets.UTF_8);
        this.logic = new ElementSpaceUtilities(recipes);
    }

    @Test
    public void testEmptyGreen() {
        ElementSpace elementSpace = new ElementSpace(ElementColor.GREEN);
        List<ElementColor> expected = Arrays.asList(ElementColor.GREEN, ElementColor.YELLOW, ElementColor.BLUE);

        List<ElementColor> actual = this.logic.getValidAdditions(elementSpace);
        Collections.sort(expected);
        Collections.sort(actual);

        assertEquals(expected, actual);
    }

    @Test
    public void testCompleteGreen() {
        ElementSpace elementSpace = new ElementSpace(ElementColor.GREEN);
        elementSpace.addElements(new Element(ElementColor.GREEN));

        assertTrue(this.logic.getValidAdditions(elementSpace).isEmpty());
    }

    @Test
    public void testEmptyOrange() {
        ElementSpace elementSpace = new ElementSpace(ElementColor.ORANGE);
        List<ElementColor> expected = Arrays.asList(ElementColor.ORANGE, ElementColor.RED, ElementColor.YELLOW);

        List<ElementColor> actual = this.logic.getValidAdditions(elementSpace);
        Collections.sort(expected);
        Collections.sort(actual);

        assertEquals(expected, actual);
    }

    @Test
    public void testCompleteOrange() {
        ElementSpace elementSpace = new ElementSpace(ElementColor.ORANGE);
        elementSpace.addElements(new Element(ElementColor.ORANGE));

        assertTrue(this.logic.getValidAdditions(elementSpace).isEmpty());
    }

    @Test
    public void testOpenElements() {
        assertFalse(this.logic.isOpenElement(ElementColor.WHITE));
        this.logic.setOpenElement(ElementColor.WHITE);
        assertTrue(this.logic.isOpenElement(ElementColor.WHITE));

        ElementSpace elementSpace = new ElementSpace(ElementColor.ORANGE);
        List<ElementColor> expected = Arrays.asList(ElementColor.ORANGE, ElementColor.RED, ElementColor.YELLOW);

        List<ElementColor> actual = this.logic.getValidAdditions(elementSpace);
        Collections.sort(expected);
        Collections.sort(actual);

        assertEquals(expected, actual);
    }

    @Test
    public void testOpenElementsBlack() {
        assertFalse(this.logic.isOpenElement(ElementColor.BLACK));
        this.logic.setOpenElement(ElementColor.BLACK);
        assertTrue(this.logic.isOpenElement(ElementColor.BLACK));

        ElementSpace elementSpace = new ElementSpace(ElementColor.ORANGE);
        List<ElementColor> expected = Arrays.asList(ElementColor.ORANGE, ElementColor.RED, ElementColor.YELLOW);

        List<ElementColor> actual = this.logic.getValidAdditions(elementSpace);
        Collections.sort(expected);
        Collections.sort(actual);

        assertEquals(expected, actual);
    }

    @Test
    public void testOpenElementsComplete() {
        this.logic.setOpenElement(ElementColor.WHITE);

        ElementSpace elementSpace = new ElementSpace(ElementColor.ORANGE);
        elementSpace.addElements(new Element(ElementColor.ORANGE));

        List<ElementColor> actual = this.logic.getValidAdditions(elementSpace);
        assertTrue(actual.isEmpty());
    }

    @Test
    public void testValidAdditionsDone() {
        List<String> recipes = Arrays.asList("PURPLE:RED,BLUE RED");
        ElementSpaceUtilities logic = new ElementSpaceUtilities(recipes);

        ElementSpace elementSpace = new ElementSpace(ElementColor.PURPLE);
        elementSpace.addElements(new Element(ElementColor.RED));

        assertTrue(logic.getValidAdditions(elementSpace).isEmpty());
    }

    @Test
    public void testEmptyRed() {
        ElementSpace elementSpace = new ElementSpace(ElementColor.RED);
        List<ElementColor> expected = Arrays.asList(ElementColor.RED);

        assertEquals(expected, this.logic.getValidAdditions(elementSpace));
    }

    @Test
    public void testEmptyBlue() {
        ElementSpace elementSpace = new ElementSpace(ElementColor.BLUE);
        List<ElementColor> expected = Arrays.asList(ElementColor.BLUE);

        assertEquals(expected, this.logic.getValidAdditions(elementSpace));
    }

    @Test
    public void testRed() {
        this.testColor(ElementColor.RED);
    }

    @Test
    public void testGreen() {
        this.testColor(ElementColor.GREEN);
    }

    @Test
    public void testBlue() {
        this.testColor(ElementColor.BLUE);
    }

    @Test
    public void testOrange() {
        this.testColor(ElementColor.ORANGE);
    }

    @Test
    public void testYellow() {
        this.testColor(ElementColor.YELLOW);
    }

    @Test
    public void testPurple() {
        this.testColor(ElementColor.PURPLE);
    }

    @Test
    public void testWhite() {
        this.testColor(ElementColor.WHITE);
    }

    @Test
    public void testBlack() {
        this.testColor(ElementColor.BLACK);
    }

    public void testColor(ElementColor color) {
        ElementSpace elementSpace = new ElementSpace(color);
        elementSpace.addElements(new Element(color));

        assertTrue(this.logic.getValidAdditions(elementSpace).isEmpty());
    }

}
