package underlings.phase;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import java.io.IOException;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import underlings.MockTest;
import underlings.gui.Gui;
import underlings.gui.Gui.PromptType;
import underlings.player.FakePlayer;
import underlings.utilities.LocaleWrap;

public class WildFinalPhaseTests extends MockTest {

    @Before
    public void init() {
        this.gui = this.mock(Gui.class);
    }

    @Test
    public void testRunWildFinalPhase() throws IOException {

        this.gui.alert(LocaleWrap.get("wild_game_over"), PromptType.ERROR);

        FinalPhase finalPhase = new WildFinalPhase(this.gui);
        List<String> recipes = Resources.readLines(Resources.getResource("DefaultRecipeList.txt"), Charsets.UTF_8);
        FakePlayer.initPlayer(recipes);

        this.replayAll();

        finalPhase.execute();
        finalPhase.turn(FakePlayer.getInstance());

    }

}
