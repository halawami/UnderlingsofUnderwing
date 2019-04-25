package tests.phase;

import java.util.Arrays;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Test;

import underlings.card.Card;
import underlings.element.ElementBag;
import underlings.element.ElementColor;
import underlings.element.ElementSpace;
import underlings.game.HatchingGround;
import underlings.phase.DragonPhase;
import underlings.phase.Phase;
import underlings.player.Player;

public class DragonPhaseTests {

	@Test
	public void testInit() {
		Phase phase = new DragonPhase(null, null, null, null, null, null);
	}
	
	@Test
	public void testSetupWithPurple() {
		HatchingGround hatchingGround = EasyMock.mock(HatchingGround.class);
		ElementBag bag = EasyMock.mock(ElementBag.class);
		Player player = EasyMock.mock(Player.class);
		List<Player> players = Arrays.asList(player);
		
		Card card = new Card();
		ElementSpace[] spaces = { new ElementSpace(ElementColor.PURPLE) };
		card.elementSpaces = spaces;
		spaces[0].elements = Arrays.asList(ElementColor.BLUE, ElementColor.RED);
		List<Card> completeEggs = Arrays.asList(card);
		
		EasyMock.expect(hatchingGround.pullAndReplaceCompleteEggs()).andReturn(completeEggs);
		bag.putElement(ElementColor.BLUE);
		bag.putElement(ElementColor.RED);
		
		EasyMock.replay(hatchingGround, bag, player);
		Phase phase = new DragonPhase(players, null, bag, hatchingGround, null, null);
		phase.setup();
		EasyMock.verify(hatchingGround, bag, player);	
	}
	
	@Test
	public void testSetupWithOrange() {
		HatchingGround hatchingGround = EasyMock.mock(HatchingGround.class);
		ElementBag bag = EasyMock.mock(ElementBag.class);
		Player player = EasyMock.mock(Player.class);
		List<Player> players = Arrays.asList(player);
		
		Card card = new Card();
		ElementSpace[] spaces = { new ElementSpace(ElementColor.ORANGE) };
		card.elementSpaces = spaces;
		spaces[0].elements = Arrays.asList(ElementColor.RED, ElementColor.YELLOW);
		List<Card> completeEggs = Arrays.asList(card);
		
		EasyMock.expect(hatchingGround.pullAndReplaceCompleteEggs()).andReturn(completeEggs);
		bag.putElement(ElementColor.RED);
		bag.putElement(ElementColor.YELLOW);
		
		EasyMock.replay(hatchingGround, bag, player);
		Phase phase = new DragonPhase(players, null, bag, hatchingGround, null, null);
		phase.setup();
		EasyMock.verify(hatchingGround, bag, player);	
	}
}
