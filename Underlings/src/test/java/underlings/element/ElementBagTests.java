package underlings.element;

import static org.junit.Assert.assertEquals;
import static underlings.gui.DrawChoice.BLACK;
import static underlings.gui.DrawChoice.BLUE;
import static underlings.gui.DrawChoice.GREEN;
import static underlings.gui.DrawChoice.ORANGE;
import static underlings.gui.DrawChoice.PURPLE;
import static underlings.gui.DrawChoice.RED;
import static underlings.gui.DrawChoice.WHITE;
import static underlings.gui.DrawChoice.YELLOW;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collections;
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

    @Test
    public void testOneAvailableDrawChoices() throws NoSuchFieldException, IllegalAccessException {
        ElementBag bag = new ElementBag(new ElementFactory(), new Random());
        Field privateMap = ElementBag.class.getDeclaredField("elementCount");
        privateMap.setAccessible(true);

        @SuppressWarnings("unchecked")
        Map<ElementColor, Integer> elementCounts = (Map<ElementColor, Integer>) privateMap.get(bag);
        elementCounts.put(ElementColor.BLUE, 1);
        elementCounts.put(ElementColor.RED, 0);
        elementCounts.put(ElementColor.YELLOW, 0);
        elementCounts.put(ElementColor.PURPLE, 0);
        elementCounts.put(ElementColor.GREEN, 0);
        elementCounts.put(ElementColor.ORANGE, 0);
        elementCounts.put(ElementColor.WHITE, 0);
        elementCounts.put(ElementColor.BLACK, 0);

        List<DrawChoice> availableDrawChoices = bag.getAvailableDrawChoices();
        assertEquals(Arrays.asList(BLUE), availableDrawChoices);
    }

    @Test
    public void testAllButOneAvailableDrawChoices() throws NoSuchFieldException, IllegalAccessException {
        ElementBag bag = new ElementBag(new ElementFactory(), new Random());
        Field privateMap = ElementBag.class.getDeclaredField("elementCount");
        privateMap.setAccessible(true);

        @SuppressWarnings("unchecked")
        Map<ElementColor, Integer> elementCounts = (Map<ElementColor, Integer>) privateMap.get(bag);
        elementCounts.put(ElementColor.BLUE, 1);
        elementCounts.put(ElementColor.RED, 1);
        elementCounts.put(ElementColor.YELLOW, 1);
        elementCounts.put(ElementColor.PURPLE, 1);
        elementCounts.put(ElementColor.GREEN, 1);
        elementCounts.put(ElementColor.ORANGE, 1);
        elementCounts.put(ElementColor.WHITE, 1);
        elementCounts.put(ElementColor.BLACK, 0);

        List<DrawChoice> availableDrawChoices = bag.getAvailableDrawChoices();
        List<DrawChoice> expectedDrawChoices = Arrays.asList(BLUE, RED, YELLOW, PURPLE, GREEN, ORANGE, WHITE);

        Collections.sort(expectedDrawChoices);
        Collections.sort(availableDrawChoices);
        assertEquals(expectedDrawChoices, availableDrawChoices);
    }

    @Test
    public void testAllAvailableDrawChoices() throws NoSuchFieldException, IllegalAccessException {
        ElementBag bag = new ElementBag(new ElementFactory(), new Random());
        Field privateMap = ElementBag.class.getDeclaredField("elementCount");
        privateMap.setAccessible(true);

        @SuppressWarnings("unchecked")
        Map<ElementColor, Integer> elementCounts = (Map<ElementColor, Integer>) privateMap.get(bag);
        elementCounts.put(ElementColor.BLUE, 1);
        elementCounts.put(ElementColor.RED, 1);
        elementCounts.put(ElementColor.YELLOW, 1);
        elementCounts.put(ElementColor.PURPLE, 1);
        elementCounts.put(ElementColor.GREEN, 1);
        elementCounts.put(ElementColor.ORANGE, 1);
        elementCounts.put(ElementColor.WHITE, 1);
        elementCounts.put(ElementColor.BLACK, 1);

        List<DrawChoice> availableDrawChoices = bag.getAvailableDrawChoices();
        List<DrawChoice> expectedDrawChoices = Arrays.asList(BLUE, RED, YELLOW, PURPLE, GREEN, ORANGE, WHITE, BLACK);

        Collections.sort(expectedDrawChoices);
        Collections.sort(availableDrawChoices);
        assertEquals(expectedDrawChoices, availableDrawChoices);
    }

    @Test
    public void testGetAvailableDrawChoicesDifferentCountValues() throws NoSuchFieldException, IllegalAccessException {
        ElementBag bag = new ElementBag(new ElementFactory(), new Random());
        Field privateMap = ElementBag.class.getDeclaredField("elementCount");
        privateMap.setAccessible(true);

        @SuppressWarnings("unchecked")
        Map<ElementColor, Integer> elementCounts = (Map<ElementColor, Integer>) privateMap.get(bag);
        elementCounts.put(ElementColor.BLUE, 0);
        elementCounts.put(ElementColor.RED, 2);
        elementCounts.put(ElementColor.YELLOW, 1);
        elementCounts.put(ElementColor.PURPLE, 3);
        elementCounts.put(ElementColor.GREEN, 1);
        elementCounts.put(ElementColor.ORANGE, 0);
        elementCounts.put(ElementColor.WHITE, 20);
        elementCounts.put(ElementColor.BLACK, 0);

        List<DrawChoice> availableDrawChoices = bag.getAvailableDrawChoices();
        List<DrawChoice> expectedDrawChoices = Arrays.asList(RED, YELLOW, PURPLE, GREEN, WHITE);

        Collections.sort(expectedDrawChoices);
        Collections.sort(availableDrawChoices);
        assertEquals(expectedDrawChoices, availableDrawChoices);
    }
}
