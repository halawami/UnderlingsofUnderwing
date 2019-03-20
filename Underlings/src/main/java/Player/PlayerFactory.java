package Player;

public class PlayerFactory {

	public Player createPlayer(int maxHandlers) {
		return new Player(maxHandlers);
	}
	
}
