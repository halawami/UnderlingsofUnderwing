package underlings.effect.element;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.easymock.EasyMock;
import org.junit.Test;

import underlings.MockTest;
import underlings.card.Card;
import underlings.card.effect.wild.alleggsinplay.DestroyAllBlackAndWhiteElementsEffect;
import underlings.card.effect.wild.alleggsinplay.AllEggsInPlayEffect;
import underlings.element.ElementColor;
import underlings.element.ElementSpace;
import underlings.element.utilities.ElementSpaceLogic;
import underlings.utilities.LocaleUtilities;

public class DestroyBlackAndWhiteElementsTests extends MockTest {

    @Test
    public void effectTest() {
        ElementSpace space = this.mock(ElementSpace.class);
        space.destroyAllElementsOfColor(ElementColor.BLACK);
        space.destroyAllElementsOfColor(ElementColor.WHITE);

        Card card = new Card();
        card.elementSpaces = new ElementSpace[] {space};
        ElementSpaceLogic logic = this.mock(ElementSpaceLogic.class);

        this.replayAll();

        AllEggsInPlayEffect effect = new DestroyAllBlackAndWhiteElementsEffect();
        effect.applyOnCardInPlay(card, logic, null, null);
    }

    @Test
    public void effectTestMultipleSpaces() {
        List<ElementSpace> spaces = this.mockListOf(ElementSpace.class).withLengthOf(2);

        for (ElementSpace space : spaces) {
            space.destroyAllElementsOfColor(ElementColor.BLACK);
            space.destroyAllElementsOfColor(ElementColor.WHITE);
        }

        Card card = new Card();
        card.elementSpaces = spaces.toArray(new ElementSpace[0]);
        ElementSpaceLogic logic = EasyMock.mock(ElementSpaceLogic.class);

        this.replayAll();

        AllEggsInPlayEffect effect = new DestroyAllBlackAndWhiteElementsEffect();
        effect.applyOnCardInPlay(card, logic, null, null);
    }

    @Test
    public void testToString() {
        DestroyAllBlackAndWhiteElementsEffect effect = new DestroyAllBlackAndWhiteElementsEffect();
        assertEquals(LocaleUtilities.get("destroy_white_black_elements"), effect.toString());
    }

}
