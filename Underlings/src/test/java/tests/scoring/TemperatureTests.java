package tests.scoring;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import underlings.card.Card;
import underlings.card.Temperature;
import underlings.scoring.ScoreUtils;

public class TemperatureTests {

	@Test
	public void testEmpty() {
		ScoreUtils scoreUtils = new ScoreUtils();
		
		int balance = scoreUtils.calculateTemperature(Collections.emptyList());
		
		assertEquals(0, balance);
	}
	
	@Test
	public void testPerfectBalance() {
		ScoreUtils scoreUtils = new ScoreUtils();

		Card cardOne = new Card();
		Card cardTwo = new Card();
		Card cardThree = new Card();

		cardOne.temperature = Temperature.WARM;
		cardTwo.temperature = Temperature.COOL;
		cardThree.temperature = Temperature.NEUTRAL;

		List<Card> cards = new ArrayList<>();
		cards.add(cardOne);
		cards.add(cardTwo);
		cards.add(cardThree);

		int balance = scoreUtils.calculateTemperature(cards);

		assertEquals(0, balance);
	}

	@Test
	public void testWarmBalance() {
		ScoreUtils scoreUtils = new ScoreUtils();

		Card cardOne = new Card();
		Card cardTwo = new Card();
		Card cardThree = new Card();
		Card cardFour = new Card();

		cardOne.temperature = Temperature.WARM;
		cardTwo.temperature = Temperature.COOL;
		cardThree.temperature = Temperature.NEUTRAL;
		cardFour.temperature = Temperature.WARM;

		List<Card> cards = new ArrayList<>();
		cards.add(cardOne);
		cards.add(cardTwo);
		cards.add(cardThree);
		cards.add(cardFour);

		int balance = scoreUtils.calculateTemperature(cards);

		assertEquals(1, balance);
	}
	
	@Test
	public void testCoolBalance() {
		ScoreUtils scoreUtils = new ScoreUtils();

		Card cardOne = new Card();
		Card cardTwo = new Card();
		Card cardThree = new Card();
		Card cardFour = new Card();

		cardOne.temperature = Temperature.WARM;
		cardTwo.temperature = Temperature.COOL;
		cardThree.temperature = Temperature.NEUTRAL;
		cardFour.temperature = Temperature.COOL;

		List<Card> cards = new ArrayList<>();
		cards.add(cardOne);
		cards.add(cardTwo);
		cards.add(cardThree);
		cards.add(cardFour);

		int balance = scoreUtils.calculateTemperature(cards);

		assertEquals(-1, balance);
	}

}
