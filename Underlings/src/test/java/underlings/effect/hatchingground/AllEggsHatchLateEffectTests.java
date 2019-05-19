package underlings.effect.hatchingground;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import underlings.MockTest;
import underlings.card.effect.Effect;
import underlings.card.effect.wild.AllEggsHatchLateEffect;
import underlings.game.HatchingGround;
import underlings.gui.Gui;
import underlings.utilities.LocaleWrap;

public class AllEggsHatchLateEffectTests extends MockTest {

    @Test
    public void testApply() {
        HatchingGround hatchingGround = this.mock(HatchingGround.class);
        Gui gui = this.mock(Gui.class);
        AllEggsHatchLateEffect effect = new AllEggsHatchLateEffect();

        this.replayAll();

        effect.on(hatchingGround).on(gui).apply();

        assertTrue(hatchingGround.lateHatching);
    }

    @Test
    public void testToString() {
        Effect effect = new AllEggsHatchLateEffect();
        assertEquals(LocaleWrap.get("egg_hatches_late_effect"), effect.toString());
    }
}
