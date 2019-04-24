package tests.card.elementPlacement;

import org.easymock.EasyMock;
import org.junit.Test;

import underlings.card.Card;
import underlings.element.ElementSpace;

public class GetValidElementSpacesTest {

	@Test
	public void noElements() {
		Card testCard = new Card();
		
		ElementSpace mockedSpace1 = EasyMock.niceMock(ElementSpace.class);
		ElementSpace mockedSpace2 = EasyMock.niceMock(ElementSpace.class);
		ElementSpace[] cardSpaces = { mockedSpace1, mockedSpace2 };
		testCard.elementSpaces = cardSpaces;
		
		EasyMock.replay(mockedSpace1, mockedSpace2);
		
		
		
		EasyMock.verify(mockedSpace1, mockedSpace2);
	}
}
