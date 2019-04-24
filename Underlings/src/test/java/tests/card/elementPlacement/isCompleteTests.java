package tests.card.elementPlacement;

import org.easymock.EasyMock;
import org.junit.Test;
import underlings.card.Card;
import underlings.element.ElementSpace;
import underlings.element.utilities.ElementSpaceLogic;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class isCompleteTests {

    @Test
    public void testCompleteCardOneElementSpace() {
        Card completeCard = new Card();
        ElementSpace mockedElementSpace = EasyMock.mock(ElementSpace.class);
        ElementSpaceLogic elementSpaceLogic = EasyMock.partialMockBuilder(ElementSpaceLogic.class)
                .addMockedMethod("isComplete", ElementSpace.class).createMock();

        completeCard.elementSpaces = new ElementSpace[]{mockedElementSpace};
        EasyMock.expect(elementSpaceLogic.isComplete(mockedElementSpace)).andReturn(true);

        EasyMock.replay(mockedElementSpace, elementSpaceLogic);

        assertTrue(elementSpaceLogic.isComplete(completeCard));

        EasyMock.verify(mockedElementSpace, elementSpaceLogic);
    }

    @Test
    public void testIncompleteCardOneElementSpace(){
        Card completeCard = new Card();
        ElementSpace mockedElementSpace = EasyMock.mock(ElementSpace.class);
        ElementSpaceLogic elementSpaceLogic = EasyMock.partialMockBuilder(ElementSpaceLogic.class)
                .addMockedMethod("isComplete", ElementSpace.class).createMock();

        completeCard.elementSpaces = new ElementSpace[]{mockedElementSpace};
        EasyMock.expect(elementSpaceLogic.isComplete(mockedElementSpace)).andReturn(false);

        EasyMock.replay(mockedElementSpace, elementSpaceLogic);

        assertFalse(elementSpaceLogic.isComplete(completeCard));

        EasyMock.verify(mockedElementSpace, elementSpaceLogic);
    }



}
