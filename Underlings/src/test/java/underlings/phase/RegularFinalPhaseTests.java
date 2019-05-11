package underlings.phase;

import javax.swing.JOptionPane;

import org.easymock.EasyMock;
import org.junit.Test;

import underlings.gui.ConcretePrompt;
import underlings.gui.Gui;
import underlings.player.FakePlayer;

public class RegularFinalPhaseTests {

    @Test
    public void testRunRegularFinalPhase() {
        Gui gui = EasyMock.mock(Gui.class);
        gui.promptHandler = EasyMock.mock(ConcretePrompt.class);

        FinalPhase finalPhase = new WildFinalPhase(gui);
        gui.promptHandler.displayMessage("All eggs hatched wild, the game wins", -1, JOptionPane.PLAIN_MESSAGE);

        EasyMock.replay(gui, gui.promptHandler);

        finalPhase.execute();
        finalPhase.turn(FakePlayer.getInstance());

        EasyMock.verify(gui, gui.promptHandler);
    }

}
