package tests.elementSpaceLogic;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import underlings.card.Card;
import underlings.element.Element;
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
	
	@Test
	public void blueElement() {
		ElementSpace space1 = new ElementSpace(ElementColor.RED);
		ElementSpace space2 = new ElementSpace(ElementColor.BLUE);
		ElementSpace[] cardSpaces = { space1, space2 };

		Card testCard = new Card();
		testCard.elementSpaces = cardSpaces;
		
		ElementSpaceLogic logic = new ElementSpaceLogic();
		List<ElementSpace> actual = logic.getPlayableSpaces(testCard, ElementColor.BLUE);
		assertEquals(Arrays.asList(space2), actual);
	}
	
	@Test
	public void blueAndRedElements() {
		ElementSpace space1 = new ElementSpace(ElementColor.RED);
		ElementSpace space2 = new ElementSpace(ElementColor.BLUE);
		ElementSpace[] cardSpaces = { space1, space2 };

		Card testCard = new Card();
		testCard.elementSpaces = cardSpaces;
		
		ElementSpaceLogic logic = new ElementSpaceLogic();
		List<ElementSpace> actual = logic.getPlayableSpaces(testCard, ElementColor.BLUE, ElementColor.RED);
		assertEquals(Arrays.asList(space1, space2), actual);
	}
	
	@Test
	public void secondaryElements() {
		ElementSpace space1 = new ElementSpace(ElementColor.ORANGE);
		ElementSpace space2 = new ElementSpace(ElementColor.GREEN);
		ElementSpace space3 = new ElementSpace(ElementColor.BLACK);
		space3.addElements(new Element(ElementColor.BLUE), new Element(ElementColor.BLUE));
		ElementSpace space4 = new ElementSpace(ElementColor.WHITE);
		ElementSpace[] cardSpaces = { space1, space2, space3, space4 };

		Card testCard = new Card();
		testCard.elementSpaces = cardSpaces;
		
		ElementSpaceLogic logic = new ElementSpaceLogic();
		List<ElementSpace> expected = Arrays.asList(space2, space4);
		List<ElementSpace> actual = logic.getPlayableSpaces(testCard, ElementColor.BLUE, ElementColor.WHITE);
		assertEquals(expected, actual);
	}
}
