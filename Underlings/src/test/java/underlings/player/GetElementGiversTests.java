package underlings.player;

import java.util.Arrays;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Assert;
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
    public void testTwoHandlersOneEffectElementGivers() {
        HandlerFactory mockHandlerFactory = EasyMock.mock(HandlerFactory.class);
        Handler handler = new Handler(HandlerState.BREAK_ROOM);
        handler.elementGiver = EasyMock.mock(ElementGiver.class);
        ElementGiver mockElementGiver = EasyMock.mock(ElementGiver.class);
        Card elementGiverCard = EasyMock.mock(Card.class);

        EasyMock.expect(mockHandlerFactory.createHandler()).andReturn(handler).times(2);
        EasyMock.expect(elementGiverCard.getElementGivers()).andReturn(Arrays.asList(mockElementGiver));
        EasyMock.replay(mockHandlerFactory, handler.elementGiver, mockElementGiver);

        Player testedPlayer = new Player(6, mockHandlerFactory, 0);
        testedPlayer.hatchedCards.add(elementGiverCard);

        List<ElementGiver> elementGivers = testedPlayer.getElementGivers();

        Assert.assertEquals(3, elementGivers.size());
        for (int i = 0; i < 2; i++) {
            Assert.assertEquals(handler.elementGiver, elementGivers.get(i));
        }
        Assert.assertEquals(mockElementGiver, elementGivers.get(2));

        EasyMock.verify(mockHandlerFactory, handler.elementGiver, mockElementGiver);
    }

//    @Test
//    public void testTwoHandlersTwoEffectElementGivers() {
//        HandlerFactory mockHandlerFactory = EasyMock.mock(HandlerFactory.class);
//        Handler handler = new Handler(HandlerState.BREAK_ROOM);
//        handler.elementGiver = EasyMock.mock(ElementGiver.class);
//        ElementGiver mockEffectElementGiver = EasyMock.mock(ElementGiver.class);
//
//        EasyMock.expect(mockHandlerFactory.createHandler()).andReturn(handler).times(2);
//        EasyMock.replay(mockHandlerFactory, handler.elementGiver, mockEffectElementGiver);
//
//        Player testedPlayer = new Player(6, mockHandlerFactory, 0);
//        testedPlayer.gainEffectElementGiver(mockEffectElementGiver);
//        testedPlayer.gainEffectElementGiver(mockEffectElementGiver);
//
//        List<ElementGiver> elementGivers = testedPlayer.getElementGivers();
//
//        Assert.assertEquals(4, elementGivers.size());
//        for (int i = 0; i < 2; i++) {
//            Assert.assertEquals(handler.elementGiver, elementGivers.get(i));
//        }
//        Assert.assertEquals(mockEffectElementGiver, elementGivers.get(2));
//        Assert.assertEquals(mockEffectElementGiver, elementGivers.get(3));
//
//        EasyMock.verify(mockHandlerFactory, handler.elementGiver, mockEffectElementGiver);
//    }

}
