package underlings.player;

import underlings.game.HandlerFactory;

public class PlayerFactory {

	private HandlerFactory handlerFactory;
	
	public PlayerFactory(HandlerFactory handlerFactory) {
		this.handlerFactory = handlerFactory;
	}

	public Player createPlayer(int maxHandlers) {
		return new Player(maxHandlers, this.handlerFactory);
	}
	
}
