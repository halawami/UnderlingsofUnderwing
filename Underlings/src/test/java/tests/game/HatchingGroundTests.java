package tests.game;

import static org.junit.Assert.assertEquals;

import org.easymock.EasyMock;
import org.junit.Test;

import underlings.card.Card;
import underlings.game.Deck;
import underlings.game.HatchingGround;

public class HatchingGroundTests {

	@Test
	public void testInitUnclaimedEggs() {
		Deck deck = EasyMock.strictMock(Deck.class);
        EasyMock.expect(deck.draw()).andReturn(new Card()).times(6);

        EasyMock.replay(deck);

        HatchingGround hatchingGround = new HatchingGround(deck);
        hatchingGround.setDimensions(3,2);
        hatchingGround.populate();
        
        EasyMock.verify(deck);
        
        assertEquals(6, hatchingGround.getUnclaimedEggs().size());
	}
	
    @Test
    public void testDeal3By2(){
        Deck deck = EasyMock.strictMock(Deck.class);
        EasyMock.expect(deck.draw()).andReturn(new Card()).times(6);

        EasyMock.replay(deck);

        HatchingGround hatchingGround = new HatchingGround(deck);
        hatchingGround.setDimensions(3,2);
        hatchingGround.populate();
        
        EasyMock.verify(deck);
    }

    @Test
    public void testDeal4By3(){
        Deck deck = EasyMock.strictMock(Deck.class);
        EasyMock.expect(deck.draw()).andReturn(new Card()).times(12);

        EasyMock.replay(deck);

        HatchingGround hatchingGround = new HatchingGround(deck);
        hatchingGround.setDimensions(4,3);
        hatchingGround.populate();
        
        EasyMock.verify(deck);
    }

    @Test
    public void testDeal4By4(){
        Deck deck = EasyMock.strictMock(Deck.class);
        EasyMock.expect(deck.draw()).andReturn(new Card()).times(16);

        EasyMock.replay(deck);

        HatchingGround hatchingGround = new HatchingGround(deck);
        hatchingGround.setDimensions(4,4);
        hatchingGround.populate();
        
        EasyMock.verify(deck);
    }
}
