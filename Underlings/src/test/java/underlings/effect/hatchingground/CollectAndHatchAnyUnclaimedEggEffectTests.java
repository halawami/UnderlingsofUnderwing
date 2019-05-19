package underlings.effect.hatchingground;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Test;

import underlings.MockTest;
import underlings.card.Card;
import underlings.card.EmptyCard;
import underlings.card.effect.domestic.playerhatchingground.CollectAndHatchAnyUnclaimedEggEffect;
import underlings.game.HatchingGround;
import underlings.gui.Gui;
import underlings.player.Player;
import underlings.utilities.EggHatchingLogic;
import underlings.utilities.LocaleWrap;

public class CollectAndHatchAnyUnclaimedEggEffectTests extends MockTest {

    @Test
    public void testOneEggToHatch() {
        Player currentPlayer = this.mock(Player.class);
        currentPlayer.hatchedCards = new ArrayList<>();
        HatchingGround hatchingGround = this.mock(HatchingGround.class);
        List<Card> mockedCards = this.mockListOf(Card.class).withLengthOf(6);
        mockedCards.get(0).points = 5;

        EasyMock.expect(hatchingGround.getDragons(5, true)).andReturn(mockedCards);
        EasyMock.expect(currentPlayer.getId()).andReturn(0);

        Gui gui = this.mock(Gui.class);
        EasyMock.expect(gui.getCard(0, LocaleWrap.get("gui_card"), hatchingGround, mockedCards))
                .andReturn(mockedCards.get(0));

        EggHatchingLogic eggHatchingLogic = this.mock(EggHatchingLogic.class);
        eggHatchingLogic.hatchEgg(mockedCards.get(0), currentPlayer);

        this.replayAll();

        CollectAndHatchAnyUnclaimedEggEffect effect = new CollectAndHatchAnyUnclaimedEggEffect();
        effect.points = 5;
        effect.on(gui).on(currentPlayer).on(hatchingGround).on(eggHatchingLogic).apply();
    }

    @Test
    public void testNoEggToHatch() {
        Player currentPlayer = this.mock(Player.class);
        currentPlayer.hatchedCards = new ArrayList<>();
        HatchingGround hatchingGround = this.mock(HatchingGround.class);
        List<Card> mockedCards = this.mockListOf(Card.class).withLengthOf(6);
        mockedCards.get(0).points = 5;

        EasyMock.expect(hatchingGround.getDragons(5, true)).andReturn(mockedCards);
        EasyMock.expect(currentPlayer.getId()).andReturn(0);
        Gui gui = this.mock(Gui.class);

        EasyMock.expect(gui.getCard(0, LocaleWrap.get("gui_card"), hatchingGround, mockedCards))
                .andReturn(EmptyCard.getInstance());
        EggHatchingLogic eggHatchingLogic = this.mock(EggHatchingLogic.class);
        CollectAndHatchAnyUnclaimedEggEffect effect =
                new CollectAndHatchAnyUnclaimedEggEffect();

        this.replayAll();

        effect.points = 5;
        effect.on(gui).on(currentPlayer).on(hatchingGround).on(eggHatchingLogic).apply();
    }

    @Test
    public void testToString() {
        CollectAndHatchAnyUnclaimedEggEffect effect = new CollectAndHatchAnyUnclaimedEggEffect();
        assertEquals(LocaleWrap.format("hatch_unclaimed_dragon_effect", effect.points), effect.toString());
    }
}
