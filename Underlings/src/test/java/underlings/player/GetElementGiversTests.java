package underlings.player;

import java.util.List;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Test;

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
    public void testSixHandlersNoEffectElementGivers() {
        HandlerFactory mockHandlerFactory = EasyMock.mock(HandlerFactory.class);
        Handler handler = new Handler(HandlerState.BREAK_ROOM);
        handler.elementGiver = EasyMock.mock(ElementGiver.class);

        EasyMock.expect(mockHandlerFactory.createHandler()).andReturn(handler).times(6);

        EasyMock.replay(mockHandlerFactory, handler.elementGiver);

        Player testedPlayer = new Player(6, mockHandlerFactory, 0);
        for (int i = 0; i < 4; i++) {
            testedPlayer.gainHandler();
        }

        List<ElementGiver> elementGivers = testedPlayer.getElementGivers();

        Assert.assertEquals(6, elementGivers.size());
        for (ElementGiver elementGiver : elementGivers) {
            Assert.assertEquals(handler.elementGiver, elementGiver);
        }

        EasyMock.verify(mockHandlerFactory, handler.elementGiver);
    }

}
