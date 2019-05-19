package underlings.card.effect.domestic.playerhatchingground;

import java.util.ArrayList;
import java.util.List;
import underlings.element.ElementColor;
import underlings.gui.Gui;
import underlings.hatchingground.HatchingGround;
import underlings.player.Player;
import underlings.utilities.EggHatchingUtilities;
import underlings.utilities.LocaleUtilities;

public class WhiteRecipeEffect extends PlayerHatchingGroundEffect {

    private String recipe000 = "RED,YELLOW,BLUE,PURPLE,ORANGE,GREEN";
    private String recipe001 = "RED,YELLOW,BLUE,PURPLE,ORANGE,BLUE,YELLOW";
    private String recipe010 = "RED,YELLOW,BLUE,PURPLE,RED,YELLOW,GREEN";
    private String recipe011 = "RED,YELLOW,BLUE,PURPLE,RED,YELLOW,BLUE,YELLOW";
    private String recipe100 = "RED,YELLOW,BLUE,RED,BLUE,ORANGE,GREEN";
    private String recipe101 = "RED,YELLOW,BLUE,RED,BLUE,ORANGE,BLUE,YELLOW";
    private String recipe110 = "RED,YELLOW,BLUE,RED,BLUE,RED,YELLOW,GREEN";
    private String recipe111 = "RED,YELLOW,BLUE,RED,BLUE,RED,YELLOW,BLUE,YELLOW";

    @Override
    protected void apply(HatchingGround hatchingGround, EggHatchingUtilities hatchingLogic, Player currentPlayer,
            Gui gui) {
        List<String[]> recipes = new ArrayList<>();
        recipes.add(this.recipe000.split(","));
        recipes.add(this.recipe001.split(","));
        recipes.add(this.recipe010.split(","));
        recipes.add(this.recipe011.split(","));
        recipes.add(this.recipe100.split(","));
        recipes.add(this.recipe101.split(","));
        recipes.add(this.recipe110.split(","));
        recipes.add(this.recipe111.split(","));

        for (String[] recipe : recipes) {
            currentPlayer.elementSpaceLogic.addRecipe(ElementColor.WHITE, recipe);
            hatchingGround.logic.addRecipe(ElementColor.WHITE, recipe);
        }
    }

    @Override
    public String toString() {
        return LocaleUtilities.get("combine_primary_secondary_for_white");
    }


}
