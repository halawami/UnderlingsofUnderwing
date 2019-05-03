package tests.card.element.placement;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.easymock.EasyMock;
import org.junit.Test;

import underlings.card.Card;
import underlings.element.ElementSpace;
import underlings.element.utilities.ElementSpaceLogic;

public class IsCompleteTests {

    @Test
    public void testCompleteCardOneElementSpace() {
        Card completeCard = new Card();
        ElementSpace mockedElementSpace = EasyMock.mock(ElementSpace.class);
        ElementSpaceLogic elementSpaceLogic = EasyMock.partialMockBuilder(ElementSpaceLogic.class)
                .addMockedMethod("isComplete", ElementSpace.class).createMock();

        completeCard.elementSpaces = new ElementSpace[] {mockedElementSpace};
        EasyMock.expect(elementSpaceLogic.isComplete(mockedElementSpace)).andReturn(true);

        EasyMock.replay(mockedElementSpace, elementSpaceLogic);

        assertTrue(elementSpaceLogic.isComplete(completeCard));

        EasyMock.verify(mockedElementSpace, elementSpaceLogic);
    }

    @Test
    public void testIncompleteCardOneElementSpace() {
        Card completeCard = new Card();
        ElementSpace mockedElementSpace = EasyMock.mock(ElementSpace.class);
        ElementSpaceLogic elementSpaceLogic = EasyMock.partialMockBuilder(ElementSpaceLogic.class)
                .addMockedMethod("isComplete", ElementSpace.class).createMock();

        completeCard.elementSpaces = new ElementSpace[] {mockedElementSpace};
        EasyMock.expect(elementSpaceLogic.isComplete(mockedElementSpace)).andReturn(false);

        EasyMock.replay(mockedElementSpace, elementSpaceLogic);

        assertFalse(elementSpaceLogic.isComplete(completeCard));

        EasyMock.verify(mockedElementSpace, elementSpaceLogic);
    }


    @Test
    public void testCompleteCardTwoElementSpace() {
        Card completeCard = new Card();
        ElementSpace firstMockedElementSpace = EasyMock.mock(ElementSpace.class);
        ElementSpace secondMockedElementSpace = EasyMock.mock(ElementSpace.class);

        ElementSpaceLogic elementSpaceLogic = EasyMock.partialMockBuilder(ElementSpaceLogic.class)
                .addMockedMethod("isComplete", ElementSpace.class).createMock();

        completeCard.elementSpaces = new ElementSpace[] {firstMockedElementSpace, secondMockedElementSpace};
        EasyMock.expect(elementSpaceLogic.isComplete(firstMockedElementSpace)).andReturn(true);
        EasyMock.expect(elementSpaceLogic.isComplete(secondMockedElementSpace)).andReturn(true);

        EasyMock.replay(firstMockedElementSpace, secondMockedElementSpace, elementSpaceLogic);

        assertTrue(elementSpaceLogic.isComplete(completeCard));

        EasyMock.verify(firstMockedElementSpace, secondMockedElementSpace, elementSpaceLogic);

    }

    @Test
    public void testIncompleteCardTwoElementSpace() {
        Card completeCard = new Card();
        ElementSpace firstMockedElementSpace = EasyMock.mock(ElementSpace.class);
        ElementSpace secondMockedElementSpace = EasyMock.mock(ElementSpace.class);

        ElementSpaceLogic elementSpaceLogic = EasyMock.partialMockBuilder(ElementSpaceLogic.class)
                .addMockedMethod("isComplete", ElementSpace.class).createMock();

        completeCard.elementSpaces = new ElementSpace[] {firstMockedElementSpace, secondMockedElementSpace};
        EasyMock.expect(elementSpaceLogic.isComplete(firstMockedElementSpace)).andReturn(true);
        EasyMock.expect(elementSpaceLogic.isComplete(secondMockedElementSpace)).andReturn(false);

        EasyMock.replay(firstMockedElementSpace, secondMockedElementSpace, elementSpaceLogic);

        assertFalse(elementSpaceLogic.isComplete(completeCard));

        EasyMock.verify(firstMockedElementSpace, secondMockedElementSpace, elementSpaceLogic);
    }


}
