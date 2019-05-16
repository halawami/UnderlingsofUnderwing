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
import underlings.utilities.LocaleWrap;

public class DrawingPhase extends SequentialPhase {

    public DrawingPhase(List<Player> players, Gui gui, ElementBag elementBag, HatchingGround hatchingGround,
            Runnable displayMethod, Field field) {
        super(players, gui, elementBag, hatchingGround, displayMethod, field);
    }

    Map<Player, List<ElementGiver>> playerElementGivers;

    @Override
    public void turn(Player player) {

        ElementGiver elementGiver = this.gui.promptChoice(LocaleWrap.get("gui_element_giver"),
                this.playerElementGivers.get(player), player.id);

        this.playerElementGivers.get(player).remove(elementGiver);

        DrawChoice drawChoice =
                this.gui.promptChoice(LocaleWrap.get("gui_draw_choice"), elementGiver.drawChoices, player.id);


        Element element = this.elementBag.drawElement(drawChoice);

        if (element != NullElement.getInstance()) {
            player.addElement(element);
        }

        this.setPhaseComplete(this.playerElementGivers.get(player).isEmpty());
    }

    @Override
    public void setup() {
        this.playerElementGivers = new HashMap<>();

        for (Player player : this.players) {
            player.onPhaseOne();
            this.playerElementGivers.put(player, player.getElementGivers());
        }
    }

    @Override
    public void teardown() {
        for (Player player : this.players) {
            player.endPhaseOne();
        }
    }

}
