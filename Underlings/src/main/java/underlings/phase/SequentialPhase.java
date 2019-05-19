package underlings.phase;

import java.util.List;

import underlings.element.ElementBag;
import underlings.field.Field;
import underlings.gui.Gui;
import underlings.hatchingground.HatchingGround;
import underlings.player.Player;

public abstract class SequentialPhase extends Phase {

    public SequentialPhase(List<Player> players, Gui gui, ElementBag elementBag, HatchingGround hatchingGround,
            Runnable displayMethod, Field field) {
        super(players, gui, elementBag, hatchingGround, displayMethod, field);
    }

    @Override
    public void execute(int turnLeader) {
        this.setup();

        for (int i = turnLeader; i < turnLeader + this.players.size(); i++) {
            this.setPhaseComplete(false);
            while (!this.isPhaseComplete()) {
                this.turn(this.players.get(i % this.players.size()));
                this.displayMethod.run();
            }
        }

        this.teardown();
    }

    protected abstract void teardown();
}
