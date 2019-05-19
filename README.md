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

### Game ```underlings.game.GameTests```

- [x] The game should be setup when the program is ran
  - ```underlings.game.GameTests.testSetup()```
- [x] The game should be displayed to the player
  - ```underlings.game.GameTests.testDisplay()```
- [x] The game should start and run until the game is over
  - The game can run until the round count reaches zero
    - ```underlings.game.GameTests.testGameLoopRoundsCompleted()```
  - The game can run until all eggs have been hatched wild
    - ```underlings.game.GameTests.testGameLoopWildHatched()```
  
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
- [x] This effect let the player look at the top three cards of deck and reorder them as desired
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
- [x] This effect let the player collect one element of a color from a list of colors, from the element bad
  - Collect 1 element from a list of [1] color
     - ```underlings.card.effect.domestic.element.CollectElementEffectTests.testOneColor()```
  - Collect 1 element from a list of [2,MAX] colors
     - ```underlings.card.effect.domestic.element.CollectElementEffectTests.testTwoColors()```
- [x] The player should know that the effect has been run
  - ```underlings.card.effect.domestic.element.CollectElementEffectTests.testToString()```