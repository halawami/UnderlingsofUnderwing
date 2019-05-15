package underlings.elements.giver;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import underlings.element.ElementGiver;
import underlings.element.ElementGiverFactory;
import underlings.gui.DrawChoice;

public class ElementGiverFactoryTests {

    @Test
    public void testCreateElementGiver() {
        ElementGiverFactory elementGiverFactory = new ElementGiverFactory();
        List<DrawChoice> drawChoices = Arrays.asList(DrawChoice.BLUE, DrawChoice.RED);
        ElementGiver elementGiver = elementGiverFactory.createElementGiver(drawChoices);

        Assert.assertEquals(drawChoices, elementGiver.drawChoices);
    }

}
