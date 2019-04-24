package tests.elementSpaceLogic;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

import underlings.card.Card;
import underlings.element.ElementColor;
import underlings.element.ElementSpace;
import underlings.element.utilities.ElementSpaceLogic;

public class GetValidElementSpacesTest {

	@Test
	public void noElements() {
		Card testCard = new Card();
		
		ElementSpaceLogic logic = new ElementSpaceLogic();

		ElementSpace space1 = new ElementSpace(ElementColor.RED);
		ElementSpace space2 = new ElementSpace(ElementColor.BLUE);
		ElementSpace[] cardSpaces = { space1, space2 };
		testCard.elementSpaces = cardSpaces;
		
		assertEquals(Arrays.asList(), logic.getPlayableSpaces());
	}
}
