package underlings.effect.hatchingground;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.easymock.EasyMock;
import org.junit.Test;

import underlings.card.effect.Effect;
import underlings.card.effect.wild.alleggsinplay.AllEggsHatchLateEffect;
import underlings.game.HatchingGround;
import underlings.gui.Gui;
import underlings.utilities.LocaleWrap;

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

    @Test
    public void testToString() {
        Effect effect = new AllEggsHatchLateEffect();
        assertEquals(LocaleWrap.get("egg_hatches_late_effect"), effect.toString());
    }
}
