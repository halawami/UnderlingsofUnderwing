package tests.phase;

public class ElementPhaseTests {

//	@Ignore
//	public void testExecuteOnePlayerTwoRandom() {
//
//		Phase elementPhase = new CollectionPhase();
//
//		Player player = EasyMock.createMock(Player.class);
//		PromptHandler promptHandler = EasyMock.mock(PromptHandler.class);
//		Display display = EasyMock.mock(Display.class);
//		GUI gui = new GUI(promptHandler, display);
//		ElementBag elementBag = EasyMock.createMock(ElementBag.class);
//		HatchingGround hatchingGround = EasyMock.createMock(HatchingGround.class);
//		Element element = EasyMock.createMock(Element.class);
//		Handler handler = EasyMock.mock(Handler.class);
//		Runnable runnable = EasyMock.mock(Runnable.class);
//
//		ElementGiver elementGiver = EasyMock.mock(ElementGiver.class);
//		elementGiver.drawChoices = Arrays.asList(DrawChoice.RANDOM);
//
//		List<DrawChoice> drawChoices = new LinkedList<>();
//		drawChoices.add(DrawChoice.RANDOM);
//
//		List<Handler> handlers = new LinkedList<>();
//		handlers.add(handler);
//		handlers.add(handler);
//		EasyMock.expect(player.getHandlers()).andReturn(handlers);
//		handler.elementGiver = elementGiver;
//
//		EasyMock.expect(promptHandler.promptElementGiver(EasyMock.anyObject(List.class))).andReturn(elementGiver);
//		EasyMock.expect(promptHandler.promptElementDrawChoice(drawChoices)).andReturn(DrawChoice.RANDOM);
//		EasyMock.expect(elementBag.drawRandomElement()).andReturn(element);
//		player.addElement(EasyMock.anyObject(Element.class));
//
//		EasyMock.expect(promptHandler.promptElementGiver(EasyMock.anyObject(List.class))).andReturn(elementGiver);
//		EasyMock.expect(promptHandler.promptElementDrawChoice(drawChoices)).andReturn(DrawChoice.RANDOM);
//		EasyMock.expect(elementBag.drawRandomElement()).andReturn(element);
//		player.addElement(EasyMock.anyObject(Element.class));
//
//		runnable.run();
//		EasyMock.expectLastCall().times(2);
//
//		EasyMock.replay(player, promptHandler, display, elementBag, hatchingGround, handler, elementGiver, runnable);
//
//		List<Player> players = new ArrayList<>();
//		players.add(player);
//
//		elementPhase.execute(players, gui, elementBag, hatchingGround, runnable);
//
//		EasyMock.verify(player, promptHandler, display, elementBag, hatchingGround, handler, elementGiver, runnable);
//
//	}

}
