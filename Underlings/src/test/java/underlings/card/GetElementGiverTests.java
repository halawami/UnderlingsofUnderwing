package underlings.card;

import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import underlings.MockTest;
import underlings.card.effect.Effect;
import underlings.card.effect.domestic.ElementGiverEffect;
import underlings.element.ElementGiver;
import underlings.gui.DrawChoice;

public class GetElementGiverTests extends MockTest {

    @Before
    public void init() {
        this.effect1 = this.mock(Effect.class);
        this.effect2 = this.mock(Effect.class);

    }

    @Test
    public void testNoElementGivers() {
        Card testedCard = new Card();
        testedCard.domesticEffects = new Effect[0];
        this.replayAll();
        List<ElementGiver> elementGivers = testedCard.getElementGivers();

        Assert.assertEquals(0, elementGivers.size());
    }

    @Test
    public void testOneElementGiversFirst() {
        ElementGiverEffect elementGiverEffect = new ElementGiverEffect(DrawChoice.RED);
        Card testedCard = new Card();
        testedCard.domesticEffects = new Effect[] {elementGiverEffect};
        this.replayAll();
        List<ElementGiver> elementGivers = testedCard.getElementGivers();

        Assert.assertEquals(1, elementGivers.size());
        Assert.assertEquals(elementGiverEffect, elementGivers.get(0));
    }

    @Test
    public void testOneElementGiversMiddle() {
        ElementGiverEffect elementGiverEffect = new ElementGiverEffect(DrawChoice.RED);

        Card testedCard = new Card();
        testedCard.domesticEffects = new Effect[] {this.effect1, elementGiverEffect, this.effect2};
        this.replayAll();
        List<ElementGiver> elementGivers = testedCard.getElementGivers();

        Assert.assertEquals(1, elementGivers.size());
        Assert.assertEquals(elementGiverEffect, elementGivers.get(0));
    }

    @Test
    public void testOneElementGiversLast() {
        ElementGiverEffect elementGiverEffect = new ElementGiverEffect(DrawChoice.RED);

        Card testedCard = new Card();
        testedCard.domesticEffects = new Effect[] {this.effect1, this.effect2, elementGiverEffect};
        this.replayAll();
        List<ElementGiver> elementGivers = testedCard.getElementGivers();

        Assert.assertEquals(1, elementGivers.size());
        Assert.assertEquals(elementGiverEffect, elementGivers.get(0));
    }

    @Test
    public void testTwoElementGivers() {

        ElementGiverEffect elementGiverEffect1 = new ElementGiverEffect(DrawChoice.RED);
        ElementGiverEffect elementGiverEffect2 = new ElementGiverEffect(DrawChoice.BLUE);

        Card testedCard = new Card();
        testedCard.domesticEffects = new Effect[] {this.effect1, elementGiverEffect1, elementGiverEffect2};
        this.replayAll();

        List<ElementGiver> elementGivers = testedCard.getElementGivers();

        Assert.assertEquals(2, elementGivers.size());
        Assert.assertEquals(elementGiverEffect1, elementGivers.get(0));
        Assert.assertEquals(elementGiverEffect2, elementGivers.get(1));
    }

}
