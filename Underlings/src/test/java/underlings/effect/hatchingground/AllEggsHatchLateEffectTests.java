package underlings.effect.hatchingground;

import static org.junit.Assert.assertTrue;

import org.easymock.EasyMock;
import org.junit.Test;

import underlings.card.effect.wild.alleggsinplay.AllEggsHatchLateEffect;
import underlings.game.HatchingGround;
import underlings.gui.Gui;

public class AllEggsHatchLateEffectTests {

    @Test
    public void testApply() {
        HatchingGround hatchingGround = EasyMock.mock(HatchingGround.class);
        Gui gui = EasyMock.mock(Gui.class);
        AllEggsHatchLateEffect effect = new AllEggsHatchLateEffect();
        effect.on(hatchingGround).on(gui);
        EasyMock.replay(hatchingGround, gui);

        effect.apply();

        EasyMock.verify(hatchingGround, gui);
        assertTrue(hatchingGround.lateHatching);
    }

}
