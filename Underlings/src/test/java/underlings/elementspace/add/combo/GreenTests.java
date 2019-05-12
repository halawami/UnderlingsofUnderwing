package underlings.elementspace.add.combo;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import underlings.element.Element;
import underlings.element.ElementColor;
import underlings.element.ElementSpace;
import underlings.element.utilities.ElementSpaceLogic;

public class GreenTests {

    private ElementSpace elementSpace;
    private Element elementOne;
    private Element elementTwo;

    private ElementColor desired = ElementColor.GREEN;
    private ElementColor elementOneColor = ElementColor.BLUE;
    private ElementColor elementTwoColor = ElementColor.YELLOW;
    ElementSpaceLogic logic;

    @Before
    public void init() throws Exception {
        this.elementSpace = new ElementSpace(this.desired);
        this.elementOne = new Element(this.elementOneColor);
        this.elementTwo = new Element(this.elementTwoColor);
        List<String> recipes = Resources.readLines(Resources.getResource("DefaultRecipeList.txt"), Charsets.UTF_8);
        this.logic = new ElementSpaceLogic(recipes);
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
