package underlings.phase;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

import java.io.IOException;
import java.util.List;

import javax.swing.JOptionPane;

import org.easymock.EasyMock;
import org.junit.Test;

import underlings.gui.ConcretePrompt;
import underlings.gui.Gui;
import underlings.player.FakePlayer;

public class WildFinalPhaseTests {

    @Test
    public void testRunWildFinalPhase() throws IOException {
        Gui gui = EasyMock.mock(Gui.class);
        gui.promptHandler = EasyMock.mock(ConcretePrompt.class);
        gui.promptHandler.displayMessage("All eggs hatched wild, the game wins", -1, JOptionPane.PLAIN_MESSAGE);

        EasyMock.replay(gui, gui.promptHandler);

        FinalPhase finalPhase = new WildFinalPhase(gui);
        finalPhase.execute();
        List<String> recipes = Resources.readLines(Resources.getResource("DefaultRecipeList.txt"), Charsets.UTF_8);
        FakePlayer.initPlayer(recipes);
        finalPhase.turn(FakePlayer.getInstance());

        EasyMock.verify(gui, gui.promptHandler);
    }

}
