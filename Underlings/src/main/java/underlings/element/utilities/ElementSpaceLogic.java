package underlings.element.utilities;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import underlings.card.Card;
import underlings.element.Element;
import underlings.element.ElementColor;
import underlings.element.ElementSpace;

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
            List<String> recipeLines =
                    Resources.readLines(Resources.getResource("DefaultRecipeList.txt"), Charsets.UTF_8);

            for (String line : recipeLines) {
                ElementColor color = ElementColor.valueOf(line.split(":")[0]);
                String[] recipes = line.split(":")[1].split(" ");
                List<List<ElementColor>> recipeList = new ArrayList<List<ElementColor>>();
                for (String recipe : recipes) {
                    List<ElementColor> constructedRecipe = new ArrayList<ElementColor>();
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

    public boolean isValidRecipe(List<ElementColor> recipe, ElementSpace space) {
        recipe = new ArrayList<ElementColor>(recipe);
        for (ElementColor color : space.getElementColors()) {
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
            elementSpace.elements.forEach((element) -> remaining.remove(element.getAlias()));

            if (remaining.isEmpty()) {
                validAdditions.clear();
                break;
            }

            validAdditions.addAll(remaining);
        }

        return new ArrayList<ElementColor>(validAdditions);
    }

    public List<ElementSpace> getPlayableSpaces(Card card, List<Element> elements) {
        Set<ElementSpace> spaces = new HashSet<>();
        for (Element element : elements) {
            spaces.addAll(getPlayableSpaces(card, element.getColor()));
        }
        return new ArrayList<>(spaces);
    }

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
        List<ElementSpace> destroyableSpaces = new ArrayList<>();
        for (ElementSpace elementSpace : card.elementSpaces) {
            for (Element element : elementSpace.elements) {
                if (element.getColor().equals(elementColor)) {
                    destroyableSpaces.add(elementSpace);
                    break;
                }
            }
        }
        return destroyableSpaces;
    }

    public List<Element> getPlayableElements(ElementSpace space, List<Element> playerElements) {
        List<ElementColor> validAdditions = getValidAdditions(space);
        playerElements = new ArrayList<Element>(playerElements);
        for (int i = 0; i < playerElements.size(); i++) {
            if (!validAdditions.contains(playerElements.get(i).getColor())) {
                playerElements.remove(i--);
            }
        }
        return playerElements;
    }
}
