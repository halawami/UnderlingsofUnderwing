package underlings.element;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import underlings.utilities.LocaleUtilities;

public class ElementSpaceLogicTests {

    ElementSpaceUtilities logic;

    @Before
    public void init() throws IOException {
        List<String> recipes =
                Resources.readLines(Resources.getResource(LocaleUtilities.get("default_recipe_list")), Charsets.UTF_8);
        logic = new ElementSpaceUtilities(recipes);
    }

    @Test
    public void testGetPlayableElementsEmpty() {
        ElementSpace space = new ElementSpace(ElementColor.BLACK);
        List<Element> playerElements = Arrays.asList();

        List<Element> actual = logic.getPlayableElements(space, playerElements);
        assertTrue(actual.isEmpty());
    }

    @Test
    public void testGetPlayableElements() {
        List<Element> playerElements = new ArrayList<>();
        playerElements.add(new Element(ElementColor.BLACK));
        playerElements.add(new Element(ElementColor.WHITE));

        ElementSpace space = new ElementSpace(ElementColor.BLACK);
        List<Element> actual = logic.getPlayableElements(space, playerElements);
        assertEquals(1, actual.size());
        assertTrue(actual.contains(playerElements.get(0)));
    }

    @Test
    public void testGetPlayableElementsAlias1() {
        logic.setOpenElement(ElementColor.BLACK);

        ElementSpace space = new ElementSpace(ElementColor.RED);
        List<Element> playerElements = new ArrayList<>();
        playerElements.add(new Element(ElementColor.BLACK));
        playerElements.add(new Element(ElementColor.WHITE));
        playerElements.add(new Element(ElementColor.RED));

        List<Element> actual = logic.getPlayableElements(space, playerElements);
        assertEquals(2, actual.size());
        assertTrue(actual.contains(playerElements.get(0)));
        assertTrue(actual.contains(playerElements.get(2)));
    }

    @Test
    public void testGetPlayableElementsAlias2() {
        logic.setOpenElement(ElementColor.BLACK);

        ElementSpace space = new ElementSpace(ElementColor.BLACK);
        List<Element> playerElements = new ArrayList<>();
        playerElements.add(new Element(ElementColor.BLACK));
        playerElements.add(new Element(ElementColor.WHITE));

        List<Element> actual = logic.getPlayableElements(space, playerElements);
        assertEquals(1, actual.size());
        assertTrue(actual.contains(playerElements.get(0)));
    }

    @Test
    public void testGetPlayableElementsAlias3() {
        logic.setOpenElement(ElementColor.BLACK);

        ElementSpace space = new ElementSpace(ElementColor.RED);
        space.addElements(new Element(ElementColor.RED));

        List<Element> playerElements = new ArrayList<>();
        playerElements.add(new Element(ElementColor.BLACK));

        List<Element> actual = logic.getPlayableElements(space, playerElements);
        assertTrue(actual.isEmpty());
    }

    @Test
    public void testResetRecipes() {
        ElementSpace space = new ElementSpace(ElementColor.GREEN);

        List<Element> playerElements = new ArrayList<>();
        playerElements.add(new Element(ElementColor.GREEN));
        playerElements.add(new Element(ElementColor.BLUE));
        playerElements.add(new Element(ElementColor.YELLOW));

        List<Element> actual1 = logic.getPlayableElements(space, playerElements);
        assertEquals(3, actual1.size());
        assertTrue(actual1.contains(playerElements.get(0)));
        assertTrue(actual1.contains(playerElements.get(1)));
        assertTrue(actual1.contains(playerElements.get(2)));

        logic.resetRecipes(ElementColor.GREEN);

        List<Element> actual2 = logic.getPlayableElements(space, playerElements);
        assertEquals(1, actual2.size());
        assertTrue(actual2.contains(playerElements.get(0)));
    }

    @Test
    public void testIsValidRecipe() {
        ElementSpace space = new ElementSpace(ElementColor.ORANGE);
        space.addElements(new Element(ElementColor.RED));

        List<String> recipes = new ArrayList<>();
        recipes.add("ORANGE:ORANGE RED,YELLOW");
        ElementSpaceUtilities logic = new ElementSpaceUtilities(recipes);

        List<ElementColor> recipe1 = Arrays.asList(ElementColor.ORANGE);
        assertFalse(logic.isValidRecipe(recipe1, space));

        List<ElementColor> recipe2 = Arrays.asList(ElementColor.RED, ElementColor.YELLOW);
        assertTrue(logic.isValidRecipe(recipe2, space));
    }
}
