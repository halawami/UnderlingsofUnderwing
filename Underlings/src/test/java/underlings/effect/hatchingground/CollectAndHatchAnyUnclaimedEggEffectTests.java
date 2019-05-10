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

public class CollectAndHatchAnyUnclaimedEggEffectTests {

    @Test
    public void testOneEggToHatch() {
        Player currentPlayer = EasyMock.mock(Player.class);
        HatchingGround hatchingGround = EasyMock.mock(HatchingGround.class);
        List<Card> mockedCards = this.getMockedCards(6);
        Gui gui = EasyMock.mock(Gui.class);
        ElementSpace elementSpace = EasyMock.mock(ElementSpace.class);
        CollectAndHatchAnyUnclaimedEggEffect collectAndHatchAnyUnclaimedEggEffect =
                new CollectAndHatchAnyUnclaimedEggEffect();
        mockedCards.get(0).points = 5;
        collectAndHatchAnyUnclaimedEggEffect.on(gui).on(currentPlayer).on(hatchingGround);
        EasyMock.expect(hatchingGround.getAllCards()).andReturn(mockedCards);

        EasyMock.replay(currentPlayer, hatchingGround, gui, elementSpace);

        collectAndHatchAnyUnclaimedEggEffect.apply();

        EasyMock.verify(currentPlayer, hatchingGround, gui, elementSpace);
    }

    private List<Card> getMockedCards(int numberOfCards) {
        List<Card> mockedCards = new ArrayList<>();
        for (int i = 0; i < numberOfCards; i++) {
            mockedCards.add(EasyMock.niceMock(Card.class));
        }
        return mockedCards;
    }
}
