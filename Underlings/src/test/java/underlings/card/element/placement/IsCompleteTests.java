package underlings.card.element.placement;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import underlings.MockTest;
import underlings.card.Card;
import underlings.element.ElementSpace;
import underlings.utilities.ElementSpaceUtilities;

public class IsCompleteTests extends MockTest {

    @Before
    public void init() {
        this.card = new Card();
        this.elementSpace = this.mock(ElementSpace.class);
        this.elementSpace2 = this.mock(ElementSpace.class);
        this.elementSpaceLogic = EasyMock.partialMockBuilder(ElementSpaceUtilities.class)
                .addMockedMethod("isComplete", ElementSpace.class).createMock();
        this.addMock(this.elementSpaceLogic);
    }

    @Test
    public void testCompleteCardOneElementSpace() {
        this.card.elementSpaces = new ElementSpace[]{this.elementSpace};
        EasyMock.expect(this.elementSpaceLogic.isComplete(this.elementSpace)).andReturn(true);

        this.replayAll();

        assertTrue(this.elementSpaceLogic.isComplete(this.card));
    }

    @Test
    public void testIncompleteCardOneElementSpace() {
        this.card.elementSpaces = new ElementSpace[]{this.elementSpace};
        EasyMock.expect(this.elementSpaceLogic.isComplete(this.elementSpace)).andReturn(false);

        this.replayAll();

        assertFalse(this.elementSpaceLogic.isComplete(this.card));
    }

    @Test
    public void testCompleteCardTwoElementSpace() {
        this.card.elementSpaces = new ElementSpace[]{this.elementSpace, this.elementSpace2};
        EasyMock.expect(this.elementSpaceLogic.isComplete(this.elementSpace)).andReturn(true);
        EasyMock.expect(this.elementSpaceLogic.isComplete(this.elementSpace2)).andReturn(true);

        this.replayAll();

        assertTrue(this.elementSpaceLogic.isComplete(this.card));
    }

    @Test
    public void testIncompleteCardTwoElementSpace() {
        this.card.elementSpaces = new ElementSpace[]{this.elementSpace, this.elementSpace2};
        EasyMock.expect(this.elementSpaceLogic.isComplete(this.elementSpace)).andReturn(true);
        EasyMock.expect(this.elementSpaceLogic.isComplete(this.elementSpace2)).andReturn(false);

        this.replayAll();

        assertFalse(this.elementSpaceLogic.isComplete(this.card));
    }


}
