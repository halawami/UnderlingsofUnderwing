package underlings.card;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import underlings.element.ElementGiver;

public class GetElementGiverTests {

    @Test
    public void testNoElementGivers() {
        Card testedCard = new Card();
        List<ElementGiver> elementGivers = testedCard.getElementGivers();

        Assert.assertEquals(0, elementGivers.size());
    }

}
