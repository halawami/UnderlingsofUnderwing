package tests.elementSpaceLogic;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import underlings.card.Card;
import underlings.element.ElementColor;
import underlings.element.ElementSpace;
import underlings.element.utilities.ElementSpaceLogic;

public class GetValidElementSpacesTest {

	@Test
	public void noElements() {
		ElementSpace space1 = new ElementSpace(ElementColor.RED);
		ElementSpace space2 = new ElementSpace(ElementColor.BLUE);
		ElementSpace[] cardSpaces = { space1, space2 };

		Card testCard = new Card();
		testCard.elementSpaces = cardSpaces;
		
		ElementSpaceLogic logic = new ElementSpaceLogic();
		List<ElementSpace> actual = logic.getPlayableSpaces(testCard);
		assertEquals(Arrays.asList(), actual);
	}
	
	@Test
	public void redElement() {
		ElementSpace space1 = new ElementSpace(ElementColor.RED);
		ElementSpace space2 = new ElementSpace(ElementColor.BLUE);
		ElementSpace[] cardSpaces = { space1, space2 };

		Card testCard = new Card();
		testCard.elementSpaces = cardSpaces;
		
		ElementSpaceLogic logic = new ElementSpaceLogic();
		List<ElementSpace> actual = logic.getPlayableSpaces(testCard, ElementColor.RED);
		assertEquals(Arrays.asList(space1), actual);
	}
}
