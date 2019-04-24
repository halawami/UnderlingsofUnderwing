package tests.player;

import org.junit.Test;
import underlings.element.Element;
import underlings.element.ElementColor;
import underlings.handler.HandlerFactory;
import underlings.player.Player;

import static org.junit.Assert.*;

public class ElementTests {

    @Test
    public void testAddElement() {
        Element element = new Element(ElementColor.BLUE);
        Player player = new Player(6, new HandlerFactory());

        player.addElement(element);
        assertTrue(player.getElements().contains(element));
    }

    @Test
    public void testRemoveElement() {
        Element element = new Element(ElementColor.BLUE);
        Player player = new Player(6, new HandlerFactory());

        player.removeElement(element);
        assertFalse(player.getElements().contains(element));
    }


}
