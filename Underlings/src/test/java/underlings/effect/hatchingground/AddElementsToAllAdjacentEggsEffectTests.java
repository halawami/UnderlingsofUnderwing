package underlings.effect.hatchingground;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Test;

import underlings.card.Card;
import underlings.card.effect.wild.adjacenteggs.AddElementsEffect;
import underlings.element.Element;
import underlings.element.ElementBag;
import underlings.element.ElementColor;
import underlings.element.ElementSpace;
import underlings.element.utilities.ElementSpaceLogic;
import underlings.game.HatchingGround;
import underlings.gui.Gui;

public class AddElementsToAllAdjacentEggsEffectTests {

    @Test
    public void testApplyOneElementColor() {
        ElementBag elementBag = EasyMock.mock(ElementBag.class);
        ElementSpaceLogic elementSpaceLogic = EasyMock.mock(ElementSpaceLogic.class);
        Card adjacentCard = EasyMock.mock(Card.class);
        AddElementsEffect testedEffect =
                EasyMock.partialMockBuilder(AddElementsEffect.class).addMockedMethod("addElementToCard").createMock();
        testedEffect.elementColors = new ElementColor[] {ElementColor.BLUE};

        testedEffect.addElementToCard(ElementColor.BLUE, adjacentCard, elementSpaceLogic, elementBag);
        Gui gui = EasyMock.mock(Gui.class);
        HatchingGround hatchingGround = EasyMock.mock(HatchingGround.class);
        EasyMock.replay(elementBag, adjacentCard, elementSpaceLogic, testedEffect, gui, hatchingGround);

        testedEffect.applyOnAdjacentEgg(adjacentCard, elementBag, elementSpaceLogic, hatchingGround, gui);

        EasyMock.verify(elementBag, adjacentCard, elementSpaceLogic, testedEffect, gui, hatchingGround);
    }

    @Test
    public void testApplyTwoDifferentElementColor() {
        ElementBag elementBag = EasyMock.mock(ElementBag.class);
        ElementSpaceLogic elementSpaceLogic = EasyMock.mock(ElementSpaceLogic.class);
        Card adjacentCard = EasyMock.mock(Card.class);
        AddElementsEffect testedEffect =
                EasyMock.partialMockBuilder(AddElementsEffect.class).addMockedMethod("addElementToCard").createMock();
        testedEffect.elementColors = new ElementColor[] {ElementColor.BLUE, ElementColor.RED};

        testedEffect.addElementToCard(ElementColor.BLUE, adjacentCard, elementSpaceLogic, elementBag);
        testedEffect.addElementToCard(ElementColor.RED, adjacentCard, elementSpaceLogic, elementBag);
        Gui gui = EasyMock.mock(Gui.class);
        HatchingGround hatchingGround = EasyMock.mock(HatchingGround.class);
        EasyMock.replay(elementBag, adjacentCard, elementSpaceLogic, testedEffect, gui, hatchingGround);

        testedEffect.applyOnAdjacentEgg(adjacentCard, elementBag, elementSpaceLogic, hatchingGround, gui);

        EasyMock.verify(elementBag, adjacentCard, elementSpaceLogic, testedEffect, gui, hatchingGround);
    }

    @Test
    public void testApplyTwoSameElementColor() {
        ElementBag elementBag = EasyMock.mock(ElementBag.class);
        ElementSpaceLogic elementSpaceLogic = EasyMock.mock(ElementSpaceLogic.class);
        Card adjacentCard = EasyMock.mock(Card.class);
        AddElementsEffect testedEffect =
                EasyMock.partialMockBuilder(AddElementsEffect.class).addMockedMethod("addElementToCard").createMock();
        testedEffect.elementColors = new ElementColor[] {ElementColor.BLUE, ElementColor.BLUE};

        testedEffect.addElementToCard(ElementColor.BLUE, adjacentCard, elementSpaceLogic, elementBag);
        testedEffect.addElementToCard(ElementColor.BLUE, adjacentCard, elementSpaceLogic, elementBag);
        Gui gui = EasyMock.mock(Gui.class);
        HatchingGround hatchingGround = EasyMock.mock(HatchingGround.class);
        EasyMock.replay(elementBag, adjacentCard, elementSpaceLogic, testedEffect, gui, hatchingGround);

        testedEffect.applyOnAdjacentEgg(adjacentCard, elementBag, elementSpaceLogic, hatchingGround, gui);

        EasyMock.verify(elementBag, adjacentCard, elementSpaceLogic, testedEffect, gui, hatchingGround);
    }

    private List<Card> getMockedCards(int numberOfCards) {
        List<Card> mockedCards = new ArrayList<>();
        for (int i = 0; i < numberOfCards; i++) {
            mockedCards.add(EasyMock.niceMock(Card.class));
        }
        return mockedCards;
    }

    @Test
    public void testAddElementToCardNoPlayableSpace() {
        ElementColor blue = ElementColor.BLUE;
        Card mockedCard = EasyMock.mock(Card.class);
        ElementSpaceLogic elementSpaceLogic = EasyMock.mock(ElementSpaceLogic.class);
        ElementBag elementBag = EasyMock.mock(ElementBag.class);

        AddElementsEffect testedEffect = new AddElementsEffect();

        EasyMock.expect(elementSpaceLogic.getPlayableSpaces(mockedCard, blue)).andReturn(Collections.emptyList());

        EasyMock.replay(mockedCard, elementSpaceLogic, elementBag);

        testedEffect.addElementToCard(blue, mockedCard, elementSpaceLogic, elementBag);

        EasyMock.verify(mockedCard, elementSpaceLogic, elementBag);
    }

    @Test
    public void testAddElementToCardOnePlayableSpace() {
        ElementColor blue = ElementColor.BLUE;
        Card mockedCard = EasyMock.mock(Card.class);
        ElementSpaceLogic elementSpaceLogic = EasyMock.mock(ElementSpaceLogic.class);
        ElementBag elementBag = EasyMock.mock(ElementBag.class);
        Element stubElement = EasyMock.niceMock(Element.class);
        ElementSpace mockedPlayableSpace = EasyMock.mock(ElementSpace.class);

        EasyMock.expect(elementSpaceLogic.getPlayableSpaces(mockedCard, blue))
                .andReturn(Arrays.asList(mockedPlayableSpace));
        EasyMock.expect(elementBag.drawElementFromList(blue)).andReturn(stubElement);
        mockedPlayableSpace.addElements(stubElement);

        EasyMock.replay(mockedCard, elementSpaceLogic, elementBag, mockedPlayableSpace);

        AddElementsEffect testedEffect = new AddElementsEffect();
        testedEffect.addElementToCard(blue, mockedCard, elementSpaceLogic, elementBag);

        EasyMock.verify(mockedCard, elementSpaceLogic, elementBag, mockedPlayableSpace);
    }

    @Test
    public void testAddElementToCardEightPlayableSpaces() {
        ElementColor blue = ElementColor.BLUE;
        Card mockedCard = EasyMock.mock(Card.class);
        ElementSpaceLogic elementSpaceLogic = EasyMock.mock(ElementSpaceLogic.class);
        ElementBag elementBag = EasyMock.mock(ElementBag.class);
        Element stubElement = EasyMock.niceMock(Element.class);
        List<ElementSpace> mockedPlayableSpaces = getMockedPlayableSpaces(8);

        EasyMock.expect(elementSpaceLogic.getPlayableSpaces(mockedCard, blue)).andReturn(mockedPlayableSpaces);
        EasyMock.expect(elementBag.drawElementFromList(blue)).andReturn(stubElement);
        mockedPlayableSpaces.get(0).addElements(stubElement);

        EasyMock.replay(mockedCard, elementSpaceLogic, elementBag);
        for (ElementSpace mockedPlayableSpace : mockedPlayableSpaces) {
            EasyMock.replay(mockedPlayableSpace);
        }

        AddElementsEffect testedEffect = new AddElementsEffect();
        testedEffect.addElementToCard(blue, mockedCard, elementSpaceLogic, elementBag);

        EasyMock.verify(mockedCard, elementSpaceLogic, elementBag);
        for (ElementSpace mockedPlayableSpace : mockedPlayableSpaces) {
            EasyMock.verify(mockedPlayableSpace);
        }
    }

    private List<ElementSpace> getMockedPlayableSpaces(int numberOfSpaces) {
        List<ElementSpace> mockedPlayableSpaces = new ArrayList<>();
        for (int i = 0; i < numberOfSpaces; i++) {
            mockedPlayableSpaces.add(EasyMock.mock(ElementSpace.class));
        }
        return mockedPlayableSpaces;
    }

    @Test
    public void testToString() {
        AddElementsEffect testedEffect = new AddElementsEffect();
        assertEquals("Add elements to adjacent cards", testedEffect.toString());
    }

}
