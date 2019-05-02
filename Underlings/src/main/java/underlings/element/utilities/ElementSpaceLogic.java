package underlings.element.utilities;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import underlings.card.Card;
import underlings.element.Element;
import underlings.element.ElementColor;
import underlings.element.ElementSpace;

import java.util.*;

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
            List<String> recipeLines = Resources.readLines(Resources.getResource("DefaultRecipeList.txt"), Charsets.UTF_8);

            for (String line : recipeLines) {
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

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isValidRecipe(List<ElementColor> recipe,
                                 ElementSpace space) {
        recipe = new ArrayList<ElementColor>(recipe);
        for (ElementColor color : space.elements) {
            if (recipe.contains(color)) {
                recipe.remove(color);
            } else {
                return false;
            }
        }
        return true;
    }

    public List<ElementColor> getValidAdditions(ElementSpace elementSpace) {
        Set<ElementColor> validAdditions = new TreeSet<ElementColor>();

        for (List<ElementColor> recipe : recipeMap.get(elementSpace.color)) {
            if (!isValidRecipe(recipe, elementSpace)) {
                continue;
            }

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
            for (Element element : elements) {
                if (getValidAdditions(space).contains(element.getColor())) {
                    spaces.add(space);
                    break;
                }
            }
        }
        return spaces;
    }

    //TODO: merge this method and the one on top
    public List<ElementSpace> getPlayableSpaces(Card card, ElementColor elementColor) {
        List<ElementSpace> spaces = new ArrayList<>();
        for (ElementSpace space : card.elementSpaces) {
            if (getValidAdditions(space).contains(elementColor)) {
                spaces.add(space);
                break;
            }
        }
        return spaces;
    }

    public List<ElementSpace> getDestroyableSpaces(Card card, ElementColor elementColor) {
        return Collections.emptyList();
    }
}
