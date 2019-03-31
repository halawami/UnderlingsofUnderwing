package tests.game;

import static org.junit.Assert.assertEquals;

import java.util.Random;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import underlings.element.Element;
import underlings.element.ElementBag;
import underlings.element.ElementColor;
import underlings.element.ElementFactory;

public class ElementTests {

	private ElementBag elementBag;
	private Random random;

	@Before
	public void init() {
		ElementFactory elementFactory = new ElementFactory();
		this.random = EasyMock.createMock(Random.class);
		this.elementBag = new ElementBag(elementFactory, this.random);
	}

	@Test
	public void testDrawListSingle() {
		EasyMock.expect(this.random.nextInt(EasyMock.anyInt())).andStubReturn(1);
		EasyMock.replay(this.random);
		
		Element drawnElement = this.elementBag.drawElementFromList(ElementColor.BLUE);
		assertEquals(ElementColor.BLUE, drawnElement.getColor());

		drawnElement = this.elementBag.drawElementFromList(ElementColor.RED);
		assertEquals(ElementColor.RED, drawnElement.getColor());

		drawnElement = this.elementBag.drawElementFromList(ElementColor.GREEN);
		assertEquals(ElementColor.GREEN, drawnElement.getColor());

		drawnElement = this.elementBag.drawElementFromList(ElementColor.YELLOW);
		assertEquals(ElementColor.YELLOW, drawnElement.getColor());

		drawnElement = this.elementBag.drawElementFromList(ElementColor.ORANGE);
		assertEquals(ElementColor.ORANGE, drawnElement.getColor());

		drawnElement = this.elementBag.drawElementFromList(ElementColor.PURPLE);
		assertEquals(ElementColor.PURPLE, drawnElement.getColor());

		drawnElement = this.elementBag.drawElementFromList(ElementColor.WHITE);
		assertEquals(ElementColor.WHITE, drawnElement.getColor());

		drawnElement = this.elementBag.drawElementFromList(ElementColor.BLACK);
		assertEquals(ElementColor.BLACK, drawnElement.getColor());
	}

	@Test
	public void testDrawListAllElements() {
		EasyMock.expect(this.random.nextInt(EasyMock.anyInt())).andStubReturn(65);
		EasyMock.replay(this.random);
		
		Element drawnElement = this.elementBag.drawElementFromList(ElementColor.BLUE, ElementColor.RED,
				ElementColor.YELLOW, ElementColor.PURPLE, ElementColor.GREEN, ElementColor.ORANGE, ElementColor.WHITE,
				ElementColor.BLACK);
		assertEquals(ElementColor.PURPLE, drawnElement.getColor());
	}

	@Test
	public void testInitNumberRemaining() {
		assertEquals(20, this.elementBag.getNumberRemaining(ElementColor.BLUE));
		assertEquals(20, this.elementBag.getNumberRemaining(ElementColor.RED));
		assertEquals(20, this.elementBag.getNumberRemaining(ElementColor.YELLOW));
		assertEquals(10, this.elementBag.getNumberRemaining(ElementColor.PURPLE));
		assertEquals(10, this.elementBag.getNumberRemaining(ElementColor.GREEN));
		assertEquals(10, this.elementBag.getNumberRemaining(ElementColor.ORANGE));
		assertEquals(5, this.elementBag.getNumberRemaining(ElementColor.WHITE));
		assertEquals(5, this.elementBag.getNumberRemaining(ElementColor.BLACK));
	}

	@Test
	public void testNumberRemainingAfterDraw() {
		assertEquals(20, this.elementBag.getNumberRemaining(ElementColor.BLUE));
		this.elementBag.drawElementFromList(ElementColor.BLUE);
		assertEquals(19, this.elementBag.getNumberRemaining(ElementColor.BLUE));
	}

}
