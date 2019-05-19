package underlings.game;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class GamePropertiesTests {

    @Test
    public void testBuilderPattern() {
        GameProperties props = GameProperties.TWO_PLAYERS;
        assertEquals(props, props.haveRoundsOf(15));
        assertEquals(props, props.hatchingGroundOfWidth(3));
        assertEquals(props, props.andHeight(2));
    }
}
