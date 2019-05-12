package underlings.effect.element;

import org.easymock.EasyMock;
import org.junit.Test;

import underlings.card.Card;
import underlings.card.effect.wild.AllEggsInPlayEffect;
import underlings.card.effect.wild.DestroyAllBlackAndWhiteElementsEffect;
import underlings.element.ElementColor;
import underlings.element.ElementSpace;
import underlings.element.utilities.ElementSpaceLogic;

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
}
