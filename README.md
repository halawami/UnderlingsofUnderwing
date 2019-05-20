# Underlings of Underwing

CSSE376 Spring 18-19 Term Project

Hussein Alawami, Mohammad Baqer, George Main IV, Wesley Siebenthaler

## Done Definition

The definition of done is created by examining and applying BVA on the rules.

#### Game Setup ```underlings.game.SetupTests```

- [x] The players should be created for [2, 6] players
  - When there are 2 players, the game should have 2 players
    - ```underlings.game.SetupTests.test2PlayerCount()```
  - When there are 6 players, the game should have 6 players
    - ```underlings.game.SetupTests.test6PlayerCount()```
- [x] The number of rounds should be set for [2, 6] players
  - When there are 2 players, there should be 15 rounds
    - ```underlings.game.SetupTests.test2PlayerRounds()```
  - When there are 3 players, there should be 13 rounds
    - ```underlings.game.SetupTests.test3PlayerRounds()```
  - When there are [4, 6] players, there should be 12 rounds
    - ```underlings.game.SetupTests.test4PlayerRounds()```
    - ```underlings.game.SetupTests.test6PlayerRounds()```

### Hatching Ground Setup ```underlings.hatchingground.SetupTests```

- [x] The hatching ground size should be set for [2, 6] players
  - When there are 2 players, the hatching ground should be 3x2
    - ```underlings.hatchingground.SetupTests.test2PlayerSize()```
  - When there are 3 players, the hatching ground size should be 4x3
    - ```underlings.hatchingground.SetupTests.test3PlayerSize()```
  - When there are [4, 6] players, the hatching ground size should be 4x4
    - ```underlings.hatchingground.SetupTests.test4PlayerSize()```
    - ```underlings.hatchingground.SetupTests.test6PlayerSize()```
- [x] The hatching ground dealt with cards for [2, 6] players
  - ```underlings.hatchingground.SetupTests.testDeal3By2()```
  - ```underlings.hatchingground.SetupTests.testDeal4By3()```
  - ```underlings.hatchingground.SetupTests.testDeal4By4()```

#### Handler Setup ```underlings.handler.SetupTests```

- [x] Players start off with 2 handlers for [2, 6] players
  - When there are 2 players, each player has 2 handlers
    - ```underlings.handler.SetupTests.test2PlayerHandlerCount()```
  - When there are 6 players, each player has 2 handlers
    - ```underlings.handler.SetupTests.test6PlayerHandlerCount()```
- [x] Handlers should start in the ready room for [2, 6] players
  - When there are 2 players, each handler for each player should start in the ready room
    - ```underlings.handler.SetupTests.test2PlayerReadyRoom()```
  - When there are 6 players, each handler for each player should start in the ready room
    - ```underlings.handler.SetupTests.test6PlayerReadyRoom()```
- [x] The maximum number of handlers should be set for [2, 6] players
  - When there are 2 players, the maximum number of handlers is 4
    - ```underlings.handler.SetupTests.test2PlayerMaxHandlers()```
  - When there are 3 players, the maximum number of handlers is 5
    - ```underlings.handler.SetupTests.test3PlayerMaxHandlers()```
  - When there are [4, 6] players, the maximum number of handlers is 6
    - ```underlings.handler.SetupTests.test4PlayerMaxHandlers()```
    - ```underlings.handler.SetupTests.test6PlayerMaxHandlers()```

### Game ```underlings.game.GameTests```

- [x] The game should be setup when the program is ran
  - ```underlings.game.GameTests.testSetup()```
- [x] The game should be displayed to the player
  - ```underlings.game.GameTests.testDisplay()```
- [x] The game should start and run until the game is over
  - The game can run until the round count reaches zero
    - ```underlings.game.GameTests.testGameLoopRoundsCompleted()```
    - ```underlings.game.GameTests.testGamoverPhaseEnd()```
  - The game can run until all eggs have been hatched wild
    - ```underlings.game.GameTests.testGameLoopWildHatched()```
  - The game can run until all cards have been used
    - ```underlings.game.GameTests.testCheckGameoverNoCards()```
    - ```underlings.game.GameTests.testCheckGameoverWithCardsTrue()```
    - ```underlings.game.GameTests.testCheckGameoverWithCardsFalse()```

### Field ```underlings.Field```

#### Field String Tests ```underlings.field.StringTests```

- [x] The field should display its color
  - ```underlings.field.StringTests.testToString()```

#### Add Tests ```underlings.field.AddTests```

- [x] Handlers can be added to the circular field
  - ```underlings.field.AddTests.testStart()```
  - ```underlings.field.AddTests.testEnd()```
- [x] Handlers can be added to the white field space
  - ```underlings.field.AddTests.testWhite()```

#### Remove Tests ```underlings.field.RemoveTests```

- [x] Handlers can be removed from the circular field
  - ```underlings.field.RemoveTests.testStart()```
  - ```underlings.field.RemoveTests.testEnd()```
  - ```underlings.field.RemoveTests.testWhite()```

#### Rotate Tests ```underlings.field.RotateTests```

- [x] Handlers can be rotated to the next field space
  - ```underlings.field.RotateTests.testStart()```
  - ```underlings.field.RotateTests.testEnd()```

#### Grid Tests ```underlings.field.GridTests```

- [x] The field is displayed as a grid
  - ```underlings.field.GridTests.testGetGrid()```
  - ```underlings.field.GridTests.testGetValidFieldSpaces()```

### Elements ```underlings.elements```

#### Done Tests ```underlings.elements.DoneTests```

- [x] An empty element space should not be complete
    - ```underlings.elements.DoneTests.testEmpty()```
- [x] A single non-combo element is placed on an empty element space
  - When a red element is placed on a red element space, the element space should be completed
    - ```tests.elementspace.single.DoneTests.testRed()```
  - When a green element is placed on a green element space, the element space should be completed
    - ```underlings.elements.DoneTests.testGreen()```
  - When a blue element is placed on a blue element space, the element space should be completed
    - ```underlings.elements.DoneTests.testBlue()```
  - When a orange element is placed on a orange element space, the element space should be completed
    - ```underlings.elements.DoneTests.testOrange()```
  - When a yellow element is placed on a yellow element space, the element space should be completed
    - ```underlings.elements.DoneTests.testYellow()```
  - When a purple element is placed on a purple element space, the element space should be completed
    - ```underlings.elements.DoneTests.testPurple()```
  - When a black element is placed on a black element space, the element space should be completed
    - ```underlings.elements.DoneTests.testBlack()```
  - When a white element is placed on a white element space, the element space should be completed
    - ```underlings.elements.DoneTests.testWhite()```

#### Orange Tests ```underlings.element.OrangeTests```

- [x] An orange element can be made from yellow and red elements
  - When only a red is placed on an orange space, the combo is not complete
    - ```underlings.element.OrangeTests.testRed()```
  - When only a yellow is placed on an orange space, the combo is not complete
    - ```underlings.element.OrangeTests.testYellow()```
  - When a red and then a yellow is placed on an orange space, the combo is complete
    - ```underlings.element.OrangeTests.testRedThenYellow()```
  - When a yellow and then red is played on an orange space, the combo is complete
    - ```underlings.element.OrangeTests.testYellowThenRed()```
  - When a yellow and red are played on an orange space, the combo is complete
    - ```underlings.element.OrangeTests.testRedAndYellow()```

#### Green Tests ```underlings.element.GreenTests```

- [x] An green element can be made from yellow and blue elements
  - When only a blue is placed on a green space, the combo is not complete
    - ```underlings.element.GreenTests.testBlue()```
  - When only a yellow is placed on a green space, the combo is not complete
    - ```underlings.element.GreenTests.testYellow()```
  - When a blue and then a yellow is placed on a green space, the combo is complete
    - ```underlings.element.GreenTests.testBlueThenYellow()```
  - When a yellow and then blue is played on a green space, the combo is complete
    - ```underlings.element.GreenTests.testYellowThenBlue()```
  - When a yellow and blue are played on a green space, the combo is complete
    - ```underlings.element.GreenTests.testBlueAndYellow()```

#### Purple Tests ```underlings.element.PurpleTests```

- [x] An purple element can be made from red and blue elements
  - When only a blue is placed on a purple space, the combo is not complete
    - ```underlings.element.PurpleTests.testBlue()```
  - When only a red is placed on a purple space, the combo is not complete
    - ```underlings.element.PurpleTests.testRed()```
  - When a blue and then a red is placed on a purple space, the combo is complete
    - ```underlings.element.PurpleTests.testBlueThenRed()```
  - When a red and then blue is played on a purple space, the combo is complete
    - ```underlings.element.PurpleTests.testRedThenBlue()```
  - When a red and blue are played on a purple space, the combo is complete
    - ```underlings.element.PurpleTests.testBlueAndRed()```

### Handlers ```underlings.handler```

#### Handler Choices ```underlings.handler.ChoiceTests```

- [x] Handlers can move between states
  - Handlers in the Ready Room can: Stay, Field Whitespace, Field, or Card
  - Handlers in the Break Room can: Ready Room
  - Handlers on the Field Whitespace can: Break Room
  - Handlers in the Field can: Stay, Break Room
  - Handlers in Incubation can: Stay
  - Handlers on a Card can: Stay, Break Room
  - ```underlings.handler.ChoiceTests.testPossibilities()```
- [x] Handler choices should be displayed to the players
  - ```underlings.handler.ChoiceTests.testToString()```

#### Handler State Display ```underlings.handler.StateStringTests```

- [x] Handlers in should display their current location
  - ```underlings.handler.StateStringTests.testReadyRoom()```
  - ```underlings.handler.StateStringTests.testBreakRoom()```
  - ```underlings.handler.StateStringTests.testCard()```
  - ```underlings.handler.StateStringTests.testIncubation()```
  - ```underlings.handler.StateStringTests.testField()```

#### Handler Element Givers ```underlings.handler.ElementGiverTests```

- [x] Handlers in the field should give a random element or field space element
  - ```underlings.handler.ElementGiverTests.testFieldAll()```
- [x] Handlers not in the field should give a random element
  - ```underlings.handler.ElementGiverTests.testNotField()```

#### Handler Movement Logic ```underlings.handler.MovementLogicTests```

- [x] When a handler moves from a card, the card no longer contains the handler
  - ```underlings.handler.MovementLogicTests.testCard()```
  - ```underlings.handler.MovementLogicTests.testCardBreakRoom()```
  - ```underlings.handler.MovementLogicTests.testNullHandler()```
  - ```underlings.handler.MovementLogicTests.testWildHandler()```
- [x] When a handler moves to the field they gain the element giver
  - ```underlings.handler.MovementLogicTests.testField()```
  - ```underlings.handler.MovementLogicTests.testFieldStay()```
  - ```underlings.handler.MovementLogicTests.testFieldWhitespace()```
- [x] When a handler leaves the field they lose the element giver
  - ```underlings.handler.MovementLogicTests.testFieldToBreakRoom()```
  - ```underlings.handler.MovementLogicTests.testFieldWhiteToBreakRoom()```
- [x] The handler can stay in its current state
  - ```underlings.handler.MovementLogicTests.testStay()```
- [x] The handler can move to the break room
  - ```underlings.handler.MovementLogicTests.testBreakRoom()```
- [x] The handler can move to the ready room
  - ```underlings.handler.MovementLogicTests.testReadyRoom()```

### Players ```underlings.player```

#### Player ID ```underlings.player.IdTests```

- [x] Players should have a player number
  - Player 1 should have a player number of 1
    - ```underlings.player.IdTests.testOne()```
  - Player 6 should have a player number of 6
    - ```underlings.player.IdTests.testSix()```
- [x] Players should display as "Player #"
  - ```underlings.player.IdTests.testToString()```

#### Add Handler ```underlings.player.AddHandlerTests```

- [x] Gain a handler while at [2, MAX) handlers
  - When at 2 handlers, adding a handler will result in a 3rd handler
    - ```underlings.player.AddHandlerTests.testTwo()```
  - When at MAX - 1 handlers, adding a handler will result in MAX handlers
    - ```underlings.player.AddHandlerTests.testAlmostMax()```
- [x] Gain a handler while at the MAX handler amount
  - When an additional handler is added, the handler count should remain at MAX
    - ```underlings.player.AddHandlerTests.testMax()```

#### Remove Handler ```underlings.player.RemoveHandlerTests```

- [x] Lose a handler while at [3, MAX] handlers
  - When at 3 handlers, the handler count should decrease to 2
    - ```underlings.player.RemoveHandlerTests.testThree()```
  - When at MAX handlers, the handler count should decrease to MAX - 1
    - ```underlings.player.RemoveHandlerTests.testMax()```
- [x] Lose a handler while at 2 handlers
  - When at 2 handlers, the handler count should remain at 2
    - ```underlings.player.RemoveHandlerTests.testTwo()```

#### Handler Deficiency ```underlings.player.HandlerDeficiencyTests```

- [x] Reach [0, 11] points while having [2, MAX] handlers
  - At 2 handlers, the handler count should remain at 2
    - ```underlings.player.HandlerDeficiencyTests.test11Points2Handlers()```
  - At MAX handlers, the handler count should remain at MAX
    - ```underlings.player.HandlerDeficiencyTests.test11PointsMaxHandlers()```
- [x] Reach 12 points while having 2 handlers
  - The handler count should increase to 3
    - ```underlings.player.HandlerDeficiencyTests.test12Points2Handlers()```
- [x] Reach 12 points while having [3, MAX] handlers
  - When at 3 handlers, the handler count should remain at 3
    - ```underlings.player.HandlerDeficiencyTests.test12Points3Handlers()```
  - When at MAX handlers, the handler count should remain at MAX
    - ```underlings.player.HandlerDeficiencyTests.test12PointsMaxHandlers()```
- [x] Reach 25 points from above 12 points while having [2, 3] handlers
  - When at 2 handlers, the handler count should increase to 3
    - ```underlings.player.HandlerDeficiencyTests.test25PointsFrom12With2Handlers()```
  - When at 3 handlers, the handler count should increase to 4
    - ```underlings.player.HandlerDeficiencyTests.test25PointsFrom12With3Handlers()```
- [x] Reach 25 points from above 12 points while having [4, MAX] handlers
  - When at 4 handlers, the handler count should remain at 4
    - ```underlings.player.HandlerDeficiencyTests.test25PointsFrom12With4Handlers()```
  - When at MAX handlers, the handler count should remain at MAX
    - ```underlings.player.HandlerDeficiencyTests.test25PointsFrom12WithMaxHandlers()```
- [x] Reach 25 points from below 12 points while having [2, 3] handlers
  - When at 2 handlers, the handler count should increase to 4
    - ```underlings.player.HandlerDeficiencyTests.test25PointsFrom0With2Handlers()```
  - When at 3 handlers, the handler count should increase to 4
    - ```underlings.player.HandlerDeficiencyTests.test25PointsFrom0With3Handlers()```
- [x] Reach 25 points from below 12 points while having [4, MAX] handlers
  - When at 4 handlers, the handler count should remain at 4
    - ```underlings.player.HandlerDeficiencyTests.test25PointsFrom0With4Handlers()```
  - When at MAX handlers, the handler count should remain at MAX
    - ```underlings.player.HandlerDeficiencyTests.test25PointsFrom0WithMaxHandlers()```
- [x] Reach 12 points with 2 handlers after dropping below 12 points
  - The handler count should remain at 2
    - ```underlings.player.HandlerDeficiencyTests.test12PointsTwice()```
- [x] Reach 25 points with [2, 3] handlers after dropping below 25 points
  - When at 2 handlers, the handler count should remain at 2
    - ```underlings.player.HandlerDeficiencyTests.test25PointsTwice2Handlers()```
  - When at 3 handlers, the handler count should remain at 3
    - ```underlings.player.HandlerDeficiencyTests.test25PointsTwice3Handlers()```

### Deck ```underlings.hatchingground.DeckTests```

- [x] The deck can be drawn from with [0, DECK_SIZE] cards
  -  At [1, DECK_SIZE] cards, the deck return a card
     - ```underlings.hatchingground.DeckTests.testDraw()```
  - At 0 cards, the deck returns an empty card
    - ```underlings.hatchingground.DeckTests.testDrawEmpty()```
- [x] Cards can be added to the deck
  - A card can be added to the deck without shuffling the deck
    - ```underlings.hatchingground.DeckTests.testAddCardNoShuffle()```
  - A card can be added to the deck and the deck can be shuffled
    - ```underlings.hatchingground.DeckTests.testAddCardShuffle()```
  - An empty card can not be added to the deck
    - ```underlings.hatchingground.DeckTests.testAddEmptyCard()```

### HatchingGround ```underlings.hatchingground```

- [x] The hatching ground can return the adjacent cards of a card
  - A card in the middle returns the card above, below, left, and right of it
    - ```underlings.hatchingground.AdjacentCardsTests.testMiddleCard()```
  - A card on the left edge returns the card above, below, and right of it
    - ```underlings.hatchingground.AdjacentCardsTests.testLeftEdgeCard()```
  - A card on the top edge returns the card below, left, and right of it
    - ```underlings.hatchingground.AdjacentCardsTests.testTopEdgeCard()```
  - A card on the bottom edge returns the card above, left, and right of it
    - ```underlings.hatchingground.AdjacentCardsTests.testBottomEdgeCard()```
  - A card on the right edge returns the card above, bottom, and left of it
    - ```underlings.hatchingground.AdjacentCardsTests.testRightEdgeCard()```
- [x] The hatching ground can return the coordinates of a card
  - ```underlings.hatchingground.AdjacentCardsTests.testGetCardCoordinatesFirstCard()```
  - ```underlings.hatchingground.AdjacentCardsTests.testGetCardCoordinatesLeftEdge()```
  - ```underlings.hatchingground.AdjacentCardsTests.testGetCardCoordinatesTopEdge()```
  - ```underlings.hatchingground.AdjacentCardsTests.testGetCardCoordinatesRightEdge()```
  - ```underlings.hatchingground.AdjacentCardsTests.testGetCardCoordinatesBottomEdge()```
  - ```underlings.hatchingground.AdjacentCardsTests.testGetCardCoordinatesMiddleEdge()```
  - ```underlings.hatchingground.AdjacentCardsTests.testGetCardCoordinatesInvalidCard()```

### Drawing Phase ```underlings.phase.DrawingPhaseTests```

- [x] The player draws elements based on their element givers
  - If the bag is not empty, they get an element
    - ```underlings.phase.DrawingPhaseTests.testTurn```
  - If the bag is empty, they do not get an element
    - ```underlings.phase.DrawingPhaseTests.testTurnNullElement```

### HandlerPhase ```underlings.phase.HandlerPhaseTests```

- [x] The player chooses a place to move a handler to
  - The player has a handler outside the break room
    - ```underlings.phase.HandlerPhaseTests.testTurn```
  - The player has a handler in the break room
    - ```underlings.phase.HandlerPhaseTests.testTurnHandlerInBreakRoom```
  - The player has no handlers
    - ```underlings.phase.HandlerPhaseTests.testTurnNoHandlers```

### Placement Phase

#### Phase ```underlings.phase.PlacementPhaseTests```
- [x] The player places one element for each of its handlers
  - ```underlings.phase.PlacementPhaseTests.testCheckTurnTwoHandlers```
  - ```underlings.phase.PlacementPhaseTests.testCheckTurnThreeHandlers```
- [x] The game ends during the PlacementPhase if all dragons are hatched wild
  - ```underlings.phase.PlacementPhaseTests.testCheckGameover```
  - ```underlings.phase.PlacementPhaseTests.testCheckGameoverNoCards```
  - ```underlings.phase.PlacementPhaseTests.testCheckGameoverLost```
- [x] The player can only choose to place another element in the same turn if they can put it on the same element space
  - ```underlings.phase.PlacementPhaseTests.testMoreMovesNoChoices```
  - ```underlings.phase.PlacementPhaseTests.testMoreMovesTrue```
- [x] The player can only place an element if they have another turn and there are places to put it
  - ```underlings.phase.PlacementPhaseTests.testTurnOver```
  - ```underlings.phase.PlacementPhaseTests.testTurnNoPlayableCards```
- [x] If the player completes an unclaimed egg, it hatches in the wild
  - ```underlings.phase.PlacementPhaseTests.testTurnCardNotComplete```
  - ```underlings.phase.PlacementPhaseTests.testTurnCardCompleteDomestic```
  - ```underlings.phase.PlacementPhaseTests.testTurnCardCompleteWild```

#### Utilities ```underlings.utilities.PlacementUtilitiesTests```
- [x] The player selects a card and element space on the card to place an element on
  - ```underlings.utilities.PlacementUtilitiesTests.testSelectCard```
  - ```underlings.utilities.PlacementUtilitiesTests.testSelectElementSpace```
- [x] The player places elements until either they have no more elements to place or they decide to stop
  - ```underlings.utilities.PlacementUtilitiesTests.testPlaceElements```
- [x] The player can only place elements on cards that need the elements they have
  - ```underlings.utilities.PlacementUtilitiesTests.testGetPlayableCards```

### Dragon Phase ```underlings.phase.DragonPhaseTests```

- [x] The player hatches any unhatched eggs that have been in incubation
  - ```underlings.phase.DragonPhaseTests.testNoIncubatedEggs```
  - ```underlings.phase.DragonPhaseTests.testOneIncubatedEgg```
  - ```underlings.phase.DragonPhaseTests.testTwoIncubatedEggs```
- [x] Eggs that have been completed should be moved to the incubator (with their handler) of the player who claimed them
  - ```underlings.phase.DragonPhaseTests.testNoCompleteEggs```
  - ```underlings.phase.DragonPhaseTests.testOneCompleteEgg```
  - ```underlings.phase.DragonPhaseTests.testTwoCompleteEggs```
  - ```underlings.phase.DragonPhaseTests.testOneCompleteEggNotThePlayers```
- [x] If the player has a hatching boost the eggs skip incubation and hatch immediately
  - ```underlings.phase.DragonPhaseTests.testHatchingTimeZero```
  - ```underlings.phase.DragonPhaseTests.testHatchingTimeZeroTwoEggs```
- [x] If the hatchingGround has a hatchingDelay the eggs take two turns to incubate
  - ```underlings.phase.DragonPhaseTests.testHatchLateEgg```
  - ```underlings.phase.DragonPhaseTests.testHatchLateEggExtended```

### Final Phase

#### Regular Phase ```underlings.phase.RegularFinalPhaseTests```
- [x] In the final phase the game will display the gameover screen and all players' scores
  - ```underlings.phase.RegularFinalPhaseTests.testRunPhase```
  
#### Wild Phase ```underlings.phase.WildFinalPhaseTests```
- [x] In the final phase the game will display the gameover screen tell all players that they lost
  - ```underlings.phase.WildFinalPhaseTests.testRunPhase```

### Rotation Phase ```underlings.phase.RotationPhaseTests```

- [x] In a rotation phase each player will finish their turn before moving to the next player
  - ```underlings.phase.RotationPhaseTests.testExecute```

### Sequential Phase ```underlings.phase.SequentialPhaseTests```

- [x] In a sequential phase the players each complete part of their turn until everyone completes their turns
  - ```underlings.phase.SequentialPhaseTests.testExecute```

### Egg Hatching ```underlings.utilities.EggHatchingLogicTests```

- [x] If the card is unclaimed it hatches in the wild
  - ```underlings.utilities.EggHatchingLogicTests.testOneWildEffect```
  - ```underlings.utilities.EggHatchingLogicTests.testTwoWildEffects```
  - ```underlings.utilities.EggHatchingLogicTests.testOneWildEffectReturnsElementsToBag
- [x] If the card is claimed it will hatch domestically
  - ```underlings.utilities.EggHatchingLogicTests.testOneDomesticEffect```
- [x] When an effect runs it checks for other eggs that hatch due to the effect
  - ```underlings.utilities.EggHatchingLogicTests.testEffectsRecursive```
- [x] When an egg hatches it returns its elements to the element bag
  - ```underlings.utilities.EggHatchingLogicTests.testReturnsElementsToBag```
  - ```underlings.utilities.EggHatchingLogicTests.testReturnNoElements```
  - ```underlings.utilities.EggHatchingLogicTests.testReturnPurpleComboElements```
  - ```underlings.utilities.EggHatchingLogicTests.testReturnOrangeComboElements```
  - ```underlings.utilities.EggHatchingLogicTests.testMultiReturnComboElements```

### LocaleWrap ```underlings.utilities.LocaleWrapTests```

- [x] When the players set the locale at the beginning of the game it changes the language of the game
  - ```underlings.utilities.LocaleWrapTests.testSetLocale```

### Draw Choices ```underlings.gui.DrawChoiceTests```

- [x] Draw choices are displayed to the player
  - ```underlings.gui.DrawChoiceTests.testToString```

### Scoring ```underlings.scoring```

#### Temperature Tests ```underlings.scoring.TemperatureTests```

- [x] Players with no cards should have a perfect balance
  - ```underlings.scoring.TemperatureTests.testNoCardsPerfectBalance()```
- [x] Players with equal warm and cool cards should have a perfect balance
  - ```underlings.scoring.TemperatureTests.testCardsPerfectBalance()```
- [x] Players with net warm cards should have a warm balance
  - ```underlings.scoring.TemperatureTests.testWarmBalance()```
- [x] Players with net cool cards should have a cool balance
  - ```underlings.scoring.TemperatureTests.testCoolBalance()```

#### Point Tests ```underlings.scoring.PointTests```

- [x] Card point total should be the sum of a players card's points
  - When a player has no cards their score should be 0
    - ```underlings.scoring.PointTests.testNoCards()```
  - When a player has one or more cards, the sum should be the card point total
    - ```underlings.scoring.PointTests.testOneCard()```
    - ```underlings.scoring.PointTests.testTwoCards()```

#### Scoring Tests ```underlings.scoring.ScoreTests```

- [x] The temperature bonus should not occur with two players
  - ```underlings.scoring.ScoreTests.testTwoPlayers()```
- [x] The temperature bonus should occur with three or more players
  - ```underlings.scoring.ScoreTests.testTwoNeutralOneEmpty()```
  - ```underlings.scoring.ScoreTests.testTwoNeutralOneEmptyOneCool()```
  - ```underlings.scoring.ScoreTests.testAll()```
- [x] Each players scores should be displayed
  - ```underlings.scoring.ScoreTests.testDisplayScores()```
- [x] The winners should be displayed
  - ```underlings.scoring.ScoreTests.testDisplayWinners()```

### Domestic Card Effects ```underlings.card.effect```

#### Gain Handlers  ```underlings.card.effect.domestic.players.player.GainHandlersEffectTests```

- Dragons
  - Abyssal Wyrm, Aeon Wyrm, Nebula Wyrm, Opalescent Whelp, Water Wyvern, Sky Wyvern, Blood Wyvern, Water Wyrm, Sky Wyrm, Blood Wyrm, Electric Drake, Steam Drake, Chromatic Drake, Nature Drake, Vadrenox, Blood Whelp, Water Whelp, Water Drake, Blood Drake, Sky Drake, Sky Whelp, Sunstone Drake, Brimstone Drake, Undine Drake, Twilight Drake, Harvest Drake, Ethereal Drake, Arbor Drake, Void Drake, Storm Drake
- [x] This effect gives the player 1 or 4 handlers 
  - The player can gain 1 handler
     - ```underlings.card.effect.domestic.players.player.GainHandlersEffectTests.testOneHandler()```
  - The player can gain 4 handlers
     - ```underlings.card.effect.domestic.players.player.GainHandlersEffectTests.testFourHandlers()```
- [x] The player should know that the effect has been run
  - ```underlings.card.effect.domestic.players.player.GainHandlersEffectTests.testToString()```

#### Select 1 Player; Steal all of their stored elements  ```underlings.card.effect.domestic.players.StealAllStoredElementsEffectTests```

- Dragons
  - Ignatius
- [x] This effect let the player steal all of stored elements from another player
  - ```underlings.card.effect.domestic.players.StealAllStoredElementsEffectTests.testEffect()```
- [x] The player should know that the effect has been run
  - ```underlings.card.effect.domestic.players.StealAllStoredElementsEffectTests.testToString()```

#### Examine the top 3 Dragons in the draw pile; reorder as desired ```underlings.card.effect.domestic.deck.ReorderTopThreeCardsEffectTests```

- Dragons
  - Lasura
- [x] The top three cards will be in the order of player
  - Deck has 0 cards
     - ```underlings.card.effect.domestic.deck.ReorderTopThreeCardsEffectTests.testNoCardsLeft()```
  - Deck has 1 card
     - ```underlings.card.effect.domestic.deck.ReorderTopThreeCardsEffectTests.testOneCardLeft()```
  - Deck has 2 card
     - ```underlings.card.effect.domestic.deck.ReorderTopThreeCardsEffectTests.testTwoCardsLeft()```
  - Deck has [3,DECK_SIZE] card
     - ```underlings.card.effect.domestic.deck.ReorderTopThreeCardsEffectTests.testThreeOrMoreCardsLeft()```
- [x] The player should know that the effect has been run
  - ```underlings.card.effect.domestic.deck.ReorderTopThreeCardsEffectTests.testToString()```

#### Collect 1 element ```underlings.card.effect.domestic.element.CollectElementEffectTests```

- Dragons
  - SKY WHELP, WATER WHELP, ARBOR WHELP, BLOOD WHELP, BRIMSTONE WHELP, ELECTRIC WHELP, TWILIGHT WHELP, HARVEST WHELP, VOID WHELP, UNDINE WHELP, STORM WHELP, STEAM WHELP, NATURE WHELP, ETHEREAL WHELP, ETHEREAL DRAKE, BLOOD DRAKE, SKY DRAKE, WATER DRAKE, AEON WHELP, NEBULA WHELP, CHROMATIC WHELP, CHROMATIC DRAKE, OPALESCENT DRAKE, BLOOD WYVERN, SKY WYVERN, WATER WYVERN
- [x] Player will get an element of color from a list of colors, from the element bag
  - Collect 1 element from a list of [1] color
     - ```underlings.card.effect.domestic.element.CollectElementEffectTests.testOneColor()```
  - Collect 1 element from a list of [2,MAX] colors
     - ```underlings.card.effect.domestic.element.CollectElementEffectTests.testTwoColors()```
- [x] The player should know that the effect has been run
  - ```underlings.card.effect.domestic.element.CollectElementEffectTests.testToString()```

  
#### Collect 5 elements of each color ```underlings.card.effect.domestic.element.CollectFiveElementsEffectTests```

- Dragons
  - TIAMAT
- [x] Player will get 5 elements of each color from a list of 5 colors 
  - Collect 5 elements, 1 element for each color from list [RED, BLUE, GREEN, BLACK, WHITE]
     - ```underlings.card.effect.domestic.element.CollectFiveElementsEffectTests.testEffect()```
- [x] The player should know that the effect has been run
  - ```underlings.card.effect.domestic.element.CollectFiveElementsEffectTests.testToString()```

#### Next Phase 1, draw Elements of your choice ```underlings.card.effect.domestic.element.DrawElementsOfChoiceNextPhaseEffectTests```

- Dragons
  - TYCHE
- [x] Player will be able to choose their elements from the element bag next phase 1, and return to normal afterward
  - On next phase 1
     - ```underlings.card.effect.domestic.element.DrawElementsOfChoiceNextPhaseEffectTests.testOnFirstPhaseOne()```
  - On second next phase 1
     - ```underlings.card.effect.domestic.element.DrawElementsOfChoiceNextPhaseEffectTests.testOnSecondPhaseOne()```
- [x] The player should know that the effect has been run
  - ```underlings.card.effect.domestic.element.DrawElementsOfChoiceNextPhaseEffectTests.testToString()```

#### ALL Eggs claimed this round take +1 round to hatch  ```underlings.card.effect.wild.AllEggsHatchLateEffectTests```

- Dragons
  - Norruth
- [x] This effect modifies the hatching ground to allow for late hatching for that round
  - ```underlings.card.effect.wild.AllEggsHatchLateEffectTests.testApply()```
- [x] The player should know the effect has been run
  - ```underlings.card.effect.wild.AllEggsHatchLateEffectTests.testToString()```
  
#### Reshuffle this card into the deck; when redrawn hatch ALL unclaimed Eggs in the Hatching grounds ```underlings.card.effect.wild.ApiaraWildEffectTests```

- Dragons
  - Apiara
- [x] This effect should shuffle the card back into the deck, when redrawn, all unclaimed eggs in the hatching ground must hatch
	- Run draw once
		- ```underlings.card.effect.wild.ApiaraWildEffectTests.testEffect()```
	- Run draw twice no cards
		- ```underlings.card.effect.wild.ApiaraWildEffectTests.testEffectTwiceNoCards()```
	- Run draw twice one card
		- ```underlings.card.effect.wild.ApiaraWildEffectTests.testEffectTwiceOneCard()```
	- Run draw twice two cards
		- ```underlings.card.effect.wild.ApiaraWildEffectTests.testEffectTwiceTwoCards()```
- [x] The player should know the effect has been run
  - ```underlings.card.effect.wild.ApiaraWildEffectTests.testToString()```
  
#### Replace a Handler on a claimed Egg with your own ```underlings.card.effect.domestic.handler.ReplaceHandlerWithYourOwnEffectTests```

- Dragons
  - VADRENOX
- [x] This effect should let the player pick one claimed card and replace its handler with one of their handlers
	- No claimed cards
		- ```underlings.card.effect.domestic.handler.ReplaceHandlerWithYourOwnEffectTests.testApplyEffectNoSelectableHandler()```
	- At least one claimed card
		- ```underlings.card.effect.domestic.handler.ReplaceHandlerWithYourOwnEffectTests.testApplyEffectSelectableHandler()```
- [x] The player should know the effect has been run
  - ```underlings.card.effect.domestic.handler.ReplaceHandlerWithYourOwnEffectTests.testToString()```
