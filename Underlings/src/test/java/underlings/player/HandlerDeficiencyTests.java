package underlings.player;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import underlings.Constructors;

public class HandlerDeficiencyTests {

    private Player player;

    @Before
    public void init() {
        this.player = Constructors.Player();
    }

    @Test
    public void test11Points2Handlers() {
        this.player.addPoints(11);
        assertEquals(2, this.player.getHandlerCount());
    }

    @Test
    public void test11PointsMaxHandlers() {
        this.gainHandler(4);

        this.player.addPoints(11);
        assertEquals(6, this.player.getHandlerCount());
    }

    @Test
    public void test12Points2Handlers() {
        this.player.addPoints(12);
        assertEquals(3, this.player.getHandlerCount());
    }

    @Test
    public void test12Points3Handlers() {
        this.gainHandler(1);

        this.player.addPoints(12);
        assertEquals(3, this.player.getHandlerCount());
    }

    @Test
    public void test12PointsMaxHandlers() {
        this.gainHandler(4);

        this.player.addPoints(12);
        assertEquals(6, this.player.getHandlerCount());
    }

    @Test
    public void test25PointsFrom12With2Handlers() {
        this.player.addPoints(12);
        this.player.loseHandler();

        this.player.addPoints(13);
        assertEquals(3, this.player.getHandlerCount());
    }

    @Test
    public void test25PointsFrom12With3Handlers() {
        this.player.addPoints(12);

        this.player.addPoints(13);
        assertEquals(4, this.player.getHandlerCount());
    }

    @Test
    public void test25PointsFrom12With4Handlers() {
        this.player.addPoints(12);
        this.gainHandler(1);

        this.player.addPoints(13);
        assertEquals(4, this.player.getHandlerCount());
    }

    @Test
    public void test25PointsFrom12WithMaxHandlers() {
        this.player.addPoints(12);
        this.gainHandler(3);

        this.player.addPoints(13);
        assertEquals(6, this.player.getHandlerCount());
    }

    @Test
    public void test25PointsFrom0With2Handlers() {
        this.player.addPoints(25);
        assertEquals(4, this.player.getHandlerCount());
    }

    @Test
    public void test25PointsFrom0With3Handlers() {
        this.gainHandler(1);

        this.player.addPoints(25);
        assertEquals(4, this.player.getHandlerCount());
    }

    @Test
    public void test25PointsFrom0With4Handlers() {
        this.gainHandler(2);

        this.player.addPoints(25);
        assertEquals(4, this.player.getHandlerCount());
    }

    @Test
    public void test25PointsFrom0WithMaxHandlers() {
        this.gainHandler(4);

        this.player.addPoints(25);
        assertEquals(6, this.player.getHandlerCount());
    }

    @Test
    public void test12PointsTwice() {
        this.player.addPoints(12);
        this.player.loseHandler();
        this.player.losePoints(12);

        this.player.addPoints(12);
        assertEquals(2, this.player.getHandlerCount());
    }

    @Test
    public void test25PointsTwice2Handlers() {
        this.player.addPoints(25);
        this.player.loseHandler();
        this.player.loseHandler();
        this.player.losePoints(25);

        this.player.addPoints(25);
        assertEquals(2, this.player.getHandlerCount());
    }

    @Test
    public void test25PointsTwice3Handlers() {
        this.player.addPoints(25);
        this.player.loseHandler();
        this.player.losePoints(25);

        this.player.addPoints(25);
        assertEquals(3, this.player.getHandlerCount());
    }

    private void gainHandler(int amt) {
        for (int i = 0; i < amt; i++) {
            this.player.gainHandler();
        }
    }

}
