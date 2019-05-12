package underlings.element;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import underlings.element.utilities.ElementSpaceLogic;

public class ElementSpaceLogicTests {

    @Test
    public void testGetPlayableElementsEmpty() {
        ElementSpaceLogic logic = new ElementSpaceLogic();
        ElementSpace space = new ElementSpace(ElementColor.BLACK);
        List<Element> playerElements = Arrays.asList();

        List<Element> actual = logic.getPlayableElements(space, playerElements);
        assertTrue(actual.isEmpty());
    }
}
