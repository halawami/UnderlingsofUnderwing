package underlings.phase;

import java.util.Arrays;
import org.easymock.EasyMock;
import org.junit.Test;
import underlings.gui.Gui;
import underlings.gui.Gui.PromptType;
import underlings.player.Player;
import underlings.scoring.ScoreUtils;

public class RegularFinalPhaseTests {

    @Test
    public void testRunRegularFinalPhase() {
        Gui gui = EasyMock.mock(Gui.class);
        Player player = EasyMock.mock(Player.class);
        Phase dragonPhase = EasyMock.mock(DragonPhase.class);
        ScoreUtils scoreUtils = EasyMock.mock(ScoreUtils.class);

        dragonPhase.setup();
        gui.alert("Game Over!", PromptType.WARNING);
        scoreUtils.calculateScores();
        scoreUtils.displayScores();
        scoreUtils.displayWinners();
        dragonPhase.turn(player);
        EasyMock.expectLastCall().times(4);

        EasyMock.replay(gui, player, dragonPhase, scoreUtils);

        FinalPhase finalPhase = new RegularFinalPhase(Arrays.asList(player), gui, dragonPhase, scoreUtils);
        finalPhase.execute();
        finalPhase.turn(player);

        EasyMock.verify(gui, player, dragonPhase, scoreUtils);
    }

}
