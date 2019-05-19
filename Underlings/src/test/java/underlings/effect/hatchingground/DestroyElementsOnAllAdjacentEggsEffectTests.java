package underlings.effect.hatchingground;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Test;

import underlings.MockTest;
import underlings.card.Card;
import underlings.card.effect.wild.adjacenteggs.destroy.AllElementsEffect;
import underlings.card.effect.wild.adjacenteggs.destroy.ElementsEffect;
import underlings.card.effect.wild.adjacenteggs.destroy.OneElementEffect;
import underlings.element.ElementColor;
import underlings.element.ElementSpace;
import underlings.element.utilities.ElementSpaceLogic;
import underlings.utilities.EggHatchingLogic;
import underlings.utilities.LocaleWrap;

public class DestroyElementsOnAllAdjacentEggsEffectTests extends MockTest {

    @Test
    public void testApplyOneElementColor() {
        ElementSpaceLogic elementSpaceLogic = this.mock(ElementSpaceLogic.class);
        Card adjacentCard = this.mock(Card.class);
        ElementsEffect effect = EasyMock.partialMockBuilder(ElementsEffect.class)
                .addMockedMethod("destroyElementsOfColorOnCard").createMock();
        this.addMock(effect);
        effect.elementColors = new ElementColor[]{ElementColor.BLUE};
        EggHatchingLogic eggHatchingLogic = this.mock(EggHatchingLogic.class);

        effect.destroyElementsOfColorOnCard(ElementColor.BLUE, adjacentCard, elementSpaceLogic);

        this.replayAll();

        effect.applyOnAdjacentEgg(adjacentCard, null, elementSpaceLogic, eggHatchingLogic, null, null, null);
    }

    @Test
    public void testApplyTwoElementColor() {
        ElementSpaceLogic elementSpaceLogic = this.mock(ElementSpaceLogic.class);
        Card adjacentCard = this.mock(Card.class);
        ElementsEffect effect = EasyMock.partialMockBuilder(ElementsEffect.class)
                .addMockedMethod("destroyElementsOfColorOnCard").createMock();
        this.addMock(effect);
        effect.elementColors = new ElementColor[]{ElementColor.BLUE, ElementColor.RED};
        EggHatchingLogic eggHatchingLogic = this.mock(EggHatchingLogic.class);

        effect.destroyElementsOfColorOnCard(ElementColor.BLUE, adjacentCard, elementSpaceLogic);
        effect.destroyElementsOfColorOnCard(ElementColor.RED, adjacentCard, elementSpaceLogic);

        this.replayAll();

        effect.applyOnAdjacentEgg(adjacentCard, null, elementSpaceLogic, eggHatchingLogic, null, null, null);
    }

    @Test
    public void testDestroyElementOnCardNoDestroyableElements() {
        ElementColor blue = ElementColor.BLUE;
        Card mockedCard = this.mock(Card.class);
        ElementSpaceLogic elementSpaceLogic = this.mock(ElementSpaceLogic.class);

        ElementsEffect effect = EasyMock.partialMockBuilder(ElementsEffect.class)
                .addMockedMethod("destroyElementsOfColorOnSpace").createMock();
        this.addMock(effect);

        EasyMock.expect(elementSpaceLogic.getDestroyableSpaces(mockedCard, blue)).andReturn(Collections.emptyList());

        this.replayAll();

        effect.destroyElementsOfColorOnCard(blue, mockedCard, elementSpaceLogic);
    }

    @Test
    public void testDestroyElementOnCardOneDestroyableSpace() {
        ElementColor blue = ElementColor.BLUE;
        Card mockedCard = this.mock(Card.class);
        ElementSpaceLogic elementSpaceLogic = this.mock(ElementSpaceLogic.class);
        ElementSpace mockedDestroyableSpace = this.mock(ElementSpace.class);

        ElementsEffect effect = EasyMock.partialMockBuilder(ElementsEffect.class)
                .addMockedMethod("destroyElementsOfColorOnSpace").createMock();
        this.addMock(effect);

        EasyMock.expect(elementSpaceLogic.getDestroyableSpaces(mockedCard, blue))
                .andReturn(Arrays.asList(mockedDestroyableSpace));
        effect.destroyElementsOfColorOnSpace(mockedDestroyableSpace, blue);

        this.replayAll();

        effect.destroyElementsOfColorOnCard(blue, mockedCard, elementSpaceLogic);
    }

    @Test
    public void testDestroyElementOnCardEightDestroyableSpace() {
        ElementColor blue = ElementColor.BLUE;
        Card mockedCard = this.mock(Card.class);
        ElementSpaceLogic elementSpaceLogic = this.mock(ElementSpaceLogic.class);
        List<ElementSpace> mockedDestroyableSpaces = this.mockListOf(ElementSpace.class).withLengthOf(8);

        ElementsEffect effect = EasyMock.partialMockBuilder(ElementsEffect.class)
                .addMockedMethod("destroyElementsOfColorOnSpace").createMock();
        this.addMock(effect);

        EasyMock.expect(elementSpaceLogic.getDestroyableSpaces(mockedCard, blue)).andReturn(mockedDestroyableSpaces);
        for (ElementSpace destroyableSpace : mockedDestroyableSpaces) {
            effect.destroyElementsOfColorOnSpace(destroyableSpace, blue);
        }

        this.replayAll();

        effect.destroyElementsOfColorOnCard(blue, mockedCard, elementSpaceLogic);
    }

    @Test
    public void testDestroyAllElementsOnAllAdjacentEggsEffect() {
        ElementColor blue = ElementColor.BLUE;
        ElementSpace mockElementSpace = this.mock(ElementSpace.class);

        AllElementsEffect effect = new AllElementsEffect();

        mockElementSpace.destroyAllElementsOfColor(blue);

        this.replayAll();

        effect.destroyElementsOfColorOnSpace(mockElementSpace, blue);
    }

    @Test
    public void testDestroyOneElementOnAllAdjacentEggsEffect() {
        ElementColor blue = ElementColor.BLUE;
        ElementSpace mockElementSpace = this.mock(ElementSpace.class);

        OneElementEffect effect = new OneElementEffect();

        mockElementSpace.destroyOneElementOfColor(blue);

        this.replayAll();

        effect.destroyElementsOfColorOnSpace(mockElementSpace, blue);
    }

    @Test
    public void testToStringPlace() {
        AllElementsEffect effect = new AllElementsEffect();
        effect.elementColors = new ElementColor[]{ElementColor.BLACK};
        StringBuilder elements = new StringBuilder();
        for (ElementColor color : effect.elementColors) {
            elements.append(color);
            elements.append(" ");
        }
        assertEquals(LocaleWrap.format("destroy_all_elements_on_adjacent_eggs_effect", elements), effect.toString());
    }

}
