package underlings.card.effect.domestic;

import underlings.card.effect.Effect;
import underlings.gui.DrawChoice;

public class ElementGiverEffect extends Effect {

    public String display;

    public ElementGiverEffect(DrawChoice... choices) {
        this.resetDrawChoice();
        for (DrawChoice choice : choices) {
            this.addDrawChoice(choice);
        }
    }


}
