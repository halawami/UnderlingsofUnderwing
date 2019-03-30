package underlings.game;

import java.util.Stack;

public class CardFactory {

	public Stack<Card> getCards() {
		Stack<Card> cards = new Stack<Card>();
		for(int i = 0; i < 50; i++) {
			cards.push(new Card());
		}
		return cards;
	}

}