package underlings.player;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Test;

import underlings.TestUtils;
import underlings.card.Card;
import underlings.element.ElementGiver;
import underlings.handler.Handler;
import underlings.handler.HandlerFactory;
import underlings.handler.HandlerState;

public class GetElementGiversTests {

    @Test
    public void testTwoHandlersNoCardElementGivers() {
        HandlerFactory mockHandlerFactory = EasyMock.mock(HandlerFactory.class);
        Handler handler = new Handler(HandlerState.BREAK_ROOM);

        EasyMock.expect(mockHandlerFactory.createHandler()).andReturn(handler).times(2);

        EasyMock.replay(mockHandlerFactory);

        Player testedPlayer = new Player(6, mockHandlerFactory, 0);
        List<ElementGiver> elementGivers = testedPlayer.getElementGivers();

        Assert.assertEquals(2, elementGivers.size());
        for (ElementGiver elementGiver : elementGivers) {
            Assert.assertEquals(handler, elementGiver);
        }

        EasyMock.verify(mockHandlerFactory);
    }

    @Test
    public void testTwoHandlersOneCardElementGivers() {
        HandlerFactory mockHandlerFactory = EasyMock.mock(HandlerFactory.class);
        Handler handler = new Handler(HandlerState.BREAK_ROOM);
        ElementGiver mockCardElementGiver = EasyMock.mock(ElementGiver.class);
        Card elementGiverCard = EasyMock.mock(Card.class);

        EasyMock.expect(mockHandlerFactory.createHandler()).andReturn(handler).times(2);
        EasyMock.expect(elementGiverCard.getElementGivers()).andReturn(Arrays.asList(mockCardElementGiver));
        EasyMock.replay(mockHandlerFactory, mockCardElementGiver, elementGiverCard);

        Player testedPlayer = new Player(6, mockHandlerFactory, 0);
        testedPlayer.hatchedCards.add(elementGiverCard);

        List<ElementGiver> elementGivers = testedPlayer.getElementGivers();

        Assert.assertEquals(3, elementGivers.size());
        for (int i = 0; i < 2; i++) {
            Assert.assertEquals(handler, elementGivers.get(i));
        }
        Assert.assertEquals(mockCardElementGiver, elementGivers.get(2));

        EasyMock.verify(mockHandlerFactory, mockCardElementGiver, elementGiverCard);
    }

    @Test
    public void testTwoHandlersTwoElementGiversOneCard() {
        HandlerFactory mockHandlerFactory = EasyMock.mock(HandlerFactory.class);
        Handler handler = new Handler(HandlerState.BREAK_ROOM);
        ElementGiver mockCardElementGiver = EasyMock.mock(ElementGiver.class);
        Card elementGiverCard = EasyMock.mock(Card.class);

        EasyMock.expect(mockHandlerFactory.createHandler()).andReturn(handler).times(2);
        EasyMock.expect(elementGiverCard.getElementGivers())
                .andReturn(Arrays.asList(mockCardElementGiver, mockCardElementGiver));
        EasyMock.replay(mockHandlerFactory, mockCardElementGiver, elementGiverCard);

        Player testedPlayer = new Player(6, mockHandlerFactory, 0);
        testedPlayer.hatchedCards.add(elementGiverCard);

        List<ElementGiver> elementGivers = testedPlayer.getElementGivers();

        Assert.assertEquals(4, elementGivers.size());
        for (int i = 0; i < 2; i++) {
            Assert.assertEquals(handler, elementGivers.get(i));
        }
        Assert.assertEquals(mockCardElementGiver, elementGivers.get(2));
        Assert.assertEquals(mockCardElementGiver, elementGivers.get(3));

        EasyMock.verify(mockHandlerFactory, mockCardElementGiver, elementGiverCard);
    }

    @Test
    public void testTwoHandlersTwoElementGiversTwoCards() {
        HandlerFactory mockHandlerFactory = EasyMock.mock(HandlerFactory.class);
        Handler handler = new Handler(HandlerState.BREAK_ROOM);
        Card elementGiverCard1 = EasyMock.mock(Card.class);
        Card elementGiverCard2 = EasyMock.mock(Card.class);
        ElementGiver cardElementGiver1 = EasyMock.mock(ElementGiver.class);
        ElementGiver cardElementGiver2 = EasyMock.mock(ElementGiver.class);

        EasyMock.expect(mockHandlerFactory.createHandler()).andReturn(handler).times(2);
        EasyMock.expect(elementGiverCard1.getElementGivers()).andReturn(Arrays.asList(cardElementGiver1));
        EasyMock.expect(elementGiverCard2.getElementGivers()).andReturn(Arrays.asList(cardElementGiver2));

        EasyMock.replay(mockHandlerFactory, cardElementGiver1, cardElementGiver2, elementGiverCard1,
                elementGiverCard2);

        Player testedPlayer = new Player(6, mockHandlerFactory, 0);
        testedPlayer.hatchedCards.add(elementGiverCard1);
        testedPlayer.hatchedCards.add(elementGiverCard2);

        List<ElementGiver> elementGivers = testedPlayer.getElementGivers();

        Assert.assertEquals(4, elementGivers.size());
        for (int i = 0; i < 2; i++) {
            Assert.assertEquals(handler, elementGivers.get(i));
        }
        Assert.assertEquals(cardElementGiver1, elementGivers.get(2));
        Assert.assertEquals(cardElementGiver2, elementGivers.get(3));

        EasyMock.verify(mockHandlerFactory, cardElementGiver1, cardElementGiver2, elementGiverCard1,
                elementGiverCard2);
    }

    @Test
    public void testEffectElementGiversTrue() {
        List<ElementGiver> effectElementGivers = TestUtils.mockListOf(ElementGiver.class).withLength(2);
        Player player = new Player(0, null, 0);
        player.effectElementGivers = effectElementGivers;
        player.useEffectElementGivers(true);

        List<ElementGiver> elementGivers = player.getElementGivers();

        Assert.assertEquals(effectElementGivers, elementGivers);
    }

    @Test
    public void testEffectElementGiversTrueThenFalse() {
        List<ElementGiver> effectElementGivers = TestUtils.mockListOf(ElementGiver.class).withLength(2);
        Player player = EasyMock.partialMockBuilder(Player.class)
                .addMockedMethod("getNormalElementGivers").createMock();
        player.effectElementGivers = effectElementGivers;

        EasyMock.expect(player.getNormalElementGivers()).andReturn(Collections.emptyList());

        EasyMock.replay(player);

        player.useEffectElementGivers(true);
        player.useEffectElementGivers(false);
        player.getElementGivers();

        EasyMock.verify(player);
    }

}
