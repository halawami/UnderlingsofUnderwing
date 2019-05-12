package underlings.elementspace.destroy;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import underlings.card.Card;
import underlings.element.Element;
import underlings.element.ElementColor;
import underlings.element.ElementSpace;
import underlings.element.utilities.ElementSpaceLogic;

public class GetDestroyableSpacesTests {

    ElementSpaceLogic logic;

    @Before
    public void loadRecipes() throws Exception {
        List<String> recipes = Resources.readLines(Resources.getResource("DefaultRecipeList.txt"), Charsets.UTF_8);
        logic = new ElementSpaceLogic(recipes);
    }

    @Test
    public void testNoDestroyableSpaces() {
        Card card = new Card();
        card.elementSpaces = getElementSpaces(8);
        ElementSpaceLogic elementSpaceLogic = logic;

        List<ElementSpace> destroyableSpaces = elementSpaceLogic.getDestroyableSpaces(card, ElementColor.BLUE);

        Assert.assertEquals(0, destroyableSpaces.size());
    }

    @Test
    public void testOneDestroyableSpaces() {
        Card card = new Card();
        card.elementSpaces = getElementSpaces(8);
        card.elementSpaces[0].elements.add(new Element(ElementColor.BLUE));
        ElementSpaceLogic elementSpaceLogic = logic;

        List<ElementSpace> destroyableSpaces = elementSpaceLogic.getDestroyableSpaces(card, ElementColor.BLUE);

        Assert.assertEquals(1, destroyableSpaces.size());
        Assert.assertEquals(card.elementSpaces[0], destroyableSpaces.get(0));
    }

    @Test
    public void testOneDestroyableSpacesDifferentElements() {
        Card card = new Card();
        card.elementSpaces = getElementSpaces(8);
        card.elementSpaces[0].elements.add(new Element(ElementColor.RED));
        card.elementSpaces[0].elements.add(new Element(ElementColor.BLUE));
        ElementSpaceLogic elementSpaceLogic = logic;

        List<ElementSpace> destroyableSpaces = elementSpaceLogic.getDestroyableSpaces(card, ElementColor.BLUE);

        Assert.assertEquals(1, destroyableSpaces.size());
        Assert.assertEquals(card.elementSpaces[0], destroyableSpaces.get(0));
    }

    @Test
    public void testSevenDestroyableSpaces() {
        Card card = new Card();
        card.elementSpaces = getElementSpaces(8);
        for (int i = 0; i < 7; i++) {
            card.elementSpaces[i].elements.add(new Element(ElementColor.RED));
        }
        ElementSpaceLogic elementSpaceLogic = logic;

        List<ElementSpace> destroyableSpaces = elementSpaceLogic.getDestroyableSpaces(card, ElementColor.RED);

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
