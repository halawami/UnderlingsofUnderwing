package underlings.effect.hatchingground;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Test;

import underlings.card.Card;
import underlings.card.effect.domestic.CollectUpToElementsFromAnyEggInPlayEffect;
import underlings.card.effect.domestic.DestroyUpToElementsOnAnyEggInPlayEffect;
import underlings.card.effect.domestic.UptoElementsFromAnyEggInPlayEffect;
import underlings.element.Element;
import underlings.element.ElementColor;
import underlings.element.ElementSpace;
import underlings.element.NullElement;
import underlings.game.HatchingGround;
import underlings.gui.Gui;
import underlings.player.Player;
import underlings.utilities.LocaleWrap;

public class UptoElementsFromAnyEggInPlayEffectTests {

    @Test
    public void testApplyOnTwoSelectedElements() {
        testApplyOnNumberOfSelectedElements(2);
    }

    @Test
    public void testApplyOnThreeSelectedElements() {
        testApplyOnNumberOfSelectedElements(3);
    }

    public void testApplyOnNumberOfSelectedElements(int numberOfSlectedElements) {
        Player currentPlayer = EasyMock.mock(Player.class);
        EasyMock.expect(currentPlayer.getId()).andReturn(10).anyTimes();
        HatchingGround hatchingGround = EasyMock.mock(HatchingGround.class);
        List<ElementSpace> mockSpaces = this.getMockedObjects(ElementSpace.class, numberOfSlectedElements);
        List<Element> mockElements = this.getMockedObjects(Element.class, numberOfSlectedElements);
        List<Card> mockCards = this.getMockedObjects(Card.class, 6);
        Gui gui = EasyMock.mock(Gui.class);
        UptoElementsFromAnyEggInPlayEffect testedEffect =
                EasyMock.partialMockBuilder(UptoElementsFromAnyEggInPlayEffect.class)
                        .addMockedMethod("applyOnSelectedElement").createMock();
        testedEffect.elementChoices = new ElementColor[] {ElementColor.BLUE};
        testedEffect.upTo = numberOfSlectedElements;
        testedEffect.on(gui).on(currentPlayer).on(hatchingGround);

        EasyMock.expect(hatchingGround.getAllCards()).andReturn(mockCards);

        for (int i = 0; i < numberOfSlectedElements; i++) {
            EasyMock.expect(gui.getElementSpaceWithColors(mockCards, testedEffect.elementChoices, 10))
                    .andReturn(mockSpaces.get(i));
            EasyMock.expect(gui.getElementOfColorsFromSpace(testedEffect.elementChoices, mockSpaces.get(i), 10))
                    .andReturn(mockElements.get(i));
            testedEffect.applyOnSelectedElement(mockElements.get(i), mockSpaces.get(i), currentPlayer);
        }

        EasyMock.replay(currentPlayer, hatchingGround, gui, testedEffect);
        mockSpaces.forEach(EasyMock::replay);
        mockElements.forEach(EasyMock::replay);

        testedEffect.apply();

        EasyMock.verify(currentPlayer, hatchingGround, gui, testedEffect);
        mockSpaces.forEach(EasyMock::verify);
        mockElements.forEach(EasyMock::verify);
    }

    @Test
    public void testDestroyNullElementPicked() {
        Player currentPlayer = EasyMock.mock(Player.class);
        ElementSpace elementSpace = EasyMock.mock(ElementSpace.class);
        Element element = NullElement.getInstance();
        DestroyUpToElementsOnAnyEggInPlayEffect testedEffect = new DestroyUpToElementsOnAnyEggInPlayEffect();

        elementSpace.destroyOneElementOfColor(element.getColor());

        EasyMock.replay(currentPlayer, elementSpace);

        testedEffect.applyOnSelectedElement(element, elementSpace, currentPlayer);

        EasyMock.verify(currentPlayer, elementSpace);
    }

    @Test
    public void testDestroyNormalElementPicked() {
        Player currentPlayer = EasyMock.mock(Player.class);
        ElementSpace elementSpace = EasyMock.mock(ElementSpace.class);
        Element element = EasyMock.mock(Element.class);

        EasyMock.expect(element.getColor()).andReturn(ElementColor.BLUE);
        elementSpace.destroyOneElementOfColor(ElementColor.BLUE);

        EasyMock.replay(currentPlayer, elementSpace, element);

        DestroyUpToElementsOnAnyEggInPlayEffect testedEffect = new DestroyUpToElementsOnAnyEggInPlayEffect();
        testedEffect.applyOnSelectedElement(element, elementSpace, currentPlayer);

        EasyMock.verify(currentPlayer, elementSpace, element);
    }

    @Test
    public void testCollectNullElementPicked() {
        Player currentPlayer = EasyMock.mock(Player.class);
        ElementSpace elementSpace = EasyMock.mock(ElementSpace.class);
        Element element = NullElement.getInstance();

        currentPlayer.addElement(element);
        elementSpace.destroyOneElementOfColor(element.getColor());

        EasyMock.replay(currentPlayer, elementSpace);

        CollectUpToElementsFromAnyEggInPlayEffect testedEffect = new CollectUpToElementsFromAnyEggInPlayEffect();
        testedEffect.applyOnSelectedElement(element, elementSpace, currentPlayer);

        EasyMock.verify(currentPlayer, elementSpace);
    }


    @Test
    public void testCollectNormalElementPicked() {
        Player currentPlayer = EasyMock.mock(Player.class);
        ElementSpace elementSpace = EasyMock.mock(ElementSpace.class);
        Element element = EasyMock.mock(Element.class);

        currentPlayer.addElement(element);
        EasyMock.expect(element.getColor()).andReturn(ElementColor.BLUE);
        elementSpace.destroyOneElementOfColor(ElementColor.BLUE);

        EasyMock.replay(currentPlayer, elementSpace, element);

        CollectUpToElementsFromAnyEggInPlayEffect testedEffect = new CollectUpToElementsFromAnyEggInPlayEffect();
        testedEffect.applyOnSelectedElement(element, elementSpace, currentPlayer);

        EasyMock.verify(currentPlayer, elementSpace, element);
    }

    private <T> List<T> getMockedObjects(Class<T> objectsClass, int numberOfObjects) {
        List<T> mockedObjects = new ArrayList<>();
        for (int i = 0; i < numberOfObjects; i++) {
            mockedObjects.add(EasyMock.mock(objectsClass));
        }
        return mockedObjects;
    }

    @Test
    public void testToStringCollect() {
        CollectUpToElementsFromAnyEggInPlayEffect effect = new CollectUpToElementsFromAnyEggInPlayEffect();
        effect.elementChoices = new ElementColor[] {ElementColor.BLACK};
        StringBuilder elements = new StringBuilder();
        for (ElementColor color : effect.elementChoices) {
            elements.append(color);
            elements.append(" ");
        }
        assertEquals(LocaleWrap.format("up_to_effect", "Collect", effect.upTo, elements), effect.toString());
    }

    @Test
    public void testToStringDestroy() {
        DestroyUpToElementsOnAnyEggInPlayEffect effect = new DestroyUpToElementsOnAnyEggInPlayEffect();
        effect.elementChoices = new ElementColor[] {ElementColor.BLACK};
        StringBuilder elements = new StringBuilder();
        for (ElementColor color : effect.elementChoices) {
            elements.append(color);
            elements.append(" ");
        }
        assertEquals(LocaleWrap.format("up_to_effect", "Destroy", effect.upTo, elements), effect.toString());
    }
}
