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
import underlings.element.NullElement;
import underlings.element.utilities.ElementSpaceLogic;
import underlings.game.HatchingGround;
import underlings.gui.Gui;
import underlings.player.FakePlayer;
import underlings.utilities.EggHatchingLogic;
import underlings.utilities.LocaleWrap;

public class AddElementsToAllAdjacentEggsEffectTests {

    @Test
    public void testApplyOneElementColor() {
        ElementBag elementBag = EasyMock.mock(ElementBag.class);
        ElementSpaceLogic elementSpaceLogic = EasyMock.mock(ElementSpaceLogic.class);
        Card adjacentCard = EasyMock.mock(Card.class);
        AddElementsEffect testedEffect =
                EasyMock.partialMockBuilder(AddElementsEffect.class).addMockedMethod("addElementToCard").createMock();
        testedEffect.elementColors = new ElementColor[] {ElementColor.BLUE};
        EggHatchingLogic eggHatchingLogic = EasyMock.mock(EggHatchingLogic.class);

        testedEffect.addElementToCard(ElementColor.BLUE, adjacentCard, elementSpaceLogic, elementBag);
        Gui gui = EasyMock.mock(Gui.class);
        HatchingGround hatchingGround = EasyMock.mock(HatchingGround.class);
        EasyMock.replay(elementBag, adjacentCard, elementSpaceLogic, testedEffect, gui, hatchingGround);
        EasyMock.replay(eggHatchingLogic);

        testedEffect.applyOnAdjacentEgg(adjacentCard, elementBag, elementSpaceLogic, eggHatchingLogic, null, null,
                null);

        EasyMock.verify(elementBag, adjacentCard, elementSpaceLogic, testedEffect, gui, hatchingGround);
        EasyMock.verify(eggHatchingLogic);
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
        EggHatchingLogic eggHatchingLogic = EasyMock.mock(EggHatchingLogic.class);
        EasyMock.replay(eggHatchingLogic);

        testedEffect.applyOnAdjacentEgg(adjacentCard, elementBag, elementSpaceLogic, eggHatchingLogic, null, null,
                null);

        EasyMock.verify(elementBag, adjacentCard, elementSpaceLogic, testedEffect, gui, hatchingGround);
        EasyMock.verify(eggHatchingLogic);
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
        EggHatchingLogic eggHatchingLogic = EasyMock.mock(EggHatchingLogic.class);
        EasyMock.replay(eggHatchingLogic);

        testedEffect.applyOnAdjacentEgg(adjacentCard, elementBag, elementSpaceLogic, eggHatchingLogic, null, null,
                null);

        EasyMock.verify(elementBag, adjacentCard, elementSpaceLogic, testedEffect, gui, hatchingGround);
        EasyMock.verify(eggHatchingLogic);
    }

    @Test
    public void testAddElementToCardNoPlayableSpace() {
        ElementSpaceLogic elementSpaceLogic = EasyMock.mock(ElementSpaceLogic.class);
        Card mockedCard = EasyMock.mock(Card.class);
        ElementColor blue = ElementColor.BLUE;
        EasyMock.expect(elementSpaceLogic.getPlayableSpaces(mockedCard, blue)).andReturn(Collections.emptyList());

        ElementBag elementBag = EasyMock.mock(ElementBag.class);
        EasyMock.replay(mockedCard, elementSpaceLogic, elementBag);

        AddElementsEffect testedEffect = new AddElementsEffect();
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

    @Test
    public void testAddElementWithNoElementsLeftInBag() {
        ElementColor blue = ElementColor.BLUE;
        Card mockedCard = EasyMock.mock(Card.class);
        ElementSpaceLogic elementSpaceLogic = EasyMock.mock(ElementSpaceLogic.class);
        ElementBag elementBag = EasyMock.mock(ElementBag.class);
        List<ElementSpace> mockedPlayableSpaces = getMockedPlayableSpaces(8);

        EasyMock.expect(elementSpaceLogic.getPlayableSpaces(mockedCard, blue)).andReturn(mockedPlayableSpaces);
        EasyMock.expect(elementBag.drawElementFromList(blue)).andReturn(NullElement.getInstance());

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

    @Test
    public void testCheckOtherHatchedCards() {
        ElementColor blue = ElementColor.BLUE;
        ElementColor black = ElementColor.BLACK;
        Card mockedCard = EasyMock.mock(Card.class);
        Card adjacentCard = EasyMock.mock(Card.class);
        adjacentCard.elementSpaces =
                new ElementSpace[] {new ElementSpace(ElementColor.BLUE), new ElementSpace(ElementColor.BLACK)};
        adjacentCard.elementSpaces[1].elements = Arrays.asList(new Element(black));
        ElementSpaceLogic elementSpaceLogic = EasyMock.mock(ElementSpaceLogic.class);
        ElementBag elementBag = EasyMock.mock(ElementBag.class);
        Element stubElement = EasyMock.niceMock(Element.class);
        ElementSpace mockedPlayableSpace = EasyMock.mock(ElementSpace.class);

        EasyMock.expect(elementSpaceLogic.getPlayableSpaces(mockedCard, blue))
                .andReturn(Arrays.asList(mockedPlayableSpace));
        EasyMock.expect(elementBag.drawElementFromList(blue)).andReturn(stubElement);
        mockedPlayableSpace.addElements(stubElement);
        EggHatchingLogic eggHatchingLogic = EasyMock.mock(EggHatchingLogic.class);
        eggHatchingLogic.hatchEgg(adjacentCard, true, FakePlayer.getInstance());

        EasyMock.replay(mockedCard, adjacentCard, elementSpaceLogic, elementBag, mockedPlayableSpace, eggHatchingLogic);

        AddElementsEffect testedEffect = new AddElementsEffect();

        testedEffect.addElementToCard(blue, mockedCard, elementSpaceLogic, elementBag);

        EasyMock.verify(mockedCard, adjacentCard, elementSpaceLogic, elementBag, mockedPlayableSpace, eggHatchingLogic);
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
        AddElementsEffect effect = new AddElementsEffect();
        effect.elementColors = new ElementColor[] {ElementColor.BLACK};
        StringBuilder elements = new StringBuilder();
        for (ElementColor color : effect.elementColors) {
            elements.append(color);
            elements.append(" ");
        }
        assertEquals(LocaleWrap.format("place_element_on_all_eggs_effect", elements), effect.toString());
    }

}
