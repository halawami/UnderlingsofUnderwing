package underlings.effect.hatchingground;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.easymock.EasyMock;
import org.junit.Test;

import underlings.MockTest;
import underlings.card.Card;
import underlings.card.effect.AllEggsInPlayEffect;
import underlings.card.effect.wild.alleggsinplay.AddElementToAllEggsInPlayEffect;
import underlings.card.effect.wild.alleggsinplay.DestroyAllElementsOnAllEggsInPlayEffect;
import underlings.element.Element;
import underlings.element.ElementBag;
import underlings.element.ElementColor;
import underlings.element.ElementSpace;
import underlings.gui.Gui;
import underlings.handler.Handler;
import underlings.handler.HandlerMovementLogic;
import underlings.hatchingground.HatchingGround;
import underlings.player.Player;
import underlings.utilities.EggHatchingUtilities;
import underlings.utilities.ElementSpaceUtilities;
import underlings.utilities.LocaleUtilities;

public class AllEggsInPlayEffectTests extends MockTest {

    @Test
    public void testApplyOnNoEggInPlay() {
        this.testApplyOnCardInPlay(0);
    }

    @Test
    public void testApplyOnOneEggInPlay() {
        this.testApplyOnCardInPlay(1);
    }

    @Test
    public void testApplyOnEggsInPlay() {
        this.testApplyOnCardInPlay(2);
    }

    private void testApplyOnCardInPlay(int numberOfCards) {
        ElementBag elementBag = this.mock(ElementBag.class);
        ElementSpaceUtilities elementSpaceLogic = this.mock(ElementSpaceUtilities.class);
        Card centerCard = this.mock(Card.class);
        HatchingGround hatchingGround = this.mock(HatchingGround.class);
        Gui gui = this.mock(Gui.class);
        Player player = new Player(0, null, 0);
        player.elementSpaceLogic = elementSpaceLogic;
        List<Card> mockedCards = this.mockListOf(Card.class).withLengthOf(numberOfCards);
        EggHatchingUtilities eggHatchingLogic = this.mock(EggHatchingUtilities.class);
        AllEggsInPlayEffect effect = EasyMock.partialMockBuilder(AllEggsInPlayEffect.class)
                .addMockedMethod("applyOnCardInPlay", Card.class)
                .addMockedMethod("applyOnCardInPlay", Card.class, ElementSpaceUtilities.class, ElementBag.class,
                        HandlerMovementLogic.class)
                .createMock();
        this.addMock(effect);

        EasyMock.expect(hatchingGround.getAllCards()).andReturn(mockedCards);
        for (Card mockedCard : mockedCards) {
            effect.applyOnCardInPlay(mockedCard);
            effect.applyOnCardInPlay(mockedCard, elementSpaceLogic, elementBag, null);

        }

        this.replayAll();

        effect.on(elementBag).on(centerCard).on(hatchingGround).on(gui).on(eggHatchingLogic).on(player).apply();
    }

    @Test
    public void testAddElementsToCardNoPlayableSpaces() {
        this.testAddElementsToCardWithPlayableSpace(0);
    }

    @Test
    public void testAddElementsToCardOnePlayableSpace() {
        this.testAddElementsToCardWithPlayableSpace(1);
    }

    @Test
    public void testAddElementsToCardEightPlayableSpace() {
        this.testAddElementsToCardWithPlayableSpace(8);
    }


    private void testAddElementsToCardWithPlayableSpace(int numberOfPlayableSpaces) {
        ElementColor blue = ElementColor.BLUE;
        Card mockedCard = this.mock(Card.class);
        ElementSpaceUtilities elementSpaceLogic = this.mock(ElementSpaceUtilities.class);
        ElementBag elementBag = this.mock(ElementBag.class);
        Element stubElement = this.mock(Element.class);
        List<ElementSpace> mockedPlayableSpaces = this.mockListOf(ElementSpace.class)
                .withLengthOf(numberOfPlayableSpaces);

        EasyMock.expect(elementSpaceLogic.getPlayableSpaces(mockedCard, blue)).andReturn(mockedPlayableSpaces);

        for (ElementSpace mockSpace : mockedPlayableSpaces) {
            EasyMock.expect(elementBag.drawElementFromList(blue)).andReturn(stubElement);
            mockSpace.addElements(stubElement);
        }

        this.replayAll();

        AddElementToAllEggsInPlayEffect effect = new AddElementToAllEggsInPlayEffect();
        effect.elementColor = ElementColor.BLUE;
        effect.applyOnCardInPlay(mockedCard, elementSpaceLogic, elementBag, null);
    }

    @Test
    public void testDestroyAllElementsOnUnclaimedCard() {
        Card cardInPlay = new Card();
        List<ElementSpace> mockSpaces = this.mockListOf(ElementSpace.class).withLengthOf(8);
        cardInPlay.elementSpaces = mockSpaces.toArray(new ElementSpace[8]);
        ElementSpaceUtilities elementSpaceLogic = this.mock(ElementSpaceUtilities.class);
        ElementBag elementBag = this.mock(ElementBag.class);

        this.replayAll();

        DestroyAllElementsOnAllEggsInPlayEffect effect = new DestroyAllElementsOnAllEggsInPlayEffect();
        effect.applyOnCardInPlay(cardInPlay, elementSpaceLogic, elementBag, null);
    }

    @Test
    public void testDestroyAllElementsOnClaimedCard() {
        Card cardInPlay = new Card();
        cardInPlay.handler = this.mock(Handler.class);
        List<ElementSpace> mockSpaces = this.mockListOf(ElementSpace.class).withLengthOf(8);
        cardInPlay.elementSpaces = mockSpaces.toArray(new ElementSpace[8]);

        mockSpaces.forEach(ElementSpace::destroyAllElements);

        this.replayAll();

        DestroyAllElementsOnAllEggsInPlayEffect effect = new DestroyAllElementsOnAllEggsInPlayEffect();
        effect.applyOnCardInPlay(cardInPlay);
    }

    @Test
    public void testToStringDestroy() {
        DestroyAllElementsOnAllEggsInPlayEffect effect = new DestroyAllElementsOnAllEggsInPlayEffect();
        assertEquals(LocaleUtilities.get("destroy_all_elements_on_all_eggs_effect"), effect.toString());
    }

    @Test
    public void testToStringPlace() {
        AddElementToAllEggsInPlayEffect effect = new AddElementToAllEggsInPlayEffect();
        effect.elementColor = ElementColor.BLACK;
        assertEquals(LocaleUtilities.format("place_element_on_all_eggs_effect", effect.elementColor),
                effect.toString());
    }

}
