package tests.cardFactory;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import org.easymock.EasyMock;
import org.junit.Test;
import underlings.card.CardFactory;
import underlings.card.Family;
import underlings.card.Temperature;
import underlings.game.Card;

import java.io.StringReader;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import static org.junit.Assert.assertEquals;

public class GetCardsTests {

    @Test
    public void testOneCard(){
        String testJson = getTestJsonOneCard();
        Gson testGson = new Gson();
        CardFactory testCardFactory = new CardFactory(testJson, testGson);

        List<Card> resultCards = testCardFactory.getCards();

        assertEquals(1, resultCards.size());

        Card testCard = resultCards.get(0);
        assertEquals("test", testCard.name);
        assertEquals("fakePath", testCard.filePath);
        assertEquals(1, testCard.points);
        assertEquals(Temperature.COOL, testCard.temperature);
        assertEquals(Family.TRIADIC, testCard.family);
    }

    @Test
    public void testTwoCards(){
        String testJson = getTestJsonTwoCards();
        Gson testGson = getTestGson();
        CardFactory testCardFactory = new CardFactory(testJson, testGson);

        List<Card> resultCards = testCardFactory.getCards();

        assertEquals(2, resultCards.size());

        Card firstCard = resultCards.get(0);
        assertEquals("first", firstCard.name);
        assertEquals("fakePath", firstCard.filePath);
        assertEquals(1, firstCard.points);
        assertEquals(Temperature.COOL, firstCard.temperature);
        assertEquals(Family.TRIADIC, firstCard.family);

        Card secondCard = resultCards.get(1);
        assertEquals("second", secondCard.name);
        assertEquals("diffFakePath", secondCard.filePath);
        assertEquals(2, secondCard.points);
        assertEquals(Temperature.NEUTRAL, secondCard.temperature);
        assertEquals(Family.TERTIARY, secondCard.family);


    }

    private Gson getTestGson() {
        return new Gson();
    }

    private String getTestJsonOneCard() {
        return "[{\"name\":\"test\",\"filePath\":\"fakePath\",\"points\":1,\"temperature\":\"COOL\",\"family\":\"TRIADIC\"}]";
    }

    private String getTestJsonTwoCards() {
        return "[{\"name\":\"first\",\"filePath\":\"fakePath\",\"points\":1,\"temperature\":\"COOL\",\"family\":\"TRIADIC\"},{\"name\":\"second\",\"filePath\":\"diffFakePath\",\"points\":2,\"temperature\":\"NEUTRAL\",\"family\":\"TERTIARY\"}]";
    }
}
