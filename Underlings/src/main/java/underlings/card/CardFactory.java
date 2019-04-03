package underlings.card;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import underlings.game.Card;

public class CardFactory {

    public Stack<Card> getCards() {
        Stack<Card> allCards = new Stack<>();

//        List<String> packNames = this.getPackNames();
//        List<Card> packCards = this.getPackCards("");
//        allCards.addAll(packCards);
        for (int i = 0; i < 50; i++) {
        	allCards.add(new Card());
        }

        return allCards;
    }

    public List<String> getPackNames() {
        return null;
    }

    private List<Card> getPackCards(String packName) {
        JsonReader packCardsReader = this.getCardsJsonReader("");
        Card[] constructedCard = this.constructPackCards(packCardsReader);
        return Arrays.asList(constructedCard);
    }

    public JsonReader getCardsJsonReader(String packName) {
        return null;
    }

    private Card[] constructPackCards(JsonReader packCardsReader) {
        Gson gson = new Gson();
        return gson.fromJson(packCardsReader, Card[].class);
    }


}