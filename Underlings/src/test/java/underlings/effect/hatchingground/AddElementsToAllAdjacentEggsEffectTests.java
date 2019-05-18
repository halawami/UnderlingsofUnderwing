package underlings.effect.hatchingground;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import underlings.MockTest;
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
import underlings.handler.WildHandler;
import underlings.utilities.EggHatchingLogic;
import underlings.utilities.LocaleWrap;

public class AddElementsToAllAdjacentEggsEffectTests extends MockTest {

    @Before
    public void init() {
        this.elementBag = this.mock(ElementBag.class);
        this.elementSpaceLogic = this.mock(ElementSpaceLogic.class);
        this.card = this.mock(Card.class);
        this.eggHatchingLogic = this.mock(EggHatchingLogic.class);
        this.gui = this.mock(Gui.class);
        this.hatchingGround = this.mock(HatchingGround.class);
    }

    @Test
    public void testApplyOneElementColor() {
        AddElementsEffect testedEffect =
                EasyMock.partialMockBuilder(AddElementsEffect.class).addMockedMethod("addElementToCard").createMock();
        testedEffect.elementColors = new ElementColor[] {ElementColor.BLUE};
        testedEffect.addElementToCard(ElementColor.BLUE, card, elementSpaceLogic, elementBag);

        this.replayAll();

        testedEffect.applyOnAdjacentEgg(card, elementBag, elementSpaceLogic, eggHatchingLogic, null, null, null);
    }

    @Test
    public void testApplyToWildCard() {
        card.handler = WildHandler.getInstance();
        AddElementsEffect testedEffect =
                EasyMock.partialMockBuilder(AddElementsEffect.class).addMockedMethod("addElementToCard").createMock();
        testedEffect.elementColors = new ElementColor[] {ElementColor.BLUE};

        this.replayAll();

        testedEffect.applyOnAdjacentEgg(card, elementBag, elementSpaceLogic, eggHatchingLogic, null, null, null);
    }

    @Test
    public void testApplyTwoDifferentElementColor() {
        AddElementsEffect testedEffect =
                EasyMock.partialMockBuilder(AddElementsEffect.class).addMockedMethod("addElementToCard").createMock();
        testedEffect.elementColors = new ElementColor[] {ElementColor.BLUE, ElementColor.RED};

        testedEffect.addElementToCard(ElementColor.BLUE, card, elementSpaceLogic, elementBag);
        testedEffect.addElementToCard(ElementColor.RED, card, elementSpaceLogic, elementBag);

        this.replayAll();

        testedEffect.applyOnAdjacentEgg(card, elementBag, elementSpaceLogic, eggHatchingLogic, null, null, null);
    }

    @Test
    public void testApplyTwoSameElementColor() {
        AddElementsEffect testedEffect =
                EasyMock.partialMockBuilder(AddElementsEffect.class).addMockedMethod("addElementToCard").createMock();
        testedEffect.elementColors = new ElementColor[] {ElementColor.BLUE, ElementColor.BLUE};

        testedEffect.addElementToCard(ElementColor.BLUE, card, elementSpaceLogic, elementBag);
        testedEffect.addElementToCard(ElementColor.BLUE, card, elementSpaceLogic, elementBag);

        this.replayAll();

        testedEffect.applyOnAdjacentEgg(card, elementBag, elementSpaceLogic, eggHatchingLogic, null, null, null);
    }

    @Test
    public void testAddElementToCardNoPlayableSpace() {
        ElementColor blue = ElementColor.BLUE;
        EasyMock.expect(elementSpaceLogic.getPlayableSpaces(card, blue)).andReturn(Collections.emptyList());

        this.replayAll();

        AddElementsEffect testedEffect = new AddElementsEffect();
        testedEffect.addElementToCard(blue, card, elementSpaceLogic, elementBag);
    }

    @Test
    public void testAddElementToCardOnePlayableSpace() {
        ElementColor blue = ElementColor.BLUE;
        Element stubElement = this.mock(Element.class);
        ElementSpace mockedPlayableSpace = this.mock(ElementSpace.class);

        EasyMock.expect(elementSpaceLogic.getPlayableSpaces(card, blue)).andReturn(Arrays.asList(mockedPlayableSpace));
        EasyMock.expect(elementBag.drawElementFromList(blue)).andReturn(stubElement);

        mockedPlayableSpace.addElements(stubElement);

        this.replayAll();

        AddElementsEffect testedEffect = new AddElementsEffect();
        testedEffect.addElementToCard(blue, card, elementSpaceLogic, elementBag);
    }

    @Test
    public void testAddElementToCardEightPlayableSpaces() {
        ElementColor blue = ElementColor.BLUE;
        Element stubElement = EasyMock.niceMock(Element.class);
        List<ElementSpace> mockedPlayableSpaces = getMockedPlayableSpaces(8);

        EasyMock.expect(elementSpaceLogic.getPlayableSpaces(card, blue)).andReturn(mockedPlayableSpaces);
        EasyMock.expect(elementBag.drawElementFromList(blue)).andReturn(stubElement);
        mockedPlayableSpaces.get(0).addElements(stubElement);

        this.replayAll();

        AddElementsEffect testedEffect = new AddElementsEffect();
        testedEffect.addElementToCard(blue, card, elementSpaceLogic, elementBag);
    }

    @Test
    public void testAddElementWithNoElementsLeftInBag() {
        ElementColor blue = ElementColor.BLUE;
        List<ElementSpace> mockedPlayableSpaces = getMockedPlayableSpaces(8);

        EasyMock.expect(elementSpaceLogic.getPlayableSpaces(card, blue)).andReturn(mockedPlayableSpaces);
        EasyMock.expect(elementBag.drawElementFromList(blue)).andReturn(NullElement.getInstance());

        this.replayAll();

        AddElementsEffect testedEffect = new AddElementsEffect();
        testedEffect.addElementToCard(blue, card, elementSpaceLogic, elementBag);
    }

    private List<ElementSpace> getMockedPlayableSpaces(int numberOfSpaces) {
        List<ElementSpace> mockedPlayableSpaces = new ArrayList<>();
        for (int i = 0; i < numberOfSpaces; i++) {
            mockedPlayableSpaces.add(this.mock(ElementSpace.class));
        }
        return mockedPlayableSpaces;
    }

    @Test
    public void testToString() {
        this.replayAll();
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
