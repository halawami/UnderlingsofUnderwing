package underlings.element.utilities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import underlings.card.Card;
import underlings.element.Element;
import underlings.element.ElementColor;
import underlings.element.ElementSpace;

/**
 * TODO: Give each player their own ElementSpaceLogic That way when they are able to create white
 * from all primary and secondary elements you can change the logic for that one player
 */

public class ElementSpaceLogic {
    private Map<ElementColor, List<List<ElementColor>>> recipeMap;

    public ElementSpaceLogic() {
        initMap();
    }

    public boolean isComplete(ElementSpace elementSpace) {
        return getValidAdditions(elementSpace).isEmpty();
    }

    public boolean isComplete(Card card) {
        for (ElementSpace elementSpace : card.elementSpaces) {
            if (!isComplete(elementSpace)) {
                return false;
            }
        }
        return true;
    }

    public void initMap() {
        recipeMap = new HashMap<ElementColor, List<List<ElementColor>>>();

        try {
            BufferedReader br = new BufferedReader(
                    new FileReader("docs\\DefaultRecipeList.txt"));

            String line = br.readLine();
            while (line != null) {
                ElementColor color = ElementColor.valueOf(line.split(":")[0]);
                String[] recipes = line.split(":")[1].split(" ");
                List<List<ElementColor>> recipeList =
                        new ArrayList<List<ElementColor>>();
                for (String recipe : recipes) {
                    List<ElementColor> constructedRecipe =
                            new ArrayList<ElementColor>();
                    for (String ingredient : recipe.split(",")) {
                        constructedRecipe.add(ElementColor.valueOf(ingredient));
                    }
                    recipeList.add(constructedRecipe);
                }
                recipeMap.put(color, recipeList);

                line = br.readLine();
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isValidRecipe(List<ElementColor> recipe,
            ElementSpace space) {
        recipe = new ArrayList<ElementColor>(recipe);
        for (ElementColor color : space.elements) {
            if (recipe.contains(color))
                recipe.remove(color);
            else
                return false;
        }
        return true;
    }

    public List<ElementColor> getValidAdditions(ElementSpace elementSpace) {
        Set<ElementColor> validAdditions = new TreeSet<ElementColor>();

        for (List<ElementColor> recipe : recipeMap.get(elementSpace.color)) {
            if (!isValidRecipe(recipe, elementSpace))
                continue;

            List<ElementColor> remaining = new ArrayList<ElementColor>(recipe);
            elementSpace.elements.forEach((color) -> remaining.remove(color));

            if (remaining.isEmpty()) {
                validAdditions.clear();
                break;
            }

            validAdditions.addAll(remaining);
        }

        return new ArrayList<ElementColor>(validAdditions);
    }

    public List<ElementSpace> getPlayableSpaces(Card card,
            List<Element> elements) {
        List<ElementSpace> spaces = new ArrayList<ElementSpace>();
        for (ElementSpace space : card.elementSpaces) {
            for (Element element : elements)
                if (getValidAdditions(space).contains(element.getColor())) {
                    spaces.add(space);
                    break;
                }
        }
        return spaces;
    }

    public List<ElementSpace> getPlayableSpaces(
            List<ElementColor> elementColors, Card card) {
        List<ElementSpace> spaces = new ArrayList<>();
        for (ElementSpace space : card.elementSpaces) {
            for (ElementColor color : elementColors)
                if (getValidAdditions(space).contains(color)) {
                    spaces.add(space);
                    break;
                }
        }
        return spaces;
    }

}
