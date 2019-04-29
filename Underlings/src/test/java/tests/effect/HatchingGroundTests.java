package tests.effect;

import java.util.Arrays;
import java.util.List;

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

public class HatchingGroundTests {

    @Test
    public void testAddElementsToAllAdjacentEggsEffectOneColor() {
        HatchingGround mockHatchingGround = EasyMock.mock(HatchingGround.class);

        Card card = new Card();
        ElementSpaceLogic mockElementSpaceLogic =
                EasyMock.mock(ElementSpaceLogic.class);

        Card adjacentCard = new Card();
        ElementSpace firstMockElementSpace =
                EasyMock.partialMockBuilder(ElementSpace.class)
                        .addMockedMethod("addElements").createMock();
        firstMockElementSpace.color = ElementColor.BLUE;
        ElementSpace secondMockElementSpace =
                EasyMock.partialMockBuilder(ElementSpace.class)
                        .addMockedMethod("addElements").createMock();
        secondMockElementSpace.color = ElementColor.BLUE;

        Card secondAdjacentCard = new Card();
        ElementSpace secondCardFirstElementSpace =
                EasyMock.partialMockBuilder(ElementSpace.class)
                        .addMockedMethod("addElements").createMock();
        secondCardFirstElementSpace.color = ElementColor.BLUE;
        ElementSpace secondCardSecondMockElementSpace =
                EasyMock.partialMockBuilder(ElementSpace.class)
                        .addMockedMethod("addElements").createMock();
        secondCardSecondMockElementSpace.color = ElementColor.BLUE;

        adjacentCard.elementSpaces = new ElementSpace[] {firstMockElementSpace,
                secondMockElementSpace};
        secondAdjacentCard.elementSpaces = new ElementSpace[] {
                secondCardFirstElementSpace, secondCardSecondMockElementSpace};
        List<Card> mockAdjacentCards =
                Arrays.asList(adjacentCard, secondAdjacentCard);

        Element mockElement = EasyMock.mock(Element.class);
        ElementBag mockElementBag = EasyMock.mock(ElementBag.class);

        AddElementsToAllAdjacentEggsEffect addElementsEffect =
                new AddElementsToAllAdjacentEggsEffect();
        addElementsEffect.on(mockHatchingGround).on(card)
                .on(mockElementSpaceLogic).on(mockElementBag);

        ElementColor[] elementColorsToAdd =
                new ElementColor[] {ElementColor.BLUE};
        addElementsEffect.elementColors = elementColorsToAdd;

        EasyMock.expect(mockHatchingGround.getAdjacentCards(card))
                .andReturn(mockAdjacentCards);
        EasyMock.expect(mockElementSpaceLogic.getPlayableSpaces(
                Arrays.asList(elementColorsToAdd), adjacentCard))
                .andReturn(Arrays.asList(firstMockElementSpace,
                        secondMockElementSpace));
        EasyMock.expect(mockElementSpaceLogic.getPlayableSpaces(
                Arrays.asList(elementColorsToAdd), secondAdjacentCard))
                .andReturn(Arrays.asList(secondCardFirstElementSpace,
                        secondCardSecondMockElementSpace));

        EasyMock.expect(
                mockElementSpaceLogic.getValidAdditions(firstMockElementSpace))
                .andReturn(Arrays.asList(ElementColor.BLUE));
        EasyMock.expect(
                mockElementSpaceLogic.getValidAdditions(secondMockElementSpace))
                .andReturn(Arrays.asList(ElementColor.BLUE));

        EasyMock.expect(mockElementSpaceLogic
                .getValidAdditions(secondCardFirstElementSpace))
                .andReturn(Arrays.asList(ElementColor.BLUE));
        EasyMock.expect(mockElementSpaceLogic
                .getValidAdditions(secondCardSecondMockElementSpace))
                .andReturn(Arrays.asList(ElementColor.BLUE));

        EasyMock.expect(mockElementBag.drawElementFromList(elementColorsToAdd))
                .andReturn(mockElement).times(4);
        firstMockElementSpace.addElements(mockElement);
        secondMockElementSpace.addElements(mockElement);
        secondCardFirstElementSpace.addElements(mockElement);
        secondCardSecondMockElementSpace.addElements(mockElement);

        EasyMock.replay(mockHatchingGround, mockElementSpaceLogic,
                firstMockElementSpace, secondMockElementSpace,
                secondCardFirstElementSpace, secondCardSecondMockElementSpace,
                mockElement, mockElementBag);

        addElementsEffect.apply();

        EasyMock.verify(mockHatchingGround, mockElementSpaceLogic,
                firstMockElementSpace, secondMockElementSpace,
                secondCardFirstElementSpace, secondCardSecondMockElementSpace,
                mockElement, mockElementBag);
    }

}
