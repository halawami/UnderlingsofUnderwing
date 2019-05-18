package underlings.effect.element;

import static org.junit.Assert.assertEquals;

import org.easymock.EasyMock;
import org.junit.Test;

import underlings.card.Card;
import underlings.card.effect.wild.DestroyAllBlackAndWhiteElementsEffect;
import underlings.card.effect.wild.alleggsinplay.AllEggsInPlayEffect;
import underlings.element.ElementColor;
import underlings.element.ElementSpace;
import underlings.element.utilities.ElementSpaceLogic;
import underlings.utilities.LocaleWrap;

public class DestroyBlackAndWhiteElementsTests {

    @Test
    public void effectTest() {
        ElementSpace space = EasyMock.mock(ElementSpace.class);
        space.destroyAllElementsOfColor(ElementColor.BLACK);
        space.destroyAllElementsOfColor(ElementColor.WHITE);

        ElementSpace[] spaces = {space};
        Card card = new Card();
        card.elementSpaces = spaces;
        ElementSpaceLogic logic = EasyMock.mock(ElementSpaceLogic.class);

        EasyMock.replay(space, logic);

        AllEggsInPlayEffect effect = new DestroyAllBlackAndWhiteElementsEffect();
        effect.applyOnCardInPlay(card, logic, null);

        EasyMock.verify(space, logic);
    }

    @Test
    public void effectTestMultipleSpaces() {
        ElementSpace space1 = EasyMock.mock(ElementSpace.class);
        space1.destroyAllElementsOfColor(ElementColor.BLACK);
        space1.destroyAllElementsOfColor(ElementColor.WHITE);

        ElementSpace space2 = EasyMock.mock(ElementSpace.class);
        space2.destroyAllElementsOfColor(ElementColor.BLACK);
        space2.destroyAllElementsOfColor(ElementColor.WHITE);

        ElementSpace[] spaces = {space1, space2};
        Card card = new Card();
        card.elementSpaces = spaces;
        ElementSpaceLogic logic = EasyMock.mock(ElementSpaceLogic.class);

        EasyMock.replay(space1, space2, logic);

        AllEggsInPlayEffect effect = new DestroyAllBlackAndWhiteElementsEffect();
        effect.applyOnCardInPlay(card, logic, null);

        EasyMock.verify(space1, space2, logic);
    }

    @Test
    public void testToString() {
        DestroyAllBlackAndWhiteElementsEffect effect = new DestroyAllBlackAndWhiteElementsEffect();
        assertEquals(LocaleWrap.get("destroy_white_black_elements"), effect.toString());
    }

}
