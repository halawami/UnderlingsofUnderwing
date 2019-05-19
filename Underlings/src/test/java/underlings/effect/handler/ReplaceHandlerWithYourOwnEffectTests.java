package underlings.effect.handler;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.easymock.EasyMock;
import org.junit.Test;

import underlings.MockTest;
import underlings.card.Card;
import underlings.card.effect.Effect;
import underlings.card.effect.domestic.handler.ReplaceHandlerWithYourOwnEffect;
import underlings.game.HatchingGround;
import underlings.gui.Gui;
import underlings.handler.Handler;
import underlings.handler.HandlerChoice;
import underlings.handler.HandlerMovementLogic;
import underlings.player.Player;
import underlings.utilities.LocaleWrap;

public class ReplaceHandlerWithYourOwnEffectTests extends MockTest {

    @Test
    public void testApplyEffectNoSelectableHandler() {
        Player currentPlayer = this.mock(Player.class);
        HatchingGround hatchingGround = this.mock(HatchingGround.class);
        List<Card> mockClaimedEggs = this.mockListOf(Card.class).withLengthOf(0);

        EasyMock.expect(currentPlayer.getId()).andReturn(0).anyTimes();
        EasyMock.expect(hatchingGround.getClaimedEggs()).andReturn(mockClaimedEggs);

        this.replayAll();

        Effect effect = new ReplaceHandlerWithYourOwnEffect();
        effect.on(currentPlayer).on(hatchingGround).apply();
    }

    @Test
    public void testApplyEffectSelectableHandler() {
        Player currentPlayer = this.mock(Player.class);
        HatchingGround hatchingGround = this.mock(HatchingGround.class);
        List<Card> mockClaimedEggs = this.mockListOf(Card.class).withLengthOf(6);
        List<Handler> mockHandlers = this.mockListOf(Handler.class).withLengthOf(2);
        Handler toBeReplaced = this.mock(Handler.class);
        mockClaimedEggs.get(2).handler = toBeReplaced;
        HandlerMovementLogic handlerMovementLogic = this.mock(HandlerMovementLogic.class);
        Gui gui = this.mock(Gui.class);

        EasyMock.expect(currentPlayer.getId()).andReturn(0).anyTimes();
        EasyMock.expect(hatchingGround.getClaimedEggs()).andReturn(mockClaimedEggs);
        EasyMock.expect(gui.promptChoice(LocaleWrap.get("choose_card_replace_handler"), mockClaimedEggs, 0))
                .andReturn(mockClaimedEggs.get(2));
        currentPlayer.handlers = mockHandlers;
        EasyMock.expect(gui.promptChoice(LocaleWrap.get("choose_replace_handler"), mockHandlers, 0))
                .andReturn(mockHandlers.get(1));
        handlerMovementLogic.move(toBeReplaced, HandlerChoice.BREAK_ROOM, currentPlayer);
        handlerMovementLogic.moveToCard(mockHandlers.get(1), mockClaimedEggs.get(2));

        this.replayAll();

        Effect effect = new ReplaceHandlerWithYourOwnEffect();
        effect.on(currentPlayer).on(hatchingGround).on(handlerMovementLogic).on(gui).apply();
    }

    @Test
    public void testToString() {
        Effect effect = new ReplaceHandlerWithYourOwnEffect();
        assertEquals(LocaleWrap.get("replace_handler_effect"), effect.toString());
    }
}
