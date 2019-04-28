package underlings.handler;

import underlings.card.Card;
import underlings.field.Field;
import underlings.game.HatchingGround;
import underlings.gui.GUI;

public class HandlerMovementLogic {

    private HatchingGround hatchingGround;
    private GUI gui;
    private Field field;

    public HandlerMovementLogic(HatchingGround hatchingGround, GUI gui, Field field) {
        this.hatchingGround = hatchingGround;
        this.gui = gui;
        this.field = field;
    }

    public void move(Handler handler, HandlerChoice choice, int playerId) {
        switch (choice) {
            case BREAK_ROOM:
                if (handler.getState() == HandlerState.CARD) {
                    Card card = this.hatchingGround.findCard(handler);
                    card.handler = null;
                }
                handler.moveToState(choice.getState());
                break;
            case CARD:
                handler.moveToState(choice.getState());
                Card chosenCard =
                        this.gui.getCard(this.hatchingGround.getUnclaimedEggs(), playerId);
                chosenCard.handler = handler;
                handler.setLocation(chosenCard.name);
                break;
            case FIELD:
                handler.moveToState(choice.getState());
                int fieldSpace = this.gui.getFieldSpace();
                this.field.addHandler(fieldSpace, handler);
                break;
            case FIELD_WHITESPACE:
                handler.moveToState(choice.getState());
                this.field.addHandlerWhitespace(handler);
                break;
            case STAY:
                if (handler.getState() == HandlerState.FIELD) {
                    this.field.rotate(handler);
                }
                return;
            default:
                handler.moveToState(choice.getState());
        }

    }

}
