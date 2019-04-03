package underlings.card;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import underlings.game.Card;

import java.io.StringReader;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class CardFactory {

    public Stack<Card> getCards() {
        List<String> packNames = this.getPackNames();
        JsonReader jsonReader = getCardsJsonReader("");
        Stack<Card> allCards = new Stack<>();
        Card card = new Card();
        card.name = "test";
        card.filePath = "fakePath";
        card.points = 1;
        card.temperature = Temperature.COOL;
        card.family = Family.TRIADIC;
        allCards.add(card);
        return allCards;
    }


    public List<String> getPackNames() {
        return null;
    }



    public JsonReader getCardsJsonReader(String packName) {
        return null;
    }


}