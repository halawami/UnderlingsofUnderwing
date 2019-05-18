package underlings.effect.players;

import java.util.List;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Test;

import underlings.TestUtils;
import underlings.card.effect.wild.DrawOneLessElementEffect;
import underlings.element.ElementGiver;
import underlings.player.Player;

public class DrawOneLessElementEffectTests {

    @Test
    public void testApplyTwoPlayers() {
        List<Player> players = TestUtils.mockListOf(Player.class).withLength(2);
        DrawOneLessElementEffect testedEffect = new DrawOneLessElementEffect();

        for (Player player : players) {
            player.addObserverEffect(testedEffect);
        }

        players.forEach(EasyMock::replay);

        testedEffect.on(players).apply();

        players.forEach(EasyMock::verify);
    }

    @Test
    public void testApplySixPlayers() {
        List<Player> players = TestUtils.mockListOf(Player.class).withLength(2);
        DrawOneLessElementEffect testedEffect = new DrawOneLessElementEffect();

        for (Player player : players) {
            player.addObserverEffect(testedEffect);
        }

        players.forEach(EasyMock::replay);

        testedEffect.on(players).apply();

        players.forEach(EasyMock::verify);
    }

    @Test
    public void testOnPhaseOne() {
        Player player = EasyMock.mock(Player.class);
        DrawOneLessElementEffect testedEffect = EasyMock.partialMockBuilder(DrawOneLessElementEffect.class)
                .addMockedMethod("getEffectElementGivers").createMock();
        List<ElementGiver> elementGivers = TestUtils.mockListOf(ElementGiver.class).withLength(2);
        List<ElementGiver> effectElementGivers = TestUtils.mockListOf(ElementGiver.class).withLength(1);

        EasyMock.expect(player.getElementGivers()).andReturn(elementGivers);
        EasyMock.expect(testedEffect.getEffectElementGivers(elementGivers)).andReturn(effectElementGivers);
        player.useEffectElementGivers(true);

        EasyMock.replay(player, testedEffect);
        elementGivers.forEach(EasyMock::replay);
        effectElementGivers.forEach(EasyMock::replay);

        testedEffect.onPhaseOne(player);

        Assert.assertEquals(effectElementGivers, player.effectElementGivers);
        EasyMock.verify(player, testedEffect);
        elementGivers.forEach(EasyMock::verify);
        effectElementGivers.forEach(EasyMock::verify);
    }

    @Test
    public void testGetEffectElementGiversOneElementGiver() {
        List<ElementGiver> elementGivers = TestUtils.mockListOf(ElementGiver.class).withLength(1);

        elementGivers.forEach(EasyMock::replay);

        DrawOneLessElementEffect testedEffect = new DrawOneLessElementEffect();
        List<ElementGiver> effectElementGivers = testedEffect.getEffectElementGivers(elementGivers);

        Assert.assertEquals(0, effectElementGivers.size());

        elementGivers.forEach(EasyMock::verify);
    }
}
