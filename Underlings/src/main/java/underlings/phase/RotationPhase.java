package underlings.phase;

import java.util.List;
import underlings.element.ElementBag;
import underlings.field.Field;
import underlings.game.HatchingGround;
import underlings.gui.GUI;
import underlings.player.Player;

public abstract class RotationPhase implements Phase {

    protected List<Player> players;
    public GUI gui;
    protected ElementBag elementBag;
    protected HatchingGround hatchingGround;
    protected Runnable displayMethod;
    protected Field field;

    protected boolean phaseComplete;

    public RotationPhase(List<Player> players, GUI gui, ElementBag elementBag,
            HatchingGround hatchingGround, Runnable displayMethod, Field field) {
        this.players = players;
        this.gui = gui;
        this.elementBag = elementBag;
        this.hatchingGround = hatchingGround;
        this.displayMethod = displayMethod;
        this.field = field;
    }

    @Override
    public void execute(int turnLeader) {
        this.phaseComplete = false;
        this.setup();

        while (!this.phaseComplete) {
            this.phaseComplete = true;
            for (int i = turnLeader; i < turnLeader + this.players.size(); i++) {
                this.turn(this.players.get(i % this.players.size()));
                this.displayMethod.run();
            }
        }

    }

    @Override
    public abstract void setup();

    @Override
    public abstract boolean turn(Player player);

}