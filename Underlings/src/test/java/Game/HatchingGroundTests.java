package Game;

import org.junit.Test;
import org.easymock.EasyMock;

public class HatchingGroundTests {

    @Test
    public void testDeal3By2(){
        Deck deck = EasyMock.strictMock(Deck.class);
        EasyMock.expect(deck.draw()).andReturn(new Card()).times(6);

        EasyMock.replay(deck);

        HatchingGround hatchingGround = new HatchingGround(3,2, deck);
        EasyMock.verify(deck);
    }

    @Test
    public void testDeal4By3(){
        Deck deck = EasyMock.strictMock(Deck.class);
        EasyMock.expect(deck.draw()).andReturn(new Card()).times(12);

        EasyMock.replay(deck);

        HatchingGround hatchingGround = new HatchingGround(4,3, deck);
        EasyMock.verify(deck);
    }






}
