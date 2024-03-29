package underlings.card.effect.domestic.element;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import underlings.MockTest;
import underlings.card.effect.Effect;
import underlings.element.ElementBag;
import underlings.element.ElementGiver;
import underlings.element.ElementGiverFactory;
import underlings.gui.DrawChoice;
import underlings.player.Player;
import underlings.utilities.LocaleUtilities;

public class DrawElementsOfChoiceNextPhaseEffectTests extends MockTest {

    private DrawElementsOfChoiceNextPhaseEffect effect;

    @Before
    public void init() {
        this.player = this.mock(Player.class);
        this.elementBag = this.mock(ElementBag.class);
    }

    @Test
    public void testApply() {
        this.effect = new DrawElementsOfChoiceNextPhaseEffect();
        this.player.addObserverEffect(this.effect);

        this.replayAll();

        this.effect.on(this.player).on(this.elementBag).apply();

        Assert.assertEquals(this.elementBag, this.effect.bag);
    }

    @Test
    public void testOnFirstPhaseOne() {
        this.effect = EasyMock.partialMockBuilder(DrawElementsOfChoiceNextPhaseEffect.class)
                .addMockedMethod("getEffectElementGivers").createMock();
        this.addMock(this.effect);
        this.effect.bag = this.elementBag;
        List<ElementGiver> elementGivers = this.mockListOf(ElementGiver.class).withLengthOf(2);
        List<ElementGiver> effectElementGivers = this.mockListOf(ElementGiver.class).withLengthOf(2);

        EasyMock.expect(this.player.getElementGivers()).andReturn(elementGivers);
        EasyMock.expect(this.effect.getEffectElementGivers(elementGivers, this.elementBag))
                .andReturn(effectElementGivers);
        this.player.useEffectElementGivers(true);

        this.replayAll();

        this.effect.onPhaseOne(this.player);

        assertEquals(effectElementGivers, this.player.effectElementGivers);
        assertTrue(this.effect.beenUsed);
    }

    @Test
    public void testOnSecondPhaseOne() {
        this.effect = EasyMock.partialMockBuilder(DrawElementsOfChoiceNextPhaseEffect.class)
                .addMockedMethod("getEffectElementGivers").createMock();
        this.effect.beenUsed = true;

        this.replayAll();

        this.effect.onPhaseOne(this.player);
    }


    @Test
    public void testGetEffectElementGiversOneElementGiver() {
        this.testGetEffectElementGivers(1);
    }

    @Test
    public void testGetEffectElementGiversTwoElementGiver() {
        this.testGetEffectElementGivers(2);
    }

    @Test
    public void testGetEffectElementGiversThreeElementGiver() {
        this.testGetEffectElementGivers(3);
    }

    private void testGetEffectElementGivers(int numberOfElementGivers) {
        this.effect = new DrawElementsOfChoiceNextPhaseEffect();
        ElementGiverFactory elementGiverFactory = this.mock(ElementGiverFactory.class);
        this.effect.elementGiverFactory = elementGiverFactory;
        this.effect.bag = this.elementBag;
        List<DrawChoice> availableDrawChoices = Arrays.asList(DrawChoice.BLUE, DrawChoice.RED);
        final List<ElementGiver> elementGivers =
                this.mockListOf(ElementGiver.class).withLengthOf(numberOfElementGivers);
        final List<ElementGiver> effectElementGivers =
                this.mockListOf(ElementGiver.class).withLengthOf(numberOfElementGivers);

        EasyMock.expect(this.elementBag.getAvailableDrawChoices()).andReturn(availableDrawChoices);
        for (ElementGiver effectElementGiver : effectElementGivers) {
            EasyMock.expect(elementGiverFactory.createElementGiver(availableDrawChoices)).andReturn(effectElementGiver);
        }

        this.replayAll();

        List<ElementGiver> actualEffectGivers = this.effect.getEffectElementGivers(elementGivers, this.elementBag);

        Assert.assertEquals(effectElementGivers, actualEffectGivers);
    }

    @Test
    public void testToString() {
        this.replayAll();
        Effect effect = new DrawElementsOfChoiceNextPhaseEffect();
        assertEquals(LocaleUtilities.get("draw_elements_of_choice"), effect.toString());
    }

}
