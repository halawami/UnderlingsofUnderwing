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

        List<ElementGiver> elementGivers = testedCard.getElementGivers();

        Assert.assertEquals(0, elementGivers.size());
    }

    @Test
    public void testOneElementGiversFirst() {
        ElementGiverEffect elementGiverEffect = new ElementGiverEffect();
        ElementGiver testElementGiver = new ElementGiver("test", DrawChoice.RED);
        elementGiverEffect.elementGiver = testElementGiver;
        Card testedCard = new Card();
        testedCard.domesticEffects = new Effect[]{elementGiverEffect};

        List<ElementGiver> elementGivers = testedCard.getElementGivers();

        Assert.assertEquals(1, elementGivers.size());
        Assert.assertEquals(testElementGiver, elementGivers.get(0));
    }

    @Test
    public void testOneElementGiversMiddle() {
        Effect mockEffect1 = EasyMock.mock(Effect.class);
        Effect mockEffect2 = EasyMock.mock(Effect.class);

        ElementGiverEffect elementGiverEffect = new ElementGiverEffect();
        ElementGiver testElementGiver = new ElementGiver("test", DrawChoice.RED);
        elementGiverEffect.elementGiver = testElementGiver;

        Card testedCard = new Card();
        testedCard.domesticEffects = new Effect[]{mockEffect1, elementGiverEffect, mockEffect2};

        List<ElementGiver> elementGivers = testedCard.getElementGivers();

        Assert.assertEquals(1, elementGivers.size());
        Assert.assertEquals(testElementGiver, elementGivers.get(0));
    }

    @Test
    public void testOneElementGiversLast() {
        Effect mockEffect1 = EasyMock.mock(Effect.class);
        Effect mockEffect2 = EasyMock.mock(Effect.class);

        ElementGiverEffect elementGiverEffect = new ElementGiverEffect();
        ElementGiver testElementGiver = new ElementGiver("test", DrawChoice.RED);
        elementGiverEffect.elementGiver = testElementGiver;

        Card testedCard = new Card();
        testedCard.domesticEffects = new Effect[]{mockEffect1, mockEffect2, elementGiverEffect};

        List<ElementGiver> elementGivers = testedCard.getElementGivers();

        Assert.assertEquals(1, elementGivers.size());
        Assert.assertEquals(testElementGiver, elementGivers.get(0));
    }

}
