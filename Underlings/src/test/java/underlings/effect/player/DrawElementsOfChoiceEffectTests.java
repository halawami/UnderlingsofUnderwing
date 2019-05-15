package underlings.effect.player;

import java.util.Arrays;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Test;

import underlings.TestUtils;
import underlings.card.effect.domestic.DrawElementsOfChoiceEffect;
import underlings.element.ElementBag;
import underlings.element.ElementGiver;
import underlings.element.ElementGiverFactory;
import underlings.gui.DrawChoice;
import underlings.player.Player;

public class DrawElementsOfChoiceEffectTests {

    @Test
    public void testApply() {
        Player player = EasyMock.mock(Player.class);
        ElementBag elementBag = EasyMock.mock(ElementBag.class);
        DrawElementsOfChoiceEffect testedEffect = new DrawElementsOfChoiceEffect();

        player.addObserverEffect(testedEffect);

        EasyMock.replay(player, elementBag);

        testedEffect.on(player).on(elementBag).apply();

        Assert.assertEquals(elementBag, testedEffect.bag);
        EasyMock.verify(player, elementBag);
    }

    @Test
    public void testOnFirstPhaseOne() {
        Player player = EasyMock.mock(Player.class);
        ElementBag elementBag = EasyMock.mock(ElementBag.class);
        DrawElementsOfChoiceEffect testedEffect = EasyMock.partialMockBuilder(DrawElementsOfChoiceEffect.class)
                .addMockedMethod("getEffectElementGivers").createMock();
        testedEffect.bag = elementBag;
        List<ElementGiver> elementGivers = TestUtils.mockListOf(ElementGiver.class).withLength(2);
        List<ElementGiver> effectElementGivers = TestUtils.mockListOf(ElementGiver.class).withLength(2);

        EasyMock.expect(player.getElementGivers()).andReturn(elementGivers);
        EasyMock.expect(testedEffect.getEffectElementGivers(elementGivers, elementBag))
                .andReturn(effectElementGivers);
        player.useEffectElementGivers(true);

        EasyMock.replay(player, testedEffect, elementBag);
        elementGivers.forEach(EasyMock::replay);
        effectElementGivers.forEach(EasyMock::replay);

        testedEffect.onPhaseOne(player);

        Assert.assertEquals(effectElementGivers, player.effectElementGivers);
        EasyMock.verify(player, testedEffect, elementBag);
        elementGivers.forEach(EasyMock::verify);
        effectElementGivers.forEach(EasyMock::verify);
    }

    @Test
    public void testOnSecondPhaseOne() {
        Player player = EasyMock.mock(Player.class);
        ElementBag elementBag = EasyMock.mock(ElementBag.class);
        DrawElementsOfChoiceEffect testedEffect = EasyMock.partialMockBuilder(DrawElementsOfChoiceEffect.class)
                .addMockedMethod("getEffectElementGivers").createMock();
        testedEffect.bag = elementBag;
        List<ElementGiver> elementGivers = TestUtils.mockListOf(ElementGiver.class).withLength(2);
        List<ElementGiver> effectedElementGivers = TestUtils.mockListOf(ElementGiver.class).withLength(2);

        EasyMock.expect(player.getElementGivers()).andReturn(elementGivers).once();
        EasyMock.expect(testedEffect.getEffectElementGivers(elementGivers, elementBag))
                .andReturn(effectedElementGivers).once();
        player.useEffectElementGivers(true);
        EasyMock.expectLastCall().once();

        EasyMock.replay(player, testedEffect, elementBag);
        elementGivers.forEach(EasyMock::replay);
        effectedElementGivers.forEach(EasyMock::replay);

        testedEffect.onPhaseOne(player);
        testedEffect.onPhaseOne(player);

        EasyMock.verify(player, testedEffect, elementBag);
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
        ElementBag elementBag = EasyMock.mock(ElementBag.class);
        DrawElementsOfChoiceEffect testedEffect = new DrawElementsOfChoiceEffect();
        ElementGiverFactory elementGiverFactory = EasyMock.mock(ElementGiverFactory.class);
        testedEffect.elementGiverFactory = elementGiverFactory;
        testedEffect.bag = elementBag;
        List<ElementGiver> elementGivers = TestUtils.mockListOf(ElementGiver.class).withLength(numberOfElementGivers);
        List<DrawChoice> availableDrawChoices = Arrays.asList(DrawChoice.BLUE, DrawChoice.RED);
        List<ElementGiver> mockEffectGivers = TestUtils.mockListOf(ElementGiver.class)
                .withLength(numberOfElementGivers);

        EasyMock.expect(elementBag.getAvailableDrawChoices()).andReturn(availableDrawChoices);
        for (ElementGiver effectElementGiver : mockEffectGivers) {
            EasyMock.expect(elementGiverFactory.createElementGiver(availableDrawChoices)).andReturn(effectElementGiver);
        }

        EasyMock.replay(elementBag, elementGiverFactory);
        elementGivers.forEach(EasyMock::replay);
        mockEffectGivers.forEach(EasyMock::replay);

        List<ElementGiver> effectElementGivers = testedEffect.getEffectElementGivers(elementGivers, elementBag);

        Assert.assertEquals(mockEffectGivers, effectElementGivers);

        EasyMock.verify(elementBag, elementGiverFactory);
        elementGivers.forEach(EasyMock::verify);
        mockEffectGivers.forEach(EasyMock::verify);
    }

}
