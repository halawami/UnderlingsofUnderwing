package underlings.phase;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import java.io.IOException;
import java.util.List;
import org.easymock.EasyMock;
import org.junit.Test;
import underlings.gui.Gui;
import underlings.gui.Gui.PromptType;
import underlings.player.FakePlayer;

public class WildFinalPhaseTests {

    @Test
    public void testRunWildFinalPhase() throws IOException {
        Gui gui = EasyMock.mock(Gui.class);
        gui.alert("All eggs hatched wild, the game wins", PromptType.ERROR);

        FinalPhase finalPhase = new WildFinalPhase(gui);
        finalPhase.execute();
        List<String> recipes = Resources.readLines(Resources.getResource("DefaultRecipeList.txt"), Charsets.UTF_8);
        FakePlayer.initPlayer(recipes);
        finalPhase.turn(FakePlayer.getInstance());

    }

}
