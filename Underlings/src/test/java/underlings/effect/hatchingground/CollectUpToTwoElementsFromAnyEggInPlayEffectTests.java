package underlings.effect.hatchingground;

import java.util.ArrayList;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Test;

import underlings.card.Card;
import underlings.card.effect.domestic.CollectUpToTwoElementsFromAnyEggInPlayEffect;
import underlings.element.ElementColor;
import underlings.element.ElementSpace;
import underlings.element.NullElement;
import underlings.game.HatchingGround;
import underlings.gui.Gui;
import underlings.player.Player;

public class CollectUpToTwoElementsFromAnyEggInPlayEffectTests {

    @Test
    public void testNoElementsPicked() {
        Player currentPlayer = EasyMock.mock(Player.class);
        HatchingGround hatchingGround = EasyMock.mock(HatchingGround.class);
        List<Card> mockedCards = getMockedCards(6);
        Gui gui = EasyMock.mock(Gui.class);
        ElementSpace elementSpace = EasyMock.mock(ElementSpace.class);
        CollectUpToTwoElementsFromAnyEggInPlayEffect testedEffect = new CollectUpToTwoElementsFromAnyEggInPlayEffect();
        testedEffect.elementChoices = new ElementColor[]{ElementColor.BLUE};
        testedEffect.on(gui).on(currentPlayer).on(hatchingGround);

        EasyMock.expect(hatchingGround.getAllCards()).andReturn(mockedCards);
        EasyMock.expect(gui.getElementSpaceContainingElementOfColors(mockedCards, testedEffect.elementChoices))
                .andReturn(elementSpace).times(2);
        EasyMock.expect(gui.getElementOfColorsFromSpace(testedEffect.elementChoices, elementSpace))
                .andReturn(NullElement.getInstance()).times(2);

        currentPlayer.addElement(NullElement.getInstance());
        elementSpace.destroyOneElementOfColor(NullElement.getInstance().getColor());
        currentPlayer.addElement(NullElement.getInstance());
        elementSpace.destroyOneElementOfColor(NullElement.getInstance().getColor());

        EasyMock.replay(currentPlayer, hatchingGround, gui, elementSpace);

        testedEffect.apply();

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
