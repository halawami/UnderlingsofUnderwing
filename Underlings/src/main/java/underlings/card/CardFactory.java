package underlings.card;

import java.io.StringReader;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import underlings.game.Card;

public class CardFactory {

    private Gson gson;
    private String jsonString;

    public CardFactory() {

    }

    public CardFactory(String jsonString, Gson gson) {
        this.jsonString = jsonString;
        this.gson = gson;
    }

    public List<Card> getCards() {
        return Arrays.asList(constructCards());
    }

    private Card[] constructCards() {
        return this.gson.fromJson(jsonString, Card[].class);
    }


}