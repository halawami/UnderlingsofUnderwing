package tests.effect;

import org.easymock.EasyMock;
import org.junit.Test;
import underlings.card.Card;
import underlings.card.effect.wild.AddElementsToAllAdjacentEggsEffect;
import underlings.element.Element;
import underlings.element.ElementBag;
import underlings.element.ElementColor;
import underlings.element.ElementSpace;
import underlings.element.utilities.ElementSpaceLogic;
import underlings.game.HatchingGround;

import java.util.Arrays;
import java.util.List;

public class HatchingGroundTests {

    @Test
    public void testAddElementsToAllAdjacentEggsEffectOneColor() {
        HatchingGround mockHatchingGround = EasyMock.mock(HatchingGround.class);

        Card card = new Card();
        ElementSpaceLogic mockElementSpaceLogic = EasyMock.mock(ElementSpaceLogic.class);

        Card adjacentCard = new Card();
        ElementSpace mockElementSpace = EasyMock.partialMockBuilder(ElementSpace.class)
                .addMockedMethod("addElements").createMock();
        mockElementSpace.color = ElementColor.BLUE;
        adjacentCard.elementSpaces = new ElementSpace[]{mockElementSpace};
        List<Card> mockAdjacentCards = Arrays.asList(adjacentCard);

        Element mockElement = EasyMock.mock(Element.class);
        ElementBag mockElementBag = EasyMock.mock(ElementBag.class);

        AddElementsToAllAdjacentEggsEffect addElementsEffect = new AddElementsToAllAdjacentEggsEffect();
        addElementsEffect.on(mockHatchingGround).on(card).on(mockElementSpaceLogic).on(mockElementBag);

        ElementColor[] elementColorsToAdd = new ElementColor[]{ElementColor.BLUE};
        addElementsEffect.elementColors = elementColorsToAdd;

        EasyMock.expect(mockHatchingGround.getAdjacentCards(card)).andReturn(mockAdjacentCards);
        EasyMock.expect(mockElementSpaceLogic.getPlayableSpaces(Arrays.asList(elementColorsToAdd), adjacentCard)).andReturn(Arrays.asList(mockElementSpace));
        EasyMock.expect(mockElementSpaceLogic.getValidAdditions(mockElementSpace)).andReturn(Arrays.asList(ElementColor.BLUE));
        EasyMock.expect(mockElementBag.drawElementFromList(elementColorsToAdd)).andReturn(mockElement);
        mockElementSpace.addElements(mockElement);

        EasyMock.replay(mockHatchingGround, mockElementSpaceLogic, mockElementSpace, mockElement, mockElementBag);

        addElementsEffect.apply();

        EasyMock.verify(mockHatchingGround, mockElementSpaceLogic, mockElementSpace, mockElement, mockElementBag);


    }

}
