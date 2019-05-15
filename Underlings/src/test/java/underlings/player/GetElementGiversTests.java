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
    public void testTwoHandlersNoEffectElementGivers() {
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
    public void testTwoHandlersOneEffectElementGivers() {
        HandlerFactory mockHandlerFactory = EasyMock.mock(HandlerFactory.class);
        Handler handler = new Handler(HandlerState.BREAK_ROOM);
        ElementGiver mockEffectElementGiver = EasyMock.mock(ElementGiver.class);
        Card elementGiverCard = EasyMock.mock(Card.class);

        EasyMock.expect(mockHandlerFactory.createHandler()).andReturn(handler).times(2);
        EasyMock.expect(elementGiverCard.getElementGivers()).andReturn(Arrays.asList(mockEffectElementGiver));
        EasyMock.replay(mockHandlerFactory, mockEffectElementGiver, elementGiverCard);

        Player testedPlayer = new Player(6, mockHandlerFactory, 0);
        testedPlayer.hatchedCards.add(elementGiverCard);

        List<ElementGiver> elementGivers = testedPlayer.getElementGivers();

        Assert.assertEquals(3, elementGivers.size());
        for (int i = 0; i < 2; i++) {
            Assert.assertEquals(handler, elementGivers.get(i));
        }
        Assert.assertEquals(mockEffectElementGiver, elementGivers.get(2));

        EasyMock.verify(mockHandlerFactory, mockEffectElementGiver, elementGiverCard);
    }

    @Test
    public void testTwoHandlersTwoElementGiversOneCard() {
        HandlerFactory mockHandlerFactory = EasyMock.mock(HandlerFactory.class);
        Handler handler = new Handler(HandlerState.BREAK_ROOM);
        ElementGiver mockEffectElementGiver = EasyMock.mock(ElementGiver.class);
        Card elementGiverCard = EasyMock.mock(Card.class);

        EasyMock.expect(mockHandlerFactory.createHandler()).andReturn(handler).times(2);
        EasyMock.expect(elementGiverCard.getElementGivers())
                .andReturn(Arrays.asList(mockEffectElementGiver, mockEffectElementGiver));
        EasyMock.replay(mockHandlerFactory, mockEffectElementGiver, elementGiverCard);

        Player testedPlayer = new Player(6, mockHandlerFactory, 0);
        testedPlayer.hatchedCards.add(elementGiverCard);

        List<ElementGiver> elementGivers = testedPlayer.getElementGivers();

        Assert.assertEquals(4, elementGivers.size());
        for (int i = 0; i < 2; i++) {
            Assert.assertEquals(handler, elementGivers.get(i));
        }
        Assert.assertEquals(mockEffectElementGiver, elementGivers.get(2));
        Assert.assertEquals(mockEffectElementGiver, elementGivers.get(3));

        EasyMock.verify(mockHandlerFactory, mockEffectElementGiver, elementGiverCard);
    }

    @Test
    public void testTwoHandlersTwoElementGiversTwoCards() {
        HandlerFactory mockHandlerFactory = EasyMock.mock(HandlerFactory.class);
        Handler handler = new Handler(HandlerState.BREAK_ROOM);
        Card elementGiverCard1 = EasyMock.mock(Card.class);
        Card elementGiverCard2 = EasyMock.mock(Card.class);
        ElementGiver effectElementGiver1 = EasyMock.mock(ElementGiver.class);
        ElementGiver effectElementGiver2 = EasyMock.mock(ElementGiver.class);

        EasyMock.expect(mockHandlerFactory.createHandler()).andReturn(handler).times(2);
        EasyMock.expect(elementGiverCard1.getElementGivers()).andReturn(Arrays.asList(effectElementGiver1));
        EasyMock.expect(elementGiverCard2.getElementGivers()).andReturn(Arrays.asList(effectElementGiver2));

        EasyMock.replay(mockHandlerFactory, effectElementGiver1, effectElementGiver2, elementGiverCard1,
                elementGiverCard2);

        Player testedPlayer = new Player(6, mockHandlerFactory, 0);
        testedPlayer.hatchedCards.add(elementGiverCard1);
        testedPlayer.hatchedCards.add(elementGiverCard2);

        List<ElementGiver> elementGivers = testedPlayer.getElementGivers();

        Assert.assertEquals(4, elementGivers.size());
        for (int i = 0; i < 2; i++) {
            Assert.assertEquals(handler, elementGivers.get(i));
        }
        Assert.assertEquals(effectElementGiver1, elementGivers.get(2));
        Assert.assertEquals(effectElementGiver2, elementGivers.get(3));

        EasyMock.verify(mockHandlerFactory, effectElementGiver1, effectElementGiver2, elementGiverCard1,
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
        List<ElementGiver> elementGivers = player.getElementGivers();

        EasyMock.verify(player);
    }

}
