package underlings.effect.player;

import java.util.List;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Test;

import underlings.TestUtils;
import underlings.card.effect.domestic.DrawElementsOfChoiceEffect;
import underlings.element.ElementBag;
import underlings.element.ElementGiver;
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

        Assert.assertEquals(elementBag, testedEffect.elementBag);
        EasyMock.verify(player, elementBag);
    }

    @Test
    public void testOnFirstPhaseOne() {
        Player player = EasyMock.mock(Player.class);
        DrawElementsOfChoiceEffect testedEffect = EasyMock.partialMockBuilder(DrawElementsOfChoiceEffect.class)
                .addMockedMethod("getEffectedElementGivers").createMock();
        List<ElementGiver> elementGivers = TestUtils.mockListOf(ElementGiver.class).withLength(2);
        List<ElementGiver> effectedElementGivers = TestUtils.mockListOf(ElementGiver.class).withLength(2);

        EasyMock.expect(player.getElementGivers()).andReturn(elementGivers);
        EasyMock.expect(testedEffect.getEffectedElementGivers(elementGivers)).andReturn(effectedElementGivers);
        player.useEffectedElementGivers(true);

        EasyMock.replay(player, testedEffect);
        elementGivers.forEach(EasyMock::replay);
        effectedElementGivers.forEach(EasyMock::replay);

        testedEffect.onPhaseOne(player);

        Assert.assertEquals(effectedElementGivers, player.effectedElementGivers);
        EasyMock.verify(player, testedEffect);
        elementGivers.forEach(EasyMock::verify);
        effectedElementGivers.forEach(EasyMock::verify);
    }

    @Test
    public void testOnSecondPhaseOne() {
        Player player = EasyMock.mock(Player.class);
        DrawElementsOfChoiceEffect testedEffect = EasyMock.partialMockBuilder(DrawElementsOfChoiceEffect.class)
                .addMockedMethod("getEffectedElementGivers").createMock();
        List<ElementGiver> elementGivers = TestUtils.mockListOf(ElementGiver.class).withLength(2);
        List<ElementGiver> effectedElementGivers = TestUtils.mockListOf(ElementGiver.class).withLength(2);

        EasyMock.expect(player.getElementGivers()).andReturn(elementGivers).once();
        EasyMock.expect(testedEffect.getEffectedElementGivers(elementGivers)).andReturn(effectedElementGivers).once();
        player.useEffectedElementGivers(true);
        EasyMock.expectLastCall().once();

        EasyMock.replay(player, testedEffect);
        elementGivers.forEach(EasyMock::replay);
        effectedElementGivers.forEach(EasyMock::replay);

        testedEffect.onPhaseOne(player);
        testedEffect.onPhaseOne(player);

        EasyMock.verify(player, testedEffect);
        elementGivers.forEach(EasyMock::verify);
        effectedElementGivers.forEach(EasyMock::verify);
    }


}
