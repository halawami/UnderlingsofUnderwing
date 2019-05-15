package underlings.element;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.junit.Test;

import underlings.gui.DrawChoice;

public class ElementBagTests {

    @Test
    public void testEmptyBag()
            throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        ElementBag bag = new ElementBag(new ElementFactory(), new Random());
        Field privateMap = ElementBag.class.getDeclaredField("elementCount");
        privateMap.setAccessible(true);

        @SuppressWarnings("unchecked")
        Map<ElementColor, Integer> elementCounts = (Map<ElementColor, Integer>) privateMap.get(bag);
        elementCounts.put(ElementColor.BLUE, 0);
        elementCounts.put(ElementColor.RED, 0);
        elementCounts.put(ElementColor.YELLOW, 0);
        elementCounts.put(ElementColor.PURPLE, 0);
        elementCounts.put(ElementColor.GREEN, 0);
        elementCounts.put(ElementColor.ORANGE, 0);
        elementCounts.put(ElementColor.WHITE, 0);
        elementCounts.put(ElementColor.BLACK, 0);

        Element actual = bag.drawRandomElement();
        Element expected = NullElement.getInstance();
        assertEquals(expected, actual);
    }

    @Test
    public void testNoAvailableDrawChoices() throws NoSuchFieldException, IllegalAccessException {
        ElementBag bag = new ElementBag(new ElementFactory(), new Random());
        Field privateMap = ElementBag.class.getDeclaredField("elementCount");
        privateMap.setAccessible(true);

        @SuppressWarnings("unchecked")
        Map<ElementColor, Integer> elementCounts = (Map<ElementColor, Integer>) privateMap.get(bag);
        elementCounts.put(ElementColor.BLUE, 0);
        elementCounts.put(ElementColor.RED, 0);
        elementCounts.put(ElementColor.YELLOW, 0);
        elementCounts.put(ElementColor.PURPLE, 0);
        elementCounts.put(ElementColor.GREEN, 0);
        elementCounts.put(ElementColor.ORANGE, 0);
        elementCounts.put(ElementColor.WHITE, 0);
        elementCounts.put(ElementColor.BLACK, 0);

        List<DrawChoice> availableDrawChoices = bag.getAvailableDrawChoices();
        assertEquals(0, availableDrawChoices.size());
    }
}
