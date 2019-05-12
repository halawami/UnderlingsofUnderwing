package underlings.phase;

import java.util.Arrays;

import javax.swing.JOptionPane;

import org.easymock.EasyMock;
import org.junit.Test;

import underlings.gui.ConcretePrompt;
import underlings.gui.Gui;
import underlings.player.Player;
import underlings.scoring.ScoreUtils;

public class RegularFinalPhaseTests {

    @Test
    public void testRunRegularFinalPhase() {
        Gui gui = EasyMock.mock(Gui.class);
        gui.promptHandler = EasyMock.mock(ConcretePrompt.class);
        Player player = EasyMock.mock(Player.class);
        Phase dragonPhase = EasyMock.mock(DragonPhase.class);
        ScoreUtils scoreUtils = EasyMock.mock(ScoreUtils.class);

        FinalPhase finalPhase = new RegularFinalPhase(Arrays.asList(player), gui, dragonPhase, scoreUtils);
        dragonPhase.setup();
        finalPhase.turn(player);
        gui.promptHandler.displayMessage("Game Over!", 0, JOptionPane.PLAIN_MESSAGE);
        scoreUtils.calculateScores();
        scoreUtils.displayScores();
        scoreUtils.displayWinners();
        dragonPhase.turn(player);
        EasyMock.expectLastCall().times(2);

        EasyMock.replay(gui, gui.promptHandler, player, dragonPhase, scoreUtils);

        finalPhase.execute();
        finalPhase.turn(player);

        EasyMock.verify(gui, gui.promptHandler, player, dragonPhase, scoreUtils);
    }

}
