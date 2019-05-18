package underlings.gui;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.JOptionPane;
import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import underlings.card.Card;
import underlings.card.EmptyCard;
import underlings.element.ElementBag;
import underlings.element.ElementFactory;
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
    public void testDisplayCardSetupTwoPlayers() {
        this.game.setUp(2);

        EasyMock.expect(this.promptHandler.promptInt("Enter Player Count [2, 6]", 2, 6)).andStubReturn(2);
        this.display.displayCard(EasyMock.anyInt(), EasyMock.anyInt(), EasyMock.anyObject(Card.class));
        EasyMock.expectLastCall().times(6);

        EasyMock.replay(this.promptHandler, this.display);
        this.gui.displayHatchingGround(this.hatchingGround);
        EasyMock.verify(this.promptHandler, this.display);
    }

    @Test
    public void testDisplayCardSetupThreePlayers() {
        this.game.setUp(3);

        EasyMock.expect(this.promptHandler.promptInt("Enter Player Count [2, 6]", 2, 6)).andStubReturn(2);
        this.display.displayCard(EasyMock.anyInt(), EasyMock.anyInt(), EasyMock.anyObject(Card.class));
        EasyMock.expectLastCall().times(12);

        EasyMock.replay(this.promptHandler, this.display);
        this.gui.displayHatchingGround(this.hatchingGround);
        EasyMock.verify(this.promptHandler, this.display);
    }

    @Test
    public void testDisplayCardSetupFourPlayers() {
        this.game.setUp(4);

        EasyMock.expect(this.promptHandler.promptInt("Enter Player Count [2, 6]", 2, 6)).andStubReturn(4);
        this.display.displayCard(EasyMock.anyInt(), EasyMock.anyInt(), EasyMock.anyObject(Card.class));
        EasyMock.expectLastCall().times(16);

        EasyMock.replay(this.promptHandler, this.display);
        this.gui.displayHatchingGround(this.hatchingGround);
        EasyMock.verify(this.promptHandler, this.display);
    }

    @Test
    public void testDisplayCardSetupSixPlayers() {
        this.game.setUp(6);

        EasyMock.expect(this.promptHandler.promptInt("Enter Player Count [2, 6]", 2, 6)).andStubReturn(6);
        this.display.displayCard(EasyMock.anyInt(), EasyMock.anyInt(), EasyMock.anyObject(Card.class));
        EasyMock.expectLastCall().times(16);

        EasyMock.replay(this.promptHandler, this.display);
        this.gui.displayHatchingGround(this.hatchingGround);
        EasyMock.verify(this.promptHandler, this.display);
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

}
