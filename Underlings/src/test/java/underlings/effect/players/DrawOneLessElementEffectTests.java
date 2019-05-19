package underlings.effect.players;

import static junit.framework.TestCase.assertEquals;

import java.util.List;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Test;

import underlings.MockTest;
import underlings.card.effect.wild.DrawOneLessElementEffect;
import underlings.element.ElementGiver;
import underlings.player.Player;

public class DrawOneLessElementEffectTests extends MockTest {

    @Test
    public void testApplyTwoPlayers() {
        List<Player> players = this.mockListOf(Player.class).withLengthOf(2);
        DrawOneLessElementEffect effect = new DrawOneLessElementEffect();

        for (Player player : players) {
            player.addObserverEffect(effect);
        }

        this.replayAll();

        effect.on(players).apply();
    }

    @Test
    public void testApplySixPlayers() {
        List<Player> players = this.mockListOf(Player.class).withLengthOf(2);
        DrawOneLessElementEffect effect = new DrawOneLessElementEffect();

        for (Player player : players) {
            player.addObserverEffect(effect);
        }

        this.replayAll();

        effect.on(players).apply();
    }

    @Test
    public void testOnPhaseOne() {
        Player player = this.mock(Player.class);
        DrawOneLessElementEffect effect = EasyMock.partialMockBuilder(DrawOneLessElementEffect.class)
                .addMockedMethod("getEffectElementGivers").createMock();
        this.addMock(effect);
        List<ElementGiver> elementGivers = this.mockListOf(ElementGiver.class).withLengthOf(2);
        List<ElementGiver> effectElementGivers = this.mockListOf(ElementGiver.class).withLengthOf(1);

        EasyMock.expect(player.getElementGivers()).andReturn(elementGivers);
        EasyMock.expect(effect.getEffectElementGivers(elementGivers)).andReturn(effectElementGivers);
        player.useEffectElementGivers(true);

        this.replayAll();

        effect.onPhaseOne(player);

        Assert.assertEquals(effectElementGivers, player.effectElementGivers);
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

    private void testGetEffectElementGivers(int numberOfGivers) {
        List<ElementGiver> elementGivers = this.mockListOf(ElementGiver.class).withLengthOf(numberOfGivers);

        this.replayAll();

        DrawOneLessElementEffect effect = new DrawOneLessElementEffect();
        List<ElementGiver> effectElementGivers = effect.getEffectElementGivers(elementGivers);

        Assert.assertEquals(numberOfGivers - 1, effectElementGivers.size());
        for (int i = 0; i < effectElementGivers.size(); i++) {
            assertEquals(elementGivers.get(i), effectElementGivers.get(i));
        }
    }
}
