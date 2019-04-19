package underlings.card;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import underlings.game.Card;

public class CardFactory {

    private Gson gson;
    private String jsonString;

    public CardFactory(String jsonString, Gson gson) {
        this.jsonString = jsonString;
        this.gson = gson;
    }

    public CardFactory() {
		// TODO Auto-generated constructor stub
	}

	public List<Card> getCards() {
		List<Card> cards = new ArrayList<Card>();
		for (int i = 0; i < 50; i++) {
			cards.add(new Card());
		}
		return cards;
    }

    private Card[] constructCards() {
        return this.gson.fromJson(this.jsonString, Card[].class);
    }

}