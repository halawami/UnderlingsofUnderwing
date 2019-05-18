package underlings.effect.player;

import java.util.Arrays;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import underlings.MockTest;
import underlings.card.effect.domestic.DrawElementsOfChoiceEffect;
import underlings.element.ElementBag;
import underlings.element.ElementGiver;
import underlings.element.ElementGiverFactory;
import underlings.gui.DrawChoice;
import underlings.player.Player;

public class DrawElementsOfChoiceEffectTests extends MockTest {

    private Player player;
    private ElementBag elementBag;
    private DrawElementsOfChoiceEffect testedEffect;

    @Before
    public void init() {
        this.player = EasyMock.mock(Player.class);
        this.elementBag = EasyMock.mock(ElementBag.class);
        this.testedEffect = new DrawElementsOfChoiceEffect();
    }

    @Test
    public void testApply() {
        this.player.addObserverEffect(this.testedEffect);

        EasyMock.replay(this.player, this.elementBag);

        this.testedEffect.on(this.player).on(this.elementBag).apply();

        Assert.assertEquals(this.elementBag, this.testedEffect.bag);
        EasyMock.verify(this.player, this.elementBag);
    }

    @Test
    public void testOnFirstPhaseOne() {
        this.testedEffect = EasyMock.partialMockBuilder(DrawElementsOfChoiceEffect.class)
                .addMockedMethod("getEffectElementGivers").createMock();
        this.testedEffect.bag = this.elementBag;
        List<ElementGiver> elementGivers = this.mockListOf(ElementGiver.class).withLengthOf(2);
        List<ElementGiver> effectElementGivers = this.mockListOf(ElementGiver.class).withLengthOf(2);

        EasyMock.expect(player.getElementGivers()).andReturn(elementGivers);
        EasyMock.expect(testedEffect.getEffectElementGivers(elementGivers, elementBag)).andReturn(effectElementGivers);
        this.player.useEffectElementGivers(true);

        EasyMock.replay(this.player, this.testedEffect, this.elementBag);
        elementGivers.forEach(EasyMock::replay);
        effectElementGivers.forEach(EasyMock::replay);

        this.testedEffect.onPhaseOne(this.player);

        Assert.assertEquals(effectElementGivers, this.player.effectElementGivers);
        EasyMock.verify(this.player, this.testedEffect, this.elementBag);
        elementGivers.forEach(EasyMock::verify);
        effectElementGivers.forEach(EasyMock::verify);
    }

    @Test
    public void testOnSecondPhaseOne() {
        this.testedEffect = EasyMock.partialMockBuilder(DrawElementsOfChoiceEffect.class)
                .addMockedMethod("getEffectElementGivers").createMock();
        this.testedEffect.bag = this.elementBag;
        List<ElementGiver> elementGivers = this.mockListOf(ElementGiver.class).withLengthOf(2);
        List<ElementGiver> effectedElementGivers = this.mockListOf(ElementGiver.class).withLengthOf(2);

        EasyMock.expect(player.getElementGivers()).andReturn(elementGivers).once();
        EasyMock.expect(testedEffect.getEffectElementGivers(elementGivers, elementBag)).andReturn(effectedElementGivers)
                .once();
        this.player.useEffectElementGivers(true);
        EasyMock.expectLastCall().once();

        EasyMock.replay(this.player, this.testedEffect, this.elementBag);
        elementGivers.forEach(EasyMock::replay);
        effectedElementGivers.forEach(EasyMock::replay);

        this.testedEffect.onPhaseOne(this.player);
        this.testedEffect.onPhaseOne(this.player);

        EasyMock.verify(this.player, this.testedEffect, this.elementBag);
        elementGivers.forEach(EasyMock::verify);
        effectedElementGivers.forEach(EasyMock::verify);
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

        ElementGiverFactory elementGiverFactory = EasyMock.mock(ElementGiverFactory.class);
        this.testedEffect.elementGiverFactory = elementGiverFactory;
        this.testedEffect.bag = this.elementBag;
        List<ElementGiver> elementGivers = this.mockListOf(ElementGiver.class).withLengthOf(numberOfElementGivers);
        List<DrawChoice> availableDrawChoices = Arrays.asList(DrawChoice.BLUE, DrawChoice.RED);
        List<ElementGiver> mockEffectGivers =
                this.mockListOf(ElementGiver.class).withLengthOf(numberOfElementGivers);

        EasyMock.expect(this.elementBag.getAvailableDrawChoices()).andReturn(availableDrawChoices);
        for (ElementGiver effectElementGiver : mockEffectGivers) {
            EasyMock.expect(elementGiverFactory.createElementGiver(availableDrawChoices)).andReturn(effectElementGiver);
        }

        EasyMock.replay(this.elementBag, elementGiverFactory);
        elementGivers.forEach(EasyMock::replay);
        mockEffectGivers.forEach(EasyMock::replay);

        List<ElementGiver> effectElementGivers =
                this.testedEffect.getEffectElementGivers(elementGivers, this.elementBag);

        Assert.assertEquals(mockEffectGivers, effectElementGivers);

        EasyMock.verify(this.elementBag, elementGiverFactory);
        elementGivers.forEach(EasyMock::verify);
        mockEffectGivers.forEach(EasyMock::verify);
    }

}
