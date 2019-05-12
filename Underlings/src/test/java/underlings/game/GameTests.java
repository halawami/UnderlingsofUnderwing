package underlings.game;

import org.easymock.EasyMock;
import org.junit.Test;
import underlings.element.ElementBag;
import underlings.gui.Gui;
import underlings.player.PlayerFactory;

public class GameTests {

    @Test
    public void testDisplay() {
        Gui gui = EasyMock.mock(Gui.class);
        HatchingGround hatchingGround = EasyMock.mock(HatchingGround.class);
        PlayerFactory playerFactory = EasyMock.mock(PlayerFactory.class);
        ElementBag elementBag = EasyMock.mock(ElementBag.class);

        Game game = new Game(gui, hatchingGround, playerFactory, elementBag);

        gui.display(EasyMock.anyInt(), EasyMock.anyInt(), EasyMock.anyInt(), EasyMock.anyObject(), EasyMock.anyObject(),
                EasyMock.anyObject());

        EasyMock.replay(gui, hatchingGround, playerFactory, elementBag);

        game.display();

        EasyMock.verify(gui, hatchingGround, playerFactory, elementBag);
    }

}
