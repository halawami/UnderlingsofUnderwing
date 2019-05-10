package underlings.effect.hatchingground;

import java.util.ArrayList;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Test;

import underlings.card.Card;
import underlings.card.effect.domestic.CollectAndHatchAnyUnclaimedEggEffect;
import underlings.element.ElementSpace;
import underlings.game.HatchingGround;
import underlings.gui.Gui;
import underlings.player.Player;
import underlings.utilities.EggHatchingLogic;

public class CollectAndHatchAnyUnclaimedEggEffectTests {

    @Test
    public void testOneEggToHatch() {
        Player currentPlayer = EasyMock.mock(Player.class);
        HatchingGround hatchingGround = EasyMock.mock(HatchingGround.class);
        List<Card> mockedCards = this.getMockedCards(6);
        Gui gui = EasyMock.mock(Gui.class);
        ElementSpace elementSpace = EasyMock.mock(ElementSpace.class);
        EggHatchingLogic eggHatchingLogic = EasyMock.mock(EggHatchingLogic.class);
        CollectAndHatchAnyUnclaimedEggEffect collectAndHatchAnyUnclaimedEggEffect =
                new CollectAndHatchAnyUnclaimedEggEffect();
        mockedCards.get(0).points = 5;
        EasyMock.expect(hatchingGround.getAllCards()).andReturn(mockedCards);
        eggHatchingLogic.hatchEgg(mockedCards.get(0), false, currentPlayer);
        collectAndHatchAnyUnclaimedEggEffect.on(gui).on(currentPlayer).on(hatchingGround).on(eggHatchingLogic);

        EasyMock.replay(currentPlayer, hatchingGround, gui, elementSpace, eggHatchingLogic);

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
