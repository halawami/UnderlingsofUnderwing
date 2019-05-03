package phase;

import java.util.List;

import element.ElementBag;
import field.Field;
import game.HatchingGround;
import gui.Gui;
import player.Player;

public abstract class SequentialPhase implements Phase {

    protected List<Player> players;
    public Gui gui;
    protected ElementBag elementBag;
    protected HatchingGround hatchingGround;
    protected Runnable displayMethod;
    protected Field field;

    protected boolean phaseComplete;

    public SequentialPhase(List<Player> players, Gui gui, ElementBag elementBag, HatchingGround hatchingGround,
            Runnable displayMethod, Field field) {
        this.players = players;
        this.gui = gui;
        this.elementBag = elementBag;
        this.hatchingGround = hatchingGround;
        this.displayMethod = displayMethod;
        this.field = field;
    }

    @Override
    public void execute(int turnLeader) {
        this.setup();

        for (int i = turnLeader; i < turnLeader + this.players.size(); i++) {
            this.phaseComplete = false;
            while (!this.phaseComplete) {
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
