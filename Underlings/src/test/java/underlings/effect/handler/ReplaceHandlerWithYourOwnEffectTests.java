package underlings.effect.handler;

import java.util.List;

import org.easymock.EasyMock;
import org.junit.Test;

import underlings.TestUtils;
import underlings.card.Card;
import underlings.card.effect.Effect;
import underlings.card.effect.domestic.ReplaceHandlerWithYourOwnEffect;
import underlings.game.HatchingGround;
import underlings.gui.Gui;
import underlings.handler.Handler;
import underlings.handler.HandlerChoice;
import underlings.handler.HandlerMovementLogic;
import underlings.player.Player;

public class ReplaceHandlerWithYourOwnEffectTests {

    @Test
    public void testApplyEffectNoSelectableHandler() {
        Player currentPlayer = EasyMock.mock(Player.class);
        HatchingGround hatchingGround = EasyMock.mock(HatchingGround.class);
        List<Card> mockClaimedEggs = TestUtils.mockListOf(Card.class).withLength(0);
        HandlerMovementLogic handlerMovementLogic = EasyMock.mock(HandlerMovementLogic.class);
        Gui gui = EasyMock.mock(Gui.class);

        EasyMock.expect(currentPlayer.getPlayerId()).andReturn(0).anyTimes();
        EasyMock.expect(hatchingGround.getClaimedEggs()).andReturn(mockClaimedEggs);

        EasyMock.replay(currentPlayer, hatchingGround, handlerMovementLogic, gui);

        Effect testedEffect = new ReplaceHandlerWithYourOwnEffect();
        testedEffect.on(currentPlayer).on(hatchingGround).on(handlerMovementLogic).on(gui).apply();

        EasyMock.verify(currentPlayer, hatchingGround, handlerMovementLogic, gui);
    }

    @Test
    public void testApplyEffectSelectableHandler() {
        Player currentPlayer = EasyMock.mock(Player.class);
        HatchingGround hatchingGround = EasyMock.mock(HatchingGround.class);
        List<Card> mockClaimedEggs = TestUtils.mockListOf(Card.class).withLength(6);
        List<Handler> mockHandlers = TestUtils.mockListOf(Handler.class).withLength(2);
        Handler toBeReplaced = EasyMock.mock(Handler.class);
        mockClaimedEggs.get(2).handler = toBeReplaced;
        HandlerMovementLogic handlerMovementLogic = EasyMock.mock(HandlerMovementLogic.class);
        Gui gui = EasyMock.mock(Gui.class);

        EasyMock.expect(currentPlayer.getPlayerId()).andReturn(0).anyTimes();
        EasyMock.expect(hatchingGround.getClaimedEggs()).andReturn(mockClaimedEggs);
        EasyMock.expect(gui.promptCard("Choose a card to replace its handler", mockClaimedEggs))
                .andReturn(mockClaimedEggs.get(2));
        EasyMock.expect(currentPlayer.getHandlers()).andReturn(mockHandlers);
        EasyMock.expect(gui.promptHandler("Choose a handler to replace with", 0, mockHandlers))
                .andReturn(mockHandlers.get(1));
        handlerMovementLogic.move(toBeReplaced, HandlerChoice.BREAK_ROOM, 0);
        handlerMovementLogic.moveToCard(mockHandlers.get(1), mockClaimedEggs.get(2));

        EasyMock.replay(currentPlayer, hatchingGround, toBeReplaced, handlerMovementLogic, gui);
        mockClaimedEggs.forEach(EasyMock::replay);
        mockHandlers.forEach(EasyMock::replay);

        Effect testedEffect = new ReplaceHandlerWithYourOwnEffect();
        testedEffect.on(currentPlayer).on(hatchingGround).on(handlerMovementLogic).on(gui).apply();

        EasyMock.verify(currentPlayer, hatchingGround, toBeReplaced, handlerMovementLogic, gui);
        mockClaimedEggs.forEach(EasyMock::verify);
        mockHandlers.forEach(EasyMock::verify);
    }
}
