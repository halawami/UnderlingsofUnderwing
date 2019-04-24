package underlings.handler;

import underlings.card.Card;
import underlings.game.HatchingGround;
import underlings.gui.GUI;

public class HandlerMovementLogic {

	private HatchingGround hatchingGround;
	private GUI gui;
	
	public HandlerMovementLogic(HatchingGround hatchingGround, GUI gui) {
		this.hatchingGround = hatchingGround;
		this.gui = gui;
	}
	
	public void move(Handler handler, HandlerChoice choice) {
		
		if (choice != HandlerChoice.STAY) {
			if (choice == HandlerChoice.CARD) {
				Card chosenCard = this.gui.getCard(this.hatchingGround.getUnclaimedEggs());
				chosenCard.handler = handler;
				handler.setLocation(chosenCard.name);
			} else if (choice == HandlerChoice.FIELD) {

			}
			if (choice == HandlerChoice.FIELD_WHITESPACE) {

			}
			handler.moveToState(choice.getState());
		}

	}
	
}
