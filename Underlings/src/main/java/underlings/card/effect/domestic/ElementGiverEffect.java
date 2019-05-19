package underlings.card.effect.domestic;

import java.util.ArrayList;
import java.util.Arrays;
import underlings.card.effect.Effect;
import underlings.gui.DrawChoice;

public class ElementGiverEffect extends Effect {

    public String display;

    public ElementGiverEffect(DrawChoice... choices) {
        this.drawChoices = new ArrayList<>(Arrays.asList(choices));
    }


}
