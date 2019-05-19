package underlings.element;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import underlings.utilities.LocaleUtilities;

public class GreenTests {

    private ElementSpace elementSpace;
    private Element elementOne;
    private Element elementTwo;

    private ElementColor desired = ElementColor.GREEN;
    private ElementColor elementOneColor = ElementColor.BLUE;
    private ElementColor elementTwoColor = ElementColor.YELLOW;
    ElementSpaceUtilities logic;

    @Before
    public void init() throws Exception {
        this.elementSpace = new ElementSpace(this.desired);
        this.elementOne = new Element(this.elementOneColor);
        this.elementTwo = new Element(this.elementTwoColor);
        List<String> recipes =
                Resources.readLines(Resources.getResource(LocaleUtilities.get("default_recipe_list")), Charsets.UTF_8);
        this.logic = new ElementSpaceUtilities(recipes);
    }

    @Test
    public void testBlue() {
        this.elementSpace.addElements(this.elementOne);
        assertFalse(logic.isComplete(this.elementSpace));
    }

    @Test
    public void testYellow() {
        this.elementSpace.addElements(this.elementTwo);
        assertFalse(logic.isComplete(this.elementSpace));
    }

    @Test
    public void testBlueThenYellow() {
        this.elementSpace.addElements(this.elementOne);
        this.elementSpace.addElements(this.elementTwo);
        assertTrue(logic.isComplete(this.elementSpace));
    }

    @Test
    public void testYellowThenBlue() {
        this.elementSpace.addElements(this.elementTwo);
        this.elementSpace.addElements(this.elementOne);
        assertTrue(logic.isComplete(this.elementSpace));
    }

    @Test
    public void testBlueAndYellow() {
        this.elementSpace.addElements(this.elementOne, this.elementTwo);
        assertTrue(logic.isComplete(this.elementSpace));
    }

}
