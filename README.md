# Underlings of Underwing

CSSE376 Spring 18-19 Term Project

Hussein Alawami, Mohammad Baqer, George Main IV, Wesley Siebenthaler

## Done Definition

The definition of done is created by examining and applying BVA on the rules.

### Game Setup ```tests.game.setup```

#### Game Setup ```tests.game.setup.GameTests```

- [x] The players should be created for [2, 6] players
  - When there are 2 players, the game should have 2 players
    - ```tests.game.setup.GameTests.test2PlayerCount()```
  - When there are 6 players, the game should have 6 players
    - ```tests.game.setup.GameTests.test6PlayerCount()```
- [x] The number of rounds should be set for [2, 6] players
  - When there are 2 players, there should be 15 rounds
    - ```tests.game.setup.GameTests.test2PlayerRounds()```
  - When there are 3 players, there should be 13 rounds
    - ```tests.game.setup.GameTests.test3PlayerRounds()```
  - When there are [4, 6] players, there should be 12 rounds
    - ```tests.game.setup.GameTests.test4PlayerRounds()```
    - ```tests.game.setup.GameTests.test6PlayerRounds()```

#### Handler Setup ```tests.game.setup.HandlerTests```

- [x] Players start off with 2 handlers for [2, 6] players
  - When there are 2 players, each player has 2 handlers
    - ```tests.game.setup.HandlerTests.test2PlayerHandlerCount()```
  - When there are 6 players, each player has 2 handlers
    - ```tests.game.setup.HandlerTests.test6PlayerHandlerCount()```
- [x] Handlers should start in the ready room for [2, 6] players
  - When there are 2 players, each handler for each player should start in the ready room
    - ```tests.game.setup.HandlerTests.test2PlayerReadyRoom()```
  - When there are 6 players, each handler for each player should start in the ready room
    - ```tests.game.setup.HandlerTests.test6PlayerReadyRoom()```
- [x] The maximum number of handlers should be set for [2, 6] players
  - When there are 2 players, the maximum number of handlers is 4
    - ```tests.game.setup.HandlerTests.test2PlayerMaxHandlers()```
  - When there are 3 players, the maximum number of handlers is 5
    - ```tests.game.setup.HandlerTests.test3PlayerMaxHandlers()```
  - When there are [4, 6] players, the maximum number of handlers is 6
    - ```tests.game.setup.HandlerTests.test4PlayerMaxHandlers()```
    - ```tests.game.setup.HandlerTests.test6PlayerMaxHandlers()```
    
#### Hatching Ground Setup ```tests.game.setup.HatchingGroundTests```

- [x] The hatching ground size should be set for [2, 6] players
  - When there are 2 players, the hatching ground should be 3x2
    - ```tests.game.setup.HatchingGroundTests.test2PlayerSize()```
  - When there are 3 players, the hatching ground size should be 4x3
    - ```tests.game.setup.HatchingGroundTests.test3PlayerSize()```
  - When there are [4, 6] players, the hatching ground size should be 4x4
    - ```tests.game.setup.HatchingGroundTests.test4PlayerSize()```
    - ```tests.game.setup.HatchingGroundTests.test6PlayerSize()```

### Player's Handler Count ```tests.player.handler```

#### Handler Deficiency ```tests.player.handler.DeficiencyTests```

- [x] Reach 12 points while having 2 handlers
  - The handler count should increase to 3
    - ```tests.player.handler.DeficiencyTests.test12Points2Handlers()```
- [x] Reach 12 points while having [3, MAX] handlers
  - When at 3 handlers, the handler count should remain at 3
    - ```tests.player.handler.DeficiencyTests.test12Points3Handlers()```
  - When at MAX handlers, the handler count should remain at MAX
    - ```tests.player.handler.DeficiencyTests.test12PointsMaxHandlers()```
- [x] Reach 25 points from above 12 points while having [2, 3] handlers
  - When at 2 handlers, the handler count should increase to 3
    - ```tests.player.handler.DeficiencyTests.test25PointsFrom12With2Handlers()```
  - When at 3 handlers, the handler count should increase to 4
    - ```tests.player.handler.DeficiencyTests.test25PointsFrom12With3Handlers()```
- [x] Reach 25 points from above 12 points while having [4, MAX] handlers
  - When at 4 handlers, the handler count should remain at 4
    - ```tests.player.handler.DeficiencyTests.test25PointsFrom12With4Handlers()```
  - When at MAX handlers, the handler count should remain at MAX
    - ```tests.player.handler.DeficiencyTests.test25PointsFrom12WithMaxHandlers()```
- [x] Reach 25 points from below 12 points while having [2, 3] handlers
  - When at 2 handlers, the handler count should increase to 4
    - ```tests.player.handler.DeficiencyTests.test25PointsFrom0With2Handlers()```
  - When at 3 handlers, the handler count should increase to 4
    - ```tests.player.handler.DeficiencyTests.test25PointsFrom0With3Handlers()```
- [x] Reach 25 points from below 12 points while having [4, MAX] handlers
  - When at 4 handlers, the handler count should remain at 4
    - ```tests.player.handler.DeficiencyTests.test25PointsFrom0With4Handlers()```
  - When at MAX handlers, the handler count should remain at MAX
    - ```tests.player.handler.DeficiencyTests.test25PointsFrom0WithMaxHandlers()```
- [x] Reach 12 points with 2 handlers after dropping below 12 points
  - The handler count should remain at 2
    - ```tests.player.handler.DeficiencyTests.test12PointsTwice()```
- [x] Reach 25 points with [2, 3] handlers after dropping below 25 points
  - When at 2 handlers, the handler count should remain at 2
    - ```tests.player.handler.DeficiencyTests.test25PointsTwice2Handlers()```
  - When at 3 handlers, the handler count should remain at 3
    - ```tests.player.handler.DeficiencyTests.test25PointsTwice3Handlers()```

#### Add Handler ```tests.player.handler.AddTests```

- [x] Gain a handler while at [2, MAX) handlers
  - When at 2 handlers, adding a handler will result in a 3rd handler
    - ```tests.player.handler.AddTests.test2Handlers()```
  - When at MAX - 1 handlers, adding a handler will result in MAX handlers
    - ```tests.player.handler.AddTests.testMaxMinus1Handlers()```
- [x] Gain a handler while at the MAX handler amount
  - When an additional handler is added, the handler count should remain at MAX
    - ```tests.player.handler.AddTests.testMaxHandler()```

#### Remove Handler ```tests.player.handler.RemoveTests```

- [x] Lose a handler while at [3, MAX] handlers
  - When at 3 handlers, the handler count should decrease to 2
    - ```tests.player.handler.RemoveTests.test3Handlers()```
  - When at MAX handlers, the handler count should decrease to MAX - 1
    - ```tests.player.handler.RemoveTests.testMaxHandlers()```
- [x] Lose a handler while at 2 handlers
  - When at 2 handlers, the handler count should remain at 2
    - ```tests.player.handler.RemoveTests.test2Handlers()```

// TODO: Remove
- [ ] Reach 12 points and gain a handler from a dragon at the same time
- [ ] Reach 25 points and gain a handler from a dragon as the same time


### Element Collection (Phase 1) ```tests.elements.drawing```

#### Drawing Specific Elements ```tests.elements.drawing.SpecificTests```
- [x] A specific color element can be drawn from the bag
  - When a red element is requested, a red element will be returned
    - ```tests.elements.drawing.SpecificTests.testRed()```
  - When a green element is requested, a green element will be returned
    - ```tests.elements.drawing.SpecificTests.testGreen()```
  - When a blue element is requested, a blue element will be returned
    - ```tests.elements.drawing.SpecificTests.testBlue()```
  - When a orange element is requested, a orange element will be returned
    - ```tests.elements.drawing.SpecificTests.testOrange()```
  - When a yellow element is requested, a yellow element will be returned
    - ```tests.elements.drawing.SpecificTests.testYellow()```
  - When a purple element is requested, a purple element will be returned
    - ```tests.elements.drawing.SpecificTests.testPurple()```
  - When a black element is requested, a black element will be returned
    - ```tests.elements.drawing.SpecificTests.testBlack()```
  - When a white element is requested, a white element will be returned
    - ```tests.elements.drawing.SpecificTests.testWhite()```

#### Drawing Random Elements ```tests.elements.drawing.RandomTests```

- [x] Drawing a Completly Random Element
  - A red element may be returned as the random element
    - ```tests.elements.drawing.RandomTests.testCompletlyRandomRed()```
  - A green element may be returned as the random element
    - ```tests.elements.drawing.RandomTests.testCompletlyRandomGreen()```
  - A blue element may be returned as the random element
    - ```tests.elements.drawing.RandomTests.testCompletlyRandomBlue()```
  - A orange element may be returned as the random element
    - ```tests.elements.drawing.RandomTests.testCompletlyRandomOrange()```
  - A yellow element may be returned as the random element
    - ```tests.elements.drawing.RandomTests.testCompletlyRandomYellow()```
  - A purple element may be returned as the random element
    - ```tests.elements.drawing.RandomTests.testCompletlyRandomPurple()```
  - A black element may be returned as the random element
    - ```tests.elements.drawing.RandomTests.testCompletlyRandomBlack()```
  - A white element may be returned as the random element
    - ```tests.elements.drawing.RandomTests.testCompletlyRandomWhite()```
- [x] Drawing a Random Primary Color Element
  - A red element may be returned as the random element
    - ```tests.elements.drawing.RandomTests.testPrimaryRandomRed()```
  - A yellow element may be returned as the random element
    - ```tests.elements.drawing.RandomTests.testPrimaryRandomYellow()```
  - A blue element may be returned as the random element
    - ```tests.elements.drawing.RandomTests.testPrimaryRandomBlue()```
- [x] Drawing a Random Secondary Color Element
  - A purple element may be returned as the random element
    - ```tests.elements.drawing.RandomTests.testSecondaryRandomPurple()```
  - A green element may be returned as the random element
    - ```tests.elements.drawing.RandomTests.testSecondaryRandomGreen()```
  - A orange element may be returned as the random element
    - ```tests.elements.drawing.RandomTests.testSecondaryRandomOrange()```
- [x] Drawing a Random Cool Element
  - A blue element may be returned as the random element
    - ```tests.elements.drawing.RandomTests.testCoolRandomBlue()```
  - A green element may be returned as the random element
    - ```tests.elements.drawing.RandomTests.testCoolRandomGreen()```
  - A purple element may be returned as the random element
    - ```tests.elements.drawing.RandomTests.testCoolRandomPurple()```
- [x] Drawing a Random Warm Element
  - A red element may be returned as the random element
    - ```tests.elements.drawing.RandomTests.testsWarmRandomRed()```
  - A yellow element may be returned as the random element
    - ```tests.elements.drawing.RandomTests.testWarmRandomYelow()```
  - A orange element may be returned as the random element
    - ```tests.elements.drawing.RandomTests.testWarmRandomOrange()```

#### Move & Assign Dragon Handlers (Phase 2)


- [x] Handler in Ready Room
  - [x] Move to Handler on Unclaimed & Unhatched Egg
  - [x] Move to Hander in Field
  - [x] Move to Handler in Field White
  - [x] Keep handler in Ready Room
  - [x] Move to illegal state


- [x] Handler in Field
  - [x] Move clockwise in Field
  - [x] Move to Break Room
  - [x] Move to illegal state


- [x] Handler on Unclaimed & Unhatched Egg
  - [x] Move to Break Room
  - [x] Keep handler on Egg
  - [x] Move to illegal state
  - [ ] Move to Handler in Ready Room due to Dragon Effect


- [x] Handler in Break Room
  - [x] Move to Handler in Ready Room
  - [x] Move to illegal state


- [x] Handler in Field White
  - [x] Move Handler to Break Room
  - [x] Move to illegal state


- [x] Handler during Incubation
  - [x] Keep handler in Incubation
  - [x] Move to illegal state


- [ ] Handler in Ready Room and all Eggs claimed
- [ ] Max handlers on field position and move another handler to same spot
- [ ] Players have different number of assigning turns (number of handlers is different)
- [ ] Players have the same number of assigning turns (number of handlers is the same)


#### Placing Elements on Eggs (Phase 3)


- [ ] No Elements in inventory
- [ ] No color spaces for specific Element
- [ ] No color spaces for combo Element
- [ ] Place Single Element
  - [ ] Place single Element on an empty color space
  - [ ] Place single Element on existing noncomplete combination
- [ ] Place multiple Elements
  - [ ] Place multiple Elements on an empty color space
  - [ ] Place multiple Elements on existing noncomplete combination
- [ ] Place black in place of any Element
- [ ] Place white in place of any Element
- [ ] Players have different number of placing turns (number of handlers is different)
- [ ] Players have same number of placing turns (number of handlers is the same)
- [ ] Phase 3 ends with Elements remaining in inventory
- [ ] Egg is Hatched in the Wild


##### Eggs Hatched in the Wild
- [ ] All Eggs have been hatched wild


#### Hatching and Claiming Completed Eggs (Phase 4)
- [ ] Claiming Completed Eggs
- [ ] No eggs are completed during the round
- [ ] One or more eggs are completed during the round
- [ ] Replace empty spaces with new Unclaimed Eggs
   - [ ] No cards are left in the deck
   - [ ] Not enough cards in the deck to replace all empty spaces
   - [ ] There are cards in the deck to replace all empty spaces
   - [ ] One of the replaced cards is Hatched Apiara
- [ ] Hatch incubated Eggs
   - [ ] Player has no incubated eggs to hatch
   - [ ] Player has one or more incubated eggs to hatch
- [ ] Special effects
   - [ ] Follows BVA of Positive Dragon Effects


### Trading Elements
- [ ] Players do not initiate a trade
- [ ] Player rejects trade offer
- [ ] Two players trade elements
- [ ] One player gives more elements than received
- [ ] One player gives no elements in the trade


### Ending the Game

#### End of Game Options

- [ ] Reach round 0 and add another round
- [ ] Reach round 0 and end game


#### End Conditions
- [ ] Round timer reaches 0
- [ ] All eggs in the hatching ground have been hatched wild
- [ ] All eggs have been hatched and collected
- [ ] All elements have been played and have not completed a dragon


#### Final Round
- [ ] Player adds another round to the game
- [ ] Players have no eggs to hatch during final round
- [ ] Players have eggs to hatch during final round


#### Temperature Bonus
- [ ] Player has no Egg
- [ ] Player has the highest net value of ~temperature~ Eggs
- [ ] More than 1 player has the same net value of ~temperature~ Eggs
- [ ] Player has a perfect temperature balance


##### Destroying Elements
- [ ] No Elements exist on Egg
- [ ] The specific color doesn't exist
- [ ] A combination of a certain color exists on Egg
- [ ] A white or black Element disguised as that certain color
- [ ] Only 1 Element of that color exists on single Egg
- [ ] Multiple Elements of that color exist on single Egg
- [ ] Wild hatched dragon is adjacent to Egg
- [ ] Destroying a color that completes all the needed Elements of an Egg (ex: Egg has all Elements and ready to incubate, but one of the Elements gets destroyed)


## BVA of Cards (Dragons)


- [ ] BVA of Adjacent Eggs        
   - [ ] Egg is in the corner of the hatching grounds
   - [ ] Egg is on the edge of the hatching grounds
   - [ ] Egg is surrounded by Eggs 


### Positive Effects:
- [ ] Gain +1 Handler
   - [ ] BVA
      - [ ] Follows BVA of gaining handlers and handler deficiency
   - [ ] Dragons
      - [ ] Abyssal Wyrm
      - [ ] Aeon Wyrm
      - [ ] Nebula Wyrm
      - [ ] Opalescent Whelp
      - [ ] Water Wyvern
      - [ ] Sky Wyvern
      - [ ] Blood Wyvern
      - [ ] Water Wyrm
      - [ ] Sky Wyrm
      - [ ] Blood Wyrm
      - [ ] Electric Drake
      - [ ] Steam Drake
      - [ ] Chromatic Drake
      - [ ] Nature Drake
      - [ ] Vadrenox
      - [ ] Blood Whelp
      - [ ] Water Whelp
      - [ ] Water Drake
      - [ ] Blood Drake
      - [ ] Sky Drake
      - [ ] Sky Whelp
      - [ ] Sunstone Drake
      - [ ] Brimstone Drake
      - [ ] Undine Drake
      - [ ] Twilight Drake
      - [ ] Harvest Drake
      - [ ] Ethereal Drake
      - [ ] Arbor Drake
      - [ ] Void Drake
      - [ ] Storm Drake
- [ ] Collect 1 Primary Element
   - [ ] BVA
      - [ ] Follows BVA of Element Collection
   - [ ] Dragons
      - [ ] Chromatic Whelp
- [ ] Collect and Hatch any 5 point or fewer unclaimed Egg
   - [ ] BVA
      - [ ] No 5 point of fewer unclaimed Egg
      - [ ] One 5 point or fewer unclaimed Egg
      - [ ] More than one 5 point or fewer unclaimed Egg
   - [ ] Dragons
      - [ ] Undine Wyrm
      - [ ] Steam Wyrm
      - [ ] Sunstone Wyrm
      - [ ] Twilight Wyrm
      - [ ] Electric Wyrm
      - [ ] Ethereal Wyrm
      - [ ] Nature Wyrm
      - [ ] Brimstone Wyrm
      - [ ] Void Wyrm
- [ ] Take up to 2 ~temperature~ Elements from any Egg in play
   - [ ] BVA
      - [ ] No Eggs in play have ~temperature~ Elements
      - [ ] One Egg in play has  ~temperature ~Elements
      - [ ] More than one Egg in play has ~temperature~ Elements
      - [ ] Egg in play has only one ~temperature~ Element
   - [ ] Dragons
      - [ ] Flamewreathed Wyvern
      - [ ] Iceshard Wyvern
- [ ] Take 1 ~color~ Element from any Egg in play
   - [ ] BVA
      - [ ] No Eggs in play have ~color~ Element
      - [ ] One Egg in play has ~color~ Element
      - [ ] More than one Egg in play has ~color~ Element
   - [ ] Dragons
      - [ ] Opalescent Wyrm
      - [ ] Chromatic Wyvern (Yellow, Blue, Red)
- [ ] Collect 1 ~color~ Element each Phase 1
   - [ ] BVA
      - [ ] Follows BVA of Element Collection
   - [ ] Dragons
      - [ ] Twilight Wyvern
      - [ ] Steam Wyvern
      - [ ] Nature Wyvern
      - [ ] Water Wyrm
      - [ ] Sky Wyrm
      - [ ] Blood Wyrm
      - [ ] Void Wyvern
      - [ ] Undine Wyvern
      - [ ] Electric Wyvern
      - [ ] Brimstone Wyvern
      - [ ] Ethereal Wyvern
      - [ ] Sunstone Wyvern
- [ ] Collect 1 random Element
   - [ ] BVA
      - [ ] Follows BVA of Element Collection
   - [ ] Dragons
      - [ ] Water Drake
      - [ ] Blood Drake
      - [ ] Sky Drake
- [ ] Collect 1 ~color~ Element
   - [ ] BVA
      - [ ] Follows BVA of Element Collection
   - [ ] Dragons
      - [ ] Water Wyvern
      - [ ] Sky Wyvern
      - [ ] Blood Wyvern
      - [ ] Tiamat (multiple)
      - [ ] Blood Whelp
      - [ ] Water Whelp
      - [ ] Sky Whelp
- [ ] Collect 2 ~color~ or ~color~ Element
   - [ ] BVA
      - [ ] Follows BVA of Element Collection
   - [ ] Dragons
      - [ ] Abyssal Whelp
- [ ] Collect 1 ~color~ or ~color~ Element
   - [ ] BVA
      - [ ] Follows BVA of Element Collection
   - [ ] Dragons
      - [ ] Nebula Whelp
      - [ ] Aeon Whelp
      - [ ] Ethereal Whelp
      - [ ] Harvest Whelp
      - [ ] Arbor Whelp
      - [ ] Electric Whelp
      - [ ] Twilight Whelp
      - [ ] Brimstone Whelp
      - [ ] Steam Whelp
      - [ ] Undine Whelp
      - [ ] Storm Whelp
      - [ ] Sunstone Whelp
      - [ ] Void Whelp
      - [ ] Nature Whelp
- [ ] Draw 1 ~Primary/Secondary~ Element
   - [ ] BVA
      - [ ] Follows BVA of Element Collection
   - [ ] Dragons
      - [ ] Chromatic Drake
      - [ ] Opalescent Drake
- [ ] Destroy up to 2 ~temperature~ Elements on any Egg in play
   - [ ] BVA
      - [ ] No Eggs in play have ~temperature~ Elements
      - [ ] One Egg in play has  ~temperature ~Elements
      - [ ] More than one Egg in play has ~temperature~ Elements
      - [ ] Egg in play has only one ~temperature~ Element


   - [ ] Dragons
      - [ ] Flamewreathed Whelp
      - [ ] Iceshard Whelp
- [ ] You may now use Black or White Elements in place of any other type
   - [ ] BVA
      - [ ] Follows BVA of Placing Elements
   - [ ] Dragons
      - [ ] Artifeles
- [ ] Draw an additional ~temperature~ Element each Phase 1
   - [ ] BVA
      - [ ] Follows BVA of Element Collection
   - [ ] Dragons
      - [ ] De’Lons
      - [ ] Xandus
- [ ] Draw an additional random Element each round
   - [ ] BVA
      - [ ] Follows BVA of Element Collection
   - [ ] Dragons
      - [ ] Whyte
- [ ] Examine the top 3 Dragons in the draw pile; reorder as desired
   - [ ] BVA
      - [ ] Draw pile has 0 dragons
      - [ ] Draw pile has 1 dragon
      - [ ] Draw pile has 2 dragons
      - [ ] Draw pile has 3 dragons
   - [ ] Dragons
      - [ ] Lusura
- [ ] Select a player; take 1 of their hatched Neutral dragons worth 9 point of fewer.
   - [ ] BVA
      - [ ] No players with hatched Neutral dragons worth 9 points or fewer
      - [ ] One player with hatched Neutral dragons worth 9 points or fewer
      - [ ] More than one player with hatched Neutral dragons worth 9 points or fewer
      - [ ] Selected player has one hatched Neutral dragon worth 9 points or fewer
      - [ ] Selected player has more than one hatched Neutral dragon worth 9 points or fewer
   - [ ] Dragons
      - [ ] Draceutrum
- [ ] If desired, hatch any 1 unclaimed Egg in the Hatching Grounds as a Wild Dragon
   - [ ] BVA
      - [ ] No unclaimed Eggs in the hatching grounds
      - [ ] One unclaimed Eggs in the hatching grounds
      - [ ] More than one unclaimed Eggs in the hatching grounds
      - [ ] Player does not desire to hatch a wild dragon
   - [ ] Dragons
      - [ ] Cordath
- [ ] You may create White by combining ALL Primary and Secondary Elements
   - [ ] BVA
      - [ ] Follows BVA of Placing Elements
   - [ ] Dragons
      - [ ] Aerillia
- [ ] Next Phase 1, draw Elements of your choice
   - [ ] BVA
      - [ ] Follows BVA of Element Collection
   - [ ] Dragons
      - [ ] Tyche
- [ ] The next Egg you complete may be hatched and collected immediately. 
   - [ ] BVA
      - [ ] You complete an Egg you claimed next
      - [ ] You complete an Egg another player claimed next
      - [ ] You complete an unclaimed Egg next
   - [ ] Dragons
      - [ ] Norruth
- [ ] Up to 2 of your Handlers may now occupy the same space on the Field
   - [ ] BVA
      - [ ] Follows BVA of Moving and Assigning Handlers
   - [ ] Dragons
      - [ ] Apiara
- [ ] Declare war on 1 other player; take ALL of their stored Elements
   - [ ] BVA
      - [ ] Selected player has no Elements
      - [ ] Selected player has one Element
      - [ ] Selected player has more than one Element
   - [ ] Dragons
      - [ ] Ignatius
- [ ] Player may steal up to 3 Orange Elements from any Eggs in play 
   - [ ] BVA
      - [ ] Player steals no Elements
      - [ ] Player steals one Element
      - [ ] Player steals three Elements
      - [ ] Player steals all Elements from one Egg
      - [ ] Player steals Elements from more than one Egg
   - [ ] Dragons
      - [ ] Aerystrasza
- [ ] Replace a Handler on a claimed Egg with your own
   - [ ] BVA
      - [ ] No Eggs are claimed
      - [ ] One Egg is claimed
      - [ ] More than one Egg is claimed
   - [ ] Dragons
      - [ ] Vadrenox
- [ ] Draw up to 4 additional Handlers (to a max. Of 6)
   - [ ] BVA
      - [ ] Follows BVA of gaining handlers and handler deficiency
   - [ ] Dragons
      - [ ] Kel’Droth
###Negative Effects:
- [ ] Hatch ALL unclaimed adjacent Eggs
   - [ ] BVA
      - [ ] No unclaimed adjacent Eggs
      - [ ] One unclaimed adjacent Egg
      - [ ] Four unclaimed adjacent Eggs
      - [ ] Follows BVA of adjacent Eggs
   - [ ] Dragons
      - [ ] Abyssal Wyrm
      - [ ] Aeon Wyrm
      - [ ] Nebla Wyrm
      - [ ] Undine Wyrm
      - [ ] Steam Wyrm
      - [ ] Sunstone Wyrm
      - [ ] Twilight Wyrm
      - [ ] Electric Wyrm
      - [ ] Ethereal Wyrm
      - [ ] Nature Wyrm
      - [ ] Brimstone Wyrm
      - [ ] Void Wyrm
      - [ ] Iceshard Wyvern
      - [ ] Flamewreathed Wyvern
      - [ ] Opalescent Wyrm
      - [ ] Water Wyrm
      - [ ] Sky Wyrm
      - [ ] Blood Wyrm
- [ ] Hatch ALL unclaimed adjacent ~Family~ Eggs
   - [ ] BVA
      - [ ] No unclaimed adjacent ~Family~ Eggs
      - [ ] One unclaimed adjacent ~Family~ Egg
      - [ ] Four unclaimed adjacent ~Family~ Eggs
      - [ ] Follows BVA of adjacent Eggs
   - [ ] Dragons 
      - [ ] Nebula Wyrm
      - [ ] Chromatic Whelp
      - [ ] Twilight Wyvern
      - [ ] Void Wyvern
      - [ ] Undine Wyvern
      - [ ] Brimstone Wyvern
      - [ ] Ethereal Wyvern
      - [ ] Sunstone Wyvern
      - [ ] Steam Wyvern
      - [ ] Nature Wyvern
      - [ ] Electric Wyvern
      - [ ] Water Wyvern
      - [ ] Sky Wyvern
      - [ ] Blood Wyvern
- [ ] Add 1 ~color~ Element to ALL adjacent Eggs
   - [ ] BVA
      - [ ] Follows BVA of Placing Elements
      - [ ] Follows BVA of Element Collection
      - [ ] Follows BVA of adjacent Eggs
   - [ ] Dragons
      - [ ] Ethereal Whelp
      - [ ] Sky Whelp
      - [ ] Water Whelp
      - [ ] Steam Whelp
      - [ ] Blood Whelp
      - [ ] Harvest Whelp
      - [ ] Arbor Whelp
      - [ ] Electric Whelp
      - [ ] Twilight Whelp
      - [ ] Brimstone Whelp
      - [ ] Undine Whelp
      - [ ] Sunstone Whelp
      - [ ] Storm Whelp
      - [ ] Void Whelp
      - [ ] Nature Whelp
- [ ] Add 1 ~color~ and 1 ~color~ to ALL adjacent Eggs
   - [ ] BVA
      - [ ] Follows BVA of Placing Elements
      - [ ] Follows BVA of Element Collection
      - [ ] Follows BVA of adjacent Eggs
   - [ ] Dragons
      - [ ] Water Drake
      - [ ] Blood Drake
      - [ ] Sky Drake
      - [ ] Storm Drake
      - [ ] Void Drake
      - [ ] Arbor Drake
      - [ ] Ethereal Drake
      - [ ] Harvest Drake
      - [ ] Twilight Drake
      - [ ] Undine Drake
      - [ ] Brimstone Drake
      - [ ] Sunstone Drake
- [ ] Add 1 Red, Blue, and Yellow Element to ALL adjacent Eggs
   - [ ] BVA
      - [ ] Follows BVA of Placing Elements
      - [ ] Follows BVA of Element Collection
      - [ ] Follows BVA of adjacent Eggs
   - [ ] Dragons
      - [ ] Chromatic Wyvern (Red, Blue, Yellow)
- [ ] Place ~color~ Elements on ALL available (spots in the Hatching Grounds/spaces in play)
   - [ ] BVA
      - [ ] Follows BVA of Placing Elements
      - [ ] Follows BVA of Element Collection
   - [ ] Dragons
      - [ ] De’Lons
      - [ ] Xandus
- [ ] Destroy 1 ~color~ Element on ALL adjacent Eggs
   - [ ] BVA
      - [ ] Follows BVA of Destroying Elements
      - [ ] Follows BVA of adjacent Eggs
   - [ ] Dragons
      - [ ] Electric Drake
      - [ ] Steam Drake
      - [ ] Nature Drake
- [ ] Drestory ALL ~color~ and ~color~ Elements on ALL adjacent Eggs
   - [ ] BVA
      - [ ] Follows BVA of Destroying Elements
      - [ ] Follows BVA of adjacent Eggs
   - [ ] Dragons
      - [ ] Abyssal Whelp
      - [ ] Nebyla Whelp
      - [ ] Aeon Whelp
- [ ] Destroy 1 of each Primary Element on ALL claimed adjacent Eggs
   - [ ] BVA
      - [ ] Follows BVA of Destroying Elements
      - [ ] Follows BVA of adjacent Eggs
   - [ ] Dragons
      - [ ] Chromatic Drake
- [ ] Destroy ALL ~temperature~ Elements on ALL adjacent Eggs
   - [ ] BVA
      - [ ] Follows BVA of Destroying Elements
   - [ ] Dragons
      - [ ] Flamewreathed Whelp
      - [ ] Iceshard Whelp
- [ ] Destroy ALL ~color~ and ~color~ Elements on Eggs in play
   - [ ] BVA
      - [ ] Follows BVA of Destroying Elements
   - [ ] Dragons
      - [ ] Artifeles
- [ ] Destroy ALL Elements on ALL claimed Eggs currently in play
   - [ ] BVA
      - [ ] Follows BVA of Destroying Elements
   - [ ] Dragons
      - [ ] Whyte
- [ ] Destroy 1 of each Secondary Element on ALL adjacent Eggs
   - [ ] BVA
      - [ ] Follows BVA of Destroying Elements
   - [ ] Dragons
      - [ ] Opalescent Drake
- [ ] All players draw 1 FEWER Element during each Phase 1
   - [ ] BVA
      - [ ] Follows BVA of Collecting Elements
      - [ ] Players draw no elements during each Phase 1
   - [ ] Dragons
      - [ ] Lusura
- [ ] Shuffle ALL adjacent Eggs into the draw pile
   - [ ] BVA
      - [ ] Follows BVA of Adjacent Eggs
   - [ ] Dragons
      - [ ] Tiamat
- [ ] ALL players must return ALL hatched Neutral dragons to the draw pile
   - [ ] BVA
      - [ ] Player has no hatched Neutral dragons
      - [ ] Player has one hatched Neutral dragon
      - [ ] Player has more than one hatched Neutral dragon
   - [ ] Dragons
      - [ ] Draceutrum
- [ ] Each Player must trade 1 Dragon to whomever controls the fewest 
   - [ ] BVA
      - [ ] All players control the same number of dragons
   - [ ] Dragons
      - [ ] Cordath
- [ ] NO players may create Black using Primary or Secondary Elements
   - [ ] BVA
      - [ ] Follows BVA of Placing Elements
   - [ ] Dragons
      - [ ] Aerillia
- [ ] Handlers on ALL adjacent Eggs move to the Ready Room
   - [ ] BVA
      - [ ] Follows BVA of Assigning Handlers
      - [ ] Follows BVA Adjacent Eggs
   - [ ] Dragons
      - [ ] Tyche
- [ ] ALL Eggs claimed this round take +1 round to hatch
   - [ ] BVA
      - [ ] Follows BVA of Hatching Eggs
   - [ ] Dragons
      - [ ] Norruth
- [ ] Reshuffle this card into the deck; when redrawn hatch ALL unclaimed Eggs in the Hatching grounds.
   - [ ] BVA
      - [ ] Follows BVA of Hatching Eggs
   - [ ] Dragons
      - [ ] Apiara
- [ ] Declare global war; destroy ALL players’ stored Elements 
   - [ ] BVA
      - [ ] Player has no stored elements
      - [ ] Player has one stored element
      - [ ] Player has more than one stored element
   - [ ] Dragons
      - [ ] Ignatius
- [ ] ALL players return their most valuable Dragon to the draw pile
   - [ ] BVA
      - [ ] Player has no dragons
      - [ ] Player has multiple of their most valuable Dragon
      - [ ] Player has one most valued Dragon
   - [ ] Dragons
      - [ ] Aerystrasza
- [ ] Remove ALL handlers from ALL Eggs in play
   - [ ] BVA
      - [ ] Follows BVA of Assigning Handlers
   - [ ] Dragons
      - [ ] Vadrenox
- [ ] Each player must immediately sacrifice 1 Handler (to min. of 2) 
   - [ ] BVA
      - [ ] Follows BVA of Assigning Handlers
   - [ ] Dragons
      - [ ] Kel’Droth
