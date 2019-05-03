package player;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import element.Element;
import element.ElementColor;
import element.NullElement;
import handler.HandlerFactory;

public class ElementTests {

    @Test
    public void testAddElement() {
        Element element = new Element(ElementColor.BLUE);
        Player player = new Player(6, new HandlerFactory(), 0);

        player.addElement(element);
        assertTrue(player.getElements().contains(element));
    }

    @Test
    public void testRemoveElement() {
        Element element = new Element(ElementColor.BLUE);
        Player player = new Player(6, new HandlerFactory(), 0);

        player.removeElement(element);
        assertFalse(player.getElements().contains(element));
    }

    @Test
    public void testNullElement() {
        Player player = new Player(6, new HandlerFactory(), 0);

        player.addElement(NullElement.getInstance());

        assertFalse(player.getElements().contains(NullElement.getInstance()));
    }


}
