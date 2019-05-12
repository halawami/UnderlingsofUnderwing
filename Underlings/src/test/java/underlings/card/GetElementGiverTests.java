package underlings.card;

import java.util.List;
import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Test;
import underlings.card.effect.Effect;
import underlings.card.effect.domestic.ElementGiverEffect;
import underlings.element.ElementGiver;
import underlings.gui.DrawChoice;

public class GetElementGiverTests {

    @Test
    public void testNoElementGivers() {
        Card testedCard = new Card();
        testedCard.domesticEffects = new Effect[0];

        List<ElementGiver> elementGivers = testedCard.getElementGivers();

        Assert.assertEquals(0, elementGivers.size());
    }

    @Test
    public void testOneElementGiversFirst() {
        ElementGiverEffect elementGiverEffect = new ElementGiverEffect(DrawChoice.RED);
        Card testedCard = new Card();
        testedCard.domesticEffects = new Effect[] {elementGiverEffect};

        List<ElementGiver> elementGivers = testedCard.getElementGivers();

        Assert.assertEquals(1, elementGivers.size());
        Assert.assertEquals(elementGiverEffect, elementGivers.get(0));
    }

    @Test
    public void testOneElementGiversMiddle() {
        Effect mockEffect1 = EasyMock.mock(Effect.class);
        Effect mockEffect2 = EasyMock.mock(Effect.class);

        ElementGiverEffect elementGiverEffect = new ElementGiverEffect(DrawChoice.RED);

        Card testedCard = new Card();
        testedCard.domesticEffects = new Effect[] {mockEffect1, elementGiverEffect, mockEffect2};

        List<ElementGiver> elementGivers = testedCard.getElementGivers();

        Assert.assertEquals(1, elementGivers.size());
        Assert.assertEquals(elementGiverEffect, elementGivers.get(0));
    }

    @Test
    public void testOneElementGiversLast() {
        Effect mockEffect1 = EasyMock.mock(Effect.class);
        Effect mockEffect2 = EasyMock.mock(Effect.class);

        ElementGiverEffect elementGiverEffect = new ElementGiverEffect(DrawChoice.RED);

        Card testedCard = new Card();
        testedCard.domesticEffects = new Effect[] {mockEffect1, mockEffect2, elementGiverEffect};

        List<ElementGiver> elementGivers = testedCard.getElementGivers();

        Assert.assertEquals(1, elementGivers.size());
        Assert.assertEquals(elementGiverEffect, elementGivers.get(0));
    }

    @Test
    public void testTwoElementGivers() {
        Effect mockEffect1 = EasyMock.mock(Effect.class);

        ElementGiverEffect elementGiverEffect1 = new ElementGiverEffect(DrawChoice.RED);
        ElementGiverEffect elementGiverEffect2 = new ElementGiverEffect(DrawChoice.BLUE);

        Card testedCard = new Card();
        testedCard.domesticEffects = new Effect[] {mockEffect1, elementGiverEffect1, elementGiverEffect2};

        List<ElementGiver> elementGivers = testedCard.getElementGivers();

        Assert.assertEquals(2, elementGivers.size());
        Assert.assertEquals(elementGiverEffect1, elementGivers.get(0));
        Assert.assertEquals(elementGiverEffect2, elementGivers.get(1));
    }

}
