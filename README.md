# Underlings of Underwing

CSSE376 Spring 18-19 Term Project

Hussein Alawami, Mohammad Baqer, George Main IV, Wesley Siebenthaler

## Done Definition

The definition of done is created by examining and applying BVA on the rules.

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




