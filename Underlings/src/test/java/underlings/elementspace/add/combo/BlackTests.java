package underlings.elementspace.add.combo;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import underlings.element.Element;
import underlings.element.ElementColor;
import underlings.element.ElementSpace;
import underlings.utilities.ElementSpaceUtilities;

public class BlackTests {

    ElementSpace blackElementSpace;
    ElementSpaceUtilities logic;

    @Before
    public void init() throws IOException {
        this.blackElementSpace = new ElementSpace(ElementColor.BLACK);
        List<String> recipes = Resources.readLines(Resources.getResource("DefaultRecipeList.txt"), Charsets.UTF_8);
        logic = new ElementSpaceUtilities(recipes);
    }

    @Test
    public void testRed() {
        this.blackElementSpace.addElements(new Element(ElementColor.RED));
        assertFalse(logic.isComplete(this.blackElementSpace));
    }

    @Test
    public void testBlue() {
        this.blackElementSpace.addElements(new Element(ElementColor.BLUE));
        assertFalse(logic.isComplete(this.blackElementSpace));
    }

    @Test
    public void testYellow() {
        this.blackElementSpace.addElements(new Element(ElementColor.YELLOW));
        assertFalse(logic.isComplete(this.blackElementSpace));
    }

    @Test
    public void testRedBlue() {
        this.blackElementSpace.addElements(new Element(ElementColor.RED));
        this.blackElementSpace.addElements(new Element(ElementColor.BLUE));
        assertFalse(logic.isComplete(this.blackElementSpace));
    }

    @Test
    public void testBlueYellow() {
        this.blackElementSpace.addElements(new Element(ElementColor.BLUE));
        this.blackElementSpace.addElements(new Element(ElementColor.YELLOW));
        assertFalse(logic.isComplete(this.blackElementSpace));
    }

    @Test
    public void testRedBlueYellow() {
        this.blackElementSpace.addElements(new Element(ElementColor.RED));
        this.blackElementSpace.addElements(new Element(ElementColor.BLUE));
        this.blackElementSpace.addElements(new Element(ElementColor.YELLOW));
        assertTrue(logic.isComplete(this.blackElementSpace));
    }

}
