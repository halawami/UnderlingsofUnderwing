package underlings.effect.hatchingground;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Test;

import underlings.card.Card;
import underlings.card.effect.wild.adjacenteggs.destroy.AllElementsEffect;
import underlings.card.effect.wild.adjacenteggs.destroy.ElementsEffect;
import underlings.card.effect.wild.adjacenteggs.destroy.OneElementEffect;
import underlings.element.ElementColor;
import underlings.element.ElementSpace;
import underlings.element.utilities.ElementSpaceLogic;
import underlings.game.HatchingGround;
import underlings.gui.Gui;

public class DestroyElementsOnAllAdjacentEggsEffectTests {

    @Test
    public void testApplyOneElementColor() {
        ElementSpaceLogic elementSpaceLogic = EasyMock.mock(ElementSpaceLogic.class);
        Card adjacentCard = EasyMock.mock(Card.class);
        ElementsEffect testedEffect = EasyMock.partialMockBuilder(ElementsEffect.class)
                .addMockedMethod("destroyElementsOfColorOnCard").createMock();
        testedEffect.elementColors = new ElementColor[] {ElementColor.BLUE};

        testedEffect.destroyElementsOfColorOnCard(ElementColor.BLUE, adjacentCard, elementSpaceLogic);
        Gui gui = EasyMock.mock(Gui.class);
        HatchingGround hatchingGround = EasyMock.mock(HatchingGround.class);
        EasyMock.replay(testedEffect, elementSpaceLogic, adjacentCard, gui, hatchingGround);

        testedEffect.applyOnAdjacentEgg(adjacentCard, null, elementSpaceLogic, hatchingGround, gui);

        EasyMock.verify(testedEffect, elementSpaceLogic, adjacentCard, gui, hatchingGround);
    }

    @Test
    public void testApplyTwoElementColor() {
        ElementSpaceLogic elementSpaceLogic = EasyMock.mock(ElementSpaceLogic.class);
        Card adjacentCard = EasyMock.mock(Card.class);
        ElementsEffect testedEffect = EasyMock.partialMockBuilder(ElementsEffect.class)
                .addMockedMethod("destroyElementsOfColorOnCard").createMock();
        testedEffect.elementColors = new ElementColor[] {ElementColor.BLUE, ElementColor.RED};

        testedEffect.destroyElementsOfColorOnCard(ElementColor.BLUE, adjacentCard, elementSpaceLogic);
        testedEffect.destroyElementsOfColorOnCard(ElementColor.RED, adjacentCard, elementSpaceLogic);
        Gui gui = EasyMock.mock(Gui.class);
        HatchingGround hatchingGround = EasyMock.mock(HatchingGround.class);

        EasyMock.replay(testedEffect, elementSpaceLogic, adjacentCard, gui, hatchingGround);

        testedEffect.applyOnAdjacentEgg(adjacentCard, null, elementSpaceLogic, hatchingGround, gui);

        EasyMock.verify(testedEffect, elementSpaceLogic, adjacentCard, gui, hatchingGround);
    }

    private List<Card> getMockedCards(int numberOfCards) {
        List<Card> mockedCards = new ArrayList<>();
        for (int i = 0; i < numberOfCards; i++) {
            mockedCards.add(EasyMock.niceMock(Card.class));
        }
        return mockedCards;
    }

    @Test
    public void testDestroyElementOnCardNoDestroyableElements() {
        ElementColor blue = ElementColor.BLUE;
        Card mockedCard = EasyMock.mock(Card.class);
        ElementSpaceLogic elementSpaceLogic = EasyMock.mock(ElementSpaceLogic.class);

        ElementsEffect testedEffect = EasyMock.partialMockBuilder(ElementsEffect.class)
                .addMockedMethod("destroyElementsOfColorOnSpace").createMock();

        EasyMock.expect(elementSpaceLogic.getDestroyableSpaces(mockedCard, blue)).andReturn(Collections.emptyList());

        EasyMock.replay(mockedCard, elementSpaceLogic);

        testedEffect.destroyElementsOfColorOnCard(blue, mockedCard, elementSpaceLogic);

        EasyMock.verify(mockedCard, elementSpaceLogic);
    }

    @Test
    public void testDestroyElementOnCardOneDestroyableSpace() {
        ElementColor blue = ElementColor.BLUE;
        Card mockedCard = EasyMock.mock(Card.class);
        ElementSpaceLogic elementSpaceLogic = EasyMock.mock(ElementSpaceLogic.class);
        ElementSpace mockedDestroyableSpace = EasyMock.mock(ElementSpace.class);

        ElementsEffect testedEffect = EasyMock.partialMockBuilder(ElementsEffect.class)
                .addMockedMethod("destroyElementsOfColorOnSpace").createMock();

        EasyMock.expect(elementSpaceLogic.getDestroyableSpaces(mockedCard, blue))
                .andReturn(Arrays.asList(mockedDestroyableSpace));
        testedEffect.destroyElementsOfColorOnSpace(mockedDestroyableSpace, blue);

        EasyMock.replay(mockedCard, elementSpaceLogic, mockedDestroyableSpace);

        testedEffect.destroyElementsOfColorOnCard(blue, mockedCard, elementSpaceLogic);

        EasyMock.verify(mockedCard, elementSpaceLogic, mockedDestroyableSpace);
    }

    @Test
    public void testDestroyElementOnCardEightDestroyableSpace() {
        ElementColor blue = ElementColor.BLUE;
        Card mockedCard = EasyMock.mock(Card.class);
        ElementSpaceLogic elementSpaceLogic = EasyMock.mock(ElementSpaceLogic.class);
        List<ElementSpace> mockedDestroyableSpaces = getMockedDestroyableSpaces(8);

        ElementsEffect testedEffect = EasyMock.partialMockBuilder(ElementsEffect.class)
                .addMockedMethod("destroyElementsOfColorOnSpace").createMock();

        EasyMock.expect(elementSpaceLogic.getDestroyableSpaces(mockedCard, blue)).andReturn(mockedDestroyableSpaces);
        for (ElementSpace destroyableSpace : mockedDestroyableSpaces) {
            testedEffect.destroyElementsOfColorOnSpace(destroyableSpace, blue);
        }

        EasyMock.replay(mockedCard, elementSpaceLogic);
        for (ElementSpace destroyableSpace : mockedDestroyableSpaces) {
            EasyMock.replay(destroyableSpace);
        }

        testedEffect.destroyElementsOfColorOnCard(blue, mockedCard, elementSpaceLogic);

        EasyMock.verify(mockedCard, elementSpaceLogic);
        for (ElementSpace destroyableSpace : mockedDestroyableSpaces) {
            EasyMock.verify(destroyableSpace);
        }
    }

    @Test
    public void testDestroyAllElementsOnAllAdjacentEggsEffect() {
        ElementColor blue = ElementColor.BLUE;
        ElementSpace mockElementSpace = EasyMock.mock(ElementSpace.class);

        AllElementsEffect testedEffect = new AllElementsEffect();

        mockElementSpace.destroyAllElementsOfColor(blue);

        EasyMock.replay(mockElementSpace);

        testedEffect.destroyElementsOfColorOnSpace(mockElementSpace, blue);

        EasyMock.verify(mockElementSpace);
    }

    @Test
    public void testDestroyOneElementOnAllAdjacentEggsEffect() {
        ElementColor blue = ElementColor.BLUE;
        ElementSpace mockElementSpace = EasyMock.mock(ElementSpace.class);

        OneElementEffect testedEffect = new OneElementEffect();

        mockElementSpace.destroyOneElementOfColor(blue);

        EasyMock.replay(mockElementSpace);

        testedEffect.destroyElementsOfColorOnSpace(mockElementSpace, blue);

        EasyMock.verify(mockElementSpace);
    }

    private List<ElementSpace> getMockedDestroyableSpaces(int numberOfSpaces) {
        List<ElementSpace> mockedPlayableSpaces = new ArrayList<>();
        for (int i = 0; i < numberOfSpaces; i++) {
            mockedPlayableSpaces.add(EasyMock.mock(ElementSpace.class));
        }
        return mockedPlayableSpaces;
    }

}
