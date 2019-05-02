package tests.elementspace.destroy;

import org.junit.Assert;
import org.junit.Test;
import underlings.card.Card;
import underlings.element.ElementColor;
import underlings.element.ElementSpace;
import underlings.element.utilities.ElementSpaceLogic;

import java.util.Collections;
import java.util.List;

public class GetDestroyableSpacesTests {

    @Test
    public void testNoDestroyableSpaces() {
        ElementSpace elementSpace = new ElementSpace();
        elementSpace.elements = Collections.emptyList();
        Card card = new Card();
        card.elementSpaces = new ElementSpace[]{elementSpace};
        ElementSpaceLogic elementSpaceLogic = new ElementSpaceLogic();

        List<ElementSpace> destroyableSpaces = elementSpaceLogic.getDestroyableSpaces(card, ElementColor.BLUE);

        Assert.assertEquals(0, destroyableSpaces.size());
    }
}
