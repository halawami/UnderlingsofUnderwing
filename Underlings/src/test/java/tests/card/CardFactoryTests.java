package tests.card;

import com.google.gson.stream.JsonReader;
import org.easymock.EasyMock;
import org.junit.Test;
import underlings.card.CardFactory;
import underlings.game.Card;

import java.io.StringReader;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

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
        assertEquals(firstCard.getName(), "test");

        EasyMock.verify(testCardFactory);
    }

    private JsonReader getFakeJsonReader() {
        String testJson = getTestJson();
        return new JsonReader(new StringReader(testJson));
    }

    private String getTestJson() {
        String testJson ="[{\"name\": \"test\"}]";
        return testJson;
    }
}
