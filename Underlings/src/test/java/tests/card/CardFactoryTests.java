package tests.card;

import static org.junit.Assert.assertEquals;

import java.io.StringReader;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import org.easymock.EasyMock;
import org.junit.Test;

import com.google.gson.stream.JsonReader;

import underlings.card.CardFactory;
import underlings.card.Family;
import underlings.card.Temperature;
import underlings.game.Card;

public class CardFactoryTests {

    @Test
    public void testGetPackCards_onePack_oneCard(){
        CardFactory testCardFactory = EasyMock.partialMockBuilder(CardFactory.class).addMockedMethod("getCardsJsonReader").addMockedMethod("getPackNames").createMock();

        List<String> listOfSize1 = Arrays.asList("");
        EasyMock.expect(testCardFactory.getPackNames()).andReturn(listOfSize1);

        JsonReader jsonReader = getFakeJsonReader();
        EasyMock.expect(testCardFactory.getCardsJsonReader(EasyMock.anyString())).andReturn(jsonReader);

        EasyMock.replay(testCardFactory);

        Stack<Card> resultCards = testCardFactory.getCards();

        assertEquals(1, resultCards.size());

        Card firstCard = resultCards.pop();
        assertEquals("test", firstCard.name);
        assertEquals("fakePath", firstCard.filePath);
        assertEquals(1, firstCard.points);
        assertEquals(Temperature.COOL, firstCard.temperature);
        assertEquals(Family.TRIADIC, firstCard.family);

        EasyMock.verify(testCardFactory);
    }

    @Test
    public void testGetPackCards_onePack_twoCards(){
        CardFactory testCardFactory = EasyMock.partialMockBuilder(CardFactory.class).addMockedMethod("getCardsJsonReader").addMockedMethod("getPackNames").createMock();

        List<String> listOfSize1 = Arrays.asList("");
        EasyMock.expect(testCardFactory.getPackNames()).andReturn(listOfSize1);

        JsonReader jsonReader = getFakeTwoCardsJsonReader();
        EasyMock.expect(testCardFactory.getCardsJsonReader(EasyMock.anyString())).andReturn(jsonReader);

        EasyMock.replay(testCardFactory);

        Stack<Card> resultCards = testCardFactory.getCards();

        assertEquals(2, resultCards.size());

        Card secondCard = resultCards.pop();
        assertEquals("second", secondCard.name);
        assertEquals("diffFakePath", secondCard.filePath);
        assertEquals(2, secondCard.points);
        assertEquals(Temperature.NEUTRAL, secondCard.temperature);
        assertEquals(Family.TERTIARY, secondCard.family);

        Card firstCard = resultCards.pop();
        assertEquals("first", firstCard.name);
        assertEquals("fakePath", firstCard.filePath);
        assertEquals(1, firstCard.points);
        assertEquals(Temperature.COOL, firstCard.temperature);
        assertEquals(Family.TRIADIC, firstCard.family);


        EasyMock.verify(testCardFactory);
    }


    private JsonReader getFakeJsonReader() {
        String testJson = getTestJson();
        return new JsonReader(new StringReader(testJson));
    }

    private String getTestJson() {
        String testJson ="[{\"name\":\"test\",\"filePath\":\"fakePath\",\"points\":1,\"temperature\":\"COOL\",\"family\":\"TRIADIC\"}]";
        return testJson;
    }

    private JsonReader getFakeTwoCardsJsonReader() {
        String testJson = getTestTwoJson();
        return new JsonReader(new StringReader(testJson));
    }

    private String getTestTwoJson(){
        String testJson = "[{\"name\":\"first\",\"filePath\":\"fakePath\",\"points\":1,\"temperature\":\"COOL\",\"family\":\"TRIADIC\"},{\"name\":\"second\",\"filePath\":\"diffFakePath\",\"points\":2,\"temperature\":\"NEUTRAL\",\"family\":\"TERTIARY\"}]";
        return testJson;
    }
}
