package underlings.player;

import underlings.handler.HandlerFactory;

public class PlayerFactory {

	private HandlerFactory handlerFactory;
	private int playerCount;
	
	public PlayerFactory(HandlerFactory handlerFactory) {
		this.handlerFactory = handlerFactory;
		this.playerCount = 0;
	}

	public Player createPlayer(int maxHandlers) {
		Player player =  new Player(maxHandlers, this.handlerFactory, ++this.playerCount);
		return player;
	}
	
}
