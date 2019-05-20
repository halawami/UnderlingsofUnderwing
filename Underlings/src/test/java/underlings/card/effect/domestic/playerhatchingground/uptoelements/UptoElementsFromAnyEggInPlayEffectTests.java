package underlings.card.effect.domestic.playerhatchingground.uptoelements;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.easymock.EasyMock;
import org.junit.Test;

import underlings.MockTest;
import underlings.card.Card;
import underlings.element.Element;
import underlings.element.ElementColor;
import underlings.element.ElementSpace;
import underlings.element.NullElement;
import underlings.gui.Gui;
import underlings.hatchingground.HatchingGround;
import underlings.player.Player;
import underlings.utilities.LocaleUtilities;

public class UptoElementsFromAnyEggInPlayEffectTests extends MockTest {

    @Test
    public void testApplyOnOneSelectedElements() {
        this.testApplyOnNumberOfSelectedElements(1);
    }

    @Test
    public void testApplyOnTwoSelectedElements() {
        this.testApplyOnNumberOfSelectedElements(2);
    }

    @Test
    public void testApplyOnThreeSelectedElements() {
        this.testApplyOnNumberOfSelectedElements(3);
    }

    private void testApplyOnNumberOfSelectedElements(int numberOfSelectedElements) {
        Player currentPlayer = this.mock(Player.class);
        EasyMock.expect(currentPlayer.getId()).andReturn(10).anyTimes();
        final HatchingGround hatchingGround = this.mock(HatchingGround.class);
        List<ElementSpace> mockSpaces = this.mockListOf(ElementSpace.class).withLengthOf(numberOfSelectedElements);
        List<Element> mockElements = this.mockListOf(Element.class).withLengthOf(numberOfSelectedElements);
        Gui gui = this.mock(Gui.class);
        UptoElementsFromAnyEggInPlayEffect effect =
                EasyMock.partialMockBuilder(UptoElementsFromAnyEggInPlayEffect.class)
                        .addMockedMethod("applyOnSelectedElement").createMock();
        this.addMock(effect);
        effect.elementChoices = new ElementColor[] {ElementColor.BLUE};
        effect.upTo = numberOfSelectedElements;

        List<Card> mockCards = this.mockListOf(Card.class).withLengthOf(6);
        EasyMock.expect(hatchingGround.getAllCards()).andReturn(mockCards);
        for (int i = 0; i < numberOfSelectedElements; i++) {
            EasyMock.expect(gui.getElementSpaceWithColors(mockCards, effect.elementChoices, 10))
                    .andReturn(mockSpaces.get(i));
            EasyMock.expect(gui.getElementOfColorsFromSpace(effect.elementChoices, mockSpaces.get(i), 10))
                    .andReturn(mockElements.get(i));
            effect.applyOnSelectedElement(mockElements.get(i), mockSpaces.get(i), currentPlayer);
        }

        this.replayAll();

        effect.on(gui).on(currentPlayer).on(hatchingGround).apply();
    }

    @Test
    public void testDestroyNullElementPicked() {
        this.testDestroyElement(NullElement.getInstance(), NullElement.getInstance().getColor());
    }

    @Test
    public void testDestroyNormalElementPicked() {
        this.testDestroyElement(this.mock(Element.class), ElementColor.BLUE);
    }

    private void testDestroyElement(Element elementPicked, ElementColor elementColor) {
        final Player currentPlayer = this.mock(Player.class);
        ElementSpace elementSpace = this.mock(ElementSpace.class);

        if (elementPicked != NullElement.getInstance()) {
            EasyMock.expect(elementPicked.getColor()).andReturn(elementColor);
        }
        elementSpace.destroyOneElementOfColor(elementColor);

        this.replayAll();

        DestroyUpToElementsOnAnyEggInPlayEffect effect = new DestroyUpToElementsOnAnyEggInPlayEffect();
        effect.applyOnSelectedElement(elementPicked, elementSpace, currentPlayer);
    }

    @Test
    public void testCollectNullElementPicked() {
        this.testCollectElement(NullElement.getInstance(), NullElement.getInstance().getColor());
    }


    @Test
    public void testCollectNormalElementPicked() {
        this.testCollectElement(this.mock(Element.class), ElementColor.BLUE);
    }

    @Test
    public void testCollectNonExistingElement() {
        Player currentPlayer = this.mock(Player.class);
        EasyMock.expect(currentPlayer.getId()).andReturn(10).anyTimes();
        final HatchingGround hatchingGround = this.mock(HatchingGround.class);
        final Gui gui = this.mock(Gui.class);
        UptoElementsFromAnyEggInPlayEffect effect =
                EasyMock.partialMockBuilder(UptoElementsFromAnyEggInPlayEffect.class)
                        .addMockedMethod("applyOnSelectedElement").createMock();
        this.addMock(effect);
        effect.elementChoices = new ElementColor[] {ElementColor.BLUE};
        effect.upTo = 1;

        List<Card> mockCards = this.mockListOf(Card.class).withLengthOf(6);
        EasyMock.expect(hatchingGround.getAllCards()).andReturn(mockCards);
        EasyMock.expect(gui.getElementSpaceWithColors(mockCards, effect.elementChoices, 10)).andReturn(null);
        this.replayAll();

        effect.on(gui).on(currentPlayer).on(hatchingGround).apply();
    }

    private void testCollectElement(Element elementPicked, ElementColor elementColor) {
        Player currentPlayer = this.mock(Player.class);
        ElementSpace elementSpace = this.mock(ElementSpace.class);

        currentPlayer.addElement(elementPicked);
        if (elementPicked != NullElement.getInstance()) {
            EasyMock.expect(elementPicked.getColor()).andReturn(elementColor);
        }
        elementSpace.destroyOneElementOfColor(elementColor);

        this.replayAll();

        CollectUpToElementsFromAnyEggInPlayEffect effect = new CollectUpToElementsFromAnyEggInPlayEffect();
        effect.applyOnSelectedElement(elementPicked, elementSpace, currentPlayer);
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
        assertEquals(LocaleUtilities.format("collect_up_to_effect", effect.upTo, elements), effect.toString());
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
        assertEquals(LocaleUtilities.format("destroy_up_to_effect", effect.upTo, elements), effect.toString());
    }
}
