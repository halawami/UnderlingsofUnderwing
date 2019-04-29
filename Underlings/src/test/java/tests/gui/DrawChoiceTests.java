package tests.gui;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Test;

import underlings.element.ElementGiver;
import underlings.gui.Display;
import underlings.gui.DrawChoice;
import underlings.gui.Gui;
import underlings.gui.PromptHandler;

public class DrawChoiceTests {

    @Test
    public void testRandom() {
        List<ElementGiver> elementGivers = new ArrayList<ElementGiver>();
        ElementGiver elementGiver = new ElementGiver("", DrawChoice.RANDOM);
        elementGivers.add(elementGiver);

        PromptHandler promptHandler = EasyMock.mock(PromptHandler.class);
        Display display = EasyMock.mock(Display.class);


        EasyMock.expect(promptHandler.promptChoice("Choose an Element Giver",
                elementGivers, 0)).andReturn(elementGiver);
        EasyMock.expect(promptHandler.promptChoice("Choose a Draw Choice",
                elementGiver.drawChoices, 0)).andReturn(DrawChoice.RANDOM);

        EasyMock.replay(promptHandler, display);

        Gui gui = new Gui(promptHandler, display);
        DrawChoice drawChoice = gui.getDrawChoice(elementGivers, 0);

        EasyMock.verify(promptHandler, display);
        assertEquals(drawChoice, DrawChoice.RANDOM);
        assertEquals(0, elementGivers.size());

    }

}
