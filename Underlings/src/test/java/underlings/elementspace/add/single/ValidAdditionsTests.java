package underlings.elementspace.add.single;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import java.util.Arrays;
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

    public void testColor(ElementColor color) {
        ElementSpace elementSpace = new ElementSpace(color);
        elementSpace.addElements(new Element(color));

        assertTrue(this.logic.getValidAdditions(elementSpace).isEmpty());
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

}
