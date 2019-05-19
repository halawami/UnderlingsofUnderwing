package underlings.effect.players;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
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
        ElementSpaceLogic logic1 = this.mock(ElementSpaceLogic.class);
        logic1.resetRecipes(ElementColor.BLACK);
        Player player1 = this.mock(Player.class);
        player1.elementSpaceLogic = logic1;

        ElementSpaceLogic logic2 = this.mock(ElementSpaceLogic.class);
        logic2.resetRecipes(ElementColor.BLACK);
        Player player2 = this.mock(Player.class);
        player2.elementSpaceLogic = logic2;

        ElementSpaceLogic logic3 = this.mock(ElementSpaceLogic.class);
        logic3.resetRecipes(ElementColor.BLACK);
        Player player3 = this.mock(Player.class);
        player3.elementSpaceLogic = logic3;

        ElementSpaceLogic logic4 = this.mock(ElementSpaceLogic.class);
        logic4.resetRecipes(ElementColor.BLACK);
        Player player4 = this.mock(Player.class);
        player4.elementSpaceLogic = logic4;

        this.replayAll();

        List<Player> players = Arrays.asList(player1, player2, player3, player4);
        new RemoveBlackRecipesEffect().on(players).apply();
    }

    @Test
    public void testToString() {
        Effect effect = new RemoveBlackRecipesEffect();
        assertEquals(LocaleWrap.get("remove_black_recipes_effect"), effect.toString());
    }
}
