package tests.elements.giver;

import org.junit.Test;

import underlings.element.ElementColor;
import underlings.element.ElementGiver;
import underlings.game.Field;
import underlings.game.FieldSpace;
import underlings.game.Handler;
import underlings.game.HandlerState;
import underlings.gui.DrawChoice;
import underlings.gui.ElementDrawChoice;


import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HandlerTests {

    @Test
    public void testNotField(){
        Handler handler = new Handler(HandlerState.BREAK_ROOM);
        ElementGiver elementGiver = handler.elementGiver;
        List<DrawChoice> drawChoices = elementGiver.drawChoices;
        assertEquals(1, drawChoices.size());
        assertTrue(drawChoices.contains(DrawChoice.RANDOM));
    }



    @Test
    public void testFieldBlue(){
        testFieldColor(DrawChoice.BLUE);
    }

    @Test
    public void testFieldRed(){
        testFieldColor(DrawChoice.RED);
    }

    @Test
    public void testFieldGreen(){
        testFieldColor(DrawChoice.GREEN);
    }

    @Test
    public void testFieldPurple(){
        testFieldColor(DrawChoice.PURPLE);
    }

    @Test
    public void testFieldOrange(){
        testFieldColor(DrawChoice.ORANGE);
    }

    @Test
    public void testFieldYellow(){
        testFieldColor(DrawChoice.YELLOW);
    }

    @Test
    public void testFieldBlack(){
        testFieldColor(DrawChoice.BLACK);
    }

    @Test
    public void testFieldWhite(){
        testFieldColor(DrawChoice.WHITE);
    }

    @Test
    public void testToStringReadyRoom(){
        Handler handler = new Handler(HandlerState.READY_ROOM);
        ElementGiver elementGiver = handler.elementGiver;
        assertEquals("Handler in Ready Room", elementGiver.toString());
    }


    private void testFieldColor(DrawChoice color){
        FieldSpace fieldSpace = new FieldSpace(color);
        Handler handler = new Handler(HandlerState.FIELD);
        fieldSpace.addHandler(handler);
        ElementGiver elementGiver = handler.elementGiver;
        List<DrawChoice> drawChoices = elementGiver.drawChoices;
        assertEquals(2, drawChoices.size());
        assertTrue(drawChoices.contains(DrawChoice.RANDOM));
        assertTrue(drawChoices.contains(color));
    }

}
