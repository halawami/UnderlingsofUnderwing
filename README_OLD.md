# Underlings of Underwing

CSSE376 Spring 18-19 Term Project

Hussein Alawami, Mohammad Baqer, George Main IV, Wesley Siebenthaler

## Done Definition

The definition of done is created by examining and applying BVA on the rules.

### Game Setup ```tests.game.setup```    

#### Element Bag Setup ```tests.game.setup.ElementBagTests```

- [x] When the game starts there should be the correct number of elements
  - The game should start with 20 blue, red, and yellow elements, 10 purple, green, and orange elements, and 5 black and white elements
    - ```tests.game.setup.ElementBagTests.testElementCount()```


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

#### Number of Elements Remaining ```tests.elements.drawing.CountTests```

- [x] Drawing an element when there are [1, 20] of that color left
  - When there are 20 left, drawing should decrease the count to 19
    - ```tests.elements.drawing.CountTests.testDraw20Left()```
  - When there is 1 left, drawing should decrease the count to 0
    - ```tests.elements.drawing.CountTests.testDraw1Left()```

### Player Element Inventory ```tests.player.ElementTests```

- [x] A player adds an element to their inventory
  - When an element is added, it appears in their inventory
    - ```tests.player.ElementsTests.testAddElement()```
- [x] A player plays an element from their inventory
  - When an element is played, it is removed from their inventory
    - ```tests.player.ElementTests.testRemoveElement()```

### Element Givers ```tests.elements.giver```

#### Draw Choices ```tests.elements.giver.DrawChoiceTests```

- [x] Draw choices should describe their colors
  - Blue draw choice should be "BLUE Element"
    - ```tests.elements.giver.DrawChoiceTests.testToStringBlue()```
  - Red draw choice should be "RED Element"
    - ```tests.elements.giver.DrawChoiceTests.testToStringRed()```

#### Handlers ```tests.elements.giver.HandlerTests```

- [x] Handlers in the field should give a random element or field space element
  - Handlers on a red field space should give red or random
    - ```tests.elements.giver.HandlerTests.testFieldRed()```
  - Handlers on a red field space should give blue or random
    - ```tests.elements.giver.HandlerTests.testFieldBlue()```
  - Handlers on a red field space should give green or random
    - ```tests.elements.giver.HandlerTests.testFieldGreen()```
  - Handlers on a red field space should give orange or random
    - ```tests.elements.giver.HandlerTests.testFieldOrange()```
  - Handlers on a red field space should give yellow or random
    - ```tests.elements.giver.HandlerTests.testFieldYellow()```
 - Handlers on a red field space should give purple or random
    - ```tests.elements.giver.HandlerTests.testFieldPurple()```
  - Handlers on a red field space should give black or random
    - ```tests.elements.giver.HandlerTests.testFieldBlack()```
  - Handlers on a red field space should give white or random
    - ```tests.elements.giver.HandlerTests.testFieldWhite()```
- [x] Handlers not in the field should give a random element
  - ```tests.elements.giver.HandlerTests.testNotField()```
- [x] Handler element givers should describe the handler and location
  - Handler in the handler room should be "Handler in Ready Room"
    - ```tests.elements.giver.HandlerTests.testToStringReadyRoom()```
  - Handler on a card should be "Handler on Card"
    - ```tests.elements.giver.HandlerTests.testToStringCard()```
  - Handler in the break room should be "Handler in Break Room"
    - ```tests.elements.giver.HandlerTests.testToStringBreakRoom()```
  - Handler in incubation should be be "Handler in Incubation"
    - ```tests.elements.giver.HandlerTests.testToStringIncubation```
  - Handler on field should be "Handler on COLOR Field Space"
    - ```tests.elements.giver.HandlerTests.testToStringFieldBlue()```
    - ```tests.elements.giver.HandlerTests.testToStringFieldWhite()```

#### Cards ```tests.elements.giver.CardTests```

### Move & Assign Dragon Handlers (Phase 2) ```tests.handler```

#### Handler Movement States ```tests.handler.movement.StateTests```

- [x] When the handler is in the Ready Room
  - ```tests.handler.movement.StateTest.testReadyRoomPossibleStates()```
  - The handler state can change to card
    - ```tests.handler.movement.StateTest.testReadyRoomToUnclaimedEggs()```
  - The handler state can change to field
  	- ```tests.handler.movement.StateTest.testReadyRoomToField()```
  - The handler state can change to field whitespace
  	- ```tests.handler.movement.StateTest.testReadyRoomToFieldWhite()```
  - The handler state can remain at ready room
  	- ```tests.handler.movement.StateTest.testReadyRoomToReadyRoom()```

- [x] Handler in Field
  - ```tests.handler.movement.StateTest.testFieldPossibleStates()```
  - The handler can remain at field
  	- ```tests.handler.movement.StateTest.testFieldMoveClockwise()```
  - The handler can change to break room
  	- ```tests.handler.movement.StateTest.testFieldToBreakRoom()```

- [x] Handler on Unclaimed Egg
  - ```tests.handler.movement.StateTest.testCardPossibleStates()```
  - The handler can change to break room
  	- ```tests.handler.movement.StateTest.testUnclaimedEggToBreakRoom()```
  - The handler remains on card
  	- ```tests.handler.movement.StateTest.testUnclaimedEggToBreakRoom()```

- [x] Handler in Break Room
  - ```tests.handler.movement.StateTest.testBreakRoomPossibleStates()```
  - The handler can change to ready room
  	- ```tests.handler.movement.StateTest.testBreakRoomToReadyRoom()```

- [x] Handler in Field White
  - ```tests.handler.movement.StateTest.testFieldWhiteSpacePossibleStates()```
  - The handler can change to break room
  	- ```tests.handler.movement.StateTest.testFieldWhiteToBreakRoom()```

- [x] Handler during Incubation
  - The handler can remain in incubation
  	- ```tests.handler.movement.StateTest.testIncubationToIncubation()```
  - The handler can change to ready room
  	- ```tests.handler.movement.StateTest.testIncubationToBreakRoom()```

#### Handler To String ```tests.handler.StringTests```

- [x] Handler's string should describe the handler and location
  - Handler in the handler room should be "Handler in Ready Room"
    - ```tests.handler.StringTests.testToStringReadyRoom()```
  - Handler on a card should be "Handler on CARD NAME"
    - ```tests.handler.StringTests.testToStringCard()```
  - Handler in the break room should be "Handler in Break Room"
    - ```tests.handler.StringTests.testToStringBreakRoom()```
  - Handler in incubation should be be "Handler in Incubation"
    - ```tests.handler.StringTests.testToStringIncubation```
  - Handler on field should be "Handler on COLOR Field Space"
    - ```tests.handler.StringTests.testToStringFieldBlue()```
    - ```tests.handler.StringTests.testToStringFieldRed()```

#### Handler Choices ```tests.handler.movement.ChoiceTests```

- [x] Handlers in the Ready Room can: Stay, Field Whitespace, Field, or Card
  - ```tests.handler.movement.ChoiceTests.testReadyRoom()```
- [x] Handlers in the Break Room can: Ready Room
  - ```tests.handler.movement.ChoiceTests.testBreakRoom()```
- [x] Handlers on the Field Whitespace can: Break Room
  - ```tests.handler.movement.ChoiceTests.testFieldWhitespace()```
- [x] Handlers in the Field can: Stay, Break Room
  - ```tests.handlers.movement.ChoiceTests.testField()```
- [x] Handlers in Incubation can: Stay
  - ```tests.handlers.movement.ChoiceTests.testIncubation()```
- [x] Handlers on a Card can: Stay, Break Room
  - ```tests.handlers.movement.ChoiceTests.testCard()```

### Placing Elements on Eggs (Phase 3)

#### Place a Single Non-Combo Element to Complete Element Space ```tests.elementspace.single.DoneTests```
- [x] An element is not placed on an empty element space
  - The element space should not be complete
    - ```tests.elementspace.single.DoneTests.testEmpty()```
- [x] A single non-combo element is placed on an empty element space
  - When a red element is placed on a red element space, the element space should be completed
    - ```tests.elementspace.single.DoneTests.testRed()```
  - When a green element is placed on a green element space, the element space should be completed
    - ```tests.elementspace.single.DoneTests.testGreen()```
  - When a blue element is placed on a blue element space, the element space should be completed
    - ```tests.elementspace.single.DoneTests.testBlue()```
  - When a orange element is placed on a orange element space, the element space should be completed
    - ```tests.elementspace.single.DoneTests.testOrange()```
  - When a yellow element is placed on a yellow element space, the element space should be completed
    - ```tests.elementspace.single.DoneTests.testYellow()```
  - When a purple element is placed on a purple element space, the element space should be completed
    - ```tests.elementspace.single.DoneTests.testPurple()```
  - When a black element is placed on a black element space, the element space should be completed
    - ```tests.elementspace.single.DoneTests.testBlack()```
  - When a white element is placed on a white element space, the element space should be completed
    - ```tests.elementspace.single.DoneTests.testWhite()```

#### Orange Combo ```tests.elementspace.combo.OrangeTests```

- [x] An orange element can be made from yellow and red elements
  - When only a red is placed on an orange space, the combo is not complete
    - ```tests.elementspace.combo.OrangeTests.testRed()```
  - When only a yellow is placed on an orange space, the combo is not complete
    - ```tests.elementspace.combo.OrangeTests.testYellow()```
  - When a red and then a yellow is placed on an orange space, the combo is complete
    - ```tests.elementspace.combo.OrangeTests.testRedThenYellow()```
  - When a yellow and then red is played on an orange space, the combo is complete
    - ```tests.elementspace.combo.OrangeTests.testYellowThenRed()```
  - When a yellow and red are played on an orange space, the combo is complete
    - ```tests.elementspace.combo.OrangeTests.testRedAndYellow()```

#### Green Combo ```tests.elementspace.combo.GreenTests```

- [x] An green element can be made from yellow and blue elements
  - When only a blue is placed on a green space, the combo is not complete
    - ```tests.elementspace.combo.GreenTests.testBlue()```
  - When only a yellow is placed on a green space, the combo is not complete
    - ```tests.elementspace.combo.GreenTests.testYellow()```
  - When a blue and then a yellow is placed on a green space, the combo is complete
    - ```tests.elementspace.combo.GreenTests.testBlueThenYellow()```
  - When a yellow and then blue is played on a green space, the combo is complete
    - ```tests.elementspace.combo.GreenTests.testYellowThenBlue()```
  - When a yellow and blue are played on a green space, the combo is complete
    - ```tests.elementspace.combo.GreenTests.testBlueAndYellow()```

#### Purple Combo ```tests.elementspace.combo.PurpleTests```

- [x] An purple element can be made from red and blue elements
  - When only a blue is placed on a purple space, the combo is not complete
    - ```tests.elementspace.combo.PurpleTests.testBlue()```
  - When only a red is placed on a purple space, the combo is not complete
    - ```tests.elementspace.combo.PurpleTests.testRed()```
  - When a blue and then a red is placed on a purple space, the combo is complete
    - ```tests.elementspace.combo.PurpleTests.testBlueThenRed()```
  - When a red and then blue is played on a purple space, the combo is complete
    - ```tests.elementspace.combo.PurpleTests.testRedThenBlue()```
  - When a red and blue are played on a purple space, the combo is complete
    - ```tests.elementspace.combo.PurpleTests.testBlueAndRed()```

#### Black Combo ```tests.elementspace.combo.BlackTests```

- [x] A black element can be made from all primary elements
  - When only a red is placed, the combo is not complete
    - ```tests.elementspace.combo.BlackTests.testRed()```
  - When only a blue is placed, the combo is not complete
    - ```tests.elementspace.combo.BlackTests.testBlue()```
  - When only a yellow is placed, the combo is not complete
    - ```tests.elementspace.combo.BlackTests.testYellow()```
  - When a red and blue is placed, the combo is not complete
    - ```tests.elementspace.combo.BlackTests.testRedBlue()```
  - When a blue and yellow is placed, the combo is not complete
    - ```tests.elementspace.combo.BlackTests.testBlueYellow()```
  - When a red and blue and yellow is placed, the combo is complete
    - ```tests.elementspace.combo.BlackTests.testRedBlueYellow()```

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

## Effect Tests ```tests.effect```

### Player Tests ```tests.effect.PlayerTests```

#### Gain One Handler

- Abyssal Wyrm
- Aeon Wyrm
- Nebula Wyrm
- Opalescent Whelp
- Water Wyvern
- Sky Wyvern
- Blood Wyvern
- Water Wyrm
- Sky Wyrm
- Blood Wyrm
- Electric Drake
- Steam Drake
- Chromatic Drake
- Nature Drake
- Vadrenox
- Blood Whelp
- Water Whelp
- Water Drake
- Blood Drake
- Sky Drake
- Sky Whelp
- Sunstone Drake
- Brimstone Drake
- Undine Drake
- Twilight Drake
- Harvest Drake
- Ethereal Drake
- Arbor Drake
- Void Drake
- Storm Drake

- [x] The player gains one handler
   - ```tests.effect.PlayerTests.testGainOneHandler()```
   
### Player Tests ```tests.effect.ElementTests```

#### Collect 1 Primary Element

- Chromatic Whelp

- [ ] The player gains one primary element
  - ```tests.effect.ElementTests.testGainPrimaryElement()```


- [ ] Collect 1 Primary Element
   - [ ] BVA
      - [ ] Follows BVA of Element Collection
   - [ ] Dragons
      
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

#### Game Scenarios

- [ ] Handler in Ready Room and all Eggs claimed
- [ ] Max handlers on field position and move another handler to same spot
- [ ] Players have different number of assigning turns (number of handlers is different)
- [ ] Players have the same number of assigning turns (number of handlers is the same)