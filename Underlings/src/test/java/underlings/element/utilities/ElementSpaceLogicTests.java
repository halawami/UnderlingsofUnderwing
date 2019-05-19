package underlings.element.utilities;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import underlings.element.Element;
import underlings.element.ElementColor;
import underlings.element.ElementSpace;

public class ElementSpaceLogicTests {

    @Test
    public void testIsValidRecipe() {
        ElementSpace space = new ElementSpace(ElementColor.ORANGE);
        space.addElements(new Element(ElementColor.RED));

        List<String> recipes = new ArrayList<>();
        recipes.add("ORANGE:ORANGE RED,YELLOW");
        ElementSpaceLogic logic = new ElementSpaceLogic(recipes);

        List<ElementColor> recipe1 = Arrays.asList(ElementColor.ORANGE);
        assertFalse(logic.isValidRecipe(recipe1, space));

        List<ElementColor> recipe2 = Arrays.asList(ElementColor.RED, ElementColor.YELLOW);
        assertTrue(logic.isValidRecipe(recipe2, space));
    }
}
