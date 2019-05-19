# Underlings of Underwing

CSSE376 Spring 18-19 Term Project

Hussein Alawami, Mohammad Baqer, George Main IV, Wesley Siebenthaler

## Done Definition

The definition of done is created by examining and applying BVA on the rules.

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

### Scoring ```underlings.scoring```

#### Temperature Tests ```underlings.scoring.TemperatureTests```

- [x] Players with no cards should have a perfect balance
  - ```underlings.scoring.TemperatureTests.testNoCards()```
- [x] Players with equal warm and cool cards should have a perfect balance
  - ```underlings.scoring.TemperatureTests.testPerfectBalance()```
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


### Domestic Card Effects

#### Effect Name ```card.effect.domestic.package.ClassName```

- Dragons
  - Dragon 1, Dragon 2, Dragon 3
- [x] Should do ...
  - ```card.effect.domestic.package.ClassName.test()```
- [x] Should do ...
  - ```card.effect.domestic.package.ClassName.test()```
