package underlings.element.utilities;

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
    private List<ElementColor> openElements;

    public ElementSpaceLogic(List<String> recipes) {
        initMap(recipes);
        openElements = new ArrayList<>();
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

    public void initMap(List<String> recipeLines) {
        recipeMap = new HashMap<>();

        for (String line : recipeLines) {
            ElementColor createdColor = ElementColor.valueOf(line.split(":")[0]);
            recipeMap.put(createdColor, new ArrayList<List<ElementColor>>());

            String[] recipes = line.split(":")[1].split(" ");
            for (String recipe : recipes) {
                addRecipe(createdColor, recipe.split(","));
            }
        }
    }

    public void addRecipe(ElementColor createdColor, String... ingredients) {
        List<List<ElementColor>> recipeList = recipeMap.get(createdColor);
        List<ElementColor> recipe = new ArrayList<ElementColor>();
        for (String ingredient : ingredients) {
            recipe.add(ElementColor.valueOf(ingredient));
        }
        recipeList.add(recipe);
    }

    public void resetRecipes(ElementColor color) {

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
        final List<Element> playableElements = new ArrayList<>();

        playerElements.forEach((Element element) -> {
            ElementColor color = element.getColor();
            if (validAdditions.contains(color) || isOpenElement(color)) {
                playableElements.add(element);
            }
        });

        return playableElements;
    }

    public void setOpenElement(ElementColor color) {
        this.openElements.add(color);
    }

    public boolean isOpenElement(ElementColor color) {
        return this.openElements.contains(color);
    }
}
