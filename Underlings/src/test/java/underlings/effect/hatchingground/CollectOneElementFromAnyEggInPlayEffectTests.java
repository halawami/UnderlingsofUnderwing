package underlings.effect.hatchingground;

import java.util.ArrayList;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Test;

import underlings.card.Card;
import underlings.card.effect.domestic.CollectOneElementFromAnyEggInPlayEffect;
import underlings.element.Element;
import underlings.element.ElementColor;
import underlings.element.ElementSpace;
import underlings.element.NullElement;
import underlings.game.HatchingGround;
import underlings.gui.Gui;
import underlings.player.Player;

public class CollectOneElementFromAnyEggInPlayEffectTests {

    @Test
    public void testNoSelectableElement() {
        Player currentPlayer = EasyMock.mock(Player.class);
        EasyMock.expect(currentPlayer.getId()).andReturn(10).anyTimes();
        HatchingGround hatchingGround = EasyMock.mock(HatchingGround.class);
        List<Card> mockedCards = this.getMockedCards(6);
        Gui gui = EasyMock.mock(Gui.class);
        CollectOneElementFromAnyEggInPlayEffect testedEffect = new CollectOneElementFromAnyEggInPlayEffect();
        testedEffect.elementChoices = new ElementColor[] {ElementColor.BLUE};
        testedEffect.on(gui).on(currentPlayer).on(hatchingGround);

        EasyMock.expect(hatchingGround.getAllCards()).andReturn(mockedCards);
        ElementSpace elementSpace = EasyMock.mock(ElementSpace.class);
        EasyMock.expect(gui.getElementSpaceWithColors(mockedCards, testedEffect.elementChoices, 10))
                .andReturn(elementSpace);
        EasyMock.expect(gui.getElementOfColorsFromSpace(testedEffect.elementChoices, elementSpace, 10))
                .andReturn(NullElement.getInstance());
        elementSpace.destroyOneElementOfColor(ElementColor.NULL);
        currentPlayer.addElement(NullElement.getInstance());

        EasyMock.replay(currentPlayer, hatchingGround, gui, elementSpace);

        testedEffect.apply();

        EasyMock.verify(currentPlayer, hatchingGround, gui, elementSpace);

    }

    @Test
    public void testASelectableElement() {
        Player currentPlayer = EasyMock.mock(Player.class);
        EasyMock.expect(currentPlayer.getId()).andReturn(10).anyTimes();
        HatchingGround hatchingGround = EasyMock.mock(HatchingGround.class);
        List<Card> mockedCards = this.getMockedCards(6);
        Gui gui = EasyMock.mock(Gui.class);
        CollectOneElementFromAnyEggInPlayEffect testedEffect = new CollectOneElementFromAnyEggInPlayEffect();
        testedEffect.elementChoices = new ElementColor[] {ElementColor.BLUE};
        testedEffect.on(gui).on(currentPlayer).on(hatchingGround);

        EasyMock.expect(hatchingGround.getAllCards()).andReturn(mockedCards);
        ElementSpace elementSpace = EasyMock.mock(ElementSpace.class);
        EasyMock.expect(gui.getElementSpaceWithColors(mockedCards, testedEffect.elementChoices, 10))
                .andReturn(elementSpace);
        Element element = EasyMock.mock(Element.class);
        EasyMock.expect(gui.getElementOfColorsFromSpace(testedEffect.elementChoices, elementSpace, 10))
                .andReturn(element);
        EasyMock.expect(element.getColor()).andReturn(ElementColor.BLUE);
        elementSpace.destroyOneElementOfColor(ElementColor.BLUE);
        currentPlayer.addElement(element);

        EasyMock.replay(currentPlayer, hatchingGround, gui, elementSpace, element);

        testedEffect.apply();

        EasyMock.verify(currentPlayer, hatchingGround, gui, elementSpace, element);

    }


    private List<Card> getMockedCards(int numberOfCards) {
        List<Card> mockedCards = new ArrayList<>();
        for (int i = 0; i < numberOfCards; i++) {
            mockedCards.add(EasyMock.niceMock(Card.class));
        }
        return mockedCards;
    }

}
