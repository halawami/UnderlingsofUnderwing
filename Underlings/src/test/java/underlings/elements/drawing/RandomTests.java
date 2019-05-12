package underlings.elements.drawing;

import static org.junit.Assert.assertEquals;

import java.util.Random;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import underlings.element.Element;
import underlings.element.ElementBag;
import underlings.element.ElementColor;
import underlings.element.ElementFactory;

public class RandomTests {

	private ElementBag elementBag;
	private Random random;

	@Before
	public void init() {
		ElementFactory elementFactory = new ElementFactory();
		this.random = EasyMock.createMock(Random.class);
		this.elementBag = new ElementBag(elementFactory, this.random);
	}

	@Test
	public void testAll() {
		assertEquals(ElementColor.BLUE, this.drawElement(Choice.RANDOM, 10));
		assertEquals(ElementColor.RED, this.drawElement(Choice.RANDOM, 30));
		assertEquals(ElementColor.YELLOW, this.drawElement(Choice.RANDOM, 50));
		assertEquals(ElementColor.PURPLE, this.drawElement(Choice.RANDOM, 65));
		assertEquals(ElementColor.GREEN, this.drawElement(Choice.RANDOM, 75));
		assertEquals(ElementColor.ORANGE, this.drawElement(Choice.RANDOM, 85));
		assertEquals(ElementColor.WHITE, this.drawElement(Choice.RANDOM, 92));
		assertEquals(ElementColor.BLACK, this.drawElement(Choice.RANDOM, 97));
	}

	@Test
	public void testPrimary() {
		assertEquals(ElementColor.BLUE, this.drawElement(Choice.PRIMARY, 10));
		assertEquals(ElementColor.RED, this.drawElement(Choice.PRIMARY, 30));
		assertEquals(ElementColor.YELLOW, this.drawElement(Choice.PRIMARY, 50));
	}

	@Test
	public void testSecondary() {
		assertEquals(ElementColor.PURPLE, this.drawElement(Choice.SECONDARY, 5));
		assertEquals(ElementColor.GREEN, this.drawElement(Choice.SECONDARY, 15));
		assertEquals(ElementColor.ORANGE, this.drawElement(Choice.SECONDARY, 25));
	}

	@Test
	public void testCool() {
		assertEquals(ElementColor.BLUE, this.drawElement(Choice.COOL, 5));
		assertEquals(ElementColor.GREEN, this.drawElement(Choice.COOL, 25));
		assertEquals(ElementColor.PURPLE, this.drawElement(Choice.COOL, 35));

	}

	@Test
	public void testWarm() {
		assertEquals(ElementColor.RED, this.drawElement(Choice.WARM, 10));
		assertEquals(ElementColor.YELLOW, this.drawElement(Choice.WARM, 25));
		assertEquals(ElementColor.ORANGE, this.drawElement(Choice.WARM, 45));

	}

	public enum Choice {
		RANDOM, PRIMARY, SECONDARY, COOL, WARM;
	}

	private ElementColor drawElement(Choice choice, int randomNumber) {
		this.randomMock(randomNumber);
		Element drawnElement;

		switch (choice) {
		case PRIMARY:
			drawnElement = this.elementBag.drawPrimaryElement();
			break;
		case SECONDARY:
			drawnElement = this.elementBag.drawSecondayElement();
			break;
		case COOL:
			drawnElement = this.elementBag.drawCoolElement();
			break;
		case WARM:
			drawnElement = this.elementBag.drawWarmElement();
			break;
		default:
			drawnElement = this.elementBag.drawRandomElement();
			break;
		}

		this.init();
		return drawnElement.getColor();
	}

	private void randomMock(int returnValue) {
		EasyMock.expect(this.random.nextInt(EasyMock.anyInt())).andStubReturn(returnValue);
		EasyMock.replay(this.random);
	}
}
