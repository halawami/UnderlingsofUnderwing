package underlings.phase;

import java.util.Arrays;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import underlings.MockTest;
import underlings.gui.Gui;
import underlings.gui.Gui.PromptType;
import underlings.player.Player;
import underlings.scoring.ScoreUtils;

public class RegularFinalPhaseTests extends MockTest {

    @Before
    public void init() {
        gui = mock(Gui.class);
        player = mock(Player.class);
        dragonPhase = mock(DragonPhase.class);
        scoreUtils = mock(ScoreUtils.class);
    }

    @Test
    public void testRunRegularFinalPhase() {
        dragonPhase.setup();
        gui.alert("Game Over!", PromptType.WARNING);
        scoreUtils.calculateScores();
        scoreUtils.displayScores();
        scoreUtils.displayWinners();
        dragonPhase.turn(player);
        EasyMock.expectLastCall().times(4);

        replayAll();

        FinalPhase finalPhase = new RegularFinalPhase(Arrays.asList(player), gui, dragonPhase, scoreUtils);
        finalPhase.execute();
        finalPhase.turn(player);
    }

}
