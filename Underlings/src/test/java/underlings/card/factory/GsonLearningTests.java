package underlings.card.factory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;

import java.io.IOException;

import org.junit.Test;

import underlings.card.Card;
import underlings.card.Family;
import underlings.card.Temperature;
import underlings.card.effect.Effect;
import underlings.card.effect.domestic.element.CollectElementEffect;
import underlings.card.effect.domestic.players.player.UseBlackOrWhiteInPlaceEffect;
import underlings.card.effect.wild.adjacenteggs.elements.add.AddElementsEffect;
import underlings.element.ElementColor;
import underlings.element.ElementSpace;
import underlings.element.ElementSpacePosition;

public class GsonLearningTests {

    @Test
    public void testOneCardNoEffects() throws IOException {
        Gson gson = new Gson();
        Card[] cards = gson.fromJson(this.getJsonAsString("oneCardNoEffects.json"), Card[].class);

        assertEquals(1, cards.length);

        Card testCard = cards[0];
        assertEquals("test", testCard.name);
        assertEquals(1, testCard.points);
        assertEquals(Temperature.COOL, testCard.temperature);
        assertEquals(Family.TRIADIC, testCard.family);
        assertEquals(2, testCard.elementSpaces.length);

        ElementSpace firstElementSpace = testCard.elementSpaces[0];
        assertEquals(ElementColor.BLUE, firstElementSpace.color);
        assertEquals(ElementSpacePosition.L3_2, firstElementSpace.position);

        ElementSpace secondElementSpace = testCard.elementSpaces[1];
        assertEquals(ElementColor.RED, secondElementSpace.color);
        assertEquals(ElementSpacePosition.R3_2, secondElementSpace.position);
    }

    @Test
    public void testTwoCardsNoEffects() throws IOException {
        Gson gson = new Gson();
        Card[] cards = gson.fromJson(this.getJsonAsString("twoCardsNoEffects.json"), Card[].class);

        assertEquals(2, cards.length);

        Card firstCard = cards[0];
        assertEquals("first", firstCard.name);
        assertEquals(1, firstCard.points);
        assertEquals(Temperature.COOL, firstCard.temperature);
        assertEquals(Family.TRIADIC, firstCard.family);

        ElementSpace firstCardFirstElementSpace = firstCard.elementSpaces[0];
        assertEquals(ElementColor.BLUE, firstCardFirstElementSpace.color);
        assertEquals(ElementSpacePosition.L3_2, firstCardFirstElementSpace.position);

        ElementSpace firstCardSecondElementSpace = firstCard.elementSpaces[1];
        assertEquals(ElementColor.RED, firstCardSecondElementSpace.color);
        assertEquals(ElementSpacePosition.R3_2, firstCardSecondElementSpace.position);

        Card secondCard = cards[1];
        assertEquals("second", secondCard.name);
        assertEquals(2, secondCard.points);
        assertEquals(Temperature.NEUTRAL, secondCard.temperature);
        assertEquals(Family.TERTIARY, secondCard.family);

        ElementSpace secondCardFirstElementSpace = secondCard.elementSpaces[0];
        assertEquals(ElementColor.GREEN, secondCardFirstElementSpace.color);
        assertEquals(ElementSpacePosition.L3_3, secondCardFirstElementSpace.position);

        ElementSpace secondCardSecondElementSpace = secondCard.elementSpaces[1];
        assertEquals(ElementColor.PURPLE, secondCardSecondElementSpace.color);
        assertEquals(ElementSpacePosition.R3_3, secondCardSecondElementSpace.position);
    }

    @Test
    public void testOneCardSimpleEffects() throws IOException {
        Gson gson = this.getGsonWithRegisteredEffects();
        Card[] cards = gson.fromJson(this.getJsonAsString("oneCardSimpleEffect.json"), Card[].class);

        Card testCard = cards[0];

        Effect[] domesticEffects = testCard.domesticEffects;
        assertEquals(1, domesticEffects.length);
        assertTrue(domesticEffects[0] instanceof UseBlackOrWhiteInPlaceEffect);

        Effect[] wildEffects = testCard.wildEffects;
        assertEquals(1, wildEffects.length);
        assertTrue(wildEffects[0] instanceof CollectElementEffect);
    }

    @Test
    public void testOneCardComplexEffects() throws IOException {
        Gson gson = this.getGsonWithRegisteredEffects();
        Card[] cards = gson.fromJson(this.getJsonAsString("oneCardComplexEffect.json"), Card[].class);

        Card testCard = cards[0];
        assertEquals(2, testCard.domesticEffects.length);
    }

    @Test
    public void testOneCardVariableEffect() throws IOException {
        Gson gson = this.getGsonWithRegisteredEffects();
        Card[] cards = gson.fromJson(this.getJsonAsString("oneCardVariableEffect.json"), Card[].class);

        Card testCard = cards[0];
        Effect wildEffect = testCard.wildEffects[0];

        assertTrue(wildEffect instanceof AddElementsEffect);
        assertEquals(ElementColor.WHITE, ((AddElementsEffect) wildEffect).elementColors[0]);
    }

    private Gson getGsonWithRegisteredEffects() {
        RuntimeTypeAdapterFactory<Effect> effectsTypeAdapter =
                RuntimeTypeAdapterFactory.of(Effect.class, "effect").registerSubtype(UseBlackOrWhiteInPlaceEffect.class)
                        .registerSubtype(CollectElementEffect.class).registerSubtype(AddElementsEffect.class);

        return new GsonBuilder().registerTypeAdapterFactory(effectsTypeAdapter).create();
    }


    private String getJsonAsString(String fileName) throws IOException {
        return Resources.toString(Resources.getResource(fileName), Charsets.UTF_8);
    }


}
