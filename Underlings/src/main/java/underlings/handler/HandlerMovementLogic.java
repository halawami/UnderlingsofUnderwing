package underlings.handler;

import underlings.card.Card;

public class HandlerMovementLogic {

	public void move(Handler handler, HandlerChoice choice) {
		
		if (choice != HandlerChoice.STAY) {
			if (choice == HandlerChoice.CARD) {
				Card chosenCard = this.gui.promptHandler.promptChoice("Choose a card",
						this.hatchingGround.getUnclaimedEggs());
				chosenCard.handler = chosenHandler;
				chosenHandler.setLocation(chosenCard.name);
			} else if (choice == HandlerChoice.FIELD) {

			}
			if (choice == HandlerChoice.FIELD_WHITESPACE) {

			}
			chosenHandler.moveToState(choice.getState());
		}

	}
	
}
