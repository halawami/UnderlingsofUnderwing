package underlings.card;

import java.util.Arrays;
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

    public List<Card> getCards() {
        return Arrays.asList(this.constructCards());
    }

    private Card[] constructCards() {
        return this.gson.fromJson(this.jsonString, Card[].class);
    }

}