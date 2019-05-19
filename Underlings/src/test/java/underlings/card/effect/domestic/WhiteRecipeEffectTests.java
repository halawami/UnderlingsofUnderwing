package underlings.card.effect.domestic;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import underlings.MockTest;
import underlings.card.effect.Effect;
import underlings.card.effect.domestic.playerhatchingground.WhiteRecipeEffect;
import underlings.element.ElementColor;
import underlings.hatchingground.HatchingGround;
import underlings.player.Player;
import underlings.utilities.ElementSpaceUtilities;
import underlings.utilities.LocaleUtilities;

public class WhiteRecipeEffectTests extends MockTest {

    String recipe000 = "RED,YELLOW,BLUE,PURPLE,ORANGE,GREEN";
    String recipe001 = "RED,YELLOW,BLUE,PURPLE,ORANGE,BLUE,YELLOW";
    String recipe010 = "RED,YELLOW,BLUE,PURPLE,RED,YELLOW,GREEN";
    String recipe011 = "RED,YELLOW,BLUE,PURPLE,RED,YELLOW,BLUE,YELLOW";
    String recipe100 = "RED,YELLOW,BLUE,RED,BLUE,ORANGE,GREEN";
    String recipe101 = "RED,YELLOW,BLUE,RED,BLUE,ORANGE,BLUE,YELLOW";
    String recipe110 = "RED,YELLOW,BLUE,RED,BLUE,RED,YELLOW,GREEN";
    String recipe111 = "RED,YELLOW,BLUE,RED,BLUE,RED,YELLOW,BLUE,YELLOW";

    @Before
    public void init() {
        this.hatchingGround = this.mock(HatchingGround.class);
        this.player = this.mock(Player.class);
    }

    @Test
    public void testEffect() {
        this.hatchingGround.logic = this.mock(ElementSpaceUtilities.class);
        this.player.elementSpaceLogic = this.mock(ElementSpaceUtilities.class);

        this.hatchingGround.logic.addRecipe(ElementColor.WHITE, this.recipe000.split(","));
        this.hatchingGround.logic.addRecipe(ElementColor.WHITE, this.recipe001.split(","));
        this.hatchingGround.logic.addRecipe(ElementColor.WHITE, this.recipe010.split(","));
        this.hatchingGround.logic.addRecipe(ElementColor.WHITE, this.recipe011.split(","));
        this.hatchingGround.logic.addRecipe(ElementColor.WHITE, this.recipe100.split(","));
        this.hatchingGround.logic.addRecipe(ElementColor.WHITE, this.recipe101.split(","));
        this.hatchingGround.logic.addRecipe(ElementColor.WHITE, this.recipe110.split(","));
        this.hatchingGround.logic.addRecipe(ElementColor.WHITE, this.recipe111.split(","));

        this.player.elementSpaceLogic.addRecipe(ElementColor.WHITE, this.recipe000.split(","));
        this.player.elementSpaceLogic.addRecipe(ElementColor.WHITE, this.recipe001.split(","));
        this.player.elementSpaceLogic.addRecipe(ElementColor.WHITE, this.recipe010.split(","));
        this.player.elementSpaceLogic.addRecipe(ElementColor.WHITE, this.recipe011.split(","));
        this.player.elementSpaceLogic.addRecipe(ElementColor.WHITE, this.recipe100.split(","));
        this.player.elementSpaceLogic.addRecipe(ElementColor.WHITE, this.recipe101.split(","));
        this.player.elementSpaceLogic.addRecipe(ElementColor.WHITE, this.recipe110.split(","));
        this.player.elementSpaceLogic.addRecipe(ElementColor.WHITE, this.recipe111.split(","));

        this.replayAll();

        Effect effect = new WhiteRecipeEffect();
        effect.on(this.hatchingGround).on(this.player).apply();
    }

    @Test
    public void testToStringDestroy() {
        this.replayAll();
        WhiteRecipeEffect effect = new WhiteRecipeEffect();
        assertEquals(LocaleUtilities.get("combine_primary_secondary_for_white"), effect.toString());
    }

}
