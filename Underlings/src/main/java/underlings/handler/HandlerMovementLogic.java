package underlings.handler;

import underlings.card.Card;
import underlings.field.Field;
import underlings.game.HatchingGround;
import underlings.gui.Gui;
import underlings.utilities.LocaleWrap;

public class HandlerMovementLogic {

    private HatchingGround hatchingGround;
    private Gui gui;
    private Field field;

    public HandlerMovementLogic(HatchingGround hatchingGround, Gui gui, Field field) {
        this.hatchingGround = hatchingGround;
        this.gui = gui;
        this.field = field;
    }

    public void move(Handler handler, HandlerChoice choice, int playerId) {
        if (handler == WildHandler.getInstance() || handler == null) {
            return;
        }
        switch (choice) {
            case BREAK_ROOM:
                if (handler.getState() == HandlerState.FIELD || handler.getState() == HandlerState.FIELD_WHITESPACE) {
                    this.field.removeHandler(handler);
                }
                if (handler.getState() == HandlerState.CARD) {
                    Card card = this.hatchingGround.findCard(handler);
                    this.removeHandlerFromCard(card);
                }
                handler.moveToState(choice.getState());
                break;
            case CARD:

                Card chosenCard = this.gui.getCard(playerId, LocaleWrap.get("handler_movement_card"),
                        this.hatchingGround, this.hatchingGround.getUnclaimedEggs());

                moveToCard(handler, chosenCard);
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

    public void removeHandlerFromCard(Card card) {
        card.handler = null;
    }

    public void moveToCard(Handler handler, Card card) {
        handler.moveToState(HandlerChoice.CARD.getState());
        card.handler = handler;
        handler.setLocation(card.name);
    }

}
