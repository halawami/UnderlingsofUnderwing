package underlings.phase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import underlings.element.Element;
import underlings.element.ElementBag;
import underlings.element.ElementGiver;
import underlings.element.NullElement;
import underlings.field.Field;
import underlings.game.HatchingGround;
import underlings.gui.DrawChoice;
import underlings.gui.Gui;
import underlings.player.Player;

public class DrawingPhase extends SequentialPhase {

    public DrawingPhase(List<Player> players, Gui gui, ElementBag elementBag,
        HatchingGround hatchingGround, Runnable displayMethod,
        Field field) {
        super(players, gui, elementBag, hatchingGround, displayMethod, field);
    }

    Map<Player, List<ElementGiver>> elementGivers;

    @Override
    public boolean turn(Player player) {

        DrawChoice drawChoice = this.gui.getDrawChoice(
            this.elementGivers.get(player), player.getPlayerId());

        Element element = this.elementBag.drawElement(drawChoice);

        if (element != NullElement.getInstance()) {
            player.addElement(element);
        }

        this.phaseComplete = this.elementGivers.get(player).isEmpty();
        return false;
    }

    @Override
    public void setup() {
        this.elementGivers = new HashMap<>();

        for (Player player : this.players) {
            this.elementGivers.put(player, player.getElementGivers());
        }
    }

}
