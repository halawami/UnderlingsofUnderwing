package underlings.effect.hatchingground;

import java.util.ArrayList;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Test;

import underlings.card.Card;
import underlings.card.EmptyCard;
import underlings.card.effect.domestic.CollectAndHatchAnyUnclaimedEggEffect;
import underlings.element.ElementSpace;
import underlings.game.HatchingGround;
import underlings.gui.Gui;
import underlings.player.Player;
import underlings.utilities.EggHatchingLogic;
import underlings.utilities.LocaleWrap;

public class CollectAndHatchAnyUnclaimedEggEffectTests {

    @Test
    public void testOneEggToHatch() {
        Player currentPlayer = EasyMock.mock(Player.class);
        currentPlayer.hatchedCards = new ArrayList<>();
        HatchingGround hatchingGround = EasyMock.mock(HatchingGround.class);
        List<Card> mockedCards = this.getMockedCards(6);
        Gui gui = EasyMock.mock(Gui.class);
        EggHatchingLogic eggHatchingLogic = EasyMock.mock(EggHatchingLogic.class);
        mockedCards.get(0).points = 5;
        EasyMock.expect(hatchingGround.getDragons(5, true)).andReturn(mockedCards);
        EasyMock.expect(currentPlayer.getPlayerId()).andReturn(0);
        EasyMock.expect(gui.getCard(0, LocaleWrap.get("gui_card"), hatchingGround, mockedCards))
                .andReturn(mockedCards.get(0));
        eggHatchingLogic.hatchEgg(mockedCards.get(0), false, currentPlayer);
        CollectAndHatchAnyUnclaimedEggEffect collectAndHatchAnyUnclaimedEggEffect =
                new CollectAndHatchAnyUnclaimedEggEffect();
        collectAndHatchAnyUnclaimedEggEffect.on(gui).on(currentPlayer).on(hatchingGround).on(eggHatchingLogic);
        ElementSpace elementSpace = EasyMock.mock(ElementSpace.class);

        EasyMock.replay(currentPlayer, hatchingGround, gui, elementSpace, eggHatchingLogic);

        collectAndHatchAnyUnclaimedEggEffect.points = 5;
        collectAndHatchAnyUnclaimedEggEffect.apply();

        EasyMock.verify(currentPlayer, hatchingGround, gui, elementSpace, eggHatchingLogic);
    }

    @Test
    public void testNoEggToHatch() {
        Player currentPlayer = EasyMock.mock(Player.class);
        currentPlayer.hatchedCards = new ArrayList<>();
        HatchingGround hatchingGround = EasyMock.mock(HatchingGround.class);
        List<Card> mockedCards = this.getMockedCards(6);
        Gui gui = EasyMock.mock(Gui.class);
        EggHatchingLogic eggHatchingLogic = EasyMock.mock(EggHatchingLogic.class);
        CollectAndHatchAnyUnclaimedEggEffect collectAndHatchAnyUnclaimedEggEffect =
                new CollectAndHatchAnyUnclaimedEggEffect();
        mockedCards.get(0).points = 5;
        EasyMock.expect(hatchingGround.getDragons(5, true)).andReturn(mockedCards);
        EasyMock.expect(currentPlayer.getPlayerId()).andReturn(0);
        EasyMock.expect(gui.getCard(0, LocaleWrap.get("gui_card"), hatchingGround, mockedCards))
                .andReturn(EmptyCard.getInstance());
        collectAndHatchAnyUnclaimedEggEffect.on(gui).on(currentPlayer).on(hatchingGround).on(eggHatchingLogic);
        ElementSpace elementSpace = EasyMock.mock(ElementSpace.class);

        EasyMock.replay(currentPlayer, hatchingGround, gui, elementSpace, eggHatchingLogic);

        collectAndHatchAnyUnclaimedEggEffect.points = 5;
        collectAndHatchAnyUnclaimedEggEffect.apply();

        EasyMock.verify(currentPlayer, hatchingGround, gui, elementSpace, eggHatchingLogic);
    }

    private List<Card> getMockedCards(int numberOfCards) {
        List<Card> mockedCards = new ArrayList<>();
        for (int i = 0; i < numberOfCards; i++) {
            mockedCards.add(EasyMock.niceMock(Card.class));
        }
        return mockedCards;
    }
}
