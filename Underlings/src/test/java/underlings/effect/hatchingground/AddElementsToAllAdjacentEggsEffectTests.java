package underlings.effect.hatchingground;

import static org.junit.Assert.assertEquals;

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
        this.testApplyElementColors(ElementColor.BLUE);
    }


    @Test
    public void testApplyTwoDifferentElementColor() {
        this.testApplyElementColors(ElementColor.BLUE, ElementColor.RED);
    }

    @Test
    public void testApplyTwoSameElementColor() {
        this.testApplyElementColors(ElementColor.BLUE, ElementColor.BLUE);
    }

    private void testApplyElementColors(ElementColor... elementColors) {
        AddElementsEffect effect =
                EasyMock.partialMockBuilder(AddElementsEffect.class).addMockedMethod("addElementToCard").createMock();
        effect.elementColors = elementColors;

        for (ElementColor elementColor : elementColors) {
            effect.addElementToCard(elementColor, this.card, this.elementSpaceLogic, this.elementBag);
        }

        this.replayAll();

        effect.applyOnAdjacentEgg(this.card, this.elementBag, this.elementSpaceLogic, this.eggHatchingLogic, null,
                null, null);
    }

    @Test
    public void testApplyToWildCard() {
        Card adjacentCard = this.mock(Card.class);
        adjacentCard.handler = WildHandler.getInstance();
        AddElementsEffect effect =
                EasyMock.partialMockBuilder(AddElementsEffect.class).addMockedMethod("addElementToCard").createMock();
        this.addMock(effect);

        this.replayAll();

        effect.applyOnAdjacentEgg(adjacentCard, null, null, null, null, null, null);
    }

    @Test
    public void testAddElementToCardNoPlayableSpace() {
        ElementColor blue = ElementColor.BLUE;
        EasyMock.expect(this.elementSpaceLogic.getPlayableSpaces(this.card, blue)).andReturn(Collections.emptyList());

        this.replayAll();

        AddElementsEffect effect = new AddElementsEffect();
        effect.addElementToCard(blue, this.card, this.elementSpaceLogic, this.elementBag);
    }

    @Test
    public void testAddElementToCardOnePlayableSpace() {
        ElementColor blue = ElementColor.BLUE;
        Element stubElement = this.mock(Element.class);
        ElementSpace mockedPlayableSpace = this.mock(ElementSpace.class);

        EasyMock.expect(this.elementSpaceLogic.getPlayableSpaces(this.card, blue))
                .andReturn(Arrays.asList(mockedPlayableSpace));
        EasyMock.expect(this.elementBag.drawElementFromList(blue)).andReturn(stubElement);

        mockedPlayableSpace.addElements(stubElement);

        this.replayAll();

        AddElementsEffect effect = new AddElementsEffect();
        effect.addElementToCard(blue, this.card, this.elementSpaceLogic, this.elementBag);
    }

    @Test
    public void testAddElementToCardEightPlayableSpaces() {
        ElementColor blue = ElementColor.BLUE;
        Element stubElement = EasyMock.niceMock(Element.class);
        List<ElementSpace> mockedPlayableSpaces = this.mockListOf(ElementSpace.class).withLengthOf(8);

        EasyMock.expect(this.elementSpaceLogic.getPlayableSpaces(this.card, blue)).andReturn(mockedPlayableSpaces);
        EasyMock.expect(this.elementBag.drawElementFromList(blue)).andReturn(stubElement);
        mockedPlayableSpaces.get(0).addElements(stubElement);

        this.replayAll();

        AddElementsEffect effect = new AddElementsEffect();
        effect.addElementToCard(blue, this.card, this.elementSpaceLogic, this.elementBag);
    }

    @Test
    public void testAddElementWithNoElementsLeftInBag() {
        ElementColor blue = ElementColor.BLUE;
        List<ElementSpace> mockedPlayableSpaces = this.mockListOf(ElementSpace.class).withLengthOf(8);

        EasyMock.expect(this.elementSpaceLogic.getPlayableSpaces(this.card, blue)).andReturn(mockedPlayableSpaces);
        EasyMock.expect(this.elementBag.drawElementFromList(blue)).andReturn(NullElement.getInstance());

        this.replayAll();

        AddElementsEffect effect = new AddElementsEffect();
        effect.addElementToCard(blue, this.card, this.elementSpaceLogic, this.elementBag);
    }

    @Test
    public void testToString() {
        this.replayAll();
        AddElementsEffect effect = new AddElementsEffect();
        effect.elementColors = new ElementColor[]{ElementColor.BLACK};
        StringBuilder elements = new StringBuilder();
        for (ElementColor color : effect.elementColors) {
            elements.append(color);
            elements.append(" ");
        }
        assertEquals(LocaleWrap.format("place_element_on_all_eggs_effect", elements), effect.toString());
    }

}
