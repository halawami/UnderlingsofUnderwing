package underlings.field;

import java.util.ArrayList;
import java.util.List;

import underlings.gui.DrawChoice;

public class FieldSpaceFactory {

    private static final DrawChoice RED = DrawChoice.RED;
    private static final DrawChoice BLUE = DrawChoice.BLUE;
    private static final DrawChoice YELLOW = DrawChoice.YELLOW;
    private static final DrawChoice ORANGE = DrawChoice.ORANGE;
    private static final DrawChoice PURPLE = DrawChoice.PURPLE;
    private static final DrawChoice GREEN = DrawChoice.GREEN;
    private static final DrawChoice BLACK = DrawChoice.BLACK;

    private static final DrawChoice[] fieldColors = {RED, BLUE, YELLOW, ORANGE,
            BLUE, RED, YELLOW, PURPLE, YELLOW, BLUE, RED, BLACK, ORANGE, RED,
            BLUE, YELLOW, GREEN, BLUE, RED, YELLOW, PURPLE, GREEN};

    public FieldSpace createFieldSpace(DrawChoice color) {
        return new FieldSpace(color);
    }

    public List<FieldSpace> createFieldArray() {
        List<FieldSpace> fieldArray = new ArrayList<>();

        for (DrawChoice color : fieldColors) {
            fieldArray.add(this.createFieldSpace(color));
        }

        return fieldArray;
    }

}
