package underlings.gui;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import javax.swing.JOptionPane;

import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import underlings.card.Card;
import underlings.card.EmptyCard;
import underlings.element.Element;
import underlings.element.ElementBag;
import underlings.element.ElementColor;
import underlings.element.ElementFactory;
import underlings.element.ElementSpace;
import underlings.element.NullElement;
import underlings.element.utilities.ElementSpaceLogic;
import underlings.game.Deck;
import underlings.game.Game;
import underlings.game.HatchingGround;
import underlings.gui.Gui.PromptType;
import underlings.handler.HandlerFactory;
import underlings.player.PlayerFactory;
import underlings.utilities.LocaleWrap;

public class GuiTests {

    private Game game;
    private HatchingGround hatchingGround;
    private Gui gui;
    private Deck deck;
    private PromptHandler promptHandler;
    private Display display;

    @Before
    public void init() throws Exception {
        this.promptHandler = EasyMock.mock(PromptHandler.class);
        this.display = EasyMock.mock(Display.class);
        this.gui = new Gui(this.promptHandler, this.display);

        this.deck = EasyMock.mock(Deck.class);
        EasyMock.expect(this.deck.draw()).andStubReturn(new Card());
        EasyMock.replay(this.deck);

        List<String> recipes = Resources.readLines(Resources.getResource("DefaultRecipeList.txt"), Charsets.UTF_8);

        ElementSpaceLogic logic = EasyMock.niceMock(ElementSpaceLogic.class);
        EasyMock.replay(logic);
        this.hatchingGround = new HatchingGround(this.deck, logic);
        this.game = new Game(this.gui, this.hatchingGround, new PlayerFactory(new HandlerFactory(), recipes),
                new ElementBag(new ElementFactory(), new Random()));
    }

    private void replay() {
        EasyMock.replay(this.promptHandler, this.display);
    }

    @After
    public void verify() {
        EasyMock.verify(this.promptHandler, this.display);
    }

    @Test
    public void testGetPlayerCountTwoPlayers() {
        EasyMock.expect(this.promptHandler.promptInt("Enter Player Count", 2, 6)).andReturn(2);

        EasyMock.replay(this.promptHandler, this.display);
        this.game.promptPlayerCount();

        assertEquals(2, this.game.numberOfPlayers);

    }

    @Test
    public void testGetPlayerCountSixPlayers() {
        EasyMock.expect(this.promptHandler.promptInt("Enter Player Count", 2, 6)).andReturn(6);

        EasyMock.replay(this.promptHandler, this.display);
        this.game.promptPlayerCount();

        assertEquals(6, this.game.numberOfPlayers);
    }

    @Test
    public void testNotify() {
        this.promptHandler.displayMessage("test", 0, JOptionPane.PLAIN_MESSAGE);
        this.replay();

        this.gui.notifyAction(0, "test");
    }

    @Test
    public void testAlertNoPlayerID() {
        this.promptHandler.displayMessage("test", JOptionPane.PLAIN_MESSAGE);
        this.replay();

        this.gui.alert("test", PromptType.REGULAR);
    }

    @Test
    public void testAlertRegular() {
        this.promptHandler.displayMessage("test", 1, JOptionPane.PLAIN_MESSAGE);
        this.replay();

        this.gui.alert("test", 1, PromptType.REGULAR);
    }

    @Test
    public void testAlertWarning() {
        this.promptHandler.displayMessage("test", 1, JOptionPane.WARNING_MESSAGE);
        this.replay();

        this.gui.alert("test", 1, PromptType.WARNING);
    }

    @Test
    public void testAlertError() {
        this.promptHandler.displayMessage("test", 1, JOptionPane.ERROR_MESSAGE);
        this.replay();

        this.gui.alert("test", 1, PromptType.ERROR);
    }

    @Test
    public void testPromptChoice() {
        EasyMock.expect(this.promptHandler.promptChoice(EasyMock.anyString(), EasyMock.anyObject(), EasyMock.anyInt()))
                .andReturn(1);
        this.replay();

        List<Integer> ints = new ArrayList<>();
        Integer result = this.gui.promptChoice("test", ints, 0);

        assertEquals(1, result.intValue());
    }


    @Test
    public void testChoiceYesToString() {
        this.replay();
        YesNoChoice choice = YesNoChoice.YES;
        assertEquals("Yes", choice.toString());
    }

    @Test
    public void testChoiceNoToString() {
        this.replay();
        YesNoChoice choice = YesNoChoice.NO;
        assertEquals("No", choice.toString());
    }

    @Test
    public void testChoiceYesBooleanValue() {
        this.replay();
        YesNoChoice choice = YesNoChoice.YES;
        assertTrue(choice.booleanValue);
    }

    @Test
    public void testChoiceNoBooleanValue() {
        this.replay();
        YesNoChoice choice = YesNoChoice.NO;
        assertFalse(choice.booleanValue);
    }

    @Test
    public void testReorderCard() {
        Card cardOne = new Card();
        Card cardTwo = new Card();
        List<Card> cardsToChoose = new ArrayList<>();
        cardsToChoose.add(cardOne);
        cardsToChoose.add(cardTwo);
        cardsToChoose.add(EmptyCard.getInstance());

        EasyMock.expect(this.promptHandler.promptChoice(LocaleWrap.get("choose_card"), cardsToChoose, 0))
                .andReturn(cardTwo);
        EasyMock.expect(this.promptHandler.promptChoice(LocaleWrap.get("choose_card"), cardsToChoose, 0))
                .andReturn(cardOne);

        this.replay();

        List<Card> choosen = this.gui.reorderCards(cardsToChoose);

        assertEquals(2, choosen.size());
        assertEquals(cardTwo, choosen.get(0));
        assertEquals(cardOne, choosen.get(1));
    }

    @Test
    public void testDisplay() {
        this.display.displayBackground();
        this.display.displayHatchingGround(this.hatchingGround);
        this.display.displayPlayers(EasyMock.anyObject());
        this.display.displayStats(EasyMock.anyObject(), EasyMock.anyInt(), EasyMock.anyInt(), EasyMock.anyInt());
        this.display.update();

        this.replay();

        this.gui.display(0, 0, 0, this.hatchingGround, Collections.emptyList(), EasyMock.mock(ElementBag.class));
    }

    @Test
    public void testPromptLocale() {
        EasyMock.expect(
                this.promptHandler.promptChoiceDropdown(EasyMock.anyString(), EasyMock.anyObject(), EasyMock.anyInt()))
                .andReturn(null);
        EasyMock.expect(this.promptHandler.promptChoiceDropdown(LocaleWrap.get("choose_language"),
                new ArrayList<Locale>(), Locale.ENGLISH)).andReturn(Locale.CANADA);

        this.replay();

        Locale locale = this.gui.promptLocale(new Locale[] {});

        assertEquals(Locale.CANADA, locale);

    }

    @Test
    public void testGetElementOfColorsFromSpaceNoChoices() {
        ElementColor[] choices = new ElementColor[0];
        ElementSpace space = new ElementSpace(ElementColor.BLACK);

        List<Element> elements = new ArrayList<>();
        elements.add(NullElement.getInstance());
        EasyMock.expect(this.promptHandler.promptChoice(LocaleWrap.get("gui_element_collect"), elements, 0))
                .andReturn(NullElement.getInstance());

        this.replay();

        Element element = this.gui.getElementOfColorsFromSpace(choices, space, 0);

        assertEquals(NullElement.getInstance(), element);

    }

    @Test
    public void testGetElementOfColorsFromSpace() {
        ElementColor[] choices = new ElementColor[2];
        choices[1] = ElementColor.RED;
        ElementSpace space = new ElementSpace(ElementColor.BLACK);
        space.addElements(new Element(ElementColor.RED));
        space.addElements(new Element(ElementColor.BLUE));

        List<Element> elements = new ArrayList<>();
        elements.add(space.elements.get(0));
        elements.add(NullElement.getInstance());
        EasyMock.expect(this.promptHandler.promptChoice(LocaleWrap.get("gui_element_collect"), elements, 0))
                .andReturn(NullElement.getInstance());

        this.replay();

        Element element = this.gui.getElementOfColorsFromSpace(choices, space, 0);

        assertEquals(NullElement.getInstance(), element);
    }

}
