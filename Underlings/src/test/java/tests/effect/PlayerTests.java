package tests.effect;

import org.easymock.EasyMock;
import org.junit.Test;
import underlings.card.effect.Effect;
import underlings.card.effect.domestic.GainOneHandlerEffect;
import underlings.player.Player;

public class PlayerTests {

	@Test
	public void testGainOneHandler() {
		Player player = EasyMock.mock(Player.class);
		Effect gainOneHandler = new GainOneHandlerEffect();
		player.gainHandler();
		EasyMock.replay(player);
		
		gainOneHandler.apply();
		EasyMock.verify(player);
	}
	
}


