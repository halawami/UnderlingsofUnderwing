package underlings.card.element.placement;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import underlings.card.Card;
import underlings.element.ElementSpace;
import underlings.element.utilities.ElementSpaceLogic;

public class IsCompleteTests {

    private Card completeCard;
    private ElementSpace firstMockedElementSpace;
    private ElementSpace secondMockedElementSpace;
    private ElementSpaceLogic elementSpaceLogic;

    @Before
    public void init() {
        this.completeCard = new Card();
        this.firstMockedElementSpace = EasyMock.mock(ElementSpace.class);
        this.secondMockedElementSpace = EasyMock.mock(ElementSpace.class);
        this.elementSpaceLogic = EasyMock.partialMockBuilder(ElementSpaceLogic.class)
                .addMockedMethod("isComplete", ElementSpace.class).createMock();
    }

    @After
    public void verify() {
        EasyMock.verify(this.firstMockedElementSpace, this.secondMockedElementSpace, this.elementSpaceLogic);
    }

    public void replay() {
        EasyMock.replay(this.firstMockedElementSpace, this.secondMockedElementSpace, this.elementSpaceLogic);
    }

    @Test
    public void testCompleteCardOneElementSpace() {
        this.completeCard.elementSpaces = new ElementSpace[] {this.firstMockedElementSpace};
        EasyMock.expect(this.elementSpaceLogic.isComplete(this.firstMockedElementSpace)).andReturn(true);

        this.replay();

        assertTrue(this.elementSpaceLogic.isComplete(this.completeCard));
    }

    @Test
    public void testIncompleteCardOneElementSpace() {
        this.completeCard.elementSpaces = new ElementSpace[] {this.firstMockedElementSpace};
        EasyMock.expect(this.elementSpaceLogic.isComplete(this.firstMockedElementSpace)).andReturn(false);

        this.replay();

        assertFalse(this.elementSpaceLogic.isComplete(this.completeCard));
    }

    @Test
    public void testCompleteCardTwoElementSpace() {
        this.completeCard.elementSpaces =
                new ElementSpace[] {this.firstMockedElementSpace, this.secondMockedElementSpace};
        EasyMock.expect(this.elementSpaceLogic.isComplete(this.firstMockedElementSpace)).andReturn(true);
        EasyMock.expect(this.elementSpaceLogic.isComplete(this.secondMockedElementSpace)).andReturn(true);

        this.replay();

        assertTrue(this.elementSpaceLogic.isComplete(this.completeCard));
    }

    @Test
    public void testIncompleteCardTwoElementSpace() {
        this.completeCard.elementSpaces =
                new ElementSpace[] {this.firstMockedElementSpace, this.secondMockedElementSpace};
        EasyMock.expect(this.elementSpaceLogic.isComplete(this.firstMockedElementSpace)).andReturn(true);
        EasyMock.expect(this.elementSpaceLogic.isComplete(this.secondMockedElementSpace)).andReturn(false);

        this.replay();

        assertFalse(this.elementSpaceLogic.isComplete(this.completeCard));
    }


}
