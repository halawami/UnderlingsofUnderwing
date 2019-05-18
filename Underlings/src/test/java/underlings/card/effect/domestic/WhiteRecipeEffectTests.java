package underlings.card.effect.domestic;

import static org.junit.Assert.assertEquals;

import org.easymock.EasyMock;
import org.junit.Test;

import underlings.card.effect.Effect;
import underlings.element.ElementColor;
import underlings.element.utilities.ElementSpaceLogic;
import underlings.game.HatchingGround;
import underlings.player.Player;
import underlings.utilities.LocaleWrap;

public class WhiteRecipeEffectTests {

    String recipe000 = "RED,YELLOW,BLUE,PURPLE,ORANGE,GREEN";
    String recipe001 = "RED,YELLOW,BLUE,PURPLE,ORANGE,BLUE,YELLOW";
    String recipe010 = "RED,YELLOW,BLUE,PURPLE,RED,YELLOW,GREEN";
    String recipe011 = "RED,YELLOW,BLUE,PURPLE,RED,YELLOW,BLUE,YELLOW";
    String recipe100 = "RED,YELLOW,BLUE,RED,BLUE,ORANGE,GREEN";
    String recipe101 = "RED,YELLOW,BLUE,RED,BLUE,ORANGE,BLUE,YELLOW";
    String recipe110 = "RED,YELLOW,BLUE,RED,BLUE,RED,YELLOW,GREEN";
    String recipe111 = "RED,YELLOW,BLUE,RED,BLUE,RED,YELLOW,BLUE,YELLOW";

    @Test
    public void testEffect() {
        HatchingGround ground = EasyMock.mock(HatchingGround.class);
        ground.logic = EasyMock.mock(ElementSpaceLogic.class);
        Player player = EasyMock.mock(Player.class);
        player.elementSpaceLogic = EasyMock.mock(ElementSpaceLogic.class);


        ground.logic.addRecipe(ElementColor.WHITE, recipe000.split(","));
        ground.logic.addRecipe(ElementColor.WHITE, recipe001.split(","));
        ground.logic.addRecipe(ElementColor.WHITE, recipe010.split(","));
        ground.logic.addRecipe(ElementColor.WHITE, recipe011.split(","));
        ground.logic.addRecipe(ElementColor.WHITE, recipe100.split(","));
        ground.logic.addRecipe(ElementColor.WHITE, recipe101.split(","));
        ground.logic.addRecipe(ElementColor.WHITE, recipe110.split(","));
        ground.logic.addRecipe(ElementColor.WHITE, recipe111.split(","));

        player.elementSpaceLogic.addRecipe(ElementColor.WHITE, recipe000.split(","));
        player.elementSpaceLogic.addRecipe(ElementColor.WHITE, recipe001.split(","));
        player.elementSpaceLogic.addRecipe(ElementColor.WHITE, recipe010.split(","));
        player.elementSpaceLogic.addRecipe(ElementColor.WHITE, recipe011.split(","));
        player.elementSpaceLogic.addRecipe(ElementColor.WHITE, recipe100.split(","));
        player.elementSpaceLogic.addRecipe(ElementColor.WHITE, recipe101.split(","));
        player.elementSpaceLogic.addRecipe(ElementColor.WHITE, recipe110.split(","));
        player.elementSpaceLogic.addRecipe(ElementColor.WHITE, recipe111.split(","));

        EasyMock.replay(ground, ground.logic, player, player.elementSpaceLogic);

        Effect effect = new WhiteRecipeEffect();
        effect.on(ground).on(player).apply();

        EasyMock.verify(ground, ground.logic, player, player.elementSpaceLogic);
    }

    @Test
    public void testToStringDestroy() {
        WhiteRecipeEffect effect = new WhiteRecipeEffect();
        assertEquals(LocaleWrap.get("combine_primary_secondary_for_white"), effect.toString());
    }

}
