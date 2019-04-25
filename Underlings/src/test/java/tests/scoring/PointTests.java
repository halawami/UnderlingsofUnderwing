package tests.scoring;

import static org.junit.Assert.assertEquals;

import java.util.Collections;

import org.junit.Test;

import underlings.scoring.ScoreUtils;

public class PointTests {

	@Test
	public void testEmpty() {
		ScoreUtils scoreUtils = new ScoreUtils();
		
		int points = scoreUtils.calculatePoints(Collections.emptyList());
		
		assertEquals(0, points);
	}
	
}
