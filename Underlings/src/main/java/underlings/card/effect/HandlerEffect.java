package underlings.card.effect;

import underlings.game.HatchingGround;
import underlings.gui.Gui;
import underlings.handler.HandlerMovementLogic;
import underlings.player.Player;

public abstract class HandlerEffect extends Effect {

    @Override
    protected abstract void apply(Player currentPlayer,
            HatchingGround hatchingGround,
            HandlerMovementLogic handlerLogic,
            Gui gui);

}
