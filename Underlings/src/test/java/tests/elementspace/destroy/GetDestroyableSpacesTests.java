package tests.elementspace.destroy;

import org.junit.Assert;
import org.junit.Test;
import underlings.card.Card;
import underlings.element.ElementColor;
import underlings.element.ElementSpace;
import underlings.element.utilities.ElementSpaceLogic;

import java.util.List;

public class GetDestroyableSpacesTests {

    @Test
    public void testNoDestroyableSpaces() {
        Card card = new Card();
        card.elementSpaces = getElementSpaces(8);
        ElementSpaceLogic elementSpaceLogic = new ElementSpaceLogic();

        List<ElementSpace> destroyableSpaces = elementSpaceLogic.getDestroyableSpaces(card, ElementColor.BLUE);

        Assert.assertEquals(0, destroyableSpaces.size());
    }

    @Test
    public void testOneDestroyableSpaces() {
        Card card = new Card();
        card.elementSpaces = getElementSpaces(8);
        card.elementSpaces[0].elements.add(ElementColor.BLUE);
        ElementSpaceLogic elementSpaceLogic = new ElementSpaceLogic();

        List<ElementSpace> destroyableSpaces = elementSpaceLogic.getDestroyableSpaces(card, ElementColor.BLUE);

        Assert.assertEquals(1, destroyableSpaces.size());
        Assert.assertEquals(card.elementSpaces[0], destroyableSpaces.get(0));
    }

    @Test
    public void testSevenDestroyableSpaces() {
        Card card = new Card();
        card.elementSpaces = getElementSpaces(8);
        for (int i = 0; i < 7; i++) {
            card.elementSpaces[i].elements.add(ElementColor.BLUE);
        }
        ElementSpaceLogic elementSpaceLogic = new ElementSpaceLogic();

        List<ElementSpace> destroyableSpaces = elementSpaceLogic.getDestroyableSpaces(card, ElementColor.BLUE);

        Assert.assertEquals(7, destroyableSpaces.size());
        for (int i = 0; i < 7; i++) {
            Assert.assertEquals(card.elementSpaces[i], destroyableSpaces.get(i));
        }
    }


    private ElementSpace[] getElementSpaces(int numberOfSpaces) {
        ElementSpace[] elementSpaces = new ElementSpace[numberOfSpaces];
        for (int i = 0; i < numberOfSpaces; i++) {
            elementSpaces[i] = new ElementSpace();
        }
        return elementSpaces;
    }
}
