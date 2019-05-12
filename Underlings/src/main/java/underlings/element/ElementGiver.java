package underlings.element;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import underlings.gui.DrawChoice;

public abstract class ElementGiver {

    public List<DrawChoice> drawChoices;

    public ElementGiver(DrawChoice... drawChoices) {
        this.drawChoices = new ArrayList<DrawChoice>();
        this.drawChoices.addAll(Arrays.asList(drawChoices));
    }

    public void addDrawChoice(DrawChoice drawChoice) {
        this.drawChoices.add(drawChoice);
    }

    public void resetDrawChoice() {
        this.drawChoices = new ArrayList<DrawChoice>();
    }

}
