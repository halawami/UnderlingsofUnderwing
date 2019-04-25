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
		
		switch (choice) {
		case CARD:
			Card chosenCard = this.gui.getCard(this.hatchingGround.getUnclaimedEggs());
			chosenCard.handler = handler;
			handler.setLocation(chosenCard.name);
			break;
		case FIELD:
			
			break;
		case FIELD_WHITESPACE:
			
			break;
		case STAY:
			return;
		default:
			break;
		}
		
		handler.moveToState(choice.getState());
		

	}
	
}
