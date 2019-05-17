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
import underlings.utilities.LocaleWrap;

public class WildFinalPhaseTests {

    @Test
    public void testRunWildFinalPhase() throws IOException {
        Gui gui = EasyMock.mock(Gui.class);
        gui.alert(LocaleWrap.get("wild_game_over"), PromptType.ERROR);

        FinalPhase finalPhase = new WildFinalPhase(gui);
        List<String> recipes = Resources.readLines(Resources.getResource("DefaultRecipeList.txt"), Charsets.UTF_8);
        FakePlayer.initPlayer(recipes);

        EasyMock.replay(gui);

        finalPhase.execute();
        finalPhase.turn(FakePlayer.getInstance());

        EasyMock.verify(gui);
    }

}
