package underlings.gui;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.List;
import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;
import underlings.field.Field;
import underlings.field.FieldSpace;
import underlings.field.FieldSpaceFactory;
import underlings.player.FakePlayer;

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

    @Test
    public void testFieldSpace0() {
        Field field = new Field(new FieldSpaceFactory());

        EasyMock.expect(this.promptHandler.pickFromGrid(EasyMock.anyString(), EasyMock.anyObject(FieldSpace[][].class),
                EasyMock.anyInt())).andReturn(field.field.get(0));

        EasyMock.replay(this.promptHandler, this.display);
        FieldSpace fieldSpace = this.gui.getFieldSpace(FakePlayer.getInstance(), field);
        EasyMock.verify(this.promptHandler, this.display);

        assertEquals(field.field.get(0), fieldSpace);
    }

    @Test
    public void testFieldSpace21() {
        Field field = new Field(new FieldSpaceFactory());

        EasyMock.expect(this.promptHandler.pickFromGrid(EasyMock.anyString(), EasyMock.anyObject(FieldSpace[][].class),
                EasyMock.anyInt())).andReturn(field.field.get(21));

        EasyMock.replay(this.promptHandler, this.display);
        FieldSpace fieldSpace = this.gui.getFieldSpace(FakePlayer.getInstance(), field);
        EasyMock.verify(this.promptHandler, this.display);

        assertEquals(field.field.get(21), fieldSpace);
    }

    @Test
    public void testYesNoChoices() {
        List<YesNoChoice> choices = YesNoChoice.getChoices();

        assertEquals(2, choices.size());
        assertTrue(choices.contains(YesNoChoice.YES));
        assertTrue(choices.contains(YesNoChoice.NO));
    }

}
