package underlings.card;

import org.easymock.EasyMock;
import org.junit.Ignore;
import org.junit.Test;

import underlings.card.effect.Effect;
import underlings.card.effect.domestic.ElementGiverEffect;
import underlings.element.ElementGiver;
import underlings.gui.DrawChoice;

public class GetElementGiverTests {

    @Test
    @Ignore
    public void testNoElementGivers() {
        Card testedCard = new Card();
        testedCard.domesticEffects = new Effect[0];

        // List<ElementGiver> elementGivers = testedCard.getElementGivers();

        // Assert.assertEquals(0, elementGivers.size());
    }

    @Test
    @Ignore
    public void testOneElementGiversFirst() {
        ElementGiverEffect elementGiverEffect = new ElementGiverEffect();
        ElementGiver testElementGiver = new ElementGiver("test", DrawChoice.RED);
        elementGiverEffect.elementGiver = testElementGiver;
        Card testedCard = new Card();
        testedCard.domesticEffects = new Effect[] {elementGiverEffect};

        // List<ElementGiver> elementGivers = testedCard.getElementGivers();

        // Assert.assertEquals(1, elementGivers.size());
        // Assert.assertEquals(testElementGiver, elementGivers.get(0));
    }

    @Test
    @Ignore
    public void testOneElementGiversMiddle() {
        Effect mockEffect1 = EasyMock.mock(Effect.class);
        Effect mockEffect2 = EasyMock.mock(Effect.class);

        ElementGiverEffect elementGiverEffect = new ElementGiverEffect();
        ElementGiver testElementGiver = new ElementGiver("test", DrawChoice.RED);
        elementGiverEffect.elementGiver = testElementGiver;

        Card testedCard = new Card();
        testedCard.domesticEffects = new Effect[] {mockEffect1, elementGiverEffect, mockEffect2};

        // List<ElementGiver> elementGivers = testedCard.getElementGivers();

        // Assert.assertEquals(1, elementGivers.size());
        // Assert.assertEquals(testElementGiver, elementGivers.get(0));
    }

    @Test
    @Ignore
    public void testOneElementGiversLast() {
        Effect mockEffect1 = EasyMock.mock(Effect.class);
        Effect mockEffect2 = EasyMock.mock(Effect.class);

        ElementGiverEffect elementGiverEffect = new ElementGiverEffect();
        ElementGiver testElementGiver = new ElementGiver("test", DrawChoice.RED);
        elementGiverEffect.elementGiver = testElementGiver;

        Card testedCard = new Card();
        testedCard.domesticEffects = new Effect[] {mockEffect1, mockEffect2, elementGiverEffect};

        // List<ElementGiver> elementGivers = testedCard.getElementGivers();
        //
        // Assert.assertEquals(1, elementGivers.size());
        // Assert.assertEquals(testElementGiver, elementGivers.get(0));
    }

    @Test
    @Ignore
    public void testTwoElementGivers() {
        Effect mockEffect1 = EasyMock.mock(Effect.class);

        ElementGiverEffect elementGiverEffect1 = new ElementGiverEffect();
        ElementGiver testElementGiver1 = new ElementGiver("test", DrawChoice.RED);
        elementGiverEffect1.elementGiver = testElementGiver1;

        ElementGiverEffect elementGiverEffect2 = new ElementGiverEffect();
        ElementGiver testElementGiver2 = new ElementGiver("test2", DrawChoice.RED);
        elementGiverEffect2.elementGiver = testElementGiver2;

        Card testedCard = new Card();
        testedCard.domesticEffects = new Effect[] {mockEffect1, elementGiverEffect1, elementGiverEffect2};

        // List<ElementGiver> elementGivers = testedCard.getElementGivers();
        //
        // Assert.assertEquals(2, elementGivers.size());
        // Assert.assertEquals(testElementGiver1, elementGivers.get(0));
        // Assert.assertEquals(testElementGiver2, elementGivers.get(1));
    }

}
