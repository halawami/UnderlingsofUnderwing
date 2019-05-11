package underlings.effect.player;

import static org.junit.Assert.assertTrue;

import java.util.LinkedList;

import org.easymock.EasyMock;
import org.junit.Test;

import underlings.card.effect.domestic.ElementGiverEffect;
import underlings.element.ElementGiver;
import underlings.gui.DrawChoice;
import underlings.player.Player;

public class ElementGiverEffectTests {

	@Test
	public void testApply() {
		Player player = EasyMock.mock(Player.class);
		player.effectElementGiver = new LinkedList<>();
		ElementGiverEffect elementGiverEffect = new ElementGiverEffect();
		elementGiverEffect.on(player);
		player.effectElementGiver.add(elementGiverEffect.elementGiver);
		EasyMock.replay(player);

		elementGiverEffect.elementGiver = new ElementGiver("something", DrawChoice.BLUE);
		elementGiverEffect.apply();

		EasyMock.verify(player);
		assertTrue(player.effectElementGiver.contains(elementGiverEffect.elementGiver));
	}

	@Test
	public void testApplyNoElementGiver() {
		Player player = EasyMock.mock(Player.class);
		player.effectElementGiver = new LinkedList<>();
		ElementGiverEffect elementGiverEffect = new ElementGiverEffect();
		elementGiverEffect.on(player);
		player.effectElementGiver.add(elementGiverEffect.elementGiver);
		EasyMock.replay(player);

		elementGiverEffect.apply();

		EasyMock.verify(player);
	}

}
