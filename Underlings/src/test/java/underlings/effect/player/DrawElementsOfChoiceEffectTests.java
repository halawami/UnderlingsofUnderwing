package underlings.effect.player;

import java.util.List;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Test;

import underlings.TestUtils;
import underlings.card.effect.domestic.DrawElementsOfChoiceEffect;
import underlings.element.ElementGiver;
import underlings.player.Player;

public class DrawElementsOfChoiceEffectTests {

    @Test
    public void testApply() {
        Player player = EasyMock.mock(Player.class);
        DrawElementsOfChoiceEffect testedEffect = new DrawElementsOfChoiceEffect();

        player.addObserverEffect(testedEffect);

        EasyMock.replay(player);

        testedEffect.on(player).apply();

        EasyMock.verify(player);
    }

    @Test
    public void testOnPhaseOne() {
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

        testedEffect.on(player).apply();

        Assert.assertEquals(effectedElementGivers, player.effectedElementGivers);
        EasyMock.verify(player, testedEffect);
        elementGivers.forEach(EasyMock::verify);
        effectedElementGivers.forEach(EasyMock::verify);
    }

}
