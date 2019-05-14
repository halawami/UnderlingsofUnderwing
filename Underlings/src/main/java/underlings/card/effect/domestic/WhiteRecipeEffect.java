package underlings.card.effect.domestic;

import java.util.ArrayList;
import java.util.List;

import underlings.card.Card;
import underlings.card.effect.HatchingGroundEffect;
import underlings.element.ElementBag;
import underlings.element.ElementColor;
import underlings.game.HatchingGround;
import underlings.gui.Gui;
import underlings.player.Player;
import underlings.utilities.EggHatchingLogic;

public class WhiteRecipeEffect extends HatchingGroundEffect {

    String recipe000 = "RED,YELLOW,BLUE,PURPLE,ORANGE,GREEN";
    String recipe001 = "RED,YELLOW,BLUE,PURPLE,ORANGE,BLUE,YELLOW";
    String recipe010 = "RED,YELLOW,BLUE,PURPLE,RED,YELLOW,GREEN";
    String recipe011 = "RED,YELLOW,BLUE,PURPLE,RED,YELLOW,BLUE,YELLOW";
    String recipe100 = "RED,YELLOW,BLUE,RED,BLUE,ORANGE,GREEN";
    String recipe101 = "RED,YELLOW,BLUE,RED,BLUE,ORANGE,BLUE,YELLOW";
    String recipe110 = "RED,YELLOW,BLUE,RED,BLUE,RED,YELLOW,GREEN";
    String recipe111 = "RED,YELLOW,BLUE,RED,BLUE,RED,YELLOW,BLUE,YELLOW";

    @Override
    protected void apply(Card centerCard, HatchingGround hatchingGround, ElementBag elementBag, Gui gui,
            Player currentPlayer, EggHatchingLogic eggHatchingLogic) {

        List<String[]> recipes = new ArrayList<>();
        recipes.add(recipe000.split(","));
        recipes.add(recipe001.split(","));
        recipes.add(recipe010.split(","));
        recipes.add(recipe011.split(","));
        recipes.add(recipe100.split(","));
        recipes.add(recipe101.split(","));
        recipes.add(recipe110.split(","));
        recipes.add(recipe111.split(","));

        for (String[] recipe : recipes) {
            currentPlayer.elementSpaceLogic.addRecipe(ElementColor.WHITE, recipe);
            hatchingGround.logic.addRecipe(ElementColor.WHITE, recipe);
        }
    }

}
