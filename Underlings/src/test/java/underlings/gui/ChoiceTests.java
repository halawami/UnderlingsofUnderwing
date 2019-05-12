package underlings.gui;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.List;
import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import underlings.element.ElementGiver;
import underlings.handler.Handler;
import underlings.handler.HandlerState;

public class ChoiceTests {

    private PromptHandler promptHandler;
    private Display display;
    private Gui gui;

    @Before
    public void init() {
        this.promptHandler = EasyMock.mock(PromptHandler.class);
        this.display = EasyMock.mock(Display.class);
        this.gui = new Gui(this.promptHandler, this.display);
    }

    @After
    public void verify() {
        EasyMock.verify(this.promptHandler, this.display);
    }

    @Test
    public void testRandomDrawChoice() {
        List<ElementGiver> elementGivers = new ArrayList<ElementGiver>();
        ElementGiver elementGiver = new Handler(HandlerState.READY_ROOM);
        elementGivers.add(elementGiver);

        EasyMock.expect(this.promptHandler.promptChoice("Choose an Element Giver", elementGivers, 0))
                .andReturn(elementGiver);
        EasyMock.expect(this.promptHandler.promptChoice("Choose a Draw Choice", elementGiver.drawChoices, 0))
                .andReturn(DrawChoice.RANDOM);
        EasyMock.replay(this.promptHandler, this.display);

        DrawChoice drawChoice = this.gui.getDrawChoice(elementGivers, 0);

        assertEquals(drawChoice, DrawChoice.RANDOM);
        assertEquals(0, elementGivers.size());

    }

    @Test
    public void testFieldSpace0() {
        EasyMock.expect(this.promptHandler.promptInt("Enter Field Space", 0, 21)).andReturn(0);
        EasyMock.replay(this.promptHandler, this.display);

        int fieldSpace = this.gui.getFieldSpace();

        assertEquals(0, fieldSpace);
    }

    @Test
    public void testFieldSpace21() {
        EasyMock.expect(this.promptHandler.promptInt("Enter Field Space", 0, 21)).andReturn(21);
        EasyMock.replay(this.promptHandler, this.display);

        int fieldSpace = this.gui.getFieldSpace();

        assertEquals(21, fieldSpace);
    }

}
