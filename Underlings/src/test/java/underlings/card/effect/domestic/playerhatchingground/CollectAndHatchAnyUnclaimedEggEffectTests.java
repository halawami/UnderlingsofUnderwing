package underlings.card.effect.domestic.playerhatchingground;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Test;

import underlings.MockTest;
import underlings.card.Card;
import underlings.card.EmptyCard;
import underlings.gui.Gui;
import underlings.hatchingground.HatchingGround;
import underlings.player.Player;
import underlings.utilities.EggHatchingUtilities;
import underlings.utilities.LocaleUtilities;

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
        EasyMock.expect(gui.getCard(0, LocaleUtilities.get("gui_card"), hatchingGround, mockedCards))
                .andReturn(mockedCards.get(0));

        EggHatchingUtilities eggHatchingLogic = this.mock(EggHatchingUtilities.class);
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

        EasyMock.expect(gui.getCard(0, LocaleUtilities.get("gui_card"), hatchingGround, mockedCards))
                .andReturn(EmptyCard.getInstance());
        EggHatchingUtilities eggHatchingLogic = this.mock(EggHatchingUtilities.class);
        CollectAndHatchAnyUnclaimedEggEffect effect =
                new CollectAndHatchAnyUnclaimedEggEffect();

        this.replayAll();

        effect.points = 5;
        effect.on(gui).on(currentPlayer).on(hatchingGround).on(eggHatchingLogic).apply();
    }

    @Test
    public void testToString() {
        CollectAndHatchAnyUnclaimedEggEffect effect = new CollectAndHatchAnyUnclaimedEggEffect();
        assertEquals(LocaleUtilities.format("hatch_unclaimed_dragon_effect", effect.points), effect.toString());
    }
}
