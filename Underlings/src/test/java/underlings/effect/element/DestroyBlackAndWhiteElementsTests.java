package underlings.effect.element;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import underlings.MockTest;
import underlings.card.Card;
import underlings.card.effect.AllEggsInPlayEffect;
import underlings.card.effect.wild.alleggsinplay.DestroyAllBlackAndWhiteElementsEffect;
import underlings.element.ElementColor;
import underlings.element.ElementSpace;
import underlings.utilities.LocaleUtilities;

public class DestroyBlackAndWhiteElementsTests extends MockTest {

    @Test
    public void effectTest() {
        ElementSpace space = this.mock(ElementSpace.class);
        space.destroyAllElementsOfColor(ElementColor.BLACK);
        space.destroyAllElementsOfColor(ElementColor.WHITE);

        Card card = new Card();
        card.elementSpaces = new ElementSpace[]{space};

        this.replayAll();

        DestroyAllBlackAndWhiteElementsEffect effect = new DestroyAllBlackAndWhiteElementsEffect();
        effect.applyOnCardInPlay(card);
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

        this.replayAll();

        AllEggsInPlayEffect effect = new DestroyAllBlackAndWhiteElementsEffect();
        effect.applyOnCardInPlay(card);
    }

    @Test
    public void testToString() {
        DestroyAllBlackAndWhiteElementsEffect effect = new DestroyAllBlackAndWhiteElementsEffect();
        assertEquals(LocaleUtilities.get("destroy_white_black_elements"), effect.toString());
    }

}
