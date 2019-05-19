package underlings.effect.players;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import underlings.MockTest;
import underlings.card.effect.Effect;
import underlings.card.effect.wild.RemoveBlackRecipesEffect;
import underlings.element.ElementColor;
import underlings.element.utilities.ElementSpaceLogic;
import underlings.player.Player;
import underlings.utilities.LocaleWrap;

public class RemoveBlackTests extends MockTest {

    @Test
    public void testEffect() {
        List<ElementSpaceLogic> logics = this.mockListOf(ElementSpaceLogic.class).withLengthOf(4);
        List<Player> players = this.mockListOf(Player.class).withLengthOf(4);
        for (int i = 0; i < 4; i++) {
            logics.get(i).resetRecipes(ElementColor.BLACK);
            players.get(i).elementSpaceLogic = logics.get(i);
        }

        this.replayAll();

        RemoveBlackRecipesEffect effect = new RemoveBlackRecipesEffect();
        effect.on(players).apply();
    }

    @Test
    public void testToString() {
        Effect effect = new RemoveBlackRecipesEffect();
        assertEquals(LocaleWrap.get("remove_black_recipes_effect"), effect.toString());
    }
}
