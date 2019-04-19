package underlings.element.utilities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import underlings.element.ElementColor;
import underlings.element.ElementSpace;

/**
 * TODO: Give each player their own ElementSpaceLogic That way when they are
 * able to create white from all primary and secondary elements you can change
 * the logic for that one player
 */

public class ElementSpaceLogic {
	private static Map<ElementColor, List<List<ElementColor>>> recipeMap;

	public static boolean isComplete(ElementSpace elementSpace) {
		if (recipeMap == null)
			initMap();

		if (recipeMap.containsKey(elementSpace.color)) {
			List<List<ElementColor>> recipes = recipeMap.get(elementSpace.color);

			for (List<ElementColor> recipe : recipes) {
				if (recipe.containsAll(elementSpace.elements) && elementSpace.elements.containsAll(recipe))
					return true;
			}
		}
		return false;
	}

	public static void initMap() {
		recipeMap = new HashMap<ElementColor, List<List<ElementColor>>>();

		try {
			BufferedReader br = new BufferedReader(new FileReader("docs\\DefaultRecipeList.txt"));
			
			String line = br.readLine();
			while (line != null) {
				ElementColor color = ElementColor.valueOf(line.split(":")[0]);
				String[] recipes = line.split(":")[1].split(" ");
				List<List<ElementColor>> recipeList = new ArrayList<List<ElementColor>>();
				for(String recipe : recipes) {
					List<ElementColor> constructedRecipe = new ArrayList<ElementColor>();
					for(String ingredient : recipe.split(",")) {
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

}
