package underlings.card;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import underlings.game.Card;

import java.io.File;
import java.io.StringReader;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class CardFactory {

    public Stack<Card> getCards() {
        Stack<Card> allCards = new Stack<>();

        List<String> packNames = this.getPackNames();
        List<Card> packCards = getPackCards("");
        allCards.addAll(packCards);

        return allCards;
    }

    public List<String> getPackNames() {
        return null;
    }

    private List<Card> getPackCards(String packName) {
        JsonReader packCardsReader = getCardsJsonReader("");
        Card[] constructedCard = constructPackCards(packCardsReader);
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