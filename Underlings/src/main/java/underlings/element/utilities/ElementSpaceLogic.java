package underlings.element.utilities;

import underlings.card.Card;
import underlings.element.ElementColor;
import underlings.element.ElementSpace;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

/**
 * TODO: Give each player their own ElementSpaceLogic That way when they are
 * able to create white from all primary and secondary elements you can change
 * the logic for that one player
 */

public class ElementSpaceLogic {
	private Map<ElementColor, List<List<ElementColor>>> recipeMap;
	
	public ElementSpaceLogic() {
		initMap();
	}

	public boolean isComplete(ElementSpace elementSpace) {
		return getValidAdditions(elementSpace).isEmpty();
	}

	public boolean isComplete(Card card){
		return isComplete(card.elementSpaces[0]);
	}

	public void initMap() {
		recipeMap = new HashMap<ElementColor, List<List<ElementColor>>>();

		try {
			BufferedReader br = new BufferedReader(new FileReader("docs\\DefaultRecipeList.txt"));

			String line = br.readLine();
			while (line != null) {
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

				line = br.readLine();
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<ElementColor> getValidAdditions(ElementSpace elementSpace) {
		Set<ElementColor> validAdditions = new TreeSet<ElementColor>();

		for (List<ElementColor> recipe : recipeMap.get(elementSpace.color)) {
			List<ElementColor> remaining = new ArrayList<ElementColor>(recipe);
			elementSpace.elements.forEach((color) -> remaining.remove(color));
			
			if(remaining.isEmpty()) {
				validAdditions.clear();
				break;
			}
			
			validAdditions.addAll(remaining);
		}

		return new ArrayList<ElementColor>(validAdditions);
	}

}
