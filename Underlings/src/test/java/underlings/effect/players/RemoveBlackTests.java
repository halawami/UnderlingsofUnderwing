package underlings.effect.players;

import java.util.Arrays;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Test;

import underlings.card.effect.wild.RemoveBlackRecipes;
import underlings.element.ElementColor;
import underlings.element.utilities.ElementSpaceLogic;
import underlings.player.Player;

public class RemoveBlackTests {

    @Test
    public void testEffect() {
        ElementSpaceLogic logic1 = EasyMock.mock(ElementSpaceLogic.class);
        logic1.resetRecipes(ElementColor.BLACK);
        Player player1 = EasyMock.mock(Player.class);
        player1.elementSpaceLogic = logic1;

        ElementSpaceLogic logic2 = EasyMock.mock(ElementSpaceLogic.class);
        logic2.resetRecipes(ElementColor.BLACK);
        Player player2 = EasyMock.mock(Player.class);
        player2.elementSpaceLogic = logic2;

        ElementSpaceLogic logic3 = EasyMock.mock(ElementSpaceLogic.class);
        logic3.resetRecipes(ElementColor.BLACK);
        Player player3 = EasyMock.mock(Player.class);
        player3.elementSpaceLogic = logic3;

        ElementSpaceLogic logic4 = EasyMock.mock(ElementSpaceLogic.class);
        logic4.resetRecipes(ElementColor.BLACK);
        Player player4 = EasyMock.mock(Player.class);
        player4.elementSpaceLogic = logic4;

        EasyMock.replay(player1, player2, player3, player4);
        EasyMock.replay(logic1, logic2, logic3, logic4);

        List<Player> players = Arrays.asList(player1, player2, player3, player4);
        new RemoveBlackRecipes().on(players).apply();

        EasyMock.verify(player1, player2, player3, player4);
        EasyMock.verify(logic1, logic2, logic3, logic4);
    }
}
