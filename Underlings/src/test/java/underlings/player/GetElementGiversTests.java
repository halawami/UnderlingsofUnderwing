package underlings.player;

import java.util.List;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import underlings.card.Card;
import underlings.element.ElementGiver;
import underlings.handler.Handler;
import underlings.handler.HandlerFactory;
import underlings.handler.HandlerState;

public class GetElementGiversTests {

	@Test
	public void testTwoHandlersNoEffectElementGivers() {
		HandlerFactory mockHandlerFactory = EasyMock.mock(HandlerFactory.class);
		Handler handler = new Handler(HandlerState.BREAK_ROOM);
		handler.elementGiver = EasyMock.mock(ElementGiver.class);

		EasyMock.expect(mockHandlerFactory.createHandler()).andReturn(handler).times(2);

		EasyMock.replay(mockHandlerFactory, handler.elementGiver);

		Player testedPlayer = new Player(6, mockHandlerFactory, 0);
		List<ElementGiver> elementGivers = testedPlayer.getElementGivers();

		Assert.assertEquals(2, elementGivers.size());
		for (ElementGiver elementGiver : elementGivers) {
			Assert.assertEquals(handler.elementGiver, elementGiver);
		}

		EasyMock.verify(mockHandlerFactory, handler.elementGiver);
	}

	@Test
	@Ignore
	public void testTwoHandlersOneEffectElementGivers() {
		HandlerFactory mockHandlerFactory = EasyMock.mock(HandlerFactory.class);
		Handler handler = new Handler(HandlerState.BREAK_ROOM);
		handler.elementGiver = EasyMock.mock(ElementGiver.class);
		ElementGiver mockEffectElementGiver = EasyMock.mock(ElementGiver.class);
		Card elementGiverCard = EasyMock.mock(Card.class);

		EasyMock.expect(mockHandlerFactory.createHandler()).andReturn(handler).times(2);
		// EasyMock.expect(elementGiverCard.getElementGivers()).andReturn(Arrays.asList(mockEffectElementGiver));
		EasyMock.replay(mockHandlerFactory, handler.elementGiver, mockEffectElementGiver, elementGiverCard);

		Player testedPlayer = new Player(6, mockHandlerFactory, 0);
		testedPlayer.hatchedCards.add(elementGiverCard);

		List<ElementGiver> elementGivers = testedPlayer.getElementGivers();

		Assert.assertEquals(3, elementGivers.size());
		for (int i = 0; i < 2; i++) {
			Assert.assertEquals(handler.elementGiver, elementGivers.get(i));
		}
		Assert.assertEquals(mockEffectElementGiver, elementGivers.get(2));

		EasyMock.verify(mockHandlerFactory, handler.elementGiver, mockEffectElementGiver, elementGiverCard);
	}

	@Test
	@Ignore
	public void testTwoHandlersTwoElementGiversOneCard() {
		HandlerFactory mockHandlerFactory = EasyMock.mock(HandlerFactory.class);
		Handler handler = new Handler(HandlerState.BREAK_ROOM);
		handler.elementGiver = EasyMock.mock(ElementGiver.class);
		ElementGiver mockEffectElementGiver = EasyMock.mock(ElementGiver.class);
		Card elementGiverCard = EasyMock.mock(Card.class);

		EasyMock.expect(mockHandlerFactory.createHandler()).andReturn(handler).times(2);
		// EasyMock.expect(elementGiverCard.getElementGivers())
		// .andReturn(Arrays.asList(mockEffectElementGiver,
		// mockEffectElementGiver));
		EasyMock.replay(mockHandlerFactory, handler.elementGiver, mockEffectElementGiver, elementGiverCard);

		Player testedPlayer = new Player(6, mockHandlerFactory, 0);
		testedPlayer.hatchedCards.add(elementGiverCard);

		List<ElementGiver> elementGivers = testedPlayer.getElementGivers();

		Assert.assertEquals(4, elementGivers.size());
		for (int i = 0; i < 2; i++) {
			Assert.assertEquals(handler.elementGiver, elementGivers.get(i));
		}
		Assert.assertEquals(mockEffectElementGiver, elementGivers.get(2));
		Assert.assertEquals(mockEffectElementGiver, elementGivers.get(3));

		EasyMock.verify(mockHandlerFactory, handler.elementGiver, mockEffectElementGiver, elementGiverCard);
	}

	@Test
	@Ignore
	public void testTwoHandlersTwoElementGiversTwoCards() {
		HandlerFactory mockHandlerFactory = EasyMock.mock(HandlerFactory.class);
		Handler handler = new Handler(HandlerState.BREAK_ROOM);
		handler.elementGiver = EasyMock.mock(ElementGiver.class);
		Card firstElementGiverCard = EasyMock.mock(Card.class);
		Card secondElementGiverCard = EasyMock.mock(Card.class);
		ElementGiver firstEffectElementGiver = EasyMock.mock(ElementGiver.class);
		ElementGiver secondEffectElementGiver = EasyMock.mock(ElementGiver.class);

		EasyMock.expect(mockHandlerFactory.createHandler()).andReturn(handler).times(2);
		// EasyMock.expect(firstElementGiverCard.getElementGivers()).andReturn(Arrays.asList(firstEffectElementGiver));
		// EasyMock.expect(secondElementGiverCard.getElementGivers()).andReturn(Arrays.asList(secondEffectElementGiver));

		EasyMock.replay(mockHandlerFactory, handler.elementGiver, firstEffectElementGiver, secondEffectElementGiver,
				firstElementGiverCard, secondElementGiverCard);

		Player testedPlayer = new Player(6, mockHandlerFactory, 0);
		testedPlayer.hatchedCards.add(firstElementGiverCard);
		testedPlayer.hatchedCards.add(secondElementGiverCard);

		List<ElementGiver> elementGivers = testedPlayer.getElementGivers();

		Assert.assertEquals(4, elementGivers.size());
		for (int i = 0; i < 2; i++) {
			Assert.assertEquals(handler.elementGiver, elementGivers.get(i));
		}
		Assert.assertEquals(firstEffectElementGiver, elementGivers.get(2));
		Assert.assertEquals(secondEffectElementGiver, elementGivers.get(3));

		EasyMock.verify(mockHandlerFactory, handler.elementGiver, firstEffectElementGiver, secondEffectElementGiver,
				firstElementGiverCard, secondElementGiverCard);
	}

}
